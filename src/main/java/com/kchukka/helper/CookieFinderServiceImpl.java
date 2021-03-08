package com.kchukka.helper;

import com.kchukka.model.Cookie;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.time.LocalDate;
import java.util.*;

/**
 * Cookie Finder Service Implementation
 *
 */
public class CookieFinderServiceImpl implements CookieFinderService{
    private static Logger logger = LogManager.getLogger(CookieFinderServiceImpl.class);
    /**
     * return the most active cookies for a specific day
     *
     */
    public List<String> getCookiesList(List<Cookie> cookiesList, String date) {
        LocalDate inputDate = LocalDate.parse(date);
        Map<String, Long> cookieCountMap = getCookieCountMap(cookiesList, inputDate);
        List<String> result = getMaxCountCookies(cookieCountMap);
        return result;
    }

    private Map<String, Long> getCookieCountMap(List<Cookie> cookiesList, LocalDate date) {
        int left = 0, right=cookiesList.size()-1;
        int matchIndex = -1;
        boolean foundMatch = false;
        Map<String, Long> cookieCountMap = new HashMap<>();
        //binary search
        while(left<right) {
            int mid = left+(right-left)/2;
            Cookie cookie = cookiesList.get(mid);
            if(isValidCookie(cookie, date)) {
                //found match
                cookieCountMap.put(cookie.getId(), 1l);
                foundMatch = true;
                matchIndex = mid;
                break;
            } else if(date.compareTo(cookie.getTimestamp())>0) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        left = matchIndex-1;
        right = matchIndex+1;
        if(foundMatch) {
            logger.debug("Found matching cookies for given date");
            //check left & right neighbours from mid
            while(left>=0 && isValidCookie(cookiesList.get(left), date)) {
                Long count = cookieCountMap.getOrDefault(cookiesList.get(left).getId(), 0l);
                cookieCountMap.put(cookiesList.get(left).getId(), ++count);
                left--;
            }
            while(right<cookiesList.size() && isValidCookie(cookiesList.get(right), date)) {
                Long count = cookieCountMap.getOrDefault(cookiesList.get(right).getId(), 0l);
                cookieCountMap.put(cookiesList.get(right).getId(), ++count);
                right++;
            }
        } else {
            logger.debug("Found no matching cookie for given date");
        }

        return cookieCountMap;
    }


    private boolean isValidCookie(Cookie cookie, LocalDate date) {
        return date!=null && cookie.getTimestamp()!=null && date.compareTo(cookie.getTimestamp())==0;
    }

    private List<String> getMaxCountCookies(Map<String, Long> cookieCountMap) {
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
}
