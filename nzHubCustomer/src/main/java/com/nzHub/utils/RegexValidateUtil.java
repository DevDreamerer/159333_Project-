package com.nzHub.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use regular expressions for form validation
 */
public class RegexValidateUtil {

    static boolean flag = false;
    static String regex = "";

    public static boolean check(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * verify email
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return check(email, regex);
    }

    /**
     * verify phone number
     *
     * @param mobile
     * @return
     */



    public static boolean checkMobile(String mobile) {
        String regex = "^(1[3-9]\\d{9}|02[012578]\\d{7})$";
        return check(mobile, regex);
    }


    /**
     * verify telephone bumber
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone(String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return  check(telephone, regex);
    }

    /**
     * verify fax
     *
     * @param fax
     * @return
     */
    public static boolean checkFax(String fax) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(fax, regex);
    }

    /**
     * verify qq
     *
     * @param QQ
     * @return
     */
    public static boolean checkQQ(String QQ) {
        String regex = "^[1-9][0-9]{4,} $";
        return check(QQ, regex);
    }
}