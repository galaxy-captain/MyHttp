package org.galaxy.sample;

import android.app.Activity;
import android.os.Bundle;

import org.galaxy.myhttp.MyHttp;

/**
 * Created by dxl584327830 on 16/7/26.
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        MyHttp.get("www.lagou.com/aaa.html/").clearParams().addParam("a=1").addParam("b", "2").execute();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.this.finish();
    }
}
