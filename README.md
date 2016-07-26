# MyHttp

### 1.主要方法
#### 1.1 设置URL
#### URL: [ (htttp://) host (path) (params) ]
* MyHttp.url(String url)  
* MyHttp.get(String url)
* MyHttp.post(String url)

#### 例：MyHttp.url("www.host.com/path?param0=arg0&param1=arg1")
#### 1.2 设置参数
* addParam(String param)
* addParam(String key,String val)
* addParams(Map<String,String> params)
* addParams(ParamMap params)

#### 例：<br>
MyHttp.url("www.host.com").addParam("param0=arg0")<br>
MyHttp.url("www.host.com").addParam("param0","arg0")<br>

#### 1.3 发起请求
* execute()
* execute(CallBack callBack)
