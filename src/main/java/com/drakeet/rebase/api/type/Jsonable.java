package com.drakeet.rebase.api.type;

import com.drakeet.rebase.api.tool.GsonBodyProvider;

/**
 * @author drakeet
 */
public class Jsonable {

    public String toJson() {
        return GsonBodyProvider.newGson().toJson(this);
    }
}
