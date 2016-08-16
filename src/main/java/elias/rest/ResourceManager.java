package elias.rest;

import java.util.Map;

public interface ResourceManager {
	public void addURL(String hash,String url);
	public void saveURLs();
	public ShortURL getUrl(String hash);
	public UrlMap getAll();
}
