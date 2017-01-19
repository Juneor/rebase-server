package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.User;

/**
 * @author drakeet
 */
public class Authorizations {

    public static User verify(String authorization) {
        //return Config.AUTHORIZATION.equals(authorization);
        // TODO: 2017/1/19  
        return new User();
    }
}
