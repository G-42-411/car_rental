package com.koko.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 13629
 * @create 2021/3/26 13:57
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private String appId;

    // 商户私钥，您的PKCS8格式RSA2私钥(应用私钥)
    private String merchantPrivateKey;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private String alipayPublicKey;

    // 签名方式
    private String signType;

    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private String gatewayUrl;

    // 字符编码格式
    private String charset;

    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private String notifyUrl;

    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private String returnUrl;
}
