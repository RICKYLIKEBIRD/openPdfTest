package Util;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Base64Util {
    private static final String BASE64_PREFIX = "data:application/pdf;base64,";
    public static String encodeToBase64(byte[] bytes) {
        return BASE64_PREFIX + Base64.encode(bytes);
    }
}
