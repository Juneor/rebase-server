package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.Category;
import javax.ws.rs.WebApplicationException;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;

/**
 * @author drakeet
 */
public class RebaseAsserts {

    public static void existCategory(String key) {
        Document result = MongoDBs.categories().find(eq(Category.KEY, key))
            .projection(include(Category.KEY))
            .limit(1).first();
        if (result == null) {
            throw new WebApplicationException(Responses.notFound("The category is not found."));
        }
    }
}
