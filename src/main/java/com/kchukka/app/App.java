package com.kchukka.app;

import com.kchukka.configuration.CookieFileConfiguration;
import com.kchukka.model.Cookie;
import com.kchukka.service.CookieFinderService;
import com.kchukka.service.CookieFinderServiceImpl;
import com.kchukka.service.CookieParserService;
import com.kchukka.service.CookieParserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Main class
 *
 */
public class App 
{
    private static Logger logger = LogManager.getLogger(CookieFinderServiceImpl.class);

    private CookieFinderService cookieFinderService;
    private CookieParserService cookieParserService;

    public App(CookieFinderService cookieFinderService, CookieParserService cookieParserService) {
        this.cookieFinderService = cookieFinderService;
        this.cookieParserService = cookieParserService;
    }

    public List<String> getActiveCookies(String date) {
        //1. parse the csv file
        List<Cookie> cookiesList = cookieParserService.getCookiesList();
        //2. find active cookies for given date
        List<String> result = cookieFinderService.getCookiesList(cookiesList, date);
        return result;
    }

    public static void main( String[] args ) throws IOException {
        if(args.length<2) {
            throw new RuntimeException("Incorrect no. of arguments");
        }
        logger.debug("File: " + args[0]);
        logger.debug("Date: " + args[1]);
        //create configuration
        CookieFileConfiguration cookieFileConfiguration = new CookieFileConfiguration();
        cookieFileConfiguration.setFilePath(args[0]);
        cookieFileConfiguration.setDelimiter(",");

        CookieParserService cookieParserService = new CookieParserServiceImpl(cookieFileConfiguration);
        CookieFinderService cookieFinderService = new CookieFinderServiceImpl();

        App app = new App(cookieFinderService, cookieParserService);
        List<String> result = app.getActiveCookies(args[1]);
        if(result.isEmpty()) {
            logger.debug("No active cookies found for the given date");
            System.out.println("No active cookies found for the given date");
        }
        for(String cookie: result) {
            System.out.println(cookie);
        }
    }
}
