package com.kchukka.service;

import com.kchukka.configuration.CookieFileConfiguration;
import com.kchukka.model.Cookie;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class CookieParserServiceTest {
    @Test
    public void test_getCookiesList() throws IOException {
        URL resource = getClass().getClassLoader().getResource("cookies.csv");
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(resource.getPath());
        cookieFileConfiguration.setDelimiter(",");
        CookieParserService cookieRecordParser = new CookieParserServiceImpl(cookieFileConfiguration);
        List<Cookie> cookiesList = cookieRecordParser.getCookiesList();
        assertNotNull(cookiesList);
        assertEquals(9, cookiesList.size());
        Cookie cookie = cookiesList.get(0);
        assertNotNull(cookie);
        assertEquals("AtY0laUfhglK3lC7",cookie.getId());
    }

    @Test(expected = RuntimeException.class)
    public void test_getCookieMapExceptionThrown() throws IOException {
            CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
            cookieFileConfiguration.setFilePath("cookies2.csv");
            cookieFileConfiguration.setDelimiter(",");
            CookieParserService cookieRecordParser = new CookieParserServiceImpl(cookieFileConfiguration);
            cookieRecordParser.getCookiesList();
    }
}