package com.drakeet.rebase.api.type;

/**
 * @author drakeet
 */
public class Result {

    public final boolean success;
    private String message;


    public Result(boolean success) {this.success = success;}


    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public static Result success() {
        return new Result(true);
    }


    public static Result failure() {
        return new Result(false);
    }


    public static Result failure(String message) {
        return new Result(false, message);
    }
}
