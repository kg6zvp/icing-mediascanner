package enterprises.mccollum.icing.tmdb.film;

import java.util.List;

public class MovieSearchResultContainer {
	Integer page;
	Integer total_results;
	Integer total_pages;
	List<SerializableMovieMetadata> results = null;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal_results() {
		return total_results;
	}
	public void setTotal_results(Integer total_results) {
		this.total_results = total_results;
	}
	public Integer getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(Integer total_pages) {
		this.total_pages = total_pages;
	}
	public List<SerializableMovieMetadata> getResults() {
		return results;
	}
	public void setResults(List<SerializableMovieMetadata> results) {
		this.results = results;
	}
}
