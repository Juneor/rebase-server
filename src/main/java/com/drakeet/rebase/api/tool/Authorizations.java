package com.drakeet.rebase.api.tool;

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
}
