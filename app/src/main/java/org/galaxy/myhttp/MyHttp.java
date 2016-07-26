package org.galaxy.myhttp;

import android.util.Log;

import org.galaxy.myhttp.utils.ParseUtil;

/**
 * Created by dxl584327830 on 16/7/25.
 */
public class MyHttp {

    public static RequestCreator get(String url) {
        return url(url, RequestCreator.GET);
    }

    public static RequestCreator post(String url) {
        return url(url, RequestCreator.POST);
    }

    public static RequestCreator url(String url) {
        return url(url, RequestCreator.POST);
    }

    public static RequestCreator url(String url, int requestMethod) {

        if (url == null || url.length() == 0) throw new NullPointerException("URL not null");

        RequestCreator paramsCreator = ParseUtil.parseUrl(url);

        paramsCreator.setRequestMethod(requestMethod);

        Log.e("TAG",paramsCreator.toString());

        return paramsCreator;

    }

}
