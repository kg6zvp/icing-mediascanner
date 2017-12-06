package enterprises.mccollum.icing.tmdb;

public class SerializableGenre {
	protected Long id;
	protected String name;
	
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
}
