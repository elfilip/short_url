package elias.rest;

import java.io.File;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import elias.rest.annotations.FileQ;

@FileQ
@Stateless
public class FileResourceManager implements ResourceManager {

	private String path="/home/felias/prace/tutorialy/rest/url_shortener/urls.json";
	private UrlMap urls;
	
	public FileResourceManager(){
		urls=getAll();
	}

	public void addURL(String hash,String url){
		urls.addUrl(hash, url);
		saveURLs();
	}
	
	public void saveURLs() {
		ObjectMapper m = new ObjectMapper();
		try {
			m.writerWithDefaultPrettyPrinter().writeValue(new File(path), urls);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public ShortURL getUrl(String hash) {
		if(urls!=null){
		return	urls.getUrl(hash);
		}else{
			urls=getAll();
			if(urls==null){
				return null;
			}else
				return urls.getUrl(hash);
		}
	}

	public UrlMap getAll() {
		if(urls!=null)
			return urls;
		
		ObjectMapper mapper = new ObjectMapper();
		
		UrlMap obj = null;
		// JSON from file to Object
		try {
			obj = mapper.readValue(new File(path), UrlMap.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(obj!=null){
			urls=obj;
			return urls;
		}else{
			return null;
		}

	}

}
