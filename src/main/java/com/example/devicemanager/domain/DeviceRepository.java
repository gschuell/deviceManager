package com.example.devicemanager.domain;

import com.example.devicemanager.utility.ErrorMessages;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DeviceRepository {

    private List<DeviceRecord> deviceStore;


    public DeviceRepository() {

        deviceStore = new ArrayList<>();
    }

    public void addDevice(DeviceRecord newDevice) {

        deviceStore.add(newDevice);
    }

    public DeviceRecord getDeviceBySerialNumber(String serialNumber ) {
        DeviceRecord tempDevice = deviceStore.stream()
                .filter(deviceRecord-> serialNumber.equals(deviceRecord.getSerialNumber()))
                .findAny()
                .orElse(null);

            if (tempDevice == null) {
                return null;
        } else {
                return tempDevice;
            }
    }

    public List<DeviceRecord> getAllDevices() {

        List<DeviceRecord> deviceList = new ArrayList<>();
        for (DeviceRecord deviceRecord : deviceStore) {
            deviceList.add(deviceRecord);
        }

        return deviceList;
    }


    public DeviceRecord getDeviceByMachineCode(String machineCode) {
        DeviceRecord tempDevice = deviceStore.stream()
                .filter(deviceRecord-> machineCode.equals(deviceRecord.getSerialNumber()))
                .findAny()
                .orElse(null);

        if (tempDevice == null) {
            return null;
        } else {
            return tempDevice;
        }
    }
}
