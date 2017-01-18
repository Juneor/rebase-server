package com.drakeet.rebase.api.tool;

import org.bson.types.ObjectId;

/**
 * @author drakeet
 */
public class ObjectIds {

    public static ObjectId objectId(final String hexString) {
        return new ObjectId(hexString);
    }
}
