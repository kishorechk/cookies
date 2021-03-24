package com.kchukka.service;

import com.kchukka.configuration.CookieFileConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class DirectoryCookieDataServiceImpl implements CookieDataService{
    private static Logger logger = LogManager.getLogger(DirectoryCookieDataServiceImpl.class);

    private CookieFileConfiguration cookieFileConfiguration;

    public DirectoryCookieDataServiceImpl(CookieFileConfiguration cookieFileConfiguration) {
        this.cookieFileConfiguration = cookieFileConfiguration;
    }

    @Override
    public List<String> getCookiesList() {
        List<String> lines = new ArrayList<>();

        final File folder = new File(cookieFileConfiguration.getFilePath());
        File[] files = listFilesForFolder(folder);

        for(File file:files) {
            List<String> cookiesList = getCookieData(file);
            lines.addAll(cookiesList);
        }
        Collections.sort(lines);

        return lines;
    }

    private File[] listFilesForFolder(final File folder) {
        return folder.listFiles();
    }

    private List<String> getCookieData(File file) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            logger.error("Unable to read the file, please provide correct file path.");
            throw new RuntimeException("Unable to read the file, please provide correct file path.");
        }
        return lines;
    }
}
