package com.example.demo.Utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeanUtil {
    public static <T> void map(HttpServletRequest req, T t) {
        try {
            Map<String, String[]> params = req.getParameterMap();
            Field[] fields = t.getClass().getDeclaredFields();
            if(params == null || params.isEmpty()) return;
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                if(key.equalsIgnoreCase("image")) continue;
                String[] values = params.get(key);
                if (key.equalsIgnoreCase("id")) {
                    if (values != null && !values[0].isEmpty()) {
                        System.out.println(values[0]);
                        field.set(t, Long.valueOf(values[0]));
                    }
                } else if(values != null) {
                    String fieldType = field.getType().getTypeName();
                    if(fieldType.equalsIgnoreCase("java.lang.String")) {
                        field.set(t, values[0]);
                    } else if(fieldType.equalsIgnoreCase("java.lang.Long") && !values[0].isEmpty()) {
                        field.set(t, Long.parseLong(values[0]));
                    } else if(fieldType.equalsIgnoreCase("java.lang.Integer") && !values[0].isEmpty()) {
                        field.set(t, Integer.valueOf(values[0]));
                    } else if(fieldType.equalsIgnoreCase("java.util.List")) {
                        String genericType = field.getGenericType().getTypeName();
                        if(genericType.equalsIgnoreCase("java.util.List<java.lang.Long>")) {
                            List<Long> typeLong = new ArrayList<>();
                            for(String str : values) {
                                typeLong.add(Long.valueOf(str));
                            }
                            field.set(t, typeLong);
                        }
                    } else if(fieldType.equalsIgnoreCase("java.sql.Date")) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date(format.parse(values[0]).getTime());
                        field.set(t, date);
                    }
                }
            }
        } catch(IllegalAccessException | ParseException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static <T> T modelMapper(Object source, Class<T> tClass) {
        return null;
    }
}
