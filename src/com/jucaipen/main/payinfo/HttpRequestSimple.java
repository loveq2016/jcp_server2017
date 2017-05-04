package com.jucaipen.main.payinfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
public class HttpRequestSimple{

    private static HttpRequestSimple instance;

    public static HttpRequestSimple getInstance()
    {
        if (instance == null)
        {
            instance = new HttpRequestSimple();
        }
        return instance;
    }

    public static void clear()
    {
        instance = null;
    }

    private HttpRequestSimple()
    {

    }

    private static Logger log = Logger.getLogger(HttpRequestSimple.class);

    /**
     * 
     * �����������������л�����
     * @param url
     * @param inputObj
     * @return
     */
    public Object postSendHttp(String url, Object inputObj)
    {
        long start = System.currentTimeMillis();
        if (url == null || "".equals(url))
        {
            log.error("request url is empty.");
            return null;
        }
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/octet-stream");
        java.io.ByteArrayOutputStream bOut = new java.io.ByteArrayOutputStream(
                1024);
        java.io.InputStream bInput = null;
        java.io.ObjectOutputStream out = null;
        Serializable returnObj = null;
        try
        {
            out = new java.io.ObjectOutputStream(bOut);
            out.writeObject(inputObj);
            out.flush();
            out.close();
            out = null;
            bInput = new java.io.ByteArrayInputStream(bOut.toByteArray());
            InputStreamEntity inputStreamEntity = new InputStreamEntity(bInput,
                    bOut.size(), null);
            inputStreamEntity.setContentEncoding(new BasicHeader(
                    HTTP.CONTENT_ENCODING, "UTF-8"));
            // ������������
            post.setEntity(inputStreamEntity);
            // ��������
            HttpResponse resp = httpClient.execute(post);
            log.info("����[" + url + "] " + resp.getStatusLine());
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK)
            {
                // // // ��Ӧ����
                // HttpEntity entity = resp.getEntity();
                // returnObj = (Serializable) SerializationUtils.deserialize(entity.getContent());
                // return returnObj;
                // ��Ӧ����
                HttpEntity entity = resp.getEntity();

                java.io.InputStream in = entity.getContent();
                java.io.ObjectInputStream oInput = new java.io.ObjectInputStream(
                        in);
                returnObj = (Serializable) oInput.readObject();
                oInput.close();
                oInput = null;
                long end = System.currentTimeMillis();
                log.info("����[" + url + "]����ʱ�� " + (end - start) + "����");
                return returnObj;
            }
            return null;
        } catch (ConnectTimeoutException cte)
        {
            log.error(cte.getMessage(), cte);
            return null;
        } catch (SocketTimeoutException cte)
        {
            log.error(cte.getMessage(), cte);
            return null;
        } catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public String postSendHttp(String url, String body)
    {
        long start = System.currentTimeMillis();
        if (url == null || "".equals(url))
        {
            log.error("request url is empty.");
            return null;
        }
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "text/html;charset=UTF-8");
        try
        {
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            stringEntity.setContentEncoding(new BasicHeader(
                    HTTP.CONTENT_ENCODING, "UTF-8"));
            // ������������
            post.setEntity(stringEntity);
            // ��������
            HttpResponse resp = httpClient.execute(post);
            log.info("����[" + url + "] " + resp.getStatusLine());
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK)
            {
                // ��Ӧ����
                HttpEntity entity = resp.getEntity();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        entity.getContent(), "UTF-8"));
                StringBuffer responseString = new StringBuffer();
                String result = br.readLine();
                while (result != null)
                {
                    responseString.append(result);
                    result = br.readLine();
                }
                long end = System.currentTimeMillis();
                log.info("����[" + url + "]����ʱ�� " + (end - start) + "����");
                return responseString.toString();
            }
            return null;
        } catch (ConnectTimeoutException cte)
        {
            log.error(cte.getMessage(), cte);
            return null;
        } catch (SocketTimeoutException cte)
        {
            log.error(cte.getMessage(), cte);
            return null;
        } catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public String getSendHttp(String url)
    {
        if (url == null || "".equals(url))
        {
            log.error("request url is empty.");
            return null;
        }
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader("Content-Type", "text/html;charset=UTF-8");
        try
        {
            // ��������
            HttpResponse resp = httpClient.execute(get);
            log.info("����[" + url + "] " + resp.getStatusLine());
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK)
            {
                // ��Ӧ����
                HttpEntity entity = resp.getEntity();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        entity.getContent()));
                StringBuffer responseString = new StringBuffer();
                String result = br.readLine();
                while (result != null)
                {
                    responseString.append(result);
                    result = br.readLine();
                }

                return responseString.toString();
            }
            return null;
        } catch (ConnectTimeoutException cte)
        {
            log.error(cte.getMessage(), cte);
            return null;
        } catch (SocketTimeoutException cte)
        {
            log.error(cte.getMessage(), cte);
            return null;
        } catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public String postSendHttp1(String url, String body)
    {
        long start = System.currentTimeMillis();
        if (url == null || "".equals(url))
        {
            log.error("request url is empty.");
            return null;
        }
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        try
        {
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            stringEntity.setContentEncoding(new BasicHeader(
                    HTTP.CONTENT_ENCODING, "UTF-8"));
            // ������������
            post.setEntity(stringEntity);
            // ��������
            HttpResponse resp = httpClient.execute(post);
            log.info("����[" + url + "] " + resp.getStatusLine());
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK)
            {
                // ��Ӧ����
                HttpEntity entity = resp.getEntity();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        entity.getContent(), "UTF-8"));
                StringBuffer responseString = new StringBuffer();
                String result = br.readLine();
                while (result != null)
                {
                    responseString.append(result);
                    result = br.readLine();
                }
                long end = System.currentTimeMillis();
                log.info("����[" + url + "]����ʱ�� " + (end - start) + "����");
                return responseString.toString();
            }
            return null;
        } catch (ConnectTimeoutException cte)
        {
            log.error(cte.getMessage(), cte);
            return null;
        } catch (SocketTimeoutException cte)
        {
            log.error(cte.getMessage(), cte);
            return null;
        } catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
    }



    public String postPramaList(String url, NameValuePair[] list)
    {
        List<NameValuePair> nvList = new ArrayList<NameValuePair>();
        for (NameValuePair nameValue : list)
        {
            nvList.add(nameValue);
        }
        return postPramaList(nvList, url);
    }

    public String postPramaList(List<NameValuePair> list, String url)
    {
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        BufferedReader br = null;
        try
        {
            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(list,
                    HTTP.UTF_8);
            // ������������
            post.setEntity(formEntiry);
            // ��������
            HttpResponse resp = httpClient.execute(post);
            log.info("����[" + url + "] " + resp.getStatusLine());
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK)
            {
                // ��Ӧ����
                HttpEntity entity = resp.getEntity();
                br = new BufferedReader(new InputStreamReader(entity
                        .getContent(), "UTF-8"));
                StringBuffer responseString = new StringBuffer();
                String result = br.readLine();
                while (result != null)
                {
                    responseString.append(result);
                    result = br.readLine();
                }
                return responseString.toString();
            } else
            {
                log.error("retcode:" + ret);
                return null;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        } finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    // do nothing
                }
            }
        }
    }

}

