package com.example.app.utility;

/**
 * Created by saeki on 2016/07/15.
 */
public class TLUtility {

    public String prevUrl(int num) {
        return "/page/" + (num > 1 ? num - 1 : 1);
    }

    public String nextUrl(int num) {
        return "/page/" + (num + 1);
    }
}
