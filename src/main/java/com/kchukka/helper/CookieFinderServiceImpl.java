package com.kchukka.helper;

import com.kchukka.model.Cookie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Cookie Finder Service Implementation
 *
 */
public class CookieFinderServiceImpl implements CookieFinderService{

    /**
     * returns list of active cookies for given date
     *
     */
    public List<String> getCookiesList(List<Cookie> cookiesList, String date) {
        LocalDate inputDate = LocalDate.parse(date);
        Map<String, Long> cookieCountMap = cookiesList.stream()
                .filter(cookie ->  isValidCookie(cookie, inputDate))
                .collect(Collectors.groupingBy(Cookie::getId, Collectors.counting()));
        List<String> result = new ArrayList<>();
        if(cookieCountMap.values().size()>0) {
            Long maxCount = Collections.max(cookieCountMap.values());

            cookieCountMap.forEach((k, v) -> {
                if (v == maxCount) {
                    result.add(k);
                }
            });
        }
        return result;
    }

    private boolean isValidCookie(Cookie cookie, LocalDate date) {
        return date!=null && cookie.getTimestamp()!=null && date.compareTo(cookie.getTimestamp())==0;
    }
}
