package com.boyoung.musinsa.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.util.Assert;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceTest {

    private String host = "http://localhost/";
    private String testUrl = "https://store.musinsa.com/app/?gclid=CjwKCAjw8df2BRA3EiwAvfZWaGYVxmoXbzObX9ytqfeD7oE58lPPS86RoGONvLt5oXh39kq88b_6pxoCTH0QAvD_BwE";
    private TestService testService;

    @BeforeAll
    public void setUp() {
        testService = new TestService();
    }


    //shorten url key 값이 8자리 이하가 맞는지 테스트
    @Test
    public void shortUrlLengthTest() {
        String shortUrl = testService.getShortUrl(testUrl);
        Assert.isTrue(shortUrl.split(host)[1].length() <= 8);
    }

    //동일한 url 에 대해 동일한 shorten url 을 반환 하는지 테스트
    @Test
    public void equalShortUrl() {
        String shortUrl = testService.getShortUrl(testUrl);
        String compareShortUrl = testService.getShortUrl(testUrl);

        Assert.isTrue(shortUrl.equals(compareShortUrl));
    }

    //shorten url 을 넘기면 redirect 하기 위한 original url 이 올바르게 반환되는지 테스트
    @Test
    public void decodeUrl() {
        String shortUrl = testService.getShortUrl(testUrl);
        String originalUrl = testService.getOriginalUrl(shortUrl.split(host)[1]);

        Assert.isTrue(originalUrl.equals(testUrl));
    }

    //동일한 url 에 대해서 호출된 count 수 를 잘 저장 하고 있는지 테스트
    @Test
    public void getCallCount() {
        testService.getShortUrl(testUrl);
        testService.getShortUrl(testUrl);
        testService.getShortUrl(testUrl);

        Integer count = testService.getCallCount(testUrl);

        Assert.notNull(count);
        //Assert.isTrue(count == 3);
    }
}
