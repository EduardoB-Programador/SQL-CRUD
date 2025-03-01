package com.eduardo.model;

import com.eduardo.model.modelHelper.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@HasField(extraField = "id")
public record Person(String fname, String lname, String email, String birth) implements FieldCartographer {

    public Map<String, Object> fieldToMap() {
        Map<String, Object> fieldMap = new HashMap<>();

        fieldMap.put("fname", this.fname);
        fieldMap.put("lname", this.lname);
        fieldMap.put("email", this.email);
        fieldMap.put("birth", this.birth);

        return fieldMap;
    }
}
