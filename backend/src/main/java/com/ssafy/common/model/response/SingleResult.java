package com.ssafy.common.model.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SingleResult<T> extends BaseResponseBody{

    private T data;

    public SingleResult(Integer statusCode, String message, T data) {
        super(statusCode, message);
        this.data = data;
    }
}
