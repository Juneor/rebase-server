package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.Authorization;
import com.drakeet.rebase.api.type.User;
import java.util.Date;
import java.util.UUID;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author drakeet
 */
public class Authorizations {

    private static final String TAG = Authorizations.class.getSimpleName();


    /**
     * Verifies that the user's authorization.
     *
     * @param username The username of the user.
     * @param authorization The authorization of the user.
     * @throws IllegalArgumentException When the format of Authorization is unexpected.
     * @throws WebApplicationException When UNAUTHORIZED.
     */
    public static void verify(String username, String authorization) {
        final String accessToken;
        if (authorization.startsWith("token")) {
            accessToken = authorization.split(" ")[1];
        } else {
            throw new IllegalArgumentException("The format of Authorization is unexpected.");
        }
        Document user = MongoDBs.users().find(eq(User.USERNAME, username)).first();
        if (user == null) {
            throwUnauthorized();
        }
        Document auth = user.get(User.AUTHORIZATION, Document.class);
        if (auth.getString(Authorization.ACCESS_TOKEN).equals(accessToken)) {
            Log.i(TAG, "Verified successfully.");
        } else {
            throwUnauthorized();
        }
    }


    private static void throwUnauthorized() {
        throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }


    public static String issueToken(String username) {
        String key = UUID.randomUUID().toString().toUpperCase() +
            "|" + username +
            "|" + System.currentTimeMillis();

        return Hashes.sha1(key);
    }


    public static Document newInstance(String username) {
        return new Document()
            .append(Authorization.ACCESS_TOKEN, issueToken(username))
            .append(Authorization.UPDATED_AT, new Date());
    }
}
