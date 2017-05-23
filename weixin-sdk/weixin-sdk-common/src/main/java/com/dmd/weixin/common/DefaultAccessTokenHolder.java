package com.dmd.weixin.common;

/**
 * @borball on 8/14/2016.
 */
public class DefaultAccessTokenHolder extends AccessTokenHolder {

    private AccessToken accessToken;

    /**
     * AccessToken 获取
     * @param tokenUrl 获取地址
     * @param clientId app id
     * @param clientSecret app secret
     */
    public DefaultAccessTokenHolder(String tokenUrl, String clientId, String clientSecret){
        super(tokenUrl, clientId, clientSecret);
    }
    /**
     *  同步获取AccessToken
     */

    @Override
    public synchronized AccessToken getAccessToken() {
        if (accessToken == null || accessToken.expired()) {
            refreshToken();
        }
        return accessToken;
    }

    /**
     * 同步刷新AccessToken
     */
    @Override
    public synchronized void refreshToken() {
        if (accessToken == null || accessToken.expired()) {
            String content = fetchAccessToken();
            AccessToken accessToken = AccessToken.fromJson(content);
            this.accessToken = accessToken;
        }
    }

    /**
     * 强制设置为无效
     */
    @Override
    public void expireToken() {
        accessToken.setExpiresIn(-30);//强制设置为无效
    }


}
