package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenIotvspLogicgroupidQueryRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayOpenIotvspLogicgroupidQueryResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.example.demo.controller.model.AliFaceModel;
import com.example.demo.controller.model.AliFaceResultModel;
import com.example.demo.controller.model.ApiRequest;
import com.example.demo.controller.model.Score;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author limian
 * @time 2023-11-16
 **/
@RestController
public class HelloWorldController {
    @RequestMapping("/aliFace")
    public String Hello() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                "2021004124674424",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEa+Sl1nLbnpLEw2++tvSPwMqJ18CUcOlcvhbX7YQVreTPQf2F/6u7uvwqCFZVOgZAbTVxdxgs3rWbfyv+s2IKOAZ7rX/qKs0jYca7HvlFeY1zZ5HJmpFhBDh0wuQFf8niHrrjiVLt1EM4bVbrx+Od1zZiTbNNMoL8HsC/WIV+fvDdCB7hfbdZgWR7euTTg6J1M14Jyy/y3j/idcD0UUKsm//8qdTNq/tLPxJAztoon5EuiGlm23XRw1iQ/6IR+LTpf4O9Hry1Y4SxKmMedk872wxA2YwO3GTVFFugxyhgPiy62KZD5sZBjmn0LpKA3ynJzMojJurfNskNwHT1ZbevAgMBAAECggEAOYqguqdrFZ/cav/5yMe7kdPWrvs/xcEixpgbWQzuvnTn3l/wiI/G0ja4aJNi4HnsSYarGHkF2kmUZNNoDK9qRMJ/nrKPyPN8XImaT6lv7A6btrAkm5Q85Alvdt1RNEqJMWblEwaTMKFx7E0m3iHn+KiSCzl0VnFClcfPPdZ7e7GQnyVYA6v/Kpy4T6NiFALilmE3vCBa6rcqWv9ffCoAGIZwHLfZtnK7Ua4LdMkmjkUXoAK4a2EbHeFqH36AqZNr1hXcrzLQaDrqu435MwHIROvfW2pmvd4wh0Eb/b0/ctt2to8sAIap0ocDcZWTQzQtHEsGHfKnFvE1MhGzsuxKYQKBgQDMHDjDppMgexyBuywGCWARzA5BmL3PX/pI2vgiylUod5gVFjq7NurzTEw5BuHIKwaN5rbsUJo7rutb6wnq/nDgUs2hFkNkg0UFFeOAUiJBCayKARBtF3HEF2kl2rUbSCPqVQnmHYN1mUXFtRK7HH57vM9CMiHMVwcrNR5OjaNM5wKBgQCmFhDTHx79bqgsDCnRi3AvcCLPDH7+av6r+/yZAhzJFQIdLSmyrPQu2Cf3NRovWu3ECgpDA7kRupkPpMkbvSRJglh4TDZWtk4mzhi6Q+Djy7OIBtlWH3udQgzFDpqSR7PEIecbV1H6+kuk8TvDm8HjbPEj06vz/2/IGlZ6uGBd+QKBgQCXGxWTEtC9LSdPi8ftuCcdRdoQCgoLalyabzFgYDZnPRKkGc7lJqUN9aBAX63rSJ8Tkog9rvZf1QF9DSZMiI38JJEC/r5pvRztxOSl7ShaydSoXl1Qxhh9OW+ojJ+jUOb15nrrl1HpT81trr3tNA0XghJWSVLoOk2eWTbUpNsOZQKBgDWwElY8zCa+W4yJjgcXnPcot4fYkUCq05JCzZ+kaCu0J8/Ipp8Ee5ac9yZZOxKtwQhK+pA5Jt0NRach7IST+qPsh66zbClRmye+HY4wnQTl9y30kNLfMHVgQYJ9uweoN/shvnQ/INgRitxRwrDzq5RZxgV/yikAnGDB6syWx0f5AoGAQ9QZvUZvY7krxtqDoqhlIgGxr/tsYOQeGtjG4eEgBFVkHlndocT4ofs3uiKg9t7pGVAf31o13t9rHh8ckVb36his56zC7jzO6KEaVTjmr4z0AMDwoQTap6SfJe4YVNLjYrZQWKGVXoDVUezNroRKfuSYbyXB8S/EvChC959Fn+U=",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg2d69qTxFgTNrJVxRoL/9DPIPFUomuRDZkulnNm1z5JmTe41CAHLT4s7KLCpqAUOxCvmqe8IxKK8Kj5rNnc56u7Afp7wIAeFpeDeH4RDxCu3A+CIUqDY08lHVo77lKpttJIT57BFN8CxKtv31oBwdM09sFxjQ6r3oGd+rmEVwbsjja2ty5MhZy8q4v5aMaORgpHZ6H2MCa2iTFMOkVvF2XBBvfCmbYLmBL3DiHc0OpLnn22LVMthSwOZ1VTOgreD6uKE/VIdS7Zd3mq8HKLpDfKyGTbg8hQ+h5Ll1pUIlSvJFDDddLfb3SeY8G2/Gcy1EypcqXT+6f5cdVkK+CkrrwIDAQAB",
                "RSA2");
        AlipayOpenIotvspLogicgroupidQueryRequest request = new AlipayOpenIotvspLogicgroupidQueryRequest();
        request.setBizContent("{" +
                "  \"isv_pid\":\"2088541763045760\"," +
                "  \"org_out_id\":\"91370783MA3CB89G3G\"" +
                "}");
        AlipayOpenIotvspLogicgroupidQueryResponse response = alipayClient.execute(request);
        System.out.println(JSONObject.toJSONString(alipayClient));
        System.out.println(JSONObject.toJSONString(request));
        if (response.isSuccess()) {
            System.out.println("调用成功");
            return JSONObject.toJSONString(response);
        } else {
            System.out.println("调用失败");
            return JSONObject.toJSONString(response);
        }
    }

    @RequestMapping("/aliScanFace")
    public JSONObject ScanFace(@RequestBody ApiRequest<AliFaceModel> apiRequest) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                "2021004124674424",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEa+Sl1nLbnpLEw2++tvSPwMqJ18CUcOlcvhbX7YQVreTPQf2F/6u7uvwqCFZVOgZAbTVxdxgs3rWbfyv+s2IKOAZ7rX/qKs0jYca7HvlFeY1zZ5HJmpFhBDh0wuQFf8niHrrjiVLt1EM4bVbrx+Od1zZiTbNNMoL8HsC/WIV+fvDdCB7hfbdZgWR7euTTg6J1M14Jyy/y3j/idcD0UUKsm//8qdTNq/tLPxJAztoon5EuiGlm23XRw1iQ/6IR+LTpf4O9Hry1Y4SxKmMedk872wxA2YwO3GTVFFugxyhgPiy62KZD5sZBjmn0LpKA3ynJzMojJurfNskNwHT1ZbevAgMBAAECggEAOYqguqdrFZ/cav/5yMe7kdPWrvs/xcEixpgbWQzuvnTn3l/wiI/G0ja4aJNi4HnsSYarGHkF2kmUZNNoDK9qRMJ/nrKPyPN8XImaT6lv7A6btrAkm5Q85Alvdt1RNEqJMWblEwaTMKFx7E0m3iHn+KiSCzl0VnFClcfPPdZ7e7GQnyVYA6v/Kpy4T6NiFALilmE3vCBa6rcqWv9ffCoAGIZwHLfZtnK7Ua4LdMkmjkUXoAK4a2EbHeFqH36AqZNr1hXcrzLQaDrqu435MwHIROvfW2pmvd4wh0Eb/b0/ctt2to8sAIap0ocDcZWTQzQtHEsGHfKnFvE1MhGzsuxKYQKBgQDMHDjDppMgexyBuywGCWARzA5BmL3PX/pI2vgiylUod5gVFjq7NurzTEw5BuHIKwaN5rbsUJo7rutb6wnq/nDgUs2hFkNkg0UFFeOAUiJBCayKARBtF3HEF2kl2rUbSCPqVQnmHYN1mUXFtRK7HH57vM9CMiHMVwcrNR5OjaNM5wKBgQCmFhDTHx79bqgsDCnRi3AvcCLPDH7+av6r+/yZAhzJFQIdLSmyrPQu2Cf3NRovWu3ECgpDA7kRupkPpMkbvSRJglh4TDZWtk4mzhi6Q+Djy7OIBtlWH3udQgzFDpqSR7PEIecbV1H6+kuk8TvDm8HjbPEj06vz/2/IGlZ6uGBd+QKBgQCXGxWTEtC9LSdPi8ftuCcdRdoQCgoLalyabzFgYDZnPRKkGc7lJqUN9aBAX63rSJ8Tkog9rvZf1QF9DSZMiI38JJEC/r5pvRztxOSl7ShaydSoXl1Qxhh9OW+ojJ+jUOb15nrrl1HpT81trr3tNA0XghJWSVLoOk2eWTbUpNsOZQKBgDWwElY8zCa+W4yJjgcXnPcot4fYkUCq05JCzZ+kaCu0J8/Ipp8Ee5ac9yZZOxKtwQhK+pA5Jt0NRach7IST+qPsh66zbClRmye+HY4wnQTl9y30kNLfMHVgQYJ9uweoN/shvnQ/INgRitxRwrDzq5RZxgV/yikAnGDB6syWx0f5AoGAQ9QZvUZvY7krxtqDoqhlIgGxr/tsYOQeGtjG4eEgBFVkHlndocT4ofs3uiKg9t7pGVAf31o13t9rHh8ckVb36his56zC7jzO6KEaVTjmr4z0AMDwoQTap6SfJe4YVNLjYrZQWKGVXoDVUezNroRKfuSYbyXB8S/EvChC959Fn+U=",
                "json",
                "GBK",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg2d69qTxFgTNrJVxRoL/9DPIPFUomuRDZkulnNm1z5JmTe41CAHLT4s7KLCpqAUOxCvmqe8IxKK8Kj5rNnc56u7Afp7wIAeFpeDeH4RDxCu3A+CIUqDY08lHVo77lKpttJIT57BFN8CxKtv31oBwdM09sFxjQ6r3oGd+rmEVwbsjja2ty5MhZy8q4v5aMaORgpHZ6H2MCa2iTFMOkVvF2XBBvfCmbYLmBL3DiHc0OpLnn22LVMthSwOZ1VTOgreD6uKE/VIdS7Zd3mq8HKLpDfKyGTbg8hQ+h5Ll1pUIlSvJFDDddLfb3SeY8G2/Gcy1EypcqXT+6f5cdVkK+CkrrwIDAQAB",
                "RSA2");
        AlipayTradePayRequest request = new AlipayTradePayRequest();
