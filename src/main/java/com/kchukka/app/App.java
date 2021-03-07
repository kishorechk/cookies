package com.kchukka.app;

import com.kchukka.configuration.CookieFileConfiguration;
import com.kchukka.helper.CookieFinder;
import com.kchukka.model.Cookie;
import com.kchukka.service.CookieParserService;
import com.kchukka.service.CookieParserServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        if(args.length<2) {
            throw new RuntimeException("Incorrect no. of arguments");
        }
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(args[0]);
        cookieFileConfiguration.setDelimiter(",");
        CookieParserService cookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
        List<Cookie> cookiesList = cookieParserService.getCookiesList();
        List<String> result = new CookieFinder().getCookie(cookiesList, args[1]);
        if(result.isEmpty()) {
            System.out.println("No active cookies found for the given date");
        }
        for(String cookie: result) {
            System.out.println(cookie);
        }
    }
}
