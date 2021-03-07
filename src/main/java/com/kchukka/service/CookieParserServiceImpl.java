package com.kchukka.service;

import com.kchukka.configuration.CookieFileConfiguration;
import com.kchukka.model.Cookie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Cookie Parser Service Implementation
 *
 */
public class CookieParserServiceImpl implements CookieParserService{
    private CookieFileConfiguration cookieFileConfiguration;

    public CookieParserServiceImpl(CookieFileConfiguration cookieFileConfiguration) {
        this.cookieFileConfiguration = cookieFileConfiguration;
    }

    /**
     * reads the csv file and return list of cookies
     *
     */
    public List<Cookie> getCookiesList() {
        String delimiter = cookieFileConfiguration.getDelimiter();
        List<Cookie> result = new ArrayList<>();

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(cookieFileConfiguration.getFilePath()));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read the file, please provide correct file path.");
        }

        for(int i=1; i<lines.size(); i++) {
            String[] contents = lines.get(i).split(delimiter);
            OffsetDateTime zonedDateTime = OffsetDateTime.parse(contents[1]);
            LocalDate localDate = zonedDateTime.toLocalDate();
            Cookie cookie = new Cookie(contents[0], localDate);
            result.add(cookie);
        }
        return result;
    }
}
