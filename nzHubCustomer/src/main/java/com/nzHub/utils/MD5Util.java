package com.nzHub.utils;

import java.security.MessageDigest;
import java.util.Random;

public class MD5Util {
    /**
     * Convert a byte[] array to a hexadecimal string.
     *
     * @param arr The byte[] array to be converted.
     *
     * @return  String Returns a hexadecimal string.
     */
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /**
     * MD5 encryption, and convert the result from a byte array to a hexadecimal string.
     *
     * @param str Content to be encrypted.
     *
     * @return String Return the encrypted hexadecimal string.
     */
    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return hex(digest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }

    /**
     * Generate a password containing a random salt.
     *
     * @param password The password to be encrypted.
     *
     * @return String The password containing the random salt.
     */
    public static String getSaltMD5(String password) {
        // Generate a 16-bit random number
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // Generate the final encryption salt
        String salt = sBuilder.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    /**
     * Verify whether the salted password matches the original password.
     *
     * @param password Original password.
     *
     * @param password Encrypted password.
     *
     *@return boolean true indicates that the password matches the original password; false indicates that the password does not match the original password.
     */
    public static boolean getSaltverifyMD5(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String Salt = new String(cs2);
        return md5Hex(password + Salt).equals(String.valueOf(cs1));
    }

}
