package com.vuw17.dto.guest;

import com.vuw17.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class GuestDTO extends BaseDTO {

    @NotEmpty(message = "First name khong duoc de trong")
    private String firstName;

    @NotEmpty(message = "Last name khong duoc de trong")
    private String lastName;

    @Min(value = 1,message = "Birthday phai lon hon 0")
    private long birthday;

    @NotEmpty(message = "Nationality khong duoc de trong")
    private String nationality;

    @NotEmpty(message = "Address khong duoc de trong")
    private String address;

    @NotEmpty(message = "Phone Number khong duoc de trong")
//    @NumberFormat(pattern = "(\\+84|0)+(3[2-9]|5[6|8|9]|9\\d(?!5)|8[1-9]|7[0|6-9])+([0-9]{7})\\b")
    @Size(min = 10,max = 10,message = "So dien thoai phai dung 10 so")
    private String phoneNumber;

    @NotEmpty(message = "Email khong duoc de trong")
    @Email(message = "Email sai dinh dang")
    private String email;

    @NotEmpty(message = "ID Card khong duoc de trong")
    @Size(min = 12,max = 12,message = "The can cuoc phai dung 12 so")
    private String idCard;
}
