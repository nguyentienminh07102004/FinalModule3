package com.example.demo.Utils;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;

public class ValidateUtil {
    public static <T> void check(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                if(value != null) continue;
                String type = field.getType().getTypeName();
                if (type.equals("java.lang.Long")) {
                    field.set(t, 0L);
                } else if(type.equals("java.lang.Integer")) {
                    field.set(t, 0);
                } else if(type.equals("java.lang.String")) {
                    field.set(t, "");
                } else if(type.equals("java.util.List")) {
                    field.set(t, new ArrayList<>());
                } else if(type.equals("java.sql.Date")) {
                    field.set(t, new Date(System.currentTimeMillis()));
                }
            }
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
