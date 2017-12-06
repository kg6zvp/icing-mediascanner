package enterprises.mccollum.icing.tmdb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

public class TheMoviedbAPIClient {
	public static final String API_KEY = "c8b6dbadc2817d249f4c587da01533b7"; //kodi: "6889f6089877fd092454d00edb44a84d"
	public static final String MOVIE_GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key="+API_KEY+"&language=en-US";
	public static final String TV_GENRE_URL = "https://api.themoviedb.org/3/genre/tv/list?api_key="+API_KEY+"&language=en-US";

	@Inject
	GenericRestClient restClient;
	
	ProgressReceiver progressReceiver;
	
	public List<MovieMetadata> searchMovies(String query, Integer year){
		MediaSearchResultContainer result = null;
		Logger.getLogger("TmDbClient").log(Level.INFO,
				String.format("Search Url: %s", buildSearchUrl(query, year)));
		result = restClient.<MediaSearchResultContainer>getRequest(MediaSearchResultContainer.class, buildSearchUrl(query, year));
		/*result = makeMovieDbRequest(() -> {
			return ClientBuilder.newClient().target(buildSearchUrl(query, year)).request().get(MediaSearchResultContainer.class);
		});//*/
		return result.getResults();
	}

	private String buildSearchUrl(String query, Integer year) {
		StringBuilder sb = new StringBuilder(String.format("http://api.themoviedb.org/3/search/movie?api_key=%s&query=%s", API_KEY, query));
		if(year != null)
			sb.append("&year="+year);
		return sb.toString();
	}
	
	SerializableGenre getGenreById(Long id) {
		/*JsonObject movieGenres = makeMovieDbRequest(() -> {
			String json = ClientBuilder.newClient().target(MOVIE_GENRE_URL).request().get(String.class);
			try(JsonReader reader = Json.createReader(new StringReader(json))) {
				return reader.readObject();
			}
		});
		JsonArray genres = movieGenres.getJsonArray("genres");
		for(int i = 0; i < genres.size(); ++i) {
			JsonObject genreJson = genres.getJsonObject(i);
			Genre genre = new Genre(genreJson);
			if(genre.id == id)
				return genre;
		} //*/
		return null;
	}
}
