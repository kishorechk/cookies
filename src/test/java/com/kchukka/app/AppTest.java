package com.kchukka.app;

import com.kchukka.configuration.CookieFileConfiguration;
import com.kchukka.service.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.net.URL;
import java.util.List;

public class AppTest extends TestCase {
    @Test
    public void test_getCookie_ValidDate_SingleCookie() {
        //create configuration
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath("cookies.csv");
        cookieFileConfiguration.setDelimiter(",");

        CookieDataService cookieDataService = new FIleCookieDataServiceImpl(cookieFileConfiguration);
        CookieParserService cookieParserService = new CookieParserServiceImpl(cookieFileConfiguration, cookieDataService);
        CookieFinderService cookieFinderService = new CookieFinderServiceImpl();

        App app = new App(cookieFinderService, cookieParserService);
        List<String> result = app.getActiveCookies("2018-12-09");
        assertEquals(1, result.size());
        assertEquals("AtY0laUfhglK3lC7", result.get(0));
    }

    @Test
    public void test_getCookie_ValidDate_MultipleActiveCookies() {
        URL resource = getClass().getClassLoader().getResource("cookies.csv");
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(resource.getPath());
        cookieFileConfiguration.setDelimiter(",");
        CookieDataService cookieDataService = new FIleCookieDataServiceImpl(cookieFileConfiguration);
        CookieParserService cookieParserService = new CookieParserServiceImpl(cookieFileConfiguration, cookieDataService);
        CookieFinderService cookieFinderService = new CookieFinderServiceImpl();

        App app = new App(cookieFinderService, cookieParserService);
        List<String> result = app.getActiveCookies("2018-12-08");
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
        CookieDataService cookieDataService = new FIleCookieDataServiceImpl(cookieFileConfiguration);
        CookieParserService cookieParserService = new CookieParserServiceImpl(cookieFileConfiguration, cookieDataService);
        CookieFinderService cookieFinderService = new CookieFinderServiceImpl();


        App app = new App(cookieFinderService, cookieParserService);
        List<String> result = app.getActiveCookies("2018-12-11");
        assertEquals(0, result.size());
    }
}