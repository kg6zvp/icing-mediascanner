package enterprises.mccollum.icing.tmdb.data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class BaseMediaFile {
	
	protected String filePath;
	
	protected String mimeType;
	
	public abstract Long getId();
	
	public abstract void setId(Long id);
	
	public String getFilePath() {
		return filePath;
	}
	public String getEncodedFilePath() {
		try {
			return URLEncoder.encode(filePath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "oops";
	}
	public void setFilePath(String relPath) {
		this.filePath = relPath;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getMimeType() {
		return mimeType;
	}
}
