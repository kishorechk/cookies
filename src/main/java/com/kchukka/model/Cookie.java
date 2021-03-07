package com.kchukka.model;

import java.time.LocalDate;

public class Cookie {
    private String id;

    private LocalDate timestamp;

    public Cookie(String id, LocalDate timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

}
