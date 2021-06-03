package com.example.devicemanager.domain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by gschuell on 5/28/2021
 */
@Getter
@Setter
@Data
public class DeviceRecord {

    @SerializedName("serial_number")
    @Expose
    private String serialNumber;
    @SerializedName("machine_code")
    @Expose
    private String machineCode;
    @SerializedName("device_name")
    @Expose
    private String deviceName;

    public DeviceRecord() {

    }


}
