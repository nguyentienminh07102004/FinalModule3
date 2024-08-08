package com.example.demo.Utils;

public class StatusUtil {
    public static String convertStatus(Integer status) {
        switch (status) {
            case 1:
                return "Hoạt động";
            case 2:
                return "Dừng bán";
            case 0:
                return "Đã xoá";
        }
        return null;
    }
}
