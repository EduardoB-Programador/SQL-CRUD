package com.eduardo.model;

import com.eduardo.model.modelHelper.FieldCartographer;

import java.util.HashMap;
import java.util.Map;

public record Clients(String email, String fname, String lname, String secNum) implements FieldCartographer {

    @Override
    public Map<String, Object> fieldToMap() {
        Map<String, Object> fieldMap = new HashMap<>();

        fieldMap.put("fname", this.fname);
        fieldMap.put("lname", this.lname);
        fieldMap.put("email", this.email);
        fieldMap.put("secNum", this.secNum);

        return fieldMap;
    }
}
