package enterprises.mccollum.icing.tmdb.tv;


import java.util.List;

public class ShowSearchResultContainer {
	Integer page;
	Integer total_results;
	Integer total_pages;
	List<SerializableShowMetadata> results = null;
	
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
	public List<SerializableShowMetadata> getResults() {
		return results;
	}
	public void setResults(List<SerializableShowMetadata> results) {
		this.results = results;
	}
}
