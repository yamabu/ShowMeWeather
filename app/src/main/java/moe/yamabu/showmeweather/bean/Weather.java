package moe.yamabu.showmeweather.bean;

import java.util.ArrayList;

public class Weather {
    public String resultcode;
    public String reason;
    public ArrayList<Result> result = new ArrayList<Result>();
    public int errorCode;

    public static class Result {
        public String wid;
        public String weather;

    }
}
