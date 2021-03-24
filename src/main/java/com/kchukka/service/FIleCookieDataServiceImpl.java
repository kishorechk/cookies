package com.kchukka.service;

import com.kchukka.configuration.CookieFileConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FIleCookieDataServiceImpl implements CookieDataService{
    private static Logger logger = LogManager.getLogger(FIleCookieDataServiceImpl.class);

    private CookieFileConfiguration cookieFileConfiguration;

    public FIleCookieDataServiceImpl(CookieFileConfiguration cookieFileConfiguration) {
        this.cookieFileConfiguration = cookieFileConfiguration;
    }

    @Override
    public List<String> getCookiesList() {
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(cookieFileConfiguration.getFilePath()));
        } catch (IOException e) {
            logger.error("Unable to read the file, please provide correct file path.");
            throw new RuntimeException("Unable to read the file, please provide correct file path.");
        }

        return lines;
    }
}
