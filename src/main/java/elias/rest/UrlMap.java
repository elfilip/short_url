package elias.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;


public class UrlMap {

	private Map<String,ShortURL> urls=new HashMap<String, ShortURL>();
	
	public UrlMap(){
		System.out.println("bg");
	}
	
	public Map<String, ShortURL> getUrls() {
		return urls;
	}

	public void setUrls(Map<String, ShortURL> urls) {
		this.urls = urls;
	}

	public void addUrl(String hash, String url){
		urls.put(hash, new ShortURL(url, hash, new Date()));
	}
	public void addUrl(String hash, String url,Date d){
		urls.put(hash, new ShortURL(url, hash, d));
	}
	
	public ShortURL getUrl(String hash){
		return urls.get(hash);
	}
}
