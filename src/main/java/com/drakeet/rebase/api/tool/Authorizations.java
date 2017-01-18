package com.drakeet.rebase.api.tool;

/**
 * @author drakeet
 */
public class Authorizations {

    public static boolean verify(String authorization) {
        return Config.AUTHORIZATION.equals(authorization);
    }
}
