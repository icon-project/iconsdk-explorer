package com.dfg.icon.util;


import com.dfg.icon.core.v3.vo.DecimalType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class HexUtil {
    /**
     * hex string을 decimal type으로 변환
     */
    public static BigDecimal getHexToDecimal(String hex, Integer decimals) {
        if(hex == null) {
            return null;
        }
        boolean isHex = true;
        if(decimals == null) {
            decimals = DecimalType.ICX.getValue();
        }
        if(hex.length()>2 && hex.substring(0, 2).toLowerCase().equals("0x")) {
            hex = hex.substring(2);
        } else if(hex.length()>3 && hex.substring(0, 3).toLowerCase().equals("-0x")) {
        	hex = hex.substring(3);
        	hex = "-" + hex;
        } else {
        	// Not hexadecimal
        	isHex = false;
        	hex = new DecimalFormat("##0").format(new BigDecimal(hex)); // 소수점 제거
        }

        BigInteger bigInt;
        if(isHex) bigInt = new BigInteger(hex, 16);
        else bigInt = new BigInteger(hex);

        BigInteger zero = new BigInteger("0");

        if ( bigInt.compareTo(zero) == 0 ){
            return new BigDecimal("0");
        }

        BigDecimal bd = new BigDecimal(bigInt);

        BigDecimal factor = BigDecimal.TEN.pow(decimals);
        return bd.divide(factor);
    }

    /**
     * 입력받은 값에 decimals만큼 자리수 나누기를 하여 decimal string으로 반환
     */
    public static String applyDecimal(String value, Integer decimals) {
        if(value == null) {
            return null;
        }
        // 16진수
        if(value.length()>2 && (value.substring(0, 2).toLowerCase().equals("0x") || value.substring(0, 3).toLowerCase().equals("-0x"))) {
            return String.valueOf(getHexToDecimal(value, decimals));
        } else {
            // 10진수는 16진수로 바꿔서 전달..
            String bigHex = new BigInteger(value, 10).toString(16);
            value = "0x" + bigHex;
            return String.valueOf(getHexToDecimal(value, decimals));
        }

    }

    /**
     * decimal string + hex string 연산 후 decimal string 반환
     */
    public static String decimalPlusHex(String decimal, String hex, Integer decimals) {
        if(decimal == null) {
            return null;
        }
        BigDecimal val1 = new BigDecimal(decimal);

        DecimalFormat df = new DecimalFormat(getDecimalFormat(decimals));
        if(hex != null) {
            return df.format(val1.add(getHexToDecimal(hex, decimals)));
        }
        return df.format(val1);
    }

    /**
     * decimal string - hex string 연산 후 decimal string 반환
     */
    public static String decimalMinusHex(String decimal, String hex, Integer decimals) {
        if(decimal == null) {
            return null;
        }
        BigDecimal val1 = new BigDecimal(decimal);

        DecimalFormat df = new DecimalFormat(getDecimalFormat(decimals));
        if(hex != null) {
            return df.format(val1.subtract(getHexToDecimal(hex, decimals)));
        }
        return df.format(val1);
    }

    /**
     * decimal string + decimal string 연산 후 decimal string 반환
     */
    public static String decimalPlusDecimal(String decimal, String decimal2, Integer decimals) {
        if(decimal == null) {
            return null;
        }
        BigDecimal val1 = new BigDecimal(decimal);

        DecimalFormat df = new DecimalFormat(getDecimalFormat(decimals));
        if(decimal2 != null) {
            BigDecimal val2 = new BigDecimal(decimal2);
            return df.format(val1.add(val2));
        }
        return df.format(val1);
    }

    /**
     * decimal string * decimal String 연산 후 decimal String 반환
     */
    public static String decimalMultiDecimal(String decimal, String decimal2, Integer decimals) {
        if(decimal == null) {
            return null;
        }
        BigDecimal val1 = new BigDecimal(decimal);

        DecimalFormat df = new DecimalFormat(getDecimalFormat(decimals));
        if(decimal2 != null) {
            BigDecimal val2 = new BigDecimal(decimal2);
            return df.format(val1.multiply(val2));
        }
        return df.format(val1);
    }

    /**
     * hex type의 string을 decimal type의 string으로 반환
     */
    public static String toDecimalString(String hex, Integer decimals) {

        if(hex == null) {
            return "0";
        }
        BigDecimal bd = new BigDecimal("1");
        if(hex.startsWith("-")){
            bd = new BigDecimal("-1");
            hex = hex.substring(1);
        }

        DecimalFormat df = new DecimalFormat(getDecimalFormat(decimals));
        return df.format(getHexToDecimal(hex, decimals).multiply(bd));
    }

    public static String toHexString(String decimal) {
        BigInteger bigInt = new BigInteger(decimal);

        BigInteger zero = new BigInteger("0");

        if ( bigInt.compareTo(zero) == 0 ){
            return "0x0";
        }

        return "0x" + bigInt.toString(16);
    }

    /**
     * decimal type string에 decimals 소수점 적용
     */
    public static String decimalFormat(String amount, Integer decimals) {
     
        BigDecimal bigAmount = new BigDecimal(amount);

        DecimalFormat df = new DecimalFormat(getDecimalFormat(decimals));
       
        return df.format(bigAmount);
    }

    /**
     * decimal 자리수형식 format 반환 (hexUtil 내부용)
     */
    public static String getDecimalFormat(Integer decimals) {
        if(decimals == null) {
            decimals = DecimalType.ICX.getValue();
        }
        if(decimals <= 0) {
            return "##0";
        }
        String dFormat = "##0.";
        for(int i=0; i<decimals; i++) {
            dFormat = dFormat + "#";
        }
        return dFormat;
    }
    
    /** hex String 을 integer로 변경
     * @return
     */
    public static int changeHexToInteger(String hexString) {
    	if(hexString.startsWith("0x")) {
    		hexString = hexString.substring(2);
    	}
    	return  Integer.parseInt( hexString, 16 );
    }

    /**
     * 0x로 시작하는 hex string을 integer로 변경.
     * 그 외엔 단순 형변환.
     */
    public static int changeStringToInteger(String str) {
        if(str.startsWith("0x")) {
            str = str.substring(2);
            return  Integer.parseInt( str, 16 );
        } else {
            return Integer.parseInt(str);
        }
    }

    public static String hexToImage(String hexString) {

        hexString = hexString.replace("\"", "");
        if(hexString.startsWith("0x")) {
            hexString = hexString.substring(2);
        }

        StringBuilder sb = new StringBuilder();
        for( int i=0; i<hexString.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hexString.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);
        }

        return sb.toString();
    }
}
