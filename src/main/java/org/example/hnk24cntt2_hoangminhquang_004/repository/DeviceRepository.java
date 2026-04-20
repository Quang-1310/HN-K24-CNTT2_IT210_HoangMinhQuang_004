package org.example.hnk24cntt2_hoangminhquang_004.repository;

import org.example.hnk24cntt2_hoangminhquang_004.model.Device;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceRepository {
    private static final List<Device> deviceList = new ArrayList<>();
    private static int count = 1;

    public List<Device> getAllDevice(){
        return deviceList;
    }

    public void addDevice(Device newDevice){
        newDevice.setId((long) count++);
        deviceList.add(newDevice);
    }

    public void deleteDevice(Long id){
        deviceList.removeIf(device -> device.getId().equals(id));
    }

    public Device getDeviceById(Long id){
        return deviceList.stream().filter(device -> device.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateDevice(Device device){
        deviceList.set(Math.toIntExact(device.getId()), device);
    }
}
