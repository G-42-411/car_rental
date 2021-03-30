package com.koko.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.koko.config.AlipayConfig;
import com.koko.util.OrderUtils;
import com.koko.util.ServletTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 13629
 * @create 2021/3/26 14:17
 */
@RestController
public class AlipayController {

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     *
     * @param name 商品名称
     * @param money 订单总价
     */
    @RequestMapping("/alipay")
    public void AliPay(String name,Double money) throws AlipayApiException, IOException {
        HttpServletResponse response = ServletTool.getResponse();
        //生成订单号
        String out_trade_no = OrderUtils.orderNumber();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppId(), alipayConfig.getMerchantPrivateKey(), "json", alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ money +"\","
                + "\"subject\":\""+ name +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result;
        result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println("*********************\n返回结果为："+result);
        response.setContentType("text/html;charset="+alipayConfig.getCharset());
        PrintWriter writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
