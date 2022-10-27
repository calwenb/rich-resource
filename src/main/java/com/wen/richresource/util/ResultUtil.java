package com.wen.richresource.util;

import com.wen.richresource.enums.ResultEnum;
import com.wen.richresource.vo.ResultVO;


/**
 * @author calwen
 * @since 2022/10/27
 */
public class ResultUtil {
    public static <T> ResultVO<T> autoDo(boolean b) {
        return b ? successDo() : errorDo();
    }

    public static <T> ResultVO<T> autoDo(boolean b, String msg) {
        return b ? successDo(msg + " 成功") : error(msg + " 失败");
    }

    public static <T> ResultVO<T> success(T data) {
        return buildSuccessVO(data, ResultEnum.SUCCESS.getMessage());
    }


    public static <T> ResultVO<T> success(T data, String msg) {
        return buildSuccessVO(data, msg);
    }

    public static <T> ResultVO<T> successDo() {
        return buildSuccessVO(null, ResultEnum.SUCCESS.getMessage());
    }

    public static <T> ResultVO<T> successDo(String msg) {
        return buildSuccessVO(null, msg);
    }


    public static <T> ResultVO<T> error(String msg) {
        return buildErrorVO(ResultEnum.ERROR.getCode(), msg);
    }

    public static <T> ResultVO<T> errorDo() {
        return buildErrorVO(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }


    public static <T> ResultVO<T> exception() {
        return buildErrorVO(ResultEnum.EXCEPTION.getCode(), ResultEnum.EXCEPTION.getMessage());
    }

    public static <T> ResultVO<T> exception(String msg) {
        return buildErrorVO(ResultEnum.EXCEPTION.getCode(), ResultEnum.EXCEPTION.getMessage() + msg);
    }


    public static <T> ResultVO<T> badRequest() {
        return buildErrorVO(ResultEnum.BAD_REQUEST.getCode(), ResultEnum.BAD_REQUEST.getMessage());
    }

    public static <T> ResultVO<T> badRequest(String msg) {
        return buildErrorVO(ResultEnum.BAD_REQUEST.getCode(), msg);
    }

    public static <T> ResultVO<T> unauthorized() {
        return buildErrorVO(ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getMessage());
    }

    public static <T> ResultVO<T> unauthorized(String msg) {
        return buildErrorVO(ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getMessage());
    }

    private static <T> ResultVO<T> buildSuccessVO(T data, String msg) {
        return ResultVO.<T>builder()
                .code(ResultEnum.SUCCESS.getCode())
                .message(msg)
                .data(data)
                .build();
    }

    private static <T> ResultVO<T> buildErrorVO(Integer code, String msg) {
        return ResultVO.<T>builder().code(code).message(msg).data(null).build();
    }
}
