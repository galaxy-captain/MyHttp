package org.galaxy.myhttp;

/**
 * Created by dxl584327830 on 16/7/26.
 */
public class HttpGo {

    private RequestCreator mRequestCreator;

    public static HttpGo createHttpGo(RequestCreator requestCreator) {
        return new HttpGo(requestCreator);
    }

    private HttpGo(RequestCreator requestCreator) {
        this.mRequestCreator = requestCreator;
    }

    private void sendRequest() {

        String httpMessage = mRequestCreator.createHttpMessage();


    }

}
