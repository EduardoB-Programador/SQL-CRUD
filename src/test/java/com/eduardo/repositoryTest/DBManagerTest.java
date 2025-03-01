package com.eduardo.repositoryTest;

import com.eduardo.model.Clients;
import com.eduardo.model.modelHelper.FieldCartographer;
import com.eduardo.reposiroty.SQLRepo;
import com.eduardo.model.User1;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DBManagerTest {

    @Test
    void SQLCreateTest() {
        SQLRepo<FieldCartographer> sqlRepo = (url, username, password) -> null;

        String query1 = sqlRepo.create(new User1("Luiz", "Sampaio", null));
        String query2 = sqlRepo.create(new Clients("cecilus@example.com","Cecilus", "Sigsmund", "100909277"));
        String query3 = sqlRepo.create(new Clients("null","Cecilus", "Sigsmund", "null"));

        System.out.println(query1);
        System.out.println(query2);
        System.out.println(query3);
    }

    @Test
    void SQLConditionBuilderTest() {
        SQLRepo<FieldCartographer> sqlRepo = (url, username, password) -> null;
        List<String> list = new ArrayList<>();

        String WHERE0 = sqlRepo.conditionBuilder(list);

        list.add("fname = 'Terry'");

        String WHERE1 = sqlRepo.conditionBuilder(list);

        System.out.println(WHERE0);
        System.out.println(WHERE1);
    }

    @Test
    void SQLRetriveTest() {
        SQLRepo<FieldCartographer> sqlRepo = (url, username, password) -> null;
        List<String> list = new ArrayList<>();

        String WHERE0 = sqlRepo.conditionBuilder(list);
        String SELECT0 = sqlRepo.retrive(new Clients(null, null, null, null), WHERE0);

        list.add("fname = 'Terry'");
        String WHERE1 = sqlRepo.conditionBuilder(list);
        String SELECT1 = sqlRepo.retrive(new Clients(null, null, null, null), WHERE1);

        System.out.println(SELECT0);
        System.out.println(SELECT1);
    }

    @Test
    void SQLUpdateTest() {
        SQLRepo<FieldCartographer> sqlRepo = (url, username, password) -> null;
        List<String> l = new ArrayList<>();

        String WHERE0 = sqlRepo.conditionBuilder(l);
        String UPDATE0 = sqlRepo.update(new User1(null, null, null), WHERE0);
        assertNull(UPDATE0);

        l.add("fname = 'Eduardo'");
        String WHERE1 = sqlRepo.conditionBuilder(l);
        String UPDATE1 = sqlRepo.update(new User1(null, null, null), WHERE1);
        assertNull(UPDATE1);
        System.out.println(WHERE1);

        String UPDATE2 = sqlRepo.update(new User1("Matheus", "Tavares", "matheust@example.com"), WHERE1);
        System.out.println(UPDATE2);
    }

    @Test
    void SQLDeleteTest() {
        SQLRepo<FieldCartographer> sqlRepo = (url, username, password) -> null;
        List<String> l = new ArrayList<>();

        String WHERE0 = sqlRepo.conditionBuilder(l);
        String DELETE0 = sqlRepo.delete(new User1(null, null, null), WHERE0);
        assertNull(DELETE0);

        l.add("fname = 'Edinaldo'");
        l.add("lname = 'Pereira'");
        String WHERE1 = sqlRepo.conditionBuilder(l);
        String DELETE1 = sqlRepo.delete(new User1(null, null, null), WHERE1);

        System.out.println(DELETE1);
    }
}
