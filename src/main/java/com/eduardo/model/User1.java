package com.eduardo.model;

import com.eduardo.model.modelHelper.FieldCartographer;

import java.util.HashMap;
import java.util.Map;

public record User1(String fname, String lname, String email) implements FieldCartographer {

    @Override
    public Map<String, Object> fieldToMap() {
        Map<String, Object> fieldMap = new HashMap<>();

        fieldMap.put("fname", this.fname);
        fieldMap.put("lname", this.lname);
        fieldMap.put("email", this.email);

        return fieldMap;
    }
}
