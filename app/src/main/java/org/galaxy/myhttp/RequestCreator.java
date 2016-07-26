package org.galaxy.myhttp;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dxl584327830 on 16/7/25.
 */
public class RequestCreator {

    @Override
    public String toString() {
        return "RequestCreator{" +
                "mHost='" + mHost + '\'' +
                ", mPath='" + mPath + '\'' +
                ", mParams=" + mParams.toString() +
                '}';
    }

    public static final int POST = 1;

    public static final int GET = 2;

    /**
     * HTTP请求的方法
     */
    int mRequestMethod = POST;

    /**
     * HTTP请求的URL
     */
    String mUrl;

    /**
     * HTTP请求的主机地址
     */
    String mHost;

    /**
     * HTTP请求的访问路径
     */
    String mPath;

    /**
     * HTTP请求的访问参数
     */
    ParamMap mParams;

    public RequestCreator() {

    }

    public RequestCreator(Builder builder) {
        mHost = builder.host;
        mPath = builder.path;
        mParams = builder.params;
    }

    public void setRequestMethod(int requestMethod) {
        this.mRequestMethod = requestMethod;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public void setHost(String host) {
        this.mHost = host;
    }

    public String getParam() {

        String path;

        path = mPath.replace("?", "");

        if (path.startsWith("&")) path = path.substring(1);

        return path;
    }

    /**
     * 添加单个参数
     *
     * @param param 例如：author = dxl584327830
     * @return
     */
    public RequestCreator addParam(String param) {
        String[] keyVal = param.split("=");
        if (keyVal.length == 2) this.mParams.setParam(keyVal[0].trim(), keyVal[1].trim());
        return this;
    }

    /**
     * 添加单个参数
     *
     * @param key 例如：author
     * @param val 例如：dxl584327830
     */
    public RequestCreator addParam(String key, String val) {
        this.mParams.setParam(key, val);
        return this;
    }

    /**
     * 添加多个参数
     *
     * @param params
     */
    public RequestCreator addParams(Map<String, String> params) {
        this.mParams.setParams(params);
        return this;
    }

    /**
     * 添加多个参数
     *
     * @param params
     */
    public RequestCreator addParams(ParamMap params) {
        this.mParams.setParams(params.paramMap);
        return this;
    }

    /**
     * 清空所有参数
     */
    public RequestCreator clearParams() {
        this.mParams.clearParam();
        return this;
    }

    /**
     * 生成HTTP请求报文
     *
     * @return
     */
    public String createHttpMessage() {
        HttpMessage httpMsg = new HttpMessage(this);
        return httpMsg.getHttpMessage();
    }

    public void execute(CallBack callBack) {
        HttpGo.createHttpGo(this);
    }

    public void execute() {
        HttpGo.createHttpGo(this);
    }

    /**
     * 构造器
     */
    public static class Builder {

        String host = null;
        String path = null;
        ParamMap params = null;

        public Builder host(String arg0) {
            host = arg0;
            return this;
        }

        public Builder path(String arg0) {
            if (arg0 == null || arg0.length() == 0) arg0 = "/";
            else if (arg0.endsWith("/")) arg0 = arg0.substring(0, arg0.length());
            path = arg0;
            return this;
        }

        public Builder params(ParamMap arg0) {
            params = arg0;
            return this;
        }

        public RequestCreator build() {
            return new RequestCreator(this);
        }

    }

    /**
     * 参数
     */
    public static class ParamMap {

        Map<String, String> paramMap = new HashMap<>();

        public ParamMap() {

        }

        public ParamMap(@NonNull Map<String, String> params) {
            paramMap = params;
        }

        public void setParam(@NonNull String key, @NonNull String val) {
            paramMap.put(key, val);
        }

        public void setParams(@NonNull Map<String, String> params) {
            paramMap.putAll(params);
        }

        public void clearParam() {
            paramMap.clear();
        }

        @Override
        public String toString() {
            return paramMap.toString();
        }

    }

}
