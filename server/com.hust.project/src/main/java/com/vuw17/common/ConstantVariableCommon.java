package com.vuw17.common;

public class ConstantVariableCommon {
    public static final String AUTHORIZATION = "Authorization";

    // biến cố định của Reservation
    public static final int STATUS__RESERVATION_1 = 1; // trạng thái chờ xử lý
    public static final int STATUS__RESERVATION_2 = 2; // trạng thái đã xác nhận
    public static final int STATUS__RESERVATION_3 = 3; // trạng thái đã nhận phòng
    public static final int STATUS__RESERVATION_4 = 4; // trạng thái đã hủy

    // biến cố định của Room
    public static final int STATUS_ROOM_1 = 1; // trạng thái phòng trống
    public static final int STATUS_ROOM_2 = 2; // trạng thái phòng đang có khách
    public static final int STATUS_ROOM_3 = 3; // trạng thái phòng đang dọn dẹp
    public static final int STATUS_ROOM_4 = 4; // trạng thái phòng đang sửa

    // Biến cố định của room reservation
    public static final int STATUS_ROOM_RESERVATION_1 = 1; // trạng thái đặt phòng: đang chờ xác nhận
    public static final int STATUS_ROOM_RESERVATION_2 = 2; // trạng thái đặt phòng: đã đặt
    public static final int STATUS_ROOM_RESERVATION_3 = 3; // trạng thái đặt phòng: đã hủy

    // Biến cố định của product
    public static final int STATUS_PRODUCT_1 = 1; // trạng thái sản phẩm: đang bán
    public static final int STATUS_PRODUCT_2 = 2; // trạng thái sản phẩm: ngừng bán
    public static final int STATUS_PRODUCT_3 = 3; // trạng thái sản phẩm: đã xóa

    //Biến cố định của type_product
    public static final int STATUS_TYPE_PRODUCT_1 = 1; // trạng thái loại sản phẩm: đang sử dụng
    public static final int STATUS_TYPE_PRODUCT_2 = 2; // trạng thái loại sản phẩm: ngừng sử dụng
    public static final int STATUS_TYPE_PRODUCT_3 = 3; // trạng thái loại sản phẩm: đã xóa

    //Biến cố định của product reservation
    public static final int STATUS_PRODUCT_RESERVATION_1 = 1; // trạng thái order sản phẩm: đang xử lý
    public static final int STATUS_PRODUCT_RESERVATION_2 = 2; // trạng thái order sản phẩm: đã duyệt
    public static final int STATUS_PRODUCT_RESERVATION_3 = 3; // trạng thái order sản phẩm: đã hủy

    //Biến cố định của floor
    public static final int STATUS_FLOOR_1 = 1; // trạng thái của tầng: đang sử dụng
    public static final int STATUS_FLOOR_2 = 2; // trạng thái của tầng: đang bảo trì

    //Biến cố định của hotel
    public static final int STATUS_HOTEL_1 = 1; // trạng thái của khách sạn: đang sử dụng
    public static final int STATUS_HOTEL_2 = 2; // trạng thái của khách sạn: đang đóng cửa
    public static final int STATUS_HOTEL_3 = 3; // trạng thái của khách sạn: đã xóa

    //Biến cố định của type_room
    public static final int STATUS_TYPE_ROOM_1 = 1; // trạng thái của loại phòng: đang sử dụng
    public static final int STATUS_TYPE_ROOM_2 = 2; // trạng thái của loại phòng: ngưng sử dụng
    public static final int STATUS_TYPE_ROOM_3 = 3; // trạng thái của loại phòng: đã xóa

    //Biến cố định của unit
    public static final int STATUS_UNIT_1 = 1; // trạng thái của đơn vị: đang sử dụng
    public static final int STATUS_UNIT_2 = 2; // trạng thái của đơn vị: ngưng sử dụng
    public static final int STATUS_UNIT_3 = 3; // trạng thái của đơn vị: đã xóa

    //Biến cố định guest


    //Static final message response

    public static final String INPUT_ID = "Input the id";

    public static final String INVALID_PHONE = "Invalid phone number!";
    public static final String INVALID_ID = "Id must be greater than 1";


    public static final String NOT_EXIST_ID = "Id does not exist";
    public static final String DELETED_ID = "Id was deleted";

    public static final String DUPLICATED_NAME = "Duplicated the name!";
    public static final String DUPLICATED_ADDRESS = "Duplicated the address!";
    public static final String DUPLICATED_PHONE = "Duplicated the phone number!";

    public static final String CREATE_SUCCESSFUL = "Create Successful!";
    public static final String UPDATE_SUCCESSFUL = "Update Successful!";
    public static final String DELETE_SUCCESSFUL = "Delete Successful!";

}