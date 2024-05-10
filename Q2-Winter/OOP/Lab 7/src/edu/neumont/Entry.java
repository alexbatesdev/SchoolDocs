package edu.neumont;

import java.util.Date;

public class Entry {
    private Date date;
    private String log;

    public Entry(Date date, String log) {
        this.date = date;
        this.log = log;
    }

    public Date getDate() {
        return date;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
