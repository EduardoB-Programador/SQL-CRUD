package com.eduardo.model.modelHelper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Should only be applied when the field is NOT auto_incremented, or has a default value
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HasField {
    String[] extraField() default {};
}
