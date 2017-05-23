package com.dmd.weixin.app;

import com.dmd.weixin.commons.DefaultDuplicatedMessageChecker;
import com.dmd.weixin.app.base.AppSetting;
import com.dmd.weixin.app.message.AppXmlMessages;
import com.dmd.weixin.common.decrypt.AesException;
import com.dmd.weixin.common.decrypt.MessageDecryption;
import com.dmd.weixin.common.decrypt.SHA1;
import com.dmd.weixin.common.message.XmlMessageHeader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @borball on 12/29/2016.
 */
@RestController
@RequestMapping("/api")
@Api(value = "小程序回调控制器", description = "小程序回调")
public class WxCallbackController {

    private static Logger logger = LoggerFactory.getLogger(WxCallbackController.class);

    @Autowired
    private DefaultDuplicatedMessageChecker duplicatedMessageChecker;

    /**
     * 小程序回调接口
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param msg_signature 签名信息
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @param encrypt_type 加密类型
     * @param content 内容
     * @return
     */
    @GetMapping("/wx/app")
    @ApiOperation(value = "小程序回调接口",notes = "小程序回调接口说明")
    public String mp(@RequestParam(value="signature") String signature,
                     @RequestParam(value="msg_signature", required = false) String msg_signature,
                     @RequestParam(value="timestamp") String timestamp,
                     @RequestParam(value="nonce") String nonce,
                     @RequestParam(value="echostr", required = false) String echostr,
                     @RequestParam(value="encrypt_type", required = false) String encrypt_type,
                     @RequestBody(required = false) String content) {

        logger.info("signature={}, msg_signature={}, timestamp={}, nonce={}, echostr={}, encrypt_type={}", signature, msg_signature, timestamp, nonce, echostr, encrypt_type);

        AppSetting appSetting = AppSetting.defaultSettings();
        try {
            if(!SHA1.getSHA1(appSetting.getToken(), timestamp, nonce).equals(signature)) {
                logger.warn("invalid request.");
                return "invalid request.";
            }
        } catch (AesException e) {
            logger.error("check signature failed:", e);
            return "invalid request.";
        }

        if (!StringUtils.isEmpty(echostr)) {
            return echostr;
        }

        XmlMessageHeader xmlRequest = null;
        if("aes".equals(encrypt_type)) {
            try {
                MessageDecryption messageDecryption = new MessageDecryption(appSetting.getToken(), appSetting.getAesKey(), appSetting.getAppId());
                xmlRequest = AppXmlMessages.fromXml(messageDecryption.decrypt(msg_signature, timestamp, nonce, content));
            } catch (AesException e) {
            }
        } else {
            xmlRequest = AppXmlMessages.fromXml(content);
        }

        dispatch(xmlRequest);

        return "";
    }


    /**
     * 具体业务逻辑
     * @param xmlRequest
     */
    private void dispatch(XmlMessageHeader xmlRequest) {
        if(!duplicatedMessageChecker.isDuplicated(xmlRequest.getFromUser() + xmlRequest.getCreateTime().getTime())) {
            //如果有需要可以调用客服接口或者模板消息接口发送消息给用户
            //Message message = new Message();
            //Templates.defaultTemplates().send(message);

            //CareMessages.defaultCareMessages().text(xmlRequest.getFromUser(), "Hello!");
            //CareMessages.defaultCareMessages().image(xmlRequest.getFromUser(), "image_media_id");
        } else {
            logger.warn("Duplicated message: {} @ {}", xmlRequest.getMsgType(), xmlRequest.getFromUser());
        }

    }

}
