package com.ajouway.common.exception;

import lombok.Getter;

@Getter
public enum CustomExceptionInfo {
    //400, 401, 403, 303

    //400

    //401
    INVALID_PASSWORD(401, "wrong password", 401001),
    INVALID_REFRESH_TOKEN(401, "invalid refresh token", 401002),
    INVALID_ENUM(401,"invalid enum",401003),

    //404
    NOT_FOUND_USER(404, "user not found", 404001),
    BUILDING_NOT_FOUND(404, "builing not found",404002);

    private final int statusCode;
    private final String message;
    private final int detailStatusCode;

    CustomExceptionInfo(int statusCode, String message, int detailStatusCode) {
        this.statusCode = statusCode;
        this.message = message;
        this.detailStatusCode = detailStatusCode;
    }
}
