package com.eduardo.repositoryTest;

import com.eduardo.model.Person;
import com.eduardo.reposiroty.DBManager;
import com.eduardo.reposiroty.JDBCMethods;
import static com.eduardo.reposiroty.Operations.*;

import com.eduardo.reposiroty.databases.MySQLRepo;
import com.eduardo.reposiroty.databases.PostgreSQLRepo;
import com.eduardo.reposiroty.databases.SQLiteRepo;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OperationTest {
    //MySQL database
    DBManager<Person> MSQLdb;
    JDBCMethods MSQLjdbc;

    //PostgreSQL database
    DBManager<Person> PSQLdb;
    JDBCMethods PSQLjdbc;

    //SQLite database
    DBManager<Person> LITEdb;
    JDBCMethods LITEjdbc;
    final Person p = new Person(null, null, null, null);

    public OperationTest() {
        String MSQLurl = "jdbc:mysql://localhost:3307/test";
        String MSQLusername = "root";
        String MSQLpassword = "aaaa";

        this.MSQLdb = new MySQLRepo<>(MSQLurl, MSQLusername, MSQLpassword);
        this.MSQLjdbc = new MySQLRepo<>(MSQLurl, MSQLusername, MSQLpassword);

        String PSQLurl = "jdbc:postgresql://localhost:5432/test";
        String PSQLusername = "postgres";
        String PSQLpassword = "aaaa";

        this.PSQLdb = new PostgreSQLRepo<>(PSQLurl, PSQLusername, PSQLpassword);
        this.PSQLjdbc = new PostgreSQLRepo<>(PSQLurl, PSQLusername, PSQLpassword);

        String url = "jdbc:sqlite:C:/SQLite/dbs/test.db";

        this.LITEdb = new SQLiteRepo<>(url);
        this.LITEjdbc = new SQLiteRepo<>(url);
    }

    //MySQL tests
    @Test
    void MySQLretriveTest() {
        List<String> l = new ArrayList<>();
        l.add("email LIKE '%@%.edu'");

        String result = SELECT(MSQLdb, MSQLjdbc, l, p);

        System.out.println(result);
        assertNotNull(result);

        String allResults = SELECT(MSQLdb, MSQLjdbc, p);

        System.out.println(allResults);
        assertNotNull(allResults);
    }

    @Test
    void MySQLinsertTest() {
        String date = "1968-10-21";

        Person p = new Person("Roy", "Alphard", "ralphard@example.com", date);

        INSERT(MSQLdb, MSQLjdbc, p);

        List<String> condition = new ArrayList<>();
        condition.add("id > 1000");

        String result = SELECT(MSQLdb, MSQLjdbc, condition, p);

        System.out.println(result);
    }

    @Test
    void MySQLupdateTest() {
        String fname = "Ley";
        String email = "ralphard@example.com";

        Person p = new Person(fname, null, email, null);

        List<String> l = new ArrayList<>();
        l.add("id = 1002");

        UPDATE(MSQLdb, MSQLjdbc, l, p);
        String result = SELECT(MSQLdb, MSQLjdbc, l, p);
        System.out.println(result);
    }

    @Test
    void MySQLdeleteTest() {
        List<String> l = new ArrayList<>();
        l.add("id = 1002");

        DELETE(MSQLdb, MSQLjdbc, l, p);

        l.removeLast();
        l.add("id >= 1000");

        String result = SELECT(MSQLdb, MSQLjdbc, l, p);
        System.out.println(result);
    }

    //PostgreSQL tests
    @Test
    void PSQLretriveTest() {
        List<String> l = new ArrayList<>();
        l.add("email LIKE '%@%.edu'");

        String result = SELECT(PSQLdb, PSQLjdbc, l, p);

        assertNotNull(result);
        System.out.println(result);

        String allResults = SELECT(PSQLdb, PSQLjdbc, p);

        assertNotNull(allResults);
        System.out.println(allResults);
    }

    @Test
    void PSQLinsertTest() {
        String date = "1968-10-21";

        Person p = new Person("Roy", "Alphard", "ralphard@example.com", date);

        INSERT(PSQLdb, PSQLjdbc, p);

        List<String> condition = new ArrayList<>();
        condition.add("id >= 1000");

        String result = SELECT(PSQLdb, PSQLjdbc, condition, p);
        System.out.println(result);
    }

    @Test
    void PSQLupdateTest() {
        String fname = "Louis";
        String lname = "Arneb";
        String email = "larneb@example.com";

        Person p = new Person(fname, lname, email, null);

        List<String> l = new ArrayList<>();
        l.add("id = 1001");

        UPDATE(PSQLdb, PSQLjdbc, l, p);
        String result = SELECT(PSQLdb, PSQLjdbc, l, this.p);
        System.out.println(result);
    }

    @Test
    void PSQLdeleteTest() {
        List<String> l = new ArrayList<>();
        l.add("id = 1001");

        DELETE(PSQLdb, PSQLjdbc, l, this.p);

        l.removeLast();
        l.add("id >= 1000");

        String result = SELECT(PSQLdb, PSQLjdbc, l, this.p);
        System.out.println(result);
    }

    //SQLite tests
    @Test
    void LITEretriveTest() {
        List<String> l = new ArrayList<>();
        l.add("email LIKE '%@%.edu'");

        String result = SELECT(LITEdb, LITEjdbc, l, p);
        assertNotNull(result);

        String allResults = SELECT(LITEdb, LITEjdbc, p);
        assertNotNull(allResults);
        System.out.println(allResults);
    }

    @Test
    void LITEinsertTest() {
        String date = "1968-10-21";

        Person p = new Person("Roy", "Alphard", "ralphard@example.com", date);

        INSERT(LITEdb, LITEjdbc, p);

        List<String> l = new ArrayList<>();
        l.add("id >= 1000");

        String result = SELECT(LITEdb, LITEjdbc, l, this.p);
        System.out.println(result);
    }

    @Test
    void LITEupdateTest() {
        String fname = "Louis";
        String lname = "Arneb";
        String email = "larneb@example.com";

        Person p = new Person(fname, lname, email, null);

        List<String> l = new ArrayList<>();
        l.add("id = 1001");

        UPDATE(LITEdb, LITEjdbc, l, p);

        l.removeLast();
        l.add("id >= 1000");
        String result = SELECT(LITEdb, LITEjdbc, l, this.p);
        System.out.println(result);
    }

    @Test
    void LITEdeleteTest() {
        List<String> l = new ArrayList<>();
        l.add("id = 1001");

        DELETE(LITEdb, LITEjdbc, l, p);

        l.removeLast();
        l.add("id >= 1000");
        String result = SELECT(LITEdb, LITEjdbc, l, p);
        System.out.println(result);
    }
}
