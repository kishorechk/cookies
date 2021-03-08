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

    @Test
    public void test_getCookieMaWithIncorrectTimestampRecord() throws IOException {
        URL resource = getClass().getClassLoader().getResource("cookies3.csv");
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(resource.getPath());
        cookieFileConfiguration.setDelimiter(",");
        CookieParserService cookieRecordParser = new CookieParserServiceImpl(cookieFileConfiguration);
        List<Cookie> cookiesList =
                cookieRecordParser.getCookiesList();
        assertNotNull(cookiesList);
        assertEquals(8, cookiesList.size());
        Cookie cookie = cookiesList.get(0);
        assertNotNull(cookie);
        assertEquals("AtY0laUfhglK3lC7",cookie.getId());
    }

    public static class CookieFinderServiceTest {
        @Test
        public void test_getCookie_ValidDate_SingleCookie() {
            URL resource = getClass().getClassLoader().getResource("cookies.csv");
            CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
            cookieFileConfiguration.setFilePath(resource.getPath());
            cookieFileConfiguration.setDelimiter(",");
            CookieParserService CookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
            List<Cookie> cookiesList = CookieParserService.getCookiesList();
            CookieFinderService cookieFinderService = new CookieFinderServiceImpl();
            List<String> result = cookieFinderService.getCookiesList(cookiesList, "2018-12-09");
            assertEquals(1, result.size());
            assertEquals("AtY0laUfhglK3lC7", result.get(0));
        }

        @Test
        public void test_getCookie_ValidDate_MultipleActiveCookies() {
            URL resource = getClass().getClassLoader().getResource("cookies.csv");
            CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
            cookieFileConfiguration.setFilePath(resource.getPath());
            cookieFileConfiguration.setDelimiter(",");
            CookieParserService CookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
            List<Cookie> cookiesList = CookieParserService.getCookiesList();
            CookieFinderService cookieFinderService = new CookieFinderServiceImpl();
            List<String> result = cookieFinderService.getCookiesList(cookiesList, "2018-12-08");
            assertEquals(3, result.size());
            assertEquals("fbcn5UAVanZf6UtG", result.get(0));
            assertEquals("SAZuXPGUrfbcn5UA", result.get(1));
            assertEquals("4sMM2LxV07bPJzwf", result.get(2));
        }

        @Test
        public void test_getCookie_InValidDate_EmptyCookiesList() {
            URL resource = getClass().getClassLoader().getResource("cookies.csv");
            CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
            cookieFileConfiguration.setFilePath(resource.getPath());
            cookieFileConfiguration.setDelimiter(",");
            CookieParserService CookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
            List<Cookie> cookiesList = CookieParserService.getCookiesList();
            CookieFinderService cookieFinderService = new CookieFinderServiceImpl();
            List<String> result = cookieFinderService.getCookiesList(cookiesList, "2019-12-09");
            assertEquals(0, result.size());
        }
    }
}