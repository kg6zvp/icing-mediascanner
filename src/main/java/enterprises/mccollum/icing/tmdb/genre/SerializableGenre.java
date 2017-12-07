package enterprises.mccollum.icing.tmdb.genre;

public class SerializableGenre {
	protected Long id;
	protected String name;
	protected String language;
	
	public SerializableGenre() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
