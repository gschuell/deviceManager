package com.example.devicemanager.controllers;

import com.example.devicemanager.domain.DeviceRecord;
import com.example.devicemanager.domain.DeviceRepository;
import com.example.devicemanager.utility.ErrorMessages;
import com.example.devicemanager.utility.SerialNumberValidator;
import com.example.devicemanager.utility.ValidateMachineCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("devicemanager")
public class DeviceController {

    private DeviceRepository deviceStore;
    private ErrorMessages errors;

    SerialNumberValidator validator = new SerialNumberValidator();
    ValidateMachineCode machineCodeValidator = new ValidateMachineCode();

    @Autowired
    public DeviceController (DeviceRepository deviceStore, ErrorMessages errors) {
        this.deviceStore = deviceStore;
        this.errors = errors;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public String createDevice(@RequestBody String deviceData) {
        String returnValue = null;
        boolean validSerialNumber = false;
        boolean validMachineCode = false;

        // Convert incoming JSON string to DeviceRecord object
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        DeviceRecord device = new DeviceRecord();
        device = gson.fromJson(deviceData, DeviceRecord.class);
        validSerialNumber = validator.validSerialNumber(device.getSerialNumber());
        validMachineCode = machineCodeValidator.validateMachineCode(device.getMachineCode());
        if (validSerialNumber && validMachineCode) {
            DeviceRecord tmp = deviceStore.getDeviceBySerialNumber(device.getSerialNumber());
            if (tmp == null) {
                deviceStore.addDevice(device);
                returnValue = "Device with serial number " + device.getSerialNumber() + " added\n";
            }
        }

        if (!validSerialNumber) {
                returnValue = errors.getError("ER003");
            }

        if (!validMachineCode) {
                returnValue = errors.getError("ER001");
            }

        return returnValue;
    }

    @RequestMapping(value = "/fetchbyserialnumber/{serialNumber}", method = RequestMethod.GET, produces = "application/json")
    public String getBySerialNumber(@PathVariable String serialNumber) {

        String returnValue = null;
        if (!validator.validSerialNumber(serialNumber)) {
            returnValue = errors.getError("ER003");
        } else {
            final GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            final Gson gson = builder.create();
            DeviceRecord device = deviceStore.getDeviceBySerialNumber(serialNumber);
            if (device != null) {
                returnValue = gson.toJson(device) + "\n";
            } else {
                returnValue = errors.getError("ER004");

            }
        }
        return returnValue;
    }

    @RequestMapping(value = "/fetchbymachinecode/{machinecode}", method = RequestMethod.GET, produces = "application/json")
    public String getByMachineCode(@PathVariable String machineCode) {

        String returnValue = null;
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        DeviceRecord device = deviceStore.getDeviceByMachineCode(machineCode);
        if (device != null) {
            returnValue = gson.toJson(device) + "\n";
        } else {
            returnValue = errors.getError("ER002");
        }
        return returnValue;
    }

    @RequestMapping(value= "/getalldevices", method = RequestMethod.GET, produces = "application/json")
    public List<DeviceRecord> getAllDevices() {
        List<DeviceRecord> devices = deviceStore.getAllDevices();

        return devices;
    }
}
