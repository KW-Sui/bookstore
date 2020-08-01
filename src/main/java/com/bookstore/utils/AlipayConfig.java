package com.bookstore.utils;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102200738855";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDr/zg6RIUAtCvOiH7TADUmj/lt71kdBtezawOmyVS/Evh+5TgbI/ddFSWLHfZUAfWACIOuCEHKgBgti/8/svbL3CjXYquZAi5QNXVh1mrLjVJvjCJT/4BD/PIc8rCVbjzKUxwR6EcIVkfcyV86L4uxkBFarv6PPQN2JQ+a6PfoAkQUsoeAmqJ852TAIIhN2wQmjSg+BDz4eOirLqYCI2wDG43rm7ZkqlNUJWZHI+mmq5pNEozow396rMgh4bUDk5Gkm4dqAAHx3NGy4XMPPR+7uz/6RJ0KNnTCPjODb92JnUP7vSM1bki20dNFcPpQcQRm7fRR+7D3tn9kLJdtr225AgMBAAECggEAEV0n7/GWr6SSDJIycqfzQTEasyIHNoGIYWUgUadCmwR0UDHhXO3ah3jE2GWN+ERkodT+i1GENbgTD1bbov4x9nHud1qFMa7dZX1pc5dpKCzvwdFXvUJxV49G6SZaI8SGqIRQ7mhewZhHlVEjto5FAv2MqVVy2Wst1AWKDmXRx7ouxjTFwIt7Hcf+pVKZqQuUWA9rsmUENZzbRd7ktOv8vNd8Xct/wUoESI/tJ/O4jPRTQSUR5hmt48B60gEjMsMU0Azs5gRBuM6huJSX76YyuSmt+LydgidwHROmhd/pgKfSumFzNo4MUtOQGUHasJXUFi+neao/ZfFLzepN8+fe4QKBgQD7eZNPY5/ig+YACCXpBySUWFHPxWSTOcvHpMit0Lk7SPnw1Xr1IcahQiMFw/EMGKf+NprVs1tlsvPMd7ZgUIIj5fP0qRfmq1u+/9F80m0hPn2+2UMbg82Irscza7z3JtHPUQLdGe6NCzDE+4yELeuslmx5aRQ0i6ECsZBuwH1DfQKBgQDwPlg9dVyqdRIlrxmTFZd28FP+BAAK3br91z7dVtwIjAmNhCM4kNZyHocCwxKPXtLcrGBoJghu3E1JQbC8Ovaxo84Q85lw5/w2vH7kfhsmVzTX9kpVGdf45YBdjmKOA5ZK0jeWfESmfoI26XvFUmFrI3I1SrXzHxEKfWh0y+Qv7QKBgQC7WjZPvU9O+SmP+IIV/IZaJvq8qrllnnedw7znEkwOSS49KS80FffgCGADWZnqgywVjyUH9VowiIRuXvn3A8QRbq60Jo5yAlSWFg1Cy5sWW3Jfj4oZo7arEFvEiY39LGS/6gESLNG3LxEkkKx9haDBskgapBizlRtd4GBUwwAQGQKBgFPfcC8EqjVYad1+JA+VmiuL+8bS3rDseDvmyau8h1T0juE5IJK+/h8ZSUlNkU08baFdDMtPe+I4R0MsC+erLjUWWvrQEOG6G/OAG0zqOdBI4coD0i+jtiLO/OGss/ughPfaLE39VHpL8Gn7WZqqCBi/Zz/ztVILpodjAPTFugSBAoGALbnQ+kuHdWhv37T1b39QfTL6orjZoDGbiMvYOpmsTFU0ONj7PMCsjMaEmvnsE5E8XwuWLvfa+WSQAV4Eo2DOxWUPfyVepwyERb8X0LESDzpMnNDnDGav9SYGVRCGpRLy3NxoieZzOiarFLV9BcxzHB3FajWhKrmqLYAMtp/ANsA=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqmWr+O7mwrZCtbWLgB7SivClrhB5h+GndL2zuLkRQWwwsWr7BCUXuL3LY979BVweLzDBwvM6123NpJ8oPgiv9caEOVj+UywbsogHXwGOgyDpPEhx38Wa5T6XHTXYRpzbSaVUhi/lCTuheOW76H53PEwzTGH6YeJNiPj2tj8yYmzFLyYjQyV9Q9Qhgjr24lFXEXTdpNU7CqPS5YLBwIPWLezQsQEZhQCElE+EA5J+VhNyzhLGMNk8297FLDX7tS/bezYKcb/WqgU9p6F4wtMw2Q/g2jUHh/WzzJ/KVBypetTF1WdSZKK0sl6J+UwMOiOW7Fxb4O9mwUARMKjf8zzKuQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/bookstore/client/orders/paySuccess";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
