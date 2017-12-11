package enterprises.mccollum.icing.tmdb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enterprises.mccollum.icing.tmdb.film.MovieSearchResultContainer;
import enterprises.mccollum.icing.tmdb.film.SerializableMovieMetadata;
import enterprises.mccollum.icing.tmdb.genre.GenreContainer;
import enterprises.mccollum.icing.tmdb.genre.SerializableGenre;
import enterprises.mccollum.icing.tmdb.required.GenericRestClient;
import enterprises.mccollum.icing.tmdb.tv.SerializableShowMetadata;
import enterprises.mccollum.icing.tmdb.tv.ShowSearchResultContainer;

public class TheMoviedbAPIClient {
	public static final String API_KEY = "c8b6dbadc2817d249f4c587da01533b7"; //kodi: "6889f6089877fd092454d00edb44a84d"
	public static final String MOVIE_GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key="+API_KEY; //+"&language=en-US";
	public static final String TV_GENRE_URL = "https://api.themoviedb.org/3/genre/tv/list?api_key="+API_KEY; //+"&language=en-US";
	
	GenericRestClient restClient;

	public TheMoviedbAPIClient(GenericRestClient restClient) {
		this.restClient = restClient;
	}
	
	transient Map<Long, SerializableGenre> genresCache = null;
	
	public List<SerializableMovieMetadata> searchMovies(String query, Integer year){
		MovieSearchResultContainer result = null;
		result = restClient.<MovieSearchResultContainer>getRequest(MovieSearchResultContainer.class, buildMovieSearchUrl(query, year));
		if(result == null) return null;
		return result.getResults();
	}

	private String buildMovieSearchUrl(String query, Integer year) {
		StringBuilder sb = new StringBuilder(String.format("http://api.themoviedb.org/3/search/movie?api_key=%s&query=%s", API_KEY, query));
		if(year != null)
			sb.append("&year="+year);
		return sb.toString();
	}
	
	public List<SerializableShowMetadata> searchShows(String query, Integer year){
		ShowSearchResultContainer result = null;
		result = restClient.<ShowSearchResultContainer>getRequest(ShowSearchResultContainer.class, buildTVSearchUrl(query, year));
		if(result == null) return null;
		return result.getResults();
	}
	
	private String buildTVSearchUrl(String query, Integer year) {
		StringBuilder sb = new StringBuilder(String.format("http://api.themoviedb.org/3/search/tv?api_key=%s&query=%s", API_KEY, query));
		if(year != null)
			sb.append("&first_air_date_year="+year);
		return sb.toString();
	}
	
	public SerializableGenre getGenreById(Long id) {
		if(genresCache == null) {
			genresCache = new HashMap<Long, SerializableGenre>(32);
			
			List<SerializableGenre> movieGenresList = restClient.getRequest(GenreContainer.class, MOVIE_GENRE_URL).getGenres();
			for(SerializableGenre genre : movieGenresList) {
				genresCache.put(genre.getId(), genre);
			}
		}
		
		if(genresCache.get(id) == null) {
			List<SerializableGenre> tvGenresList = restClient.getRequest(GenreContainer.class, TV_GENRE_URL).getGenres();
			if(genresCache.get(id) != null)
				return genresCache.get(id);
			for(SerializableGenre genre : tvGenresList) {
				genresCache.put(genre.getId(), genre);
			}
		}
		return genresCache.get(id);
	}
}
