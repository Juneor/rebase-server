package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.User;
import java.util.Date;
import java.util.UUID;

/**
 * @author drakeet
 */
public class Authorizations {

    /**
     * xxx
     *
     * @param username xxx
     * @param authorization xx
     * @return xx
     * @throws 403
     */
    public static boolean verify(String username, String authorization) {
        if (Config.AUTHORIZATION.equals(authorization)) {
            return true;
        }
        return false;
    }


    public static String issueToken(String username) {
        String key = UUID.randomUUID().toString().toUpperCase() +
            "|" + username +
            "|" + System.currentTimeMillis();

        return Hashes.sha1(key);
    }


    public static User.Authorization newInstance(String username) {
        User.Authorization authorization = new User.Authorization();
        authorization.setAccessToken(issueToken(username));
        authorization.setUpdatedAt(new Date());
        return authorization;
    }
}
