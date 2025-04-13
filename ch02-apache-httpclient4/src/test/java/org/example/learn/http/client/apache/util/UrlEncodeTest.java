package org.example.learn.http.client.apache.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * percent-encoding使用UTF-8获取字符的byte sequence,然后将byte sequence用%+hex表示
 * 默认情况下，application/x-www-form-urlencoded 的字符编码是 UTF-8,参数名/值按照utf-8的code unit序列完成percent-encoding转码
 *
 *
 * key1=value1&key2=中文
 * key1=value1&key2=%E4%B8%AD%E6%96%87
 */
public class UrlEncodeTest {

    @Test
    public void test00() throws Exception {
        // 获取字符的code-point
        System.out.println("codePoint of 中 is " + Integer.toHexString(Character.codePointAt("中", 0)));
        System.out.println("codePoint of 文 is " + Integer.toHexString(Character.codePointAt("文", 0)));
        // 使用code-point表达字符
        String str = "\u4e2d\u6587"; // "中文"
        System.out.println("str = " + str);

        System.out.println("---------------------------------------------");
        // 获取字符code-point的code-unit sequence
        byte[] utf8Bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("Hex.encodeHexString(utf8Bytes) = " + Hex.encodeHexString(utf8Bytes));

        byte[] utf16Bytes = str.getBytes(StandardCharsets.UTF_16);
        System.out.println("Hex.encodeHexString(utf8Bytes) = " + Hex.encodeHexString(utf16Bytes));
        System.out.println("---------------------------------------------");

        // 中
        //UTF-8 code units: E4 B8 AD
        //UTF-16 code units: 4E2D
    }

    @Test
    public void test01() throws Exception {
        String str = "中文";
        String encode = URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        System.out.println("encode = " + encode); // %E4%B8%AD%E6%96%87
    }

    @Test
    public void test10() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("key1", "value1"));
        params.add(new BasicNameValuePair("key2", "中文"));
        String str = URLEncodedUtils.format(params, StandardCharsets.UTF_8);
        System.out.println("str = " + str);
    }
}
