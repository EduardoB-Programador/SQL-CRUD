package com.eduardo.reposiroty;

import com.eduardo.model.modelHelper.FieldCartographer;
import com.eduardo.model.modelHelper.HasField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operations {

    @SuppressWarnings("unchecked")
    public static String SELECT(DBManager db, JDBCMethods jdbc, List<String> l, FieldCartographer t) {
        if (db == null || jdbc == null || t == null)
            return null;

        String condition = db.conditionBuilder(l);
        String retrive = db.retrive(t, condition);

        StringBuilder result = new StringBuilder();

        Statement st;
        ResultSet rs;

        try {
            st = jdbc.getStatement();
            rs = jdbc.executeQuery(st, retrive);
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data.");
            return "couldn't fetch data";
        }

        List<String> lk = t.fieldToMap().keySet().stream().toList();

        //I want the extra fields to be shown as the first column, i guess this is one way to do it
        if (t.getClass().isAnnotationPresent(HasField.class)) {
            String[] extraFields = t.getClass().getAnnotation(HasField.class).extraField();

            List<String> lStr = new ArrayList<>();
            lStr.addAll(Arrays.asList(extraFields));
            lStr.addAll(lk);
            lk = lStr;
        }

        for (String s : lk) {
            result.append(s);

            if (s.length() <= 4) {
                if (s.length() % 4 == 0)
                    result.append("\t\t\t\t");
                else
                    result.append("\t\t\t\t\t");
            } else {
                if (s.length() % 4 == 0)
                    result.append("\t\t\t");
                else
                    result.append("\t\t\t\t");
            }
        }

        result.append("\n");

        try {
            while (rs.next()) {

                for (String s : lk) {
                    String value = rs.getObject(s).toString();

                    result.append(value);

                    if (value.length() <= 4) {
                        if (value.length() % 4 == 0)
                            result.append("\t\t\t\t");
                        else
                            result.append("\t\t\t\t\t");
                    } else if (value.length() <= 8) {
                        if (value.length() % 4 == 0)
                            result.append("\t\t\t");
                        else
                            result.append("\t\t\t\t");
                    } else {
                        if (value.length() % 4 == 0)
                            result.append("\t\t");

                        else
                            result.append("\t\t\t");
                    }
                }

                result.append("\n");
            }

            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Couldn't acess data.");
            return "Couldn't acess data.";
        }

        return result.toString();

    }

    public static String SELECT(DBManager db, JDBCMethods jdbc, FieldCartographer t) {
        return SELECT(db, jdbc, new ArrayList<>(), t);
    }

    @SuppressWarnings("unchecked")
    public static void INSERT(DBManager db, JDBCMethods jdbc, FieldCartographer value) {
        if (db == null || jdbc == null || value == null)
            return ;

        String INSERT = db.create(value);

        Statement st;

        try {
            st = jdbc.getStatement();
            jdbc.executeUpdate(st, INSERT);

            st.close();
        } catch (SQLException e) {
            System.out.println("Couldn't make INSERT/DELETE/UPDATE operaion.");
        }

    }
    @SuppressWarnings("unchecked")
    public static void UPDATE(DBManager db, JDBCMethods jdbc, List<String> l, FieldCartographer values) {
        if (db == null || jdbc == null || l == null || l.isEmpty() || values == null) {
            System.out.println("No parameters must be null");
            return ;
        }

        String condition = db.conditionBuilder(l);
        String UPDATE = db.update(values, condition);

        Statement st;

        try {
            st = jdbc.getStatement();
            jdbc.executeUpdate(st, UPDATE);

            st.close();
        } catch (SQLException e) {
            System.out.println("Couldn't update any entry.");
        }

    }

    @SuppressWarnings("unchecked")
    public static void DELETE(DBManager db, JDBCMethods jdbc, List<String> l, FieldCartographer t) {
        if (db == null || jdbc == null || l == null || l.isEmpty() || t == null) {
            System.out.println("No parameters must be null");
            return ;
        }

        String condition = db.conditionBuilder(l);
        String DELETE = db.delete(t, condition);

        Statement st;

        try {
            st = jdbc.getStatement();
            jdbc.executeUpdate(st, DELETE);

            st.close();
        } catch (SQLException e) {
            System.out.println("Couldn't delete any entry.");
        }
    }
}
