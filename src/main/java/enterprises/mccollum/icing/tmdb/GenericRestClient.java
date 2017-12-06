package enterprises.mccollum.icing.tmdb;

public interface GenericRestClient {
	/**
	 * Perform a GET request on the given url
	 * @param url the url to perform the GET request on
	 * @return the deserialized result
	 */
	<T> T getRequest(Class<T> klasse, String url);
}
