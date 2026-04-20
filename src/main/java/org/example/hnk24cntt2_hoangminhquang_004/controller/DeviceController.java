package org.example.hnk24cntt2_hoangminhquang_004.controller;

import jakarta.validation.Valid;
import org.example.hnk24cntt2_hoangminhquang_004.model.Device;
import org.example.hnk24cntt2_hoangminhquang_004.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class DeviceController {
    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @GetMapping({"/","/viewDevice"})
    public String viewDevice(Model model){
        List<Device> deviceList = deviceService.getAllDevice();
        if(deviceList.isEmpty()){
            model.addAttribute("emptyDevice", "Chưa có thiết bị nào trong kho");
        }
        else {
            model.addAttribute("deviceList", deviceList);
        }
        return "view-device";
    }

    @GetMapping("/addDevice")
    public String viewFormAddDivice(Model model){
        model.addAttribute("device", new Device());
        model.addAttribute("isEdit", false);
        return "add-device";
    }

    @PostMapping("/addDevice")
    public String addDevice(@Valid @ModelAttribute("device") Device device,
                            BindingResult result,
                            @RequestParam(value = "imageDevice", required = false) MultipartFile file,
                            Model model){
        if(result.hasErrors()){
            return "view-device";
        }

        try {
            File uploadDir = new File("C:/RikkeiHackathon/");
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            if (file != null && !file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                if (originalFileName != null && originalFileName.toLowerCase().matches(".*\\.(jpg|png)$")) {
                    String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
                    File destination = new File(uploadDir.getAbsolutePath() + File.separator + uniqueFileName);
                    file.transferTo(destination);
                    device.setDeviceImage(uniqueFileName);
                } else {
                    model.addAttribute("errorImage", "Chỉ chấp nhận file .jpg hoặc .png");
                    return "add-device";
                }
            } else {
                device.setDeviceImage("default-image.jpg");
            }
            deviceService.addDevice(device);
            return "redirect:/view-device";
        } catch (IOException e) {
            return "add-device";
        }
    }

    @GetMapping("/deleteDevice/{id}")
    public String deleteDevice(@PathVariable("id") Long id) {
        deviceService.deleteDevice(id);
        return "redirect:/view-device";
    }

    @GetMapping("/editDevice/{id}")
    public String editDeviceForm(@PathVariable("id") Long id, Model model) {
        Device device = deviceService.getDeviceById(id);
        if (device != null) {
            model.addAttribute("deivice", device);
            model.addAttribute("isEdit", true);
            return "add-device";
        }
        return "redirect:/view-device";
    }

    @PostMapping("/updateDevice")
    public String updateDevice(@Valid @ModelAttribute("device") Device device,
                               BindingResult result,
                               @RequestParam(value = "imageDevice", required = false) MultipartFile file,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "add-device";
        }

        try {
            Device oldDevice = deviceService.getDeviceById(device.getId());
            File uploadDir = new File("C:/RikkeiHackathon/");
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            if (file != null && !file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                if (originalFileName != null && originalFileName.toLowerCase().matches(".*\\.(jpg|png)$")) {
                    String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
                    File destination = new File(uploadDir.getAbsolutePath() + File.separator + uniqueFileName);
                    file.transferTo(destination);
                    device.setDeviceImage(uniqueFileName);
                } else {
                    device.setDeviceImage(oldDevice.getDeviceImage());
                }

                deviceService.updateDevice(device);
            }

            return "redirect:/view-device";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