//异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl("");

/******必传参数******/
        JSONObject bizContent = new JSONObject();
//商户订单号，商家自定义，保持唯一性
        bizContent.put("out_trade_no", apiRequest.data.out_trade_no);
//支付金额，最小值0.01元
        bizContent.put("total_amount", changeF2Y(apiRequest.data.total_amount));
//订单标题，不可使用特殊符号
        bizContent.put("subject", "团餐商品");
//1.条码场景：bar_code；2.刷脸场景：security_code
        bizContent.put("scene", "security_code");
//根据auth_code_type上传付款码，auth_code_type=bar_code，则填写用户支付宝钱包中的付款码；auth_code_type=security_code，则填写设备刷脸返回的付款码；
        bizContent.put("auth_code", apiRequest.data.auth_code);
        //商户收单id
        bizContent.put("seller_id","2088041545270152");

/******可选参数******/
//当面付场景下，当面付快捷版则传 OFFLINE_PAYMENT;其它支付宝当面付产品传 FACE_TO_FACE_PAYMENT；不传则默认使用FACE_TO_FACE_PAYMENT。
//bizContent.put("product_code", "FACE_TO_FACE_PAYMENT");
//// 商品明细信息
//JSONArray goodsDetail = new JSONArray();
//JSONObject goods1 = new JSONObject();
//goods1.put("goods_id", "goodsNo1");
//goods1.put("goods_name", "子商品1");
//goods1.put("quantity", 1);
//goods1.put("price", 0.01);
//goodsDetail.add(goods1);
//bizContent.put("goods_detail", goodsDetail);

