package com.kchukka.app;

import com.kchukka.configuration.CookieFileConfiguration;
import com.kchukka.helper.CookieFinderService;
import com.kchukka.helper.CookieFinderServiceImpl;
import com.kchukka.model.Cookie;
import com.kchukka.service.CookieParserService;
import com.kchukka.service.CookieParserServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * Main class
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        if(args.length<2) {
            throw new RuntimeException("Incorrect no. of arguments");
        }
        //1. create configuration
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(args[0]);
        cookieFileConfiguration.setDelimiter(",");
        //2. parse the csv file
        CookieParserService cookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
        List<Cookie> cookiesList = cookieParserService.getCookiesList();
        //3. find active cookies for given date
        CookieFinderService cookieFinderService = new CookieFinderServiceImpl();
        List<String> result = cookieFinderService.getCookiesList(cookiesList, args[1]);
        if(result.isEmpty()) {
            System.out.println("No active cookies found for the given date");
        }
        for(String cookie: result) {
            System.out.println(cookie);
        }
    }
}
