package org.galaxy.myhttp;

/**
 * Created by dxl584327830 on 16/7/26.
 */
public class HttpMessage {

    private static final String SPACE = " ";

    private RequestCreator mRequestCreator;

    private StringBuffer mHttpMessage;

    HttpMessage(RequestCreator requestCreator) {
        this.mRequestCreator = requestCreator;
        createMessage(requestCreator);
    }

    String getHttpMessage() {
        return this.mHttpMessage.toString();
    }

    void createMessage(RequestCreator requestCreator) {

        switch (mRequestCreator.mRequestMethod) {
            case RequestCreator.GET:
                httpGetMsg();
                break;
            case RequestCreator.POST:
                httpPostMsg();
                break;
            default:
                httpPostMsg();
                break;
        }

    }

    void httpGetMsg() {
        mHttpMessage = new StringBuffer();

        mHttpMessage.append("GET");
        mHttpMessage.append(SPACE);
        mHttpMessage.append(mRequestCreator.mPath + "?" + mRequestCreator.getParam());
        mHttpMessage.append(SPACE);

    }

    void httpPostMsg() {
        mHttpMessage = new StringBuffer();
    }


}
