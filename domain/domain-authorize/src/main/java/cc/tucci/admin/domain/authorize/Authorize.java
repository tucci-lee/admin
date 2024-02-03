package cc.tucci.admin.domain.authorize;

import java.io.Serializable;

/**
 * @author tucci
 */
public class Authorize implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long uid;
    private String token;
    private Long createTime;
    private String deviceId;
    private String deviceModel;
    private String platform;
    private String ip;

    private Authorize() {
    }

    public static Authorize build(Long uid) {
        Authorize authorize = new Authorize();
        authorize.setUid(uid);
        return authorize;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
