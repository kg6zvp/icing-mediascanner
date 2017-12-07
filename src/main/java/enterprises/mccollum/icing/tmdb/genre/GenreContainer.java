package enterprises.mccollum.icing.tmdb.genre;

import java.util.List;

public class GenreContainer {
	List<SerializableGenre> genres;

	public List<SerializableGenre> getGenres() {
		return genres;
	}
	public void setGenres(List<SerializableGenre> genres) {
		this.genres = genres;
	}
}
