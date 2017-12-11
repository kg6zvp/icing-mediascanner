package enterprises.mccollum.icing.tmdb.media;

import com.github.amr.mimetypes.MimeType;
import com.github.amr.mimetypes.MimeTypes;

public final class MediaFileUtils {
	private static final String UNKNOWN_MIME_TYPE = "unknown/x";
	
	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	public static String getMimeTypeByFileName(String fileName){
		MimeType mimeType = MimeTypes.getInstance()
				.getByExtension(getFileExtension(fileName));
		if(mimeType == null)
			return UNKNOWN_MIME_TYPE;
		return mimeType.getMimeType();
	}
}
