package com.eduardo.model.modelHelper;

import java.util.Map;

@FunctionalInterface
public interface FieldCartographer {

    Map<String, Object> fieldToMap();
}
