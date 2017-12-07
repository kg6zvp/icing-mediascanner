package enterprises.mccollum.icing.tmdb.tv;
import java.util.List;

public class SerializableShowMetadata {
	public Long id;
	public String name;
	public String original_name;
	
	public String poster_path;
	public Double popularity;
	public String backdrop_path;
	public Double vote_average;
	public String overview;
	public String first_air_date;
	public List<String> origin_country;
	public List<Long> genre_ids;
	public String original_language;
	public Long vote_count;
	
	public SerializableShowMetadata() {}

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
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public Double getPopularity() {
		return popularity;
	}
	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	public Double getVote_average() {
		return vote_average;
	}
	public void setVote_average(Double vote_average) {
		this.vote_average = vote_average;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getFirst_air_date() {
		return first_air_date;
	}
	public void setFirst_air_date(String first_air_date) {
		this.first_air_date = first_air_date;
	}
	public List<String> getOrigin_country() {
		return origin_country;
	}
	public void setOrigin_country(List<String> origin_country) {
		this.origin_country = origin_country;
	}
	public List<Long> getGenre_ids() {
		return genre_ids;
	}
	public void setGenre_ids(List<Long> genre_ids) {
		this.genre_ids = genre_ids;
	}
	public String getOriginal_language() {
		return original_language;
	}
	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}
	public Long getVote_count() {
		return vote_count;
	}
	public void setVote_count(Long vote_count) {
		this.vote_count = vote_count;
	}
}