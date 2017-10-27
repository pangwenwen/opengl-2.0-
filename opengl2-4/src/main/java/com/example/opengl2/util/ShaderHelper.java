package com.example.opengl2.util;

import android.opengl.GLES20;

import com.example.opengl2.util.LogConfiger;

/**
 * Created by hua.pang on 2017/10/23.
 */

public class ShaderHelper {
    private static final String TAG = "ShaderHelper";
    public static int compileVertexShader(String shadercode){
        return compileShader(GLES20.GL_VERTEX_SHADER,shadercode);
    }

    public static int compileFragrementShader(String shadercode){
        return compileShader(GLES20.GL_FRAGMENT_SHADER,shadercode);

    }

    /**
     * 编译着色器
     * @param type
     * @param shader
     * @return
     */
    private static int compileShader(int type,String shader){
        //创建新的着色器对象，
        int shaderId = GLES20.glCreateShader(type);
        if (shaderId == 0){
            //创建失败
            if (LogConfiger.ON){
               LogConfiger.d(TAG,"compileShader creat shader fail");
            }
            return 0;
        }
        //上传和编译着色器源代码
        GLES20.glShaderSource(shaderId,shader);
        GLES20.glCompileShader(shaderId);
        //获取着色器信息日志
        if (LogConfiger.ON){
            LogConfiger.d(TAG,"shadercode : "+shader+",info = "+GLES20.glGetShaderInfoLog(shaderId));
        }
        //取出编译状态
        int[] status = new int[1];
        GLES20.glGetShaderiv(shaderId,GLES20.GL_COMPILE_STATUS,status,0);
        if (status[0] == 0){
            if (LogConfiger.ON){
                LogConfiger.d(TAG,"compileShader compile shader fail");
            }
            GLES20.glDeleteShader(shaderId);
            //编译失败
            return 0;
        }

        return shaderId;
    }

    /**
     * 连接着色器
     * @param vertexId
     * @param fragrementId
     * @return
     */
    public static int linkprogrom(int vertexId,int fragrementId){
        int progremId = GLES20.glCreateProgram();
        if (progremId == 0){
            if (LogConfiger.ON){
                LogConfiger.d(TAG,"compileShader creat program fail");
            }
            //失败
            return 0;
        }

        //顶点着色器和片源着色器都附加到程序对象上
        GLES20.glAttachShader(progremId,vertexId);
        GLES20.glAttachShader(progremId,fragrementId);

        //将着色器连接
        GLES20.glLinkProgram(progremId);
        //检测连接的状态
        int[] status = new int[1];
        GLES20.glGetProgramiv(progremId,GLES20.GL_LINK_STATUS,status,0);
        if (status[0] == 0){
            //连接失败
            if (LogConfiger.ON){
                LogConfiger.d(TAG,"compileShader link program fail");
            }
            GLES20.glDeleteProgram(progremId);
            return 0;
        }
        return progremId;
    }

    /**
     * 验证这个程序对于当前opengl的状态是否有效
     * @param progremId
     * @return
     */
    public static boolean isEffective(int progremId){
        GLES20.glValidateProgram(progremId);
        int[] validate = new int[1];
        GLES20.glGetProgramiv(progremId,GLES20.GL_VALIDATE_STATUS,validate,0);
        LogConfiger.d(TAG,"Result is validate is "+validate[0]+"\nlog = "+GLES20.glGetProgramInfoLog(progremId));
        return validate[0] != 0;
    }
}
