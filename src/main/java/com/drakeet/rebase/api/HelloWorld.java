package com.drakeet.rebase.api;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author drakeet
 */
@Path("/") public class HelloWorld {

    @GET @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getClichedMessage() {
        Map<String, String> map = new HashMap<>();
        map.put("drakeet", "Hello World!");
        return map;
    }
}