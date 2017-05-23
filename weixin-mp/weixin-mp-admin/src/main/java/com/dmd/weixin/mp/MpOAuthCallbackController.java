package com.dmd.weixin.mp;

import com.dmd.weixin.common.oauth2.AccessToken;
import com.dmd.weixin.common.oauth2.OpenUser;
import com.dmd.weixin.mp.oauth2.MpOAuth2s;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by exizhai on 12/17/2015.
 */
@Controller
@Api(value = "公众号OAuth回调接口", description = "公众号OAuth回调接口")
public class MpOAuthCallbackController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(WxCallbackController.class);

    /**
     * 公众号OAuth回调接口
     * @return
     */
    @GetMapping("/wx/oauth/mp")
    @ApiOperation(value = "公众号OAuth回调接口",notes = "公众号OAuth回调接口")
    public void mp(@RequestParam(value="code") String code, @RequestParam(value="state", required = false) String state) {

        logger.info("code:{}, state:{}", code, state);
        AccessToken accessToken = MpOAuth2s.defaultOAuth2s().getAccessToken(code);
        OpenUser openUser = MpOAuth2s.defaultOAuth2s().userInfo(accessToken.getAccessToken(), accessToken.getOpenId());
        //do your logic
    }
}
