package com.blackcat.baseconversion.model;

public class BaseName {
    public static String get(int i) {
        switch (i) {
            case 2:
                return "binary";
            case 3:
                return "ternary";
            case 4:
                return "quaternary";
            case 5:
                return "quinary";
            case 6:
                return "senary";
            case 7:
                return "septenary";
            case 8:
                return "octal";
            case 9:
                return "nonary";
            case 10:
                return "decimal";
            case 11:
                return "undecimal";
            case 12:
                return "duodecimal";
            case 13:
                return "tridecimal";
            case 14:
                return "tetradecimal";
            case 15:
                return "pentadecimal";
            case 16:
                return "hexadecimal";
            default:
                return "N/A";
        }
    }
}
