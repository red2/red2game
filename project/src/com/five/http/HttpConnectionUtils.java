package com.five.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * HTTP connection helper
 * 
 * @author
 * 
 */
public class HttpConnectionUtils implements Runnable
{
    private static final String TAG = HttpConnectionUtils.class.getSimpleName();
    
    // ///////////////////////////////////////////////////////////
    /**
     * 开始状态
     */
    public static final byte DID_START = 0;
    
    /**
     * 错误状态
     */
    public static final byte DID_ERROR = 1;
    
    /**
     * 成功状态
     */
    public static final byte DID_SUCCEED = 2;
    
    // ///////////////////////////////////////////////////////////
    /**
     * GET方式
     */
    private static final byte GET = 0;
    
    /**
     * POST方式
     */
    private static final byte POST = 1;
    
    /**
     * PUT
     */
    private static final byte PUT = 2;
    
    /**
     * DELETE
     */
    private static final byte DELETE = 3;
    
    /**
     * BITMAP图
     */
    private static final byte BITMAP = 4;
    
    /**
     * 地址
     */
    private String url;
    
    /**
     * 标识方法类型
     */
    private int method;
    
    /**
     * Handler对象
     */
    private Handler handler;
    
    /**
     * 连接类型
     */
    private int connectEvent;
    
    /**
     * HttpClient对象
     */
    private HttpClient httpClient;
    
    /**
     * 参数列表
     */
    private ArrayList<NameValuePair> postParameter = new ArrayList<NameValuePair>();
    
    /**
     * 参数列表
     */
    private Hashtable<String, String> getParameter = new Hashtable<String, String>();
    
    /**
     * 构造
     * 
     * @param handler
     */
    public HttpConnectionUtils(Handler handler)
    {
        this.handler = handler;
    }
    
    /**
     * 
     * @param url
     * @param connectEvent
     */
    private void startHttpConnect(int method, String url, int event)
    {
        this.method = method;
        this.url = url;
        if (method == GET)
        {
            this.url = this.addParameter(url);
        }
        Log.i("http-url==================", this.url);
        this.connectEvent = event;
        ConnectionManager.getInstance().push(this);
    }
    
    /**
     * GET
     * 
     * @param url
     * @param event
     */
    public void get(String url, int event)
    {
        startHttpConnect(GET, url, event);
    }
    
    /**
     * POST
     * 
     * @param url
     * @param event
     */
    public void post(String url, int event)
    {
        startHttpConnect(POST, url, event);
    }
    
    /**
     * PUT
     * 
     * @param url
     * @param event
     */
    public void put(String url, int event)
    {
        startHttpConnect(PUT, url, event);
    }
    
    /**
     * DELETE
     * 
     * @param url
     * @param event
     */
    public void delete(String url, int event)
    {
        startHttpConnect(DELETE, url, event);
    }
    
    /**
     * BITMAP
     * 
     * @param url
     * @param event
     */
    public void bitmap(String url, int event)
    {
        startHttpConnect(BITMAP, url, event);
    }
    
    /**
     * 启动线程
     */
    public void run()
    {
        handler.sendMessage(Message.obtain(handler, HttpConnectionUtils.DID_START, this.connectEvent, this.connectEvent));
        httpClient = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 6000);
        try
        {
            HttpResponse response = null;
            switch (method)
            {
                case GET:
                {
                    response = httpClient.execute(new HttpGet(url));
                }
                    break;
                case POST:
                {
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setEntity(new UrlEncodedFormEntity(postParameter, HTTP.UTF_8));
                    response = httpClient.execute(httpPost);
                }
                    break;
                case PUT:
                {
                    HttpPut httpPut = new HttpPut(url);
                    httpPut.setEntity(new UrlEncodedFormEntity(postParameter, HTTP.UTF_8));
                    response = httpClient.execute(httpPut);
                }
                    break;
                case DELETE:
                {
                    response = httpClient.execute(new HttpDelete(url));
                }
                    break;
                case BITMAP:
                {
                    response = httpClient.execute(new HttpGet(url));
                    processBitmapEntity(response.getEntity());
                }
                    break;
            }
            if (method < BITMAP)
            {
                processEntity(response.getEntity());
            }
        }
        catch (Exception e)
        {
            // handler.sendMessage(Message.obtain(handler,
            // HttpConnectionUtils.DID_ERROR, e));
            Message message = Message.obtain(handler, DID_ERROR, this.connectEvent, this.connectEvent, e);
            handler.sendMessage(message);
        }
        ConnectionManager.getInstance().didComplete(this);
    }
    
    /**
     * 其他类似json数据
     * 
     * @param entity
     * @throws IllegalStateException
     * @throws IOException
     */
    private void processEntity(HttpEntity entity) throws IllegalStateException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
        String line, result = "";
        while ((line = br.readLine()) != null)
        {
            result += line;
        }
        // Message message = Message.obtain(handler, DID_SUCCEED, result);
        Message message = Message.obtain(handler, DID_SUCCEED, this.connectEvent, this.connectEvent, result);
        handler.sendMessage(message);
    }
    
    /**
     * 图片
     * 
     * @param entity
     * @throws IOException
     */
    private void processBitmapEntity(HttpEntity entity) throws IOException
    {
        BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
        Bitmap bm = BitmapFactory.decodeStream(bufHttpEntity.getContent());
        // handler.sendMessage(Message.obtain(handler, DID_SUCCEED, bm));
        Message message = Message.obtain(handler, DID_SUCCEED, this.connectEvent, this.connectEvent, bm);
        handler.sendMessage(message);
        
    }
    
    /**
     * 添加参数
     * 
     * @param key
     * @param value
     */
    public void addGetParmeter(String key, String value)
    {
        if (getParameter == null)
        {
            getParameter = new Hashtable<String, String>();
        }
        if (key != null && value != null)
        {
            getParameter.put(key, value);
        }
    }
    
    /**
     * 添加参数
     * 
     * @param key
     * @param value
     */
    public void addPostParmeter(String key, String value)
    {
        if (postParameter == null)
        {
            postParameter = new ArrayList<NameValuePair>();
        }
        if (key != null && value != null)
        {
            postParameter.add(new BasicNameValuePair(key, value));
        }
    }
    
    /**
     * 添加参数
     */
    private String addParameter(String url)
    {
        StringBuffer sb = new StringBuffer(); 
        Enumeration<String> enumeration = getParameter.keys();
        if (enumeration != null && enumeration.hasMoreElements())
        {
            sb.append(url);
            String key;
            while (enumeration.hasMoreElements())
            {
                key = (String) enumeration.nextElement();
                sb.append(key);
                sb.append("=");
                sb.append(getParameter.get(key));
                if (!enumeration.hasMoreElements())
                    continue;
                sb.append("&");
            }
            enumeration = null;
            getParameter = null;
        }
        else
        {
            return url;
        }
        return sb.toString();
    }
}