package com.boyoung.musinsa.test;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TestRestController {

    private final TestService testService;

    @PostMapping("/encode/url")
    public ResponseEntity<String> getShortUrl(@RequestParam("url") String url) {
        try {
            String shortUrl = testService.getShortUrl(url);
            return new ResponseEntity<>(shortUrl, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable("shortUrl") String shortUrl) {

        try {
            HttpHeaders httpHeaders = testService.getRedirectHeaders(shortUrl);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count/url")
    public ResponseEntity<Integer> getCallCount(String url) {
        try {
            Integer callCount = testService.getCallCount(url);
            return new ResponseEntity<>(callCount, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
