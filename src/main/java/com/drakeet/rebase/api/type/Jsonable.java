package com.drakeet.rebase.api.type;

import com.drakeet.rebase.api.tool.Globals;

/**
 * @author drakeet
 */
public class Jsonable {

    public String toJson() {
        return Globals.newGson().toJson(this);
    }
}
