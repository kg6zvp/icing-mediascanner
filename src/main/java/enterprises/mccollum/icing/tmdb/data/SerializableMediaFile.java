package enterprises.mccollum.icing.tmdb.data;

/**
 * @author smccollum
 *
 */
public class SerializableMediaFile extends BaseMediaFile {
	Long id;
	
	protected Long mediaSourceId;

	public Long getMediaSourceId() {
		return mediaSourceId;
	}
	public void setMediaSourceId(Long mediaSourceId) {
		this.mediaSourceId = mediaSourceId;
	}
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
