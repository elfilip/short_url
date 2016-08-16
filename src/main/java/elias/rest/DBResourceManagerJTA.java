package elias.rest;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import elias.rest.annotations.DBQ;
import elias.rest.annotations.DBQJTA;

@DBQJTA
@Stateless
public class DBResourceManagerJTA implements ResourceManager {

	@PersistenceContext(unitName="eliasJTA")
	EntityManager em;
	
	private UrlMap urls;

	public DBResourceManagerJTA() {

	}

	public void addURL(String hash, String url) {
		ShortURL shortURL = new ShortURL(url, hash, new Date());
	//	em.getTransaction().begin();
		em.persist(shortURL);
	//	em.getTransaction().commit();
	//	urls.addUrl(hash, url);
	}

	public void saveURLs() {
	//	em.getTransaction().begin();
		for (Entry<String, ShortURL> url : urls.getUrls().entrySet()) {
			ShortURL u = em.find(ShortURL.class, url.getKey());
			if (u == null) {
				em.persist(url.getValue());
			}

		}
	//	em.getTransaction().commit();
	}

	public ShortURL getUrl(String hash) {
	
		return em.find(ShortURL.class, hash);
	}

	public UrlMap getAll() {
	
		if (urls == null) {
			// em.getTransaction().begin();
			urls=new UrlMap();
			List<ShortURL> list = em.createQuery("select a FROM ShortURL a", ShortURL.class).getResultList();
			for (ShortURL url : list) {
				urls.addUrl(url.getHash(), url.getUrl(), url.getTimestamp());
			}
			//em.getTransaction().commit();
			return urls;
		} else {
			return urls;
		}
	
	}

}
