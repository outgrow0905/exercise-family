package com.latte.cj.royalty.model;

import java.util.ArrayList;
import java.util.List;

public class RoyaltyCode {
    private static String fileName;
    private static List<String> royaltyCodes = new ArrayList<>();

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        RoyaltyCode.fileName = fileName;
    }

    public static List<String> getRoyaltyCodes() {
        return royaltyCodes;
    }

    public static void setRoyaltyCodes(String royaltyCodes) {
        RoyaltyCode.royaltyCodes.add(royaltyCodes);
    }

    public static void remove() {
        fileName = null;
        royaltyCodes.clear();
    }
}
