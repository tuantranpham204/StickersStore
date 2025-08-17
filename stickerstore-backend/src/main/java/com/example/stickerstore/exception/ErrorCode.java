package com.example.stickerstore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {

    //error codes here
    INTERNAL_ERROR(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    CANNOT_CHANGE_PURCHASED_ORDERS(400, "Cannot make changes to purchased orders", HttpStatus.BAD_REQUEST),
    INVALID_NUMBER_OF_CART(400, "Only 1 cart is allowed", HttpStatus.BAD_REQUEST),
    CANNOT_UPDATE_ORDER_PRODUCT(400, "Cannot update order product", HttpStatus.BAD_REQUEST),
    ;


    private int code;
    private String message;
    private HttpStatus status;
}
