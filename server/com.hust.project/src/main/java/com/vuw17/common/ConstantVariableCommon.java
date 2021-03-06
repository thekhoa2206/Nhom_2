package com.vuw17.common;

public class ConstantVariableCommon {
    public static final String AUTHORIZATION = "Authorization";

    // biến cố định của Reservation
    public static final int STATUS_RESERVATION_1 = 1; // trạng thái đã đặt phòng
    public static final int STATUS_RESERVATION_2 = 2; // trạng thái đã đặt
    public static final int STATUS_RESERVATION_3 = 3; // trạng thái đã hoàn thành
    public static final int STATUS_RESERVATION_4 = 4; // trạng thái đã hủy

    // biến cố định của Room
    public static final int STATUS_ROOM_1 = 1; // trạng thái phòng trống
    public static final int STATUS_ROOM_2 = 2; // trạng thái phòng đang có khách
    public static final int STATUS_ROOM_3 = 3; // trạng thái phòng đang dọn dẹp
    public static final int STATUS_ROOM_4 = 4; // trạng thái phòng đang sửa
    public static final int STATUS_ROOM_5 = 5; // trạng thái phòng đang được đặt

    // Biến cố định của room reservation
    public static final int STATUS_ROOM_RESERVATION_1 = 1; // trạng thái đặt phòng: đặt phòng thành công
    public static final int STATUS_ROOM_RESERVATION_2 = 2; // trạng thái đặt phòng: đã hủy

    // Biến cố định của product
    public static final int STATUS_PRODUCT_1 = 1; // trạng thái sản phẩm: đang bán
    public static final int STATUS_PRODUCT_2 = 2; // trạng thái sản phẩm: ngừng bán
    public static final int STATUS_PRODUCT_3 = 3; // trạng thái sản phẩm: đã xóa

    //Biến cố định của occupied_room
    public static final int STATUS_OCCUPIED_ROOM_1 = 1; // da check in thanh cong
    public static final int STATUS_OCCUPIED_ROOM_2 = 2; // da check out thanh cong

    //Biến cố định của bill
    public static final int STATUS_BILL_1 = 1; // chua thanh toan
    public static final int STATUS_BILL_2 = 2; // da thanh toan


    //Biến cố định của type_product
    public static final int STATUS_TYPE_PRODUCT_1 = 1; // trạng thái loại sản phẩm: đang sử dụng
    public static final int STATUS_TYPE_PRODUCT_2 = 2; // trạng thái loại sản phẩm: ngừng sử dụng
    public static final int STATUS_TYPE_PRODUCT_3 = 3; // trạng thái loại sản phẩm: đã xóa

    //Biến cố định của product reservation
    public static final int STATUS_PRODUCT_RESERVATION_1 = 1; // trạng thái order sản phẩm: đang xử lý
    public static final int STATUS_PRODUCT_RESERVATION_2 = 2; // trạng thái order sản phẩm: đã duyệt
    public static final int STATUS_PRODUCT_RESERVATION_3 = 3; // trạng thái order sản phẩm: đã hủy


    //Biến cố định của type_room
    public static final int STATUS_TYPE_ROOM_1 = 1; // trạng thái của loại phòng: đang sử dụng
    public static final int STATUS_TYPE_ROOM_2 = 2; // trạng thái của loại phòng: ngưng sử dụng
    public static final int STATUS_TYPE_ROOM_3 = 3; // trạng thái của loại phòng: đã xóa

    //Biến cố định của unit
    public static final int STATUS_UNIT_1 = 1; // trạng thái của đơn vị: đang sử dụng
    public static final int STATUS_UNIT_2 = 2; // trạng thái của đơn vị: ngưng sử dụng
    public static final int STATUS_UNIT_3 = 3; // trạng thái của đơn vị: đã xóa

    //Biến cố định của type_product
    public static final int STATUS_PRICE_1 = 1; // trạng thái giá phòng: đang sử dụng
    public static final int STATUS_PRICE_2 = 2; // trạng thái giá phòng: ngừng sử dụng
    public static final int STATUS_PRICE_3 = 3; // trạng thái giá phòng: đã xóa

