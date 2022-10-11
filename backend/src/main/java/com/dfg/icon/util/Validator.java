package com.dfg.icon.util;

import java.util.regex.Pattern;

/**
 * Created by LYJ on 2019-03-06.
 */
public class Validator {

    private static final String datePattern = "^([0-9]{4})\\-([0-9]{2})\\-([0-9]{2})$";
    private static final String downTypePattern = "^[0-1]{1}$";
    private static final String addressPattern = "^[c|h]{1}x[a-f0-9]{40}$";
    private static final String hashPattern = "^0x[a-f0-9]{64}$";
    private static final int downMaxSize = 10000;

    public static boolean isValidDatePattern(String date) {
        if(date == null) {
            return false;
        }
        return Pattern.matches(datePattern, date);
    }

    public static boolean isValidDownType(String type) {
        if(type == null) {
            return false;
        }
        return Pattern.matches(downTypePattern, type);
    }

    public static boolean isValidMaxDownload(Integer count) {
        if(count > downMaxSize) {
            return false;
        }
        return true;
    }
    public static boolean isAddressPattern(String address) {
        if(address == null) {
            return false;
        }
        return Pattern.matches(addressPattern, address);
    }
    public static boolean isHashPattern(String hash) {
        if(hash == null) {
            return false;
        }
        return Pattern.matches(hashPattern, hash);
    }
}
