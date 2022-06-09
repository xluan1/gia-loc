package com.gialoc.springboot.payload.response;

import com.gialoc.springboot.model.enums.RoleUser;
import com.gialoc.springboot.model.enums.StatusUser;
import com.gialoc.springboot.payload.request.AddressRequest;

public class UserResponse {
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private StatusUser statusUser;
    private RoleUser roleUser;
    private AddressRequest address;
}
