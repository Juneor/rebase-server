package com.drakeet.rebase.api.tool;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author drakeet
 */
public class Log {

    private static final Logger logger = new Logger();
    public static String prefix = "";


    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }


    private static String tag(String tag) {
        return prefix + " " + tag;
    }


    public static void i(String tag, String message) {
        logger.i(tag(tag), message);
    }


    public static void d(String tag, String message) {
        logger.d(tag(tag), message);
    }


    public static void e(String tag, String msg, Throwable tr) {
        logger.e(tag(tag), msg + '\n' + getStackTraceString(tr));
    }


    /**
     * Handy function to get a loggable stack trace from a Throwable.
     *
     * @param provided An exception to log.
     */
    public static String getStackTraceString(Throwable provided) {
        if (provided == null) {
            return "";
        }

        Throwable throwable = provided;
        while (throwable != null) {
            throwable = throwable.getCause();
        }

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        provided.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }


    private static class Logger {

        Level level;


        void i(String tag, String message) {
            level = Level.INFO;
            System.out.println(tag + ": " + message);
        }


        void d(String tag, String message) {
            level = Level.DEBUG;
            System.out.println(tag + ": " + message);
        }


        void e(String tag, String message) {
            level = Level.ERROR;
            System.err.println(tag + ": " + message);
        }
    }
}