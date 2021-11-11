package com.vuw17.dto.guest;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class GuestDTO extends BaseDTO {
//    @Column(name = "first_name", nullable = true, length = 30)
    private String firstName;

//    @Column(name = "last_name", nullable = true, length = 30)
    private String lastName;

//    @Column(name = "date_of_birth", nullable = true)
    private long dateOfBirth;

//    @Column(name = "nationality", nullable = true, length = 30)
    private String nationality;

//    @Column(name = "address", nullable = true, length = 150)
    private String address;

//    @Column(name = "phone_number", nullable = true, length = 15)
    private String phoneNumber;

//    @Column(name = "email", nullable = true, length = 100)
    private String email;

//    @Column(name = "id_card", nullable = true, length = 50)
    private String idCard;

//    @Column(name = "expiry_date", nullable = true)
    private long expiryDate;
}
