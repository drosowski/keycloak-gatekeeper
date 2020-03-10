package com.example.restservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

@RestController
public class ReverseController {

    @Autowired
    ObjectMapper json;

    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 3000));
    OkHttpClient client = new OkHttpClient.Builder().proxy(proxy).build();

    @GetMapping("/reverse")
    public String reverse(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
        Request request = new Request.Builder()
                .url("http://greetingservice:3000/greeting?name=" + name)
                .build();
        String greeting = client.newCall(request).execute().body().string();
        TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
        };
        Map<String, Object> greetingMap = json.readValue(greeting, typeRef);
        return StringUtils.reverse((String) greetingMap.get("content"));
    }
}
