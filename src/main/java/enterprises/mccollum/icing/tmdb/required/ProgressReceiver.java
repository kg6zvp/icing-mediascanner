package enterprises.mccollum.icing.tmdb.required;

public interface ProgressReceiver {
	/**
	 * Update the progress for display purposes or whatever
	 * 
	 * @param completed the number of files scanned
	 * @param total the number of total files to be scanned
	 */
	void updateProgress(int completed, Integer total);
	
	void noResultsFor(String query, Integer year);
}
