package com.example.devicemanager.utility;

import java.util.Optional;

public class ValidateMachineCode {

    public boolean validateMachineCode(String machCodeIn) {
        Optional <String> opt = Optional.of(machCodeIn);
        return !opt.isEmpty();
    }
}
