package com.huawei.uitls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	private static RestTemplate restTemplate = null;

	static {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setConnectTimeout(10000);
		simpleClientHttpRequestFactory.setReadTimeout(10000);
		restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
		List<HttpMessageConverter<?>> msgConverters = restTemplate.getMessageConverters();
		HttpMessageConverter<?> converter = null;
		for (HttpMessageConverter<?> item : msgConverters) {
			// 将 spring 的 StringMsg 的默认的iso格式的编码转化成utf-8
			if (item.getClass() == StringHttpMessageConverter.class) {
				converter = item;
				if (converter != null) {
					msgConverters.remove(converter);
				}
				continue;
			}
			// 取消spring restClient内置的Jackson转化避免转化string的出错
			if (item.getClass() == MappingJackson2HttpMessageConverter.class) {
				msgConverters.remove(item);
				continue;
			}
		}
		// 添加编码格式
		msgConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
	}

	public static String restExchangeString(String url, HttpMethod method, String requestData) {
		if (url == null || url.equals("")) {
			return null;
		}
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.exchange(Normalizer.normalize(url, Form.NFKC), method, new HttpEntity<String>(requestData),
				String.class);
		if(entity == null){
			return null;
		}
		return entity.getBody();
	}
	
	public static String exchangeWithMap(String url, LinkedMultiValueMap<String, Object> map ,HttpMethod httpMethod){
		ResponseEntity<String> entity = null;
		try {
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setBufferRequestBody(false);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			HttpHeaders headers =new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			entity = restTemplate.exchange(Normalize(url), httpMethod, requestEntity, String.class); 
			if(entity == null)
				return "";
		} catch (RestClientException e) {
			throw new RuntimeException(RestClient.class.getName() + " :RestClientException exception !!");
		}
		return entity.getBody();
		
	}

	private static String Normalize(String param){
		if(param == null){
			return "";
		}
		return Normalizer.normalize(param, Form.NFKC);
	}
	
	public static String post(String Url, Map<String, Object> paramerter){
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(Url);
		Set<Entry<String, Object>> entrys = paramerter.entrySet();
		List<NameValuePair> pairs = new ArrayList<>();
		for (Entry<String, Object> entry : entrys) {
			pairs.add(new NameValuePair(entry.getKey(), entry.getValue().toString()));
		}
		method.setRequestBody(pairs.toArray(new NameValuePair[0]));
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			httpClient.executeMethod(method);
			reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
			String tmp = null;
			while((tmp = reader.readLine()) != null){
				result.append(tmp);
			}
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException(RestClient.class.getName() + " :Post request is Exception!!");
		}finally {
			if(reader != null)
			try {
				reader.close();
				reader = null;
			} catch (IOException e) {
				throw new RuntimeException(RestClient.class.getName() + ": reader stream close error !!");
			}
		}
	}
}