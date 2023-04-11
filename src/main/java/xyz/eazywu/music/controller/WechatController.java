package xyz.eazywu.music.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * 微信公众号接口OAuth授权
 */
@Api(tags = "微信公众号接口")
@RestController
@RequestMapping("/wechat")
@RequiredArgsConstructor
public class WechatController {

    private final WxMpService wxMpService;

    @GetMapping("/auth_uri")
    public String getAuthUrl(@PathParam("redirectUri") String redirectUri) {
        return wxMpService.getOAuth2Service().buildAuthorizationUrl(redirectUri, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
    }

    /**
     * TODO ??? 为什么是Post
     */
    @PostMapping("/get_user_info")
    public WxOAuth2UserInfo getUserInfo(@PathParam("code") String code) throws WxErrorException {
        WxOAuth2AccessToken accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
        return wxMpService.getOAuth2Service().getUserInfo(accessToken, null);
    }
}
