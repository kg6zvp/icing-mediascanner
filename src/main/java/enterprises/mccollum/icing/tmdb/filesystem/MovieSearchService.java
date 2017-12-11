package enterprises.mccollum.icing.tmdb.filesystem;

import java.util.Collections;
import java.util.List;

import org.apache.commons.text.similarity.LevenshteinDistance;

import enterprises.mccollum.icing.tmdb.TheMoviedbAPIClient;
import enterprises.mccollum.icing.tmdb.filesystem.VFSNode.Type;
import enterprises.mccollum.icing.tmdb.film.SerializableMovieMetadata;

public class MovieSearchService {
	protected TheMoviedbAPIClient movieDb;
	
	LevenshteinDistance lDistance = new LevenshteinDistance();

	public static final String TITLE_YEAR_REGEX = ".* \\([0-9][0-9][0-9][0-9]\\)";
	
	public MovieSearchService(TheMoviedbAPIClient movieDb) {
		this.movieDb = movieDb;
	}
	
	/**
	 * Parse and Search file path of movie in the movieDb
	 * @param filePath the full file path of the movie 
	 * @return List<{@link MovieMetadata} of all result from MovieDb
	 */
	public List<SerializableMovieMetadata> searchMovies(VFSNode file) {
		if(file.getType() != Type.FILE)
			return null;
			
		String fileName = file.getName();
		String title = getTitle(fileName);
		List<SerializableMovieMetadata> results = movieDb.searchMovies(title, getYear(fileName));
		
		//sort by Levenshtein Distance
		Collections.sort(results, (a, b) ->
				lDistance.apply(title, a.getTitle())
					.compareTo(lDistance.apply(title, b.getTitle())
		));
		return results;
	}
	
	protected String getTitle(String fileName) {
		if(hasYear(fileName)) {
			return fileName.substring(0, fileName.lastIndexOf('(')).trim();
		} else {
			return fileName;
		}
	}

	protected Integer getYear(String fileName) {
		if(hasYear(fileName)){
			String title = getTitle(fileName);
			Integer year = Integer.valueOf(fileName.substring(fileName.indexOf('(', title.length())+1, fileName.lastIndexOf(')')));
			return year;
		} else {
			return null;
		}
	}

	protected boolean hasYear(String fileName) {
		return fileName.matches(TITLE_YEAR_REGEX);
	}
}
