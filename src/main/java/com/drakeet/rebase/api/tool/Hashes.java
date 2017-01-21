package com.drakeet.rebase.api.tool;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

/**
 * @author drakeet
 */
public final class Hashes {

    public static String sha1(String text) {
        return Hashing.sha1()
            .hashString(text, StandardCharsets.UTF_8)
            .toString();
    }
}
