package com.drakeet.rebase.api.tool;

import java.net.URI;

/**
 * @author drakeet
 */
public class URIs {

    public static URI create(String... dirs) {
        StringBuilder builder = new StringBuilder(Globals.ENDPOINT);
        for (String dir : dirs) {
            builder.append("/").append(dir);
        }
        return URI.create(builder.toString());
    }
}
