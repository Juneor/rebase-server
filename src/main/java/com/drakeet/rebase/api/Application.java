package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.GsonJsonProvider;
import com.drakeet.rebase.api.tool.Log;
import com.drakeet.rebase.api.tool.MongoJDBC;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author drakeet
 */
public class Application extends ResourceConfig {

    public Application() {
        register(UserResource.class);
        register(CategoryResource.class);
        register(FeedResource.class);
        register(GsonJsonProvider.class);
        Log.prefix = "------> [Rebase] ~ ";
        MongoJDBC.setUp();
    }
}
