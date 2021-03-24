package com.kchukka.service;

import com.kchukka.configuration.CookieFileConfiguration;
import com.kchukka.model.Cookie;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cookie Parser Service Implementation
 *
 */
public class CookieParserServiceImpl implements CookieParserService{
    private static Logger logger = LogManager.getLogger(CookieParserServiceImpl.class);

    private CookieFileConfiguration cookieFileConfiguration;

    private CookieDataService cookieDataService;

    public CookieParserServiceImpl(CookieFileConfiguration cookieFileConfiguration, CookieDataService cookieDataService) {
        this.cookieFileConfiguration = cookieFileConfiguration;
        this.cookieDataService = cookieDataService;
    }

    /**
     * reads the csv file and return list of cookies
     *
     */
    public List<Cookie> getCookiesList() {
        String delimiter = cookieFileConfiguration.getDelimiter();
        List<Cookie> result = new ArrayList<>();

        List<String> lines =  cookieDataService.getCookiesList();
        /*List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(cookieFileConfiguration.getFilePath()));
        } catch (IOException e) {
            logger.error("Unable to read the file, please provide correct file path.");
            throw new RuntimeException("Unable to read the file, please provide correct file path.");
        }*/

        for(int i=1; i<lines.size(); i++) {
            String[] contents = lines.get(i).split(delimiter);
            try {
                OffsetDateTime zonedDateTime = OffsetDateTime.parse(contents[1]);
                LocalDate localDate = zonedDateTime.toLocalDate();
                Cookie cookie = new Cookie(contents[0], localDate);
                result.add(cookie);
            }
            catch (DateTimeParseException parseException) {
                logger.error("Unable to parse cookie line, ignoring the line");
            }
        }
        return result;
    }
}
