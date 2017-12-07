package enterprises.mccollum.icing.tmdb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import enterprises.mccollum.icing.tmdb.film.SerializableMovieMetadata;
import enterprises.mccollum.icing.tmdb.genre.SerializableGenre;
import enterprises.mccollum.icing.tmdb.required.GenericRestClient;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Supplier;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@RunWith(MockitoJUnitRunner.class)
public class TheMovieDbApiClientTest {
	@InjectMocks
	TheMoviedbAPIClient tmDb;
	
	@Before
	public void setupTests() {
		tmDb.restClient = new GenericRestClient() {
			public <T> T getRequest(final Class<T> klasse, final String url) {
				return makeMovieDbRequest(() -> {
					return ClientBuilder.newClient().target(url).request().get(klasse);
				});
			}
	
			/**
			 * Handles requests to TheMovieDb in a way that allows the consumer of this function not to worry about rate limits
			 * @param requestLambda The lambda that actually performs the request
			 * @return
			 */
			private <T> T makeMovieDbRequest(Supplier<T> requestLambda) {
				T result = null;
				while(result == null) {
					try {
						result = requestLambda.get();
					} catch(ClientErrorException e) {
						Response r = e.getResponse();
						if(r.getStatus() != 429) //we don't know what to do with it
							throw new RuntimeException(e.getCause());
						
						int waitTime = 250; //wait at least 250 milliseconds
						
						/**
						 * Deal with the Retry-After header if it exists
						 */
						String value = r.getHeaderString("Retry-After");
						if(value != null && value.matches("[0-9]*")) {
							waitTime += (1000*Integer.parseInt(value));
						}
						try {
							wait(waitTime);
						} catch (InterruptedException error) {
							error.printStackTrace();
						}
					}
				}
				return result;
			}
		};
	}
	
	@Test
	public void testSearch() {
		List<SerializableMovieMetadata> results = tmDb.searchMovies("Serenity", 2005);
		assertEquals(2, results.size()); //should contain two
		SerializableMovieMetadata real = null;
		FIND_REAL_RESULT: for(SerializableMovieMetadata result : results) {
			if(result.getId() == 16320) {
				real = result;
				break FIND_REAL_RESULT;
			}
		}
		assertNotNull(real);
	}
	
	@Test
	public void testGenres() {
		final Long ADVENTURE_GENRE_ID = 12L;
		final String ADVENTURE_GENRE_NAME = "Adventure";
		SerializableGenre adventureGenre = tmDb.getGenreById(ADVENTURE_GENRE_ID);
		assertNotNull(adventureGenre);
		assertEquals(ADVENTURE_GENRE_ID, adventureGenre.getId());
		assertEquals(ADVENTURE_GENRE_NAME, adventureGenre.getName());
	}
}
