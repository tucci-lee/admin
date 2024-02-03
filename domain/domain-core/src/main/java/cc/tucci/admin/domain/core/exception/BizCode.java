package cc.tucci.admin.domain.core.exception;

/**
 * @author tucci
 */
public enum BizCode implements ErrorCode {

    /**
     * 101xx 参数错误
     */
    PARAMETER_ERROR(10100, "参数错误"),
    PARAMETER_TYPE_ERROR(10101, "参数类型错误"),
    JSON_PARSE_ERROR(10102, "json解析错误"),

    /**
     * 102xx 请求错误
     */
    REQUEST_ERROR(10200, "请求错误"),
    METHOD_NOT_ALLOWED(10201, "请求方法不支持"),
    UNSUPPORTED_MEDIA_TYPE(10202, "不支持的媒体类型"),
    NOT_FOUND(10203, "未找到"),
    FREQUENT_REQUESTS(10290, "请求频繁"),

    /**
     * 103xx 文件上传错误
     */
    FILE_SIZE_LIMIT(10301, "文件大小超过限制"),
    FILE_TYPE_ERROR(10302, "文件类型错误"),
    FILE_UPLOAD_ERROR(10303, "文件上传错误"),


    /**
     * 1111x 用户认证授权错误
     */
    UNAUTHENTICATED(11111, "未认证"),
    UNAUTHORIZED(11112, "未授权"),

    /**
     * 113xx 验证码错误
     */
    CAPTCHA_TYPE_ERROR(11300, "验证码类型错误"),
    IMAGE_CAPTCHA_ERROR(11301, "验证码错误"),
    SMS_CAPTCHA_ERROR(11302, "验证码错误"),
    EMAIL_CAPTCHA_ERROR(11303, "验证码错误"),
    BEHAVITOR_CAPTCHA_ERROR(11304, "验证码错误"),


    /**
     * 20000 系统错误
     */
    SERVER_ERROR(20000, "系统错误"),
    NOT_OPEN(20001, "未开放"),
    SERVICE_LIMIT(21000, "服务器太忙了！限流中"),

    /**
     * 3xxxx 请求第三方服务错误
     */
    REQUEST_THIRD_PARTY_SERVICE_FAIL(30000, "请求第三方服务失败"),
    REQUEST_UACPAY_FAIL(30101, "请求云闪付失败"),
    REQUEST_WEXINPAY_FAIL(30102, "请求微信支付失败"),
    REQUEST_ALIPAY_FAIL(30103, "请求支付宝失败"),
    REQUEST_FS_FAIL(30201, "请求文件系统失败"),
    RQUEST_REAL_SERVICE_FAIL(30301, "请求实名认证服务失败"),


    /**
     * 400xx 账号错误
     */
    ACCOUNT_NOT_EXIST(40001, "账号不存在"),
    ACCOUNT_EXIST(40002, "账号已经存在"),
    ACCOUNT_OR_PASSWORD_ERROR(40003, "账号或密码错误"),
    ACCOUNT_LOCKED(40004, "账号锁定"),
    PASSWORD_ERROR(40005, "密码错误"),
    PROHIBITED_LOGIN(40006, "禁止登录"),
    PHONE_REQUIRED(40007, "手机号不能为空"),
    PHONE_EXIST(40008, "手机号已存在"),
    PHONE_NOT_EXIST(40009, "手机号不存在"),
    SECOND_PASSWORD_ERROR(40010, "安全密码错误"),
    EMAIL_EXIST(40011, "邮箱已存在"),
    EMAIL_NOT_EXIST(40012, "邮箱不存在"),
    /**
     * 41xxx 用户错误
     */
    SEX_ERROR(41001, "性别错误"),
    NOT_REAL(41002, "未实名认证"),
    REAL_ALREADY(41003, "已实名认证"),
    REAL_VERIFY_FAIL(41004, "实名认证失败"),
    REAL_NAME_REQUIRED(41005, "姓名不能为空"),
    ID_CARD_REQUIRED(41006, "身份证不能为空"),

    /**
     * 42xxx 钱包错误
     */
    WALLET_BALANCE_NOT_ENOUGH(42001, "钱包余额不足"),
    RECHARGE_AMOUNT_ERROR(42002, "充值金额错误"),
    WITHDRAW_AMOUNT_ERROR(42003, "提现金额错误"),

    /**
     * 9xxxx 后端业务错误
     */
    PARENT_NOT_EXIST(90001, "父级不存在"),
    LEVEL_ERROR(90002, "层级错误"),
    RES_RELATED(90003, "资源有角色关联，无法操作"),
    RES_NAME_EXIST(90004, "资源名称已经存在"),
    ROLE_NAME_EXIST(90005, "角色名称已经存在"),
    ROLE_RELATED(90006, "角色有用户关联，无法操作"),
    DEPT_NAME_EXIST(90007, "部门名称已经存在"),
    DEPT_RELATED(90008, "部门有用户关联，无法操作"),
    DEPT_HAS_SUB(90009, "部门有下级，无法操作"),
    NOT_OPERATE_ADMIN(90011, "无法操作管理员"),
    CRONTAB_NAME_EXIST(90101, "定时任务名称已经存在"),
    CRONTAB_ADD_ERROR(90102, "定时任务添加错误"),
    CRONTAB_DELETE_ERROR(90103, "定时任务删除错误"),
    CRONTAB_RESUME_ERROR(90104, "定时任务恢复错误"),
    CRONTAB_PAUSE_ERROR(90105, "定时任务暂停错误"),
    CRONTAB_START_ERROR(90106, "定时任务执行失败"),
    NOT_OPERATE(-1, "演示项目，无法操作");


    BizCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    final int code;
    final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
