package org.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("https://juejin.cn");
        // 执行请求
        CloseableHttpResponse response = httpclient.execute(httpGet);
        // 获取状态是
        Integer statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        try {
            // 请求体内容
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            // jsoup 解析HTML
            Document doc = Jsoup.parse(content);
            ArrayList itemList = doc.select(".item");
            System.out.println(itemList);
        } finally {
            httpclient.close();
        }
    }
}