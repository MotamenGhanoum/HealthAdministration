package com.sigma.it.health.exceptionHandling;

public class userErrorResponse {

    private int statue;
    private String message;
    private long time;

    public userErrorResponse() {
    }

    public userErrorResponse(int statue, String message, long time) {
        this.statue = statue;
        this.message = message;
        this.time = time;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
