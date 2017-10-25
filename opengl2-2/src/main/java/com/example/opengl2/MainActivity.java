package com.example.opengl2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGlsurfaceView;
    private boolean mIsSetRender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGlsurfaceView = new GLSurfaceView(this);
        if (!isSupportGL2()){
            return;
        }
        mGlsurfaceView.setEGLContextClientVersion(2);
        mGlsurfaceView.setRenderer(new MyRender(this));
        mIsSetRender = true;
        setContentView(mGlsurfaceView);

    }

    /**
     * 是否支持opengles 2.0,
     * @return
     */
    private boolean isSupportGL2(){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = manager.getDeviceConfigurationInfo();
        return info.reqGlEsVersion>=0x20000;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsSetRender){
            mGlsurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsSetRender){
            mGlsurfaceView.onPause();
        }
    }
}
