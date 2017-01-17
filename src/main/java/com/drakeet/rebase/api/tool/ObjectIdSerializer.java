package com.drakeet.rebase.api.tool;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.bson.types.ObjectId;

/**
 * @author drakeet
 */
public class ObjectIdSerializer implements JsonSerializer<ObjectId> {

    @Override
    public JsonElement serialize(ObjectId objectId, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(objectId.toHexString());
    }
}