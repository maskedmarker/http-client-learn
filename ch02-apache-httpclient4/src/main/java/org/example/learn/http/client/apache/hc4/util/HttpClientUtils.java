package org.example.learn.http.client.apache.hc4.util;

public class HttpClientUtils {

    /**
     * httpclient的日志初始化可以通过jvm启动时添加系统参数,也可以启动后设置
     * 打印日志的系统参数
     * -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog
     * -Dorg.apache.commons.logging.simplelog.showdatetime=true
     * -Dorg.apache.commons.logging.simplelog.log.org.apache.http=DEBUG
     * -Dorg.apache.commons.logging.simplelog.log.org.apache.http.wire=ERROR
     */
    public static void initLog() {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "INFO");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "DEBUG");
    }
}
