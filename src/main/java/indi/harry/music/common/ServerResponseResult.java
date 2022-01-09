package indi.harry.music.common;

import java.io.Serializable;

/**
 * Created by Avicii4 at 2022/1/7.
 * 统一返回服务对象
 */
public class ServerResponseResult<T> implements Serializable {

    // 服务端状态码
    private int status;

    //返回信息
    private String msg;

    //返回数据
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

    // 请求是否成功
    public boolean isSuccessful() {
        return this.status == ResponseCode.SUCCESS.getCode();
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

    // 得到响应成功的状态码
    public static <T> ServerResponseResult<T> responseSuccess() {
        return new ServerResponseResult<>(ResponseCode.SUCCESS.getCode());
    }

    // 得到响应成功的状态码与信息
    public static <T> ServerResponseResult<T> responseSuccessMessage(String msg) {
        return new ServerResponseResult<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    // 得到响应成功的状态码与数据
    public static <T> ServerResponseResult<T> responseSuccess(T data) {
        return new ServerResponseResult<>(ResponseCode.SUCCESS.getCode(), data);
    }

    // 得到响应成功的状态码、信息与数据
    public static <T> ServerResponseResult<T> responseSuccess(String msg, T data) {
        return new ServerResponseResult<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    // 得到响应失败的状态码与描述
    public static <T> ServerResponseResult<T> responseError() {
        return new ServerResponseResult<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    // 得到响应失败的状态码与信息
    public static <T> ServerResponseResult<T> responseErrorMessage(String errorMessage) {
        return new ServerResponseResult<>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    // 得到响应失败的错误信息
    public static <T> ServerResponseResult<T> responseErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponseResult<>(errorCode, errorMessage);
    }

}
