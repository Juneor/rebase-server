package com.drakeet.rebase.api.type;

import com.drakeet.rebase.api.tool.GsonJsonProvider;

/**
 * @author drakeet
 */
public class Jsonable {

    public String toJson() {
        return GsonJsonProvider.newGson().toJson(this);
    }
}
