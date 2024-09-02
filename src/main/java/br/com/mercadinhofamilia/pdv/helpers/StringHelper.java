package br.com.mercadinhofamilia.pdv.helpers;

public class StringHelper {

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isPositiveNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }

    public static boolean isPositiveIntegerNumber(String str) {
        return str.matches("^\\d+$");
    }
}
