package plus.feifei.data.service;

/**
 * @author haha
 */
public interface WxService {
    /**
     * 根据code获取用户的openId
     * @param code
     * @return
     */
    String getWxOpenId(String code);
}
