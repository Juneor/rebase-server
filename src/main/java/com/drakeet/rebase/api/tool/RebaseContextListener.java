package com.drakeet.rebase.api.tool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author drakeet
 */
public class RebaseContextListener implements ServletContextListener {

    @Override public void contextInitialized(ServletContextEvent servletContextEvent) {
        Log.prefix = "[Rebase] ~";
        MongoJDBC.setUp();
    }


    @Override public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}