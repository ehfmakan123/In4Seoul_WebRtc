package com.ssafy.common.model.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ListResult<T>  extends BaseResponseBody{

    private List<T> data;

    public ListResult(Integer statusCode, String message, List<T> data) {
        super(statusCode, message);
        this.data = data;
    }
}
