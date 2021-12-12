package com.vuw17.validate;

import com.vuw17.entities.User;
import com.vuw17.exceptions.InputException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidateUser {

    //Hàm validate email
    public static String validateEmail(String email){
        boolean checkEmail = regexEmail(email);
        if (checkEmail == true) {
            return email;
        }
        else {
            throw new InputException("Email không đúng định dạng");
        }
    }

    //Hàm validate phone
    public static String validatePhone(String phone){
        boolean checkPhone = regexPhone(phone);
        if (checkPhone == true) {
            return phone;
        }
        else {
            throw new InputException("Phone không đúng định dạng");
        }
    }

    // Hàm validate username
    public static String validateUsername(String username, List<User> users){
        getSpecialCharacterCount(username);
        boolean checkUsername = checkUsernameExist(username, users);
        if (checkUsername == true) {
            return username;
        }
        else {
            throw new InputException("Username bị trùng");
        }
    }



    //hàm kiểm tra kí tự đặc biệt
    public static boolean getSpecialCharacterCount(String s) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if (b == true)
            return false;
        else
            return true;
    }

    //Hàm kiểm tra input phone
    public static boolean regexPhone(String s) {
        Pattern p = Pattern.compile("^(0[3|5|7|8|9])+([0-9]{8})$");
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }

    //Hàm kiểm tra email
    public static boolean regexEmail(String s) {
        Pattern p = Pattern.compile("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$");
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }

    //Hàm kiểm tra tồn tại username
    public static boolean checkUsernameExist(String username, List<User> users){
        for (User user:  users) {
            if (username.compareTo(user.getUsername()) == 0){
                return false;
            }
        }
        return true;
    }


}
