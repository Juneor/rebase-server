package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.Failure;

/**
 * @author drakeet
 */
public class Failures {

    public static Failure error(final String error) {
        return new Failure(error);
    }
}
