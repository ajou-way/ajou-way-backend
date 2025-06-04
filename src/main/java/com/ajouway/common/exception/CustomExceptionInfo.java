package com.ajouway.common.exception;

import lombok.Getter;

@Getter
public enum CustomExceptionInfo {
    //400, 401, 403, 303

    //400
    ALREADY_EXIST_AMENITY_INFO_TYPE(400, "already exist amenity info type", 400001),

    //401
    INVALID_PASSWORD(401, "wrong password", 401001),
    INVALID_REFRESH_TOKEN(401, "invalid refresh token", 401002),
    INVALID_ENUM(401, "invalid enum", 401003),

    //404
    NOT_FOUND_USER(404, "user not found", 404001),
    BUILDING_NOT_FOUND(404, "builing not found", 404002),
    AMENITY_NOT_FOUND(404, "amenity not found", 404003),

    //409
    IMAGE_EXTENSION_NOT_SUPPORTED(409, "image extension not supported", 409001),
    IMAGE_UPLOAD_FAIL(409, "image upload fail", 409002),

    //500
    ROUTE_ERROR(500, "route error", 500001),
    JSON_CONVERT_FAILED(500, "JSON 문자열 변환에 실패했습니다.", 500002);

    private final int statusCode;
    private final String message;
    private final int detailStatusCode;

    CustomExceptionInfo(int statusCode, String message, int detailStatusCode) {
        this.statusCode = statusCode;
        this.message = message;
        this.detailStatusCode = detailStatusCode;
    }
}
