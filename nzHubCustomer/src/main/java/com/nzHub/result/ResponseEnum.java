package com.nzHub.result;

public enum ResponseEnum {
    USER_INFO_NULL(300, "User information cannot be empty"),
    EMAIL_ERROR(301, "Invalid email format"),
    MOBILE_ERROR(302, "Invalid mobile number format"),
    USERNAME_EXISTS(303, "Username already exists"),
    USER_REGISTER_ERROR(304, "User registration failed"),
    USERNAME_NOT_EXISTS(305, "Username does not exist"),
    PASSWORD_ERROR(306, "Incorrect password"),
    PARAMETER_NULL(307, "Parameter is null"),
    NOT_LOGIN(308, "Not logged in"),
    CART_ADD_ERROR(309, "Failed to add to cart"),
    PRODUCT_NOT_EXISTS(310, "Product does not exist"),
    PRODUCT_STOCK_ERROR(311, "Insufficient product stock"),
    CART_UPDATE_ERROR(312, "Failed to update cart"),
    CART_UPDATE_PARAMETER_ERROR(313, "Cart update parameter error"),
    CART_UPDATE_STOCK_ERROR(314, "Failed to update product stock"),
    CART_REMOVE_ERROR(315, "Failed to remove from cart"),
    ORDERS_CREATE_ERROR(316, "Failed to create order main record"),
    ORDER_DETAIL_CREATE_ERROR(317, "Failed to create order details"),
    USER_ADDRESS_ADD_ERROR(318, "Failed to add new address"),
    USER_ADDRESS_SET_DEFAULT_ERROR(319, "Failed to set default address");


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
