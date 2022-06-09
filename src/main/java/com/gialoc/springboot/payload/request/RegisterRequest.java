package com.gialoc.springboot.payload.request;

import com.gialoc.springboot.payload.validation.username.UserNameValidation;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterRequest {
    @NotBlank(message = "Tên người dùng không được để trống")
    @Length(max = 16, message = "Tên người dùng không được quá 16 ký tự")
    @UserNameValidation
    private String userName;
    @NotBlank
    @Email(message = "Phải thuộc định dạng email")
    private String email;
    @NotBlank
    @Length(min = 6, message = "Mật khẩu phải từ 6 kí tự trở lên")
    private String password;
    @NotBlank
    @Length(min = 6, message = "Mật khẩu phải từ 6 kí tự trở lên")
    private String rePassword;
    @NotBlank(message = "Tên không được để trống")
    private String firstName;
    @NotBlank(message = "Họ tên không được để trống")
    private String lastName;
    @NotBlank
    @Length(min = 10, max = 10, message = "Số điện thoại phải đủ 10 số")
    private String phoneNumber;
    @NotNull(message = "Không được để trống địa chỉ")
    private @Valid AddressRequest address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }
}
