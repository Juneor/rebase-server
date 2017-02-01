package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.User;
import com.drakeet.rebase.api.type.User.Authorization;
import java.util.Date;
import java.util.UUID;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author drakeet
 */
public class Authorizations {

    private static final String TAG = Authorizations.class.getSimpleName();


    /**
     * xxx
     *
     * @param username xxx
     * @param authorization xx
     * @return xx
     * @throws 403
     */
    public static boolean verify(String username, String authorization) {
        try {
            final String accessToken;
            if (authorization.startsWith("token")) {
                accessToken = authorization.split(" ")[1];
            } else {
                throw new IllegalArgumentException("The format of Authorization is unexpected.");
            }
            Document user = MongoDBs.users().find(eq(User.USERNAME, username)).first();
            Document auth = user.get(User.AUTHORIZATION, Document.class);
            if (auth.getString("access_token").equals(accessToken)) {
                Log.i(TAG, "Verified successfully.");
                return true;
            }
        } catch (final Exception e) {
            Log.e(TAG, "[verify]", e);
            return false;
        }
        return false;
    }


    public static String issueToken(String username) {
        String key = UUID.randomUUID().toString().toUpperCase() +
            "|" + username +
            "|" + System.currentTimeMillis();

        return Hashes.sha1(key);
    }


    public static Document newInstance(String username) {
        Document authorization = new Document()
            .append(Authorization.ACCESS_TOKEN, issueToken(username))
            .append(Authorization.UPDATED_AT, new Date());
        return authorization;
    }
}
