package org.example.learn.http.client.apache.spec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.net.PercentCodec;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * http是本文协议,header都是字符串
 * 即使header乱码也是乱码后的字符串,只要只要编码规则就能正确还原
 */
public class Ch01HttpHeaderTest {

    @Test
    public void test0() throws Exception {
        String url = "http://localhost:8080/myapp/body/response/empty";
        HttpPost httpPost = new HttpPost(url);

        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpPost)) {

            Header[] allHeaders = response.getAllHeaders();
            Arrays.stream(allHeaders).forEach(header -> {
                try {
                    String name = header.getName();
                    System.out.println("name = " + name);
                    String rawValue = header.getValue();
                    System.out.println("rawValue = " + rawValue);
                    if (rawValue.contains("%")) {
                        byte[] rawBytes = new PercentCodec().decode(rawValue.getBytes(StandardCharsets.UTF_8));
                        System.out.println("rawBytes = " + Hex.encodeHexString(rawBytes));
                        String value = new String(rawBytes, StandardCharsets.UTF_8);
                        System.out.println("value = " + value);
                    }
                    System.out.println("---------------------------");
                } catch (DecoderException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
