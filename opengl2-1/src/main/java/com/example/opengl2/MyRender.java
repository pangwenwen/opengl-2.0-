package com.example.opengl2;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hua.pang on 2017/10/20.
 */

public class MyRender implements GLSurfaceView.Renderer {

    /**
     * 当surface 被创建时调用
     * @param gl10
     * @param eglConfig
     */
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //清空屏幕时使用的颜色
        GLES20.glClearColor(1,0,0,1);

    }

    /**
     * 每次surface尺寸变化时，会调用
     * @param gl10
     * @param i
     * @param i1
     */
    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        //设置视口尺寸大小，也就是告诉opengl可以用于渲染的surface的大小
        GLES20.glViewport(0,0,i,i1);

    }

    /**
     * 当绘制一帧时，会被调用，此方法中一定要绘制东西，甚至只是清屏，防止出现闪屏效果
     * @param gl10
     */
    @Override
    public void onDrawFrame(GL10 gl10) {
        //清空屏幕，并会使用glClearColor 中设置的颜色填充屏幕
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

    }
}
