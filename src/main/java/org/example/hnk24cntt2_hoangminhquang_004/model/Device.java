package org.example.hnk24cntt2_hoangminhquang_004.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Device {
    private Long id;

    @NotBlank(message = "Tên thiết bị không được để trống")
    @Size(min = 3, max = 100, message = "Độ dài tối thiểu từ 3 - 100 ký tự")
    private String deviceName;

    @NotBlank(message = "Tên thương hiệu không được để trống")
    private String brand;

    @Min(value = 1, message = "Giá tối thiểu là 1 đồng")
    @Max(value = 10000, message = "Giá tối đa là 10000 đồng")
    private Double price;

    private String deviceImage;

    public Device() {
    }

    public Device(Long id, String deviceName, String brand, Double price, String deviceImage) {
        this.id = id;
        this.deviceName = deviceName;
        this.brand = brand;
        this.price = price;
        this.deviceImage = deviceImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDeviceImage() {
        return deviceImage;
    }

    public void setDeviceImage(String deviceImage) {
        this.deviceImage = deviceImage;
    }
}
