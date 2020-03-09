package com.example.restservice;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.OkHttpClient;
import okhttp3.Request;

@RestController
public class ReverseController {

    OkHttpClient client = new OkHttpClient();

    @GetMapping("/reverse")
    public String reverse(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
        Request request = new Request.Builder()
                .url("http://greetingservice.greetingservice.svc:30080/greeting?name=" + name)
                .build();
        String greeting = client.newCall(request).execute().body().string();
        return StringUtils.reverse(greeting);
    }
}
