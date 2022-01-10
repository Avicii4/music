package indi.harry.music.common;

import java.io.Serializable;

/**
 * 统一返回服务对象
 * Created by Avicii4 at 2022/1/7.
 */
public class ServerResponseResult<T> implements Serializable {

    // 服务端状态码
    private int status;

    // 返回信息
    private String msg;

    // 返回数据
    private T data;

    private ServerResponseResult(int status) {
        this.status = status;
    }

    private ServerResponseResult(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponseResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponseResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    // 得到响应成功的状态码与信息
    public static <T> ServerResponseResult<T> responseSuccessMessage(ResponseCode code) {
        return new ServerResponseResult<>(code.getCode(), code.getDesc());
    }

    // 得到响应成功的状态码与数据
    public static <T> ServerResponseResult<T> responseSuccess(ResponseCode code, T data) {
        return new ServerResponseResult<>(code.getCode(), data);
    }

    // 得到响应成功的状态码、信息与数据
    public static <T> ServerResponseResult<T> responseSuccess(int status, String msg, T data) {
        return new ServerResponseResult<>(status, msg, data);
    }

    // 得到响应失败的状态码与描述
    public static <T> ServerResponseResult<T> responseError(ResponseCode code) {
        return new ServerResponseResult<>(code.getCode(), code.getDesc());
    }

    // 得到响应失败的状态码与信息
    public static <T> ServerResponseResult<T> responseErrorMessage(ResponseCode code) {
        return new ServerResponseResult<>(code.getCode(), code.getDesc());
    }

}
