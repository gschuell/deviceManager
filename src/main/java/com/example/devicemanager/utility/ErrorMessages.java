package com.example.devicemanager.utility;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessages {

    String ER001 = new String(
            "{ " +
             "\"resourceKey\": \"machine.code.invalid," +
              "\"errorCode\": \"ER001\"," +
              "\"message\": \"The machine code is incorrect. Check the Machine code you provided and try again.\"" + "}");


    String ER002 = new String(
            "{ " +
                    "\"resourceKey\": \"machine.code.not.found\"," +
                    "\"errorCode\": \"ER002\"," +
                    "\"message\": \"The machine code does not match our records.\"" + "}");


    String ER003 = new String(
            "{ " +
                    "\"resourceKey\": \"serial.number.invalid\"," +
                    "\"errorCode\": \"ER003," +
                    "\"message\": \"The serial number entered can include a - z, A - Z, 0 - 9 and hyphen." +
                    "Please correct your entry." + "}");


    String ER004 = new String(
            "{ " +
                    "\"resourceKey\": \"serial.number.not.found\"," +
                    "\"errorCode\": \"ER004\"," +
                    "\"message\": \"The serial number does not match our records.\"" + "}");

    public String getError(String errorCode) {
        String returnCode = null;

        if (errorCode.equals("ER001")){
            returnCode = ER001;
        }
        if (errorCode.equals("ER002")){
            returnCode = ER002;
        }
        if (errorCode.equals("ER003")){
            returnCode = ER003;
        }
        if (errorCode.equals("ER004")){
            returnCode = ER004;
        }

        return returnCode;
    }

}
