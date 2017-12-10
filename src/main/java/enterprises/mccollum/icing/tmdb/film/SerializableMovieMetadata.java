package enterprises.mccollum.icing.tmdb.film;

import java.util.List;

public class SerializableMovieMetadata extends BaseMovieMetadata {
	protected List<Integer> genre_ids;

	public List<Integer> getGenre_ids() {
		return genre_ids;
	}
	public void setGenre_ids(List<Integer> genre_ids) {
		this.genre_ids = genre_ids;
	}
}