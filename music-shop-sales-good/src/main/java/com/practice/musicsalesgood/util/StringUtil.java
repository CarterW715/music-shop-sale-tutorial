package com.practice.musicsalesgood.util;

public final class StringUtil {
    private StringUtil() {
    }

    public static boolean equalsIgnoreCaseNotNull(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        return a.equalsIgnoreCase(b);
    }

    public static boolean containsIgnoreCaseNotNull(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        return a.toLowerCase().contains(b.toLowerCase());
    }

    public static boolean isEmptyOrNull(String a) {
        return a == null || a.equals("") || a.trim().equals("");
    }
}
