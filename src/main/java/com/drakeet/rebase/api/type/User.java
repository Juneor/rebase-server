package com.drakeet.rebase.api.type;

import com.drakeet.rebase.api.constraint.Username;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * @author drakeet
 */
public class User {

    public static final String _ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String DESCRIPTION = "description";
    public static final String AUTHORIZATION = "authorization";
    public static final String CREATED_AT = "created_at";

    @NotNull @Username
    public String username;

    @NotNull @Length(min = 1, max = 128)
    public String password;

    @NotNull @Length(min = 1, max = 12)
    public String name;

    @NotNull @Length(min = 1, max = 64)
    @Email(regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
    public String email;

    @NotNull @Length(min = 1, max = 512)
    public String description;
}