package com.kchukka.service;

import com.kchukka.model.Cookie;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

public interface CookieParserService {
    List<Cookie> getCookiesList();

}
