package indi.harry.music.common;

/**
 * 统一定义响应码
 */
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),
    ERROR(400, "ERROR");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
