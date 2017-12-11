package enterprises.mccollum.icing.tmdb.required;

import enterprises.mccollum.icing.tmdb.filesystem.VFSNode;
import enterprises.mccollum.icing.tmdb.film.SerializableMovieMetadata;

public interface ScanListener {
	/**
	 * Notify the user that the given file is being scanned
	 * 
	 * @param fileName
	 */
	void scanning(String fileName);

	void noResultsFor(String name);

	/**
	 * Notify the user that the given file has the given metadata
	 * 
	 * @param file
	 * @param serializableMovieMetadata
	 */
	void found(VFSNode file, SerializableMovieMetadata serializableMovieMetadata);
	
	/**
	 * Notify the listener that scanning is complete
	 */
	void scanComplete();
}
