package com.github.edgarzed.CBRTestTask.util;

public class ServiceUtil {
    private ServiceUtil() {
    }

    public static String paramToLowerCase(String param) {
        return param == null ? null : param.toLowerCase();
    }
}
