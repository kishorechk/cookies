package com.kchukka.service;

import com.kchukka.model.Cookie;

import java.util.List;

public interface CookieFinderService {
    List<String> getCookiesList(List<Cookie> cookiesList, String date);
}