    //Biến cố định của guest
    public static final int STATUS_GUEST_1 = 1; // trạng thái khach: đang sử dụng
    public static final int STATUS_GUEST_2 = 2; // trạng thái khach: da xoa

    //Biến cố định của type_room
    public static final int STATUS_TYPE_PRICE_1 = 1; // trạng thái của loại phòng: đang ap dụng
    public static final int STATUS_TYPE_PRICE_2 = 2; // trạng thái của loại phòng: ngưng ap dụng
    public static final int STATUS_TYPE_PRICE_3 = 3; // trạng thái của loại phòng: đã xóa

    //Biến cố định của service
    public static final int STATUS_SERVICE_1 = 1; // đang ap dụng
    public static final int STATUS_SERVICE_2 = 2; // ngưng ap dụng
    public static final int STATUS_SERVICE_3 = 3; // đã xóa
    //Biến cố định table
    public static final String table_hotel = "hotel";
    public static final String table_room = "room";
    public static final String table_type_room = "type_room";
    public static final String table_guest = "guest";
    public static final String table_price = "price";
    public static final String table_product = "product";
    public static final String table_product_reserved = "product_reserved";
    public static final String table_reservation = "reservation";
    public static final String table_reservation_guest = "reservation_guest";
    public static final String table_role = "role";
    public static final String table_room_price = "room_price";
    public static final String table_room_reserved = "room_reserved";
    public static final String table_type_product = "type_product";
    public static final String table_unit = "unit";
    public static final String table_user = "user";
    public static final String table_type_price = "type_price";
    public static final String table_occupied_room = "occupied_room";
    public static final String table_service_used = "service_used";
    public static final String table_hosted_at = "hosted_at";
    public static final String table_service = "service";
    public static final String table_bill = "bill";


    //Biến cố định table_id
    public static final int table_hotel_id = 1;
    public static final int table_room_id = 2;
    public static final int table_type_room_id = 3;
    public static final int table_guest_id = 4;
    public static final int table_price_id = 5;
    public static final int table_product_id = 6;
    public static final int table_product_reserved_id = 7;
    public static final int table_reservation_id = 8;
    public static final int table_reservation_guest_id = 9;
    public static final int table_roles_id = 10;
    public static final int table_room_price_id = 11;
    public static final int table_room_reserved_id = 12;
    public static final int table_type_product_id = 13;
    public static final int table_unit_id = 14;
    public static final int table_users_id = 15;

    //Biến cố định nội dung nhật ký
    public static final String NOTE_PRICE_ROOM_CREATE = "Thêm mới giá phòng";
    public static final String NOTE_PRICE_ROOM_UPDATE = "Cập nhật giá phòng";
    public static final String NOTE_PRICE_ROOM_DELETE = "Xóa giá phòng";


    //Biến cố định của action
    public static final int ACTION_CREATE = 1;
    public static final int ACTION_UPDATE = 2;
    public static final int ACTION_DELETE = 3;
    public static final int ACTION_LOGIN = 4;
    //Biến cố định của type_action
    public static final String TYPE_ACTION_CREATE = "Thêm mới bản ghi";
    public static final String TYPE_ACTION_UPDATE = "Cập nhật bản ghi";
    public static final String TYPE_ACTION_DELETE = "Xóa bản ghi";

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

    public static final int STATUS_USER_1 = 1;
    public static final int STATUS_USER_2 = 2;
    public static final int STATUS_USER_3 = 3;

    public static String changeIntToStringReservationStatus(int status){
        if(status == STATUS_RESERVATION_1) return "Chờ nhận phòng";
        else if(status == STATUS_RESERVATION_2) return "Đã đặt";
        else if(status == STATUS_RESERVATION_3) return "Hoàn thành";
        else return "Đã hủy";
    }

    public static String changeIntToStringUserStatus(int status){
        if(status == STATUS_USER_1) return "Đang làm việc";
        else if(status == STATUS_USER_2) return "Đã nghỉ";
        else return "Đã xóa";
    }
}