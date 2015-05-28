package com.jrj.yqcm.utils;

import java.net.URL;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * Http����ȡ���ݹ���
 * 
 * @author coldwater
 * 
 */
public class HttpConnectionClient {
	/**
	 * ���ӳ�ʱ
	 */
	private static int DEFAULT_CONNECTION_TIMEOUT = 1000 * 3;
	/**
	 * ���䳬ʱ
	 */
	private static int DEFAULT_SO_TIMEOUT = 1000 * 3;
	/**
	 * ���������
	 */
	private static int DEFAULT_CONNECTIONS_MAX_TOTAL = 200;
	/**
	 * ÿhost���������
	 */
	private static int DEFAULT_CONNECTIONS_MAX_PERHOST = 50;
	// ��ʼ���õ���ͬ����
	private final ReentrantLock lock = new ReentrantLock();

	// ע�⣺���ﲻ��static����Ϊ���ö��httpConnectionClient�����
	private MultiThreadedHttpConnectionManager connectionManager = null;
	private HttpClient httpClient = null;

	private int connectionTimeOut = DEFAULT_CONNECTION_TIMEOUT;
	private int soTimeOut = DEFAULT_SO_TIMEOUT;
	private int connectionMaxTotal = DEFAULT_CONNECTIONS_MAX_TOTAL;
	private int connectionMaxPerHost = DEFAULT_CONNECTIONS_MAX_PERHOST;
	/**
	 * �����ַ���
	 */
	private String codeing = "utf-8";
	/**
	 * �����ַ���
	 */
	private String charset = null;
	
	private String getCharset(){
		if (charset==null){
			return codeing;
		}else{
			return charset;
		}
	}
	private HttpClient getHttpClient() {
		lock.lock();
		try {
			if (connectionManager == null) {
				System.out
						.println("=====new MultiThreadedHttpConnectionManager()");
				connectionManager = new MultiThreadedHttpConnectionManager();
				configure();
			}
			if (httpClient == null) {
				httpClient = new HttpClient(connectionManager);
			}

		} finally {
			lock.unlock();
		}
		return httpClient;
	}

	/**
	 * ����connectionmanager
	 */
	private void configure() {
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setConnectionTimeout(connectionTimeOut);
		params.setMaxTotalConnections(connectionMaxTotal);
		params.setDefaultMaxConnectionsPerHost(connectionMaxPerHost);
		params.setSoTimeout(soTimeOut);
	}

	/**
	 * ����http��ҳ���ݣ�ʹ��Get�����ύ
	 * 
	 * @param url
	 * @return
	 */
	public String getContextByGetMethod(GetMethod gm) {
		HttpClient client = getHttpClient();
		// ���ñ���
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				getCharset());
		DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY); 
		String result = "";
		try {
			client.executeMethod(gm);

			if (gm.getStatusCode() >= 400) {
//				throw new HttpException("Connection Error!return Status :"
//						+ gm.getStatusCode());
				System.out.println("Reason-Phrase:" + gm.getResponseHeader("Reason-Phrase"));
				System.out.println("url:" + gm.getURI());
			}
			result = new String(gm.getResponseBody(),codeing);
		} catch (Exception e) {
		} finally {
			gm.releaseConnection();
		}
		return result;
	}

	/**
	 * ����http��ҳ���ݣ�ʹ��Get�����ύ
	 * 
	 * @param url
	 * @return
	 */
	public String getContextByGetMethod(String url) {
		GetMethod gm = new GetMethod(url);
		return getContextByGetMethod(gm);
	}

	/**
	 * ����http��ҳ���ݣ�ʹ��Post�����ύ
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param param
	 *            ��������ֵ���б�
	 * @return
	 */
	public String getContextByPostMethod(String url, List<KeyValue> params) {
		HttpClient client = getHttpClient();
		// ���ñ���
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				getCharset());
		PostMethod post = null;
		String result = "";
		try {
			// �����ύ��ַ
			URL u = new URL(url);
			client.getHostConfiguration().setHost(u.getHost(),
					u.getPort() == -1 ? u.getDefaultPort() : u.getPort(),
					u.getProtocol());
			post = new PostMethod(u.getPath());

			// ƴ��ֵ��
			NameValuePair[] nvps = new NameValuePair[params.size()];
			int i = 0;
			for (KeyValue kv : params) {
				nvps[i] = new NameValuePair(kv.getKey(), kv.getValue());
				i++;
			}
			// �ύ����
			post.setRequestBody(nvps);

			client.executeMethod(post);
			result = new String(post.getResponseBody(),codeing);
		} catch (Exception e) {
		} finally {
			if (post != null)
				post.releaseConnection();
		}
		return result;
	}

	/**
	 * ����http��ҳ���ݣ�ʹ��POST�����ύ
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param contentType
	 *            �ύ�������ݵ�����
	 * @param requestCodeing
	 *            �ύ���ݵ��ַ�����
	 * @param body
	 *            �ύ������
	 * @return
	 */
	public String getContextByPostMethod(String url, String contentType,
			String requestCodeing, String body) {
		return this.getContextByMethod(url, "POST", contentType,
				requestCodeing, body);
	}

	/**
	 * ����http��ҳ���ݣ�ʹ��PUT�����ύ
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param contentType
	 *            �ύ�������ݵ�����
	 * @param requestCodeing
	 *            �ύ���ݵ��ַ�����
	 * @param body
	 *            �ύ������
	 * @return
	 */
	public String getContextByPutMethod(String url, String contentType,
			String requestCodeing, String body) {
		return this.getContextByMethod(url, "PUT", contentType, requestCodeing,
				body);
	}

	/**
	 * ����http��ҳ���ݣ�ʹ��DELETE�����ύ
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param contentType
	 *            �ύ�������ݵ�����
	 * @param requestCodeing
	 *            �ύ���ݵ��ַ�����
	 * @param body
	 *            �ύ������
	 * @return
	 */
	public String getContextByDeleteMethod(String url, String contentType,
			String requestCodeing, String body) {
		return this.getContextByMethod(url, "DELETE", contentType,
				requestCodeing, body);
	}

	/**
	 * ����http��ҳ���ݣ�ʹ���ύ������֧��POST\PUT\DELETE
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param method
	 *            ִ�еķ�����֧��POST\PUT\DELETE
	 * @param contentType
	 *            �ύ�������ݵ�����
	 * @param requestCodeing
	 *            �ύ���ݵ��ַ�����
	 * @param body
	 *            �ύ������
	 * @return
	 */
	public String getContextByMethod(String url, String method,
			String contentType, String requestCodeing, String body) {
		HttpClient client = getHttpClient();
		//���ñ���
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				getCharset());
		HttpMethod mt = null;
		String result = "";
		try {
			// �����ύ��ַ
			URL u = new URL(url);
			client.getHostConfiguration().setHost(u.getHost(),
					u.getPort() == -1 ? u.getDefaultPort() : u.getPort(),
					u.getProtocol());
			if ("POST".equalsIgnoreCase(method)) {
				mt = new PostMethod(u.getPath());
			} else if ("PUT".equalsIgnoreCase(method)) {
				mt = new PutMethod(u.getPath());
			} else if ("DELETE".equalsIgnoreCase(method)) {
				mt = new DeleteMethod(u.getPath());
			} else {
			}

			// �ύ����
			// mt.addRequestHeader("content-type", contentType);
			StringRequestEntity entity = new StringRequestEntity(body,
					contentType, requestCodeing);
			((EntityEnclosingMethod) mt).setRequestEntity(entity);
			client.executeMethod(mt);
			if (mt.getStatusCode() >= 400) {
				throw new HttpException("Connection Error!return Status :"
						+ mt.getStatusCode());
			}
			byte[] resultBytes = mt.getResponseBody();
			result = new String(resultBytes, codeing);
		} catch (Exception e) {
		} finally {
			if (mt != null)
				mt.releaseConnection();
		}
		return result;
	}

	/**
	 * ����http���󷵻�״̬�룬ʹ��POST�����ύ
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param contentType
	 *            �ύ�������ݵ�����
	 * @param requestCodeing
	 *            �ύ���ݵ��ַ�����
	 * @param body
	 *            �ύ������
	 * @return
	 */
	public int getStatusByPostMethod(String url, String contentType,
			String requestCodeing, String body) {
		return this.getStatusByMethod(url, "POST", contentType, requestCodeing,
				body);
	}

	/**
	 * ����http���󷵻�״̬�룬ʹ��PUT�����ύ
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param contentType
	 *            �ύ�������ݵ�����
	 * @param requestCodeing
	 *            �ύ���ݵ��ַ�����
	 * @param body
	 *            �ύ������
	 * @return
	 */
	public int getStatusByPutMethod(String url, String contentType,
			String requestCodeing, String body) {
		return this.getStatusByMethod(url, "PUT", contentType, requestCodeing,
				body);
	}

	/**
	 * ����http���󷵻�״̬�룬ʹ��DELETE�����ύ
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param contentType
	 *            �ύ�������ݵ�����
	 * @param requestCodeing
	 *            �ύ���ݵ��ַ�����
	 * @param body
	 *            �ύ������
	 * @return
	 */
	public int getStatusByDeleteMethod(String url, String contentType,
			String requestCodeing, String body) {
		return this.getStatusByMethod(url, "DELETE", contentType,
				requestCodeing, body);
	}

	/**
	 * ����http���󷵻�״̬�룬ʹ���ύ������֧��POST\PUT\DELETE
	 * 
	 * @param url
	 *            ���ʵ�ַ
	 * @param method
	 *            ִ�еķ�����֧��POST\PUT\DELETE
	 * @param contentType
	 *            �ύ�������ݵ�����
	 * @param requestCodeing
	 *            �ύ���ݵ��ַ�����
	 * @param body
	 *            �ύ������
	 * @return
	 */
	public int getStatusByMethod(String url, String method, String contentType,
			String requestCodeing, String body) {
		HttpClient client = getHttpClient();
		// ���ñ���
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				getCharset());
		HttpMethod mt = null;
		int result = -1;
		try {
			// �����ύ��ַ
			URL u = new URL(url);
			client.getHostConfiguration().setHost(u.getHost(),
					u.getPort() == -1 ? u.getDefaultPort() : u.getPort(),
					u.getProtocol());
			if ("POST".equalsIgnoreCase(method)) {
				mt = new PostMethod(u.getPath());
			} else if ("PUT".equalsIgnoreCase(method)) {
				mt = new PutMethod(u.getPath());
			} else if ("DELETE".equalsIgnoreCase(method)) {
				mt = new DeleteMethod(u.getPath());
			} else {
			}

			// �ύ����
			// mt.addRequestHeader("content-type", contentType);
			StringRequestEntity entity = new StringRequestEntity(body,
					contentType, requestCodeing);
			((EntityEnclosingMethod) mt).setRequestEntity(entity);
			client.executeMethod(mt);
			result = mt.getStatusCode();

		} catch (Exception e) {
		} finally {
			if (mt != null)
				mt.releaseConnection();
		}
		return result;
	}

	public int getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(int connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public int getConnectionMaxTotal() {
		return connectionMaxTotal;
	}

	public void setConnectionMaxTotal(int connectionMaxTotal) {
		this.connectionMaxTotal = connectionMaxTotal;
	}

	public int getConnectionMaxPerHost() {
		return connectionMaxPerHost;
	}

	public void setConnectionMaxPerHost(int connectionMaxPerHost) {
		this.connectionMaxPerHost = connectionMaxPerHost;
	}

	/**
	 * @param codeing
	 *            the codeing to set
	 */
	public void setCodeing(String codeing) {
		this.codeing = codeing;
	}

	/**
	 * @return the codeing
	 */
	public String getCodeing() {
		return codeing;
	}

	public void setSoTimeOut(int soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public int getSoTimeOut() {
		return soTimeOut;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
}

