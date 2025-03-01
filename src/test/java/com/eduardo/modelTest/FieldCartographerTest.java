package com.eduardo.modelTest;

import com.eduardo.model.User1;
import com.eduardo.model.modelHelper.FieldCartographer;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FieldCartographerTest {

    @Test
    void fieldToMap() {
        FieldCartographer fc = new User1("Matheus", "Silva", "matheusilva@example.com");

        Set<String> s0 = new HashSet<>();
        s0.add("fname");
        s0.add("lname");
        s0.add("email");

        Collection<Object> s1 = new HashSet<>();
        s1.add("Matheus");
        s1.add("Silva");
        s1.add("matheusilva@example.com");

        assertEquals(s0, fc.fieldToMap().keySet());
        assertEquals(s1.stream().toList(), fc.fieldToMap().values().stream().toList());
    }
}
