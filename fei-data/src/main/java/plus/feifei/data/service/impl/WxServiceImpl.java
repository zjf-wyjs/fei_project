package plus.feifei.data.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import plus.feifei.common.utils.RRException;
import plus.feifei.data.service.WxService;

/**
 * @author haha
 */
@Service
public class WxServiceImpl implements WxService {
    @Value("${app.wx.app_id}")
    private String appId;
    @Value("${app.wx.secret}")
    private String secret;
    @Override
    public String getWxOpenId(String code) {
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        HttpRequest httpRequest = HttpRequest.get(url);
        HttpResponse execute = httpRequest.execute();
        String body = execute.body();
        System.out.println(body);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(body);
            String openid = jsonNode.path("openid").asText();
            if(StringUtils.isEmpty(openid)){
                String errmsg = jsonNode.path("errmsg").asText();
                String errcode = jsonNode.path("errcode").asText();
                throw new RRException(errmsg,Integer.parseInt(errcode));
            }
            return openid;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RRException(e.getMessage());
        }
    }
}
