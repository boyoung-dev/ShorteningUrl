package com.boyoung.musinsa.test;


import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class TestService {

    final Logger logger = LoggerFactory.getLogger(TestService.class);

    final String host = "http://localhost/";
    final char[] base62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    final Map<String, Integer> urlCount = new HashMap<>();
    final Map<String, String> shortUrlInfo = new HashMap<>();

    public String getShortUrl(String originalUrl) {

        originalUrl = originalUrl.trim();
        Integer asciiCodeSum = getAsciiCodeSum(originalUrl);
        String encode = base62Encode(asciiCodeSum);

        saveInfo(originalUrl, encode);

        return host + encode;
    }

    Integer getAsciiCodeSum(String url) {
        Integer asciiSum = 0;

        for(int i = 0; i < url.length(); i ++) {
            asciiSum += (byte)url.charAt(i);
        }

        return asciiSum;
    }

    String base62Encode(Integer asciiCode) {
        String encode = "";

        do {
            int remainder = asciiCode%62;
            asciiCode /= 62;
            encode += base62[remainder];
        }while(asciiCode > 0);

        return encode;
    }

    void saveInfo(String originalUrl, String shortUrl) {

        Integer count = urlCount.get(originalUrl);
        if(count == null) {
            count = 0;
        }
        urlCount.put(originalUrl, ++count);
        shortUrlInfo.put(shortUrl, originalUrl);

    }

    public HttpHeaders getRedirectHeaders(String shortUrl) throws URISyntaxException {

        String originalUrl = getOriginalUrl(shortUrl);
        URI redirectUri = new URI(originalUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return httpHeaders;
    }

    String getOriginalUrl(String shortUrl) {
        String originalUrl = shortUrlInfo.get(shortUrl);

        if(originalUrl != null) {
            return originalUrl;
        }else {
            throw new NullPointerException("not found original Url");
        }
    }

    public Integer getCallCount(String url) {
        Integer count = urlCount.get(url);

        if(count != null) {
            return count;
        }else {
            return 0;
        }
    }


}
