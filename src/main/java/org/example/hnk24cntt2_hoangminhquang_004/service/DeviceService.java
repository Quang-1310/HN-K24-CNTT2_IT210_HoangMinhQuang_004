package org.example.hnk24cntt2_hoangminhquang_004.service;

import org.example.hnk24cntt2_hoangminhquang_004.model.Device;
import org.example.hnk24cntt2_hoangminhquang_004.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevice(){
        return deviceRepository.getAllDevice();
    }

    public void addDevice(Device newDevice){
        deviceRepository.addDevice(newDevice);
    }

    public void deleteDevice(Long id){
        deviceRepository.deleteDevice(id);
    }

    public Device getDeviceById(Long id){
        return deviceRepository.getDeviceById(id);
    }

    public void updateDevice(Device device){
        deviceRepository.updateDevice(device);
    }
}
