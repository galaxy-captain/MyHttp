package org.galaxy.myhttp.utils;

import org.galaxy.myhttp.RequestCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dxl584327830 on 16/7/25.
 */
public class ParseUtil {

    public static RequestCreator parseUrl(String url) {

        if (url.startsWith("http://")) {
            url = url.substring(7);
        } else if (url.startsWith("https://")) {
            url = url.substring(8);
        }

        RequestCreator.Builder builder = new RequestCreator.Builder();

        int endHost = paresHost(builder, url);
        int endPath = parsePath(builder, url, endHost);
        parseParam(builder, url, endPath);

        return builder.build();
    }

    /**
     * 解析Host
     *
     * @param builder
     * @param url
     * @return
     */
    public static int paresHost(RequestCreator.Builder builder, String url) {

        int endHost = url.indexOf("/");                 //
        if (endHost == -1) endHost = url.indexOf("?");  //
        if (endHost == -1) endHost = url.length();      //

        String host = url.substring(0, endHost);

        builder.host(host);

        return endHost;
    }

    /**
     * 解析Path
     *
     * @param builder
     * @param url
     * @param endHost
     * @return
     */
    public static int parsePath(RequestCreator.Builder builder, String url, int endHost) {
        int endPath = url.indexOf("?");
        if (endPath == -1) endPath = url.indexOf("/");
        if (endPath == -1) endPath = url.length();

        String path = url.substring(endHost, endPath);

        if (path == null || path.length() == 0) path = "/";

        builder.path(path);

        return endPath;
    }

    /**
     * 解析Param
     *
     * @param builder
     * @param url
     * @param endPath
     */
    public static void parseParam(RequestCreator.Builder builder, String url, int endPath) {

        String strParam = url.substring(endPath);

        if (strParam.length() > 0) strParam = strParam.substring(1);

        RequestCreator.ParamMap paramMap = parseParam(strParam);

        builder.params(paramMap);

    }

    public static RequestCreator.ParamMap parseParam(String str) {

        // 无参数
        if (str == null || str.length() == 0) return new RequestCreator.ParamMap();

        Map<String, String> params = new HashMap<>();

        String[] kvs = str.split("&");

        // 一个参数
        if (kvs.length == 0) {
            String[] keyVal = str.split("=");
            addParamToMap(params, keyVal[0], keyVal[1]);
        }

        // 两个及两个以上参数
        else if (kvs.length > 0) {
            for (String kv : kvs) {
                String[] keyVal = kv.split("=");
                if (keyVal != null && keyVal.length == 2)
                    addParamToMap(params, keyVal[0], keyVal[1]);
            }
        }

        return new RequestCreator.ParamMap(params);

    }

    public static void addParamToMap(Map<String, String> map, String key, String val) {
        if (key == null || key.length() == 0)
            throw new NullPointerException("url params has empty key");
        if (val == null || val.length() == 0)
            throw new NullPointerException("url params has empty value");
        map.put(key.trim(), val.trim());
    }

}
