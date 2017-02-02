package com.drakeet.rebase.api.type;

import com.drakeet.rebase.api.constraint.CategoryKey;
import com.drakeet.rebase.api.constraint.Username;
import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.Length;

/**
 * @author drakeet
 */
public class Category extends Jsonable {

    public static final String KEY = "key";
    public static final String NAME = "name";
    public static final String RANK = "rank";
    public static final String OWNER = "owner";
    public static final String UPDATED_AT = "updated_at";
    public static final String CREATED_AT = "created_at";

    @CategoryKey
    public String key;

    @Length(min = 1, max = 12)
    public String name;

    @Max(65536)
    public int rank;

    @Username
    public String owner;
}
