package com.five.http;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class HttpHandler extends Handler
{
    /**
     * Context实例
     */
    private Context context;
    
    /**
     * 等待对话框
     */
    private ProgressDialog progressDialog;
    
    /**
     * 是否显示等待框
     */
    private boolean isShowLoading = true;
    
    /**
     * 是否提示联网结果
     */
    private boolean isShowNote = true;
    
    public HttpHandler(Context context)
    {
        this.context = context;
        this.isShowLoading = true;
    }
    
    public HttpHandler(Context context, boolean isShowDialog)
    {
        this.context = context;
        this.isShowLoading = isShowDialog;
    }
    
    public HttpHandler(Context context, boolean isShowDialog, boolean isShowNote)
    {
        this.context = context;
        this.isShowLoading = isShowDialog;
        this.isShowNote = isShowNote;
    }
    
    protected void start()
    {
        if (this.isShowLoading)
        {
            progressDialog = ProgressDialog.show(context, "Please Wait...", "processing...", true);
        }
    }
    
    protected void succeed(JSONObject jObject, int event)
    {
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }
    
    protected void failed(JSONObject jObject, int event)
    {
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }
    
    protected void error(int event)
    {
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }
    
    protected void otherHandleMessage(Message message)
    {
    }
    
    public void handleMessage(Message message)
    {
        Log.i("arg1=====================", "" + message.arg1);
        switch (message.what)
        {
            case HttpConnectionUtils.DID_START:
            {
                // connection start
                Log.d(context.getClass().getSimpleName(), "http connection start...");
                start();
            }
                break;
            case HttpConnectionUtils.DID_SUCCEED:
            {
                // connection success
                if (progressDialog != null && progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                String response = (String) message.obj;
                Log.d(context.getClass().getSimpleName(), "http connection return." + response);
                try
                {
                    JSONObject jObject = new JSONObject(response == null ? "" : response.trim());
                    if ("200".equals(jObject.getString("status")))
                    {
                        // 获取数据成功
                        // Toast.makeText(context, "operate succeed:" +
                        // jObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        succeed(jObject, message.arg1);
                    }
                    else
                    {
                        if (this.isShowNote)
                        {
                            Toast.makeText(context, "operate fialed:" + jObject.getString("status"), Toast.LENGTH_LONG).show();
                        }
                        failed(jObject, message.arg1);
                    }
                }
                catch (JSONException e1)
                {
                    if (progressDialog != null && progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                    e1.printStackTrace();
                    if (this.isShowNote)
                    {
                        Toast.makeText(context, "connection fail,please check connection!", Toast.LENGTH_LONG).show();
                    }
                    error(message.arg1);
                }
            }
                break;
            case HttpConnectionUtils.DID_ERROR:
            {
                // connection error
                if (progressDialog != null && progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
                Exception e = (Exception) message.obj;
                e.printStackTrace();
                Log.e(context.getClass().getSimpleName(), "connection fail." + e.getMessage());
                if (this.isShowNote)
                {
                    Toast.makeText(context, "connection fail,please check connection!", Toast.LENGTH_LONG).show();
                }
                
                //
                error(message.arg1);
            }
                break;
        }
        otherHandleMessage(message);
    }
    
}