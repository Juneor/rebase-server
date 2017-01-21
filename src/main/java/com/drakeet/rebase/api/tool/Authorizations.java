package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.User;

/**
 * @author drakeet
 */
public class Authorizations {

    public static User verify(String username, String authorization) {
        if (Config.AUTHORIZATION.equals(authorization)) {
            return new User("drakeet");
        }
        return null;
    }
}
