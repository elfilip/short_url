package elias.rest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="URL", schema="mysql")
public class ShortURL {
	
	@Column
	@Size(min=4)
	private String url;
	@Column
	@Id
	private String hash;
	@Column
	private Date timestamp;
	
	public ShortURL(){
		
	}
	
	public ShortURL(String url, String hash, Date timestamp) {
		super();
		this.url = url;
		this.hash = hash;
		this.timestamp = timestamp;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String toString(){
		if(url!=null){
			return url;
		}else{
			return "unknown";
		}
	}
	
	
}
