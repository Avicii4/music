package indi.harry.music.common;

/**
 * 统一定义响应码
 */
public enum ResponseCode {

    SUCCESS(200, "请求成功"),

    LOGIN_SUCCESS(600000, "登录成功"),
    USER_UNKNOWN(600001, "用户不存在"),
    WRONG_PASSWORD(600002, "密码输入错误"),
    USER_EXISTS(600003, "用户已存在"),
    REGISTER_SUCCESS(600004, "注册成功"),
    REGISTER_FAIL(600005, "注册失败"),
    CHANGE_PASSWORD_SUCCESS(600006, "密码修改成功"),
    CHANGE_PASSWORD_FAIL(600007, "密码修改失败"),

    NO_QUERY_RESULT(600008, "没有符合的查询结果"),
    QUERY_SUCCESS(600009, "查询成功"),
    ARTIST_NAME_BLANK(600010, "艺人姓名不能为空"),
    ARTIST_NAME_DUPLICATE(600011, "艺人不能重名"),
    ADD_ARTIST_SUCCESS(600012, "新增艺人信息成功"),
    ADD_ARTIST_FAIL(600013, "新增艺人信息失败"),
    ARTIST_UNKNOWN(600014, "艺人信息不存在"),
    ARTIST_CANNOT_DELETE(600015, "尚存在专辑信息的艺人无法删除"),
    ARTIST_DELETE_SUCCESS(600016, "删除艺人信息成功"),
    ARTIST_DELETE_FAIL(600017, "删除艺人信息失败"),
    ARTIST_MODIFY_SUCCESS(600018, "艺人信息修改成功"),
    ARTIST_MODIFY_FAIL(600019, "艺人信息修改失败"),

    ALBUM_ADD_SUCCESS(600050, "专辑信息添加成功"),
    ALBUM_ADD_FAIL(600051, "专辑信息添加失败"),
    ALBUM_NAME_BLANK(600052, "专辑名称不能为空"),
    ALBUM_DELETE_SUCCESS(600053, "专辑信息删除成功"),
    ALBUM_DELETE_FAIL(600054, "专辑信息删除失败"),
    ALBUM_MODIFY_SUCCESS(600055, "专辑信息修改成功"),
    ALBUM_MODIFY_FAIL(600056, "专辑信息修改时发生未知错误"),

    ERROR(400, "未知错误");

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
