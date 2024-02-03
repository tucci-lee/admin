package cc.tucci.admin.domain.core.dto;

import lombok.Data;

/**
 * @author tucci
 */
@Data
public class BasicQuery {

    private String appid;
    private String version;
    private String deviceId;
    private String deviceModel;
    private String platform;

    public interface Platform {
        String APP = "app";
        String WEB = "web";
        String MP_WEIXIN = "mp-weixin";
    }
}
