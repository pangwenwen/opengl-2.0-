package com.example.opengl2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.opengl2.util.BufferUtil;
import com.example.opengl2.util.LogConfiger;
import com.example.opengl2.util.ResourceReader;
import com.example.opengl2.util.ShaderHelper;
import com.example.opengl2.view.TableTnnis;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hua.pang on 2017/10/20.
 */

public class MyRender implements GLSurfaceView.Renderer {
    private static final String TAG = "MyRender";
    private static final String U_COLOR = "u_Color";
    private static final String A_APOSITION = "a_Position";
    private static final String A_COLOR = "a_Color";
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int BYTES_PER_FLOAT = 4;

    private static final int STRIDE = (COLOR_COMPONENT_COUNT+POSITION_COMPONENT_COUNT)*4;

    private Context mContext;
    private int progremId;
    private int uColorLocation;
    private int aVertexLocation;
    private int aColorLocation;

    public MyRender(Context context) {
        this.mContext = context;
    }

    /**
     * 当surface 被创建时调用
     * @param gl10
     * @param eglConfig
     */
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //清空屏幕时使用的颜色
        GLES20.glClearColor(0,0,0,1);

        //1. 加载着色器
        String vertexSource = ResourceReader.readFromSource(mContext,R.raw.vertex);
        String fragrementSource = ResourceReader.readFromSource(mContext,R.raw.fragrement);
        //2. 编译着色器
        int vertexShader = ShaderHelper.compileVertexShader(vertexSource);
        int fragrementShader = ShaderHelper.compileFragrementShader(fragrementSource);

        //3. 连接着色器
        progremId = ShaderHelper.linkprogrom(vertexShader,fragrementShader);
        if (LogConfiger.ON){
            LogConfiger.d(TAG,"vertexShader = "+vertexShader+"fragrementShader="+fragrementShader+"; progremId = "+progremId);
        }

        if (LogConfiger.ON){
            ShaderHelper.isEffective(progremId);
        }
        //4. 通知opengl 绘制时使用这个程序
        GLES20.glUseProgram(progremId);

        //5.获取片段着色器中的位置，和顶点着色器属性的位置
        //uColorLocation = GLES20.glGetUniformLocation(progremId,U_COLOR);
        aColorLocation = GLES20.glGetAttribLocation(progremId,A_COLOR);
        aVertexLocation = GLES20.glGetAttribLocation(progremId,A_APOSITION);

        //6. 关联属性与顶点数据
        //1. 属性的位置；2.点的坐标用几个数表示；3.数据的类型；4：只对整形有意义；5.多余一个属性时才有意义；6顶点的数据
        FloatBuffer buffer = BufferUtil.floatToBuffer(TableTnnis.Tangles);
        //GLES20.glVertexAttribPointer(aVertexLocation,2,GLES20.GL_FLOAT,false,0,buffer);
        GLES20.glVertexAttribPointer(aVertexLocation,POSITION_COMPONENT_COUNT,GLES20.GL_FLOAT,false,STRIDE,buffer);
        //2. 顶点着色器中的a_Color与位置信息关联
        buffer.position(POSITION_COMPONENT_COUNT);
        GLES20.glVertexAttribPointer(aColorLocation,COLOR_COMPONENT_COUNT,GLES20.GL_FLOAT,false,STRIDE,buffer);

        //7. 使能顶点数组
        GLES20.glEnableVertexAttribArray(aColorLocation);
        GLES20.glEnableVertexAttribArray(aVertexLocation);

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
        //8. 绘制
         //绘制正方形
        /*GLES20.glUniform4f(uColorLocation,1.0f,1.0f,0.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,6);
        GLES20.glUniform4f(uColorLocation,1.0f,1.0f,1.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,6,6);*/

         //绘制线
        /*GLES20.glUniform4f(uColorLocation,0.0f,0.0f,1.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES,12,2);
         //绘制点
        GLES20.glUniform4f(uColorLocation,1.0f,0.0f,0.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS,14,1);
        GLES20.glUniform4f(uColorLocation,0.0f,1.0f,0.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS,15,1);*/

        /*GLES20.glUniform4f(uColorLocation,1.0f,1.0f,0.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,10,6);*/

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN,0,6);
        GLES20.glDrawArrays(GLES20.GL_LINES,6,2);
        GLES20.glDrawArrays(GLES20.GL_POINTS,8,1);
        GLES20.glDrawArrays(GLES20.GL_POINTS,9,1);

    }
}
