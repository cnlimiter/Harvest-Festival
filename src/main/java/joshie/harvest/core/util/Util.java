package joshie.harvest.core.util;

public final class Util {
    private Util() {}

    public static String safeFormat(String format, Object... args) {
        try {
            return String.format(format, args);
        } catch (Exception e) {
            return "Format Error: " + format;
        }
    }
}
