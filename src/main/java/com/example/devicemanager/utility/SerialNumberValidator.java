package com.example.devicemanager.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SerialNumberValidator {

    public boolean validSerialNumber(String serialIn) {

    String regexValue = "[A-Z,a-z.0-9]+-1?[A-Z,a-z,0-9]+";
    Pattern pattern = Pattern.compile(regexValue);
    Matcher matcher = pattern.matcher(serialIn);

    return matcher.find();
}


}