//// 扩展信息
//JSONObject extendParams = new JSONObject();
//extendParams.put("sys_service_provider_id", "2088511833207846");
//bizContent.put("extend_params", extendParams);

//// 返回参数选项
//JSONArray queryOptions = new JSONArray();
//queryOptions.add("fund_bill_list");
//queryOptions.add("voucher_detail_list");
//bizContent.put("query_options", queryOptions);

        request.setBizContent(bizContent.toString());
        AlipayTradePayResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
            AliFaceResultModel scanFaceJson = new AliFaceResultModel();
            scanFaceJson.amount = response.getReceiptAmount();
            JSONObject isvResponse = new JSONObject();
            isvResponse.put("data",scanFaceJson);
            isvResponse.put("code","1");
            isvResponse.put("message",response.getMsg());
            return isvResponse;
        } else {
            System.out.println("调用失败");
            JSONObject isvResponse = new JSONObject();
            isvResponse.put("code","0");
            isvResponse.put("message",response.getMsg());
            return isvResponse;
        }
    }


    /**
     * 分转元
     *
     * @param amount 金额（分）
     * @return 金额（元，两位小数）
     */
    public static String changeF2Y(String amount) {
        if (StringUtils.isEmpty(amount)) {
            return "0.00";
        }
        BigDecimal yuan = (new BigDecimal(amount)).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
        return yuan.toString();
    }

}
