package ru.kpfu.itis.capitalcity.api;

/**
 * Created by ainurminibaev on 21.08.15.
 */
public enum HttpResponse {

    SUCCESS(200, "Success"),
    FAIL(400, "Fail"),
    NOT_FOUND(404, "Not Found");

    private int code;
    private String msg;

    HttpResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
