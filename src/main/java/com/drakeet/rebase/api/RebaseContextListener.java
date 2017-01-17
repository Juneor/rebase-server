package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Log;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author drakeet
 */
public class RebaseContextListener implements ServletContextListener {

    @Override public void contextInitialized(ServletContextEvent servletContextEvent) {
        Log.prefix = "[Rebase] ~";
        MongoJDBC.setup();
    }


    @Override public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}