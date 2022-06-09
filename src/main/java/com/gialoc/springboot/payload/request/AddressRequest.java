package com.gialoc.springboot.payload.request;

import javax.validation.constraints.NotBlank;

public class AddressRequest {
    @NotBlank(message = "Quốc gia không được để trống")
    private String country; // (quốc gia)
    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
    private String province; // or city (tỉnh)
    @NotBlank(message = "Huyện/Quận không được để trống")
    private String town; // or district (huyện)
    @NotBlank(message = "Xã/Phường không được để trống")
    private String village; // or ward (xã)
    @NotBlank(message = "Thôn/Đường không được để trống")
    private String hamlet; // or street (thôn)

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getHamlet() {
        return hamlet;
    }

    public void setHamlet(String hamlet) {
        this.hamlet = hamlet;
    }
}
