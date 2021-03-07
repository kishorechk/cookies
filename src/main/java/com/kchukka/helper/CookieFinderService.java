package com.kchukka.helper;

import com.kchukka.model.Cookie;

import java.util.List;

public interface CookieFinderService {
    public List<String> getCookie(List<Cookie> cookiesList, String date);
}
