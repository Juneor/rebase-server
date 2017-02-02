package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.ConstraintViolationExceptionMapper;
import com.drakeet.rebase.api.tool.GsonBodyProvider;
import com.drakeet.rebase.api.tool.Log;
import com.drakeet.rebase.api.tool.MongoDBs;
import com.drakeet.rebase.api.tool.ValidationConfigurationContextResolver;
import com.drakeet.rebase.api.tool.WebExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * @author drakeet
 */
public class Application extends ResourceConfig {

    public Application() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);

        register(UserResource.class);
        register(CategoryResource.class);
        register(FeedResource.class);
        register(GsonBodyProvider.class);
        register(WebExceptionMapper.class);
        register(ConstraintViolationExceptionMapper.class);
        register(ValidationConfigurationContextResolver.class);

        Log.prefix = "------> [Rebase] ~ ";

        MongoDBs.setUp();
    }
}
