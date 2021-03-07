package com.kchukka.helper;

import com.kchukka.configuration.CookieFileConfiguration;
import com.kchukka.model.Cookie;
import com.kchukka.service.CookieParserService;
import com.kchukka.service.CookieParserServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class CookieFinderTest {
    @Test
    public void test_getCookie_ValidDate_SingleCookie() throws IOException {
        URL resource = getClass().getClassLoader().getResource("cookies.csv");
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(resource.getPath());
        cookieFileConfiguration.setDelimiter(",");
        CookieParserService CookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
        List<Cookie> cookiesList = CookieParserService.getCookiesList();
        List<String> result = new CookieFinder().getCookie(cookiesList, "2018-12-09");
        assertEquals(1, result.size());
        assertEquals("AtY0laUfhglK3lC7", result.get(0));
    }

    @Test
    public void test_getCookie_ValidDate_MultipleCookie() throws IOException {
        URL resource = getClass().getClassLoader().getResource("cookies.csv");
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(resource.getPath());
        cookieFileConfiguration.setDelimiter(",");
        CookieParserService CookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
        List<Cookie> cookiesList = CookieParserService.getCookiesList();
        List<String> result = new CookieFinder().getCookie(cookiesList, "2018-12-08");
        assertEquals(3, result.size());
        assertEquals("fbcn5UAVanZf6UtG", result.get(0));
    }

    @Test
    public void test_getCookie_InValidDate() throws IOException {
        URL resource = getClass().getClassLoader().getResource("cookies.csv");
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(resource.getPath());
        cookieFileConfiguration.setDelimiter(",");
        CookieParserService CookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
        List<Cookie> cookiesList = CookieParserService.getCookiesList();
        List<String> result = new CookieFinder().getCookie(cookiesList, "2019-12-09");
        assertEquals(0, result.size());
    }
}