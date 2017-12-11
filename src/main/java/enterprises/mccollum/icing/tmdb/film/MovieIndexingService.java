package enterprises.mccollum.icing.tmdb.film;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enterprises.mccollum.icing.tmdb.filesystem.FileIndexingService;
import enterprises.mccollum.icing.tmdb.filesystem.MovieSearchService;
import enterprises.mccollum.icing.tmdb.filesystem.VFSNode;
import enterprises.mccollum.icing.tmdb.required.ScanListener;
import enterprises.mccollum.icing.tmdb.required.VFSBrowser;

public class MovieIndexingService {
	protected VFSBrowser filesystem;
	protected FileIndexingService fileIndexer;
	protected MovieSearchService movieSearch;
	
	public MovieIndexingService(MovieSearchService movieSearch,
								VFSBrowser filesystem,
								FileIndexingService fileIndexer) {
		//THIS CONSTRUCTOR IS FOR TEST PURPOSES TO INJECT A FILE INDEXER IF NECESSARY
		this.movieSearch = movieSearch;
		this.filesystem = filesystem;
		this.fileIndexer = fileIndexer;
	}
	
	public MovieIndexingService(VFSBrowser filesystem, MovieSearchService movieSearch) {
		this(movieSearch, filesystem, new FileIndexingService());
	}
	
	public Map<VFSNode, SerializableMovieMetadata> performIndex(ScanListener user, VFSNode src){
		Map<VFSNode, SerializableMovieMetadata> dict = new HashMap<VFSNode, SerializableMovieMetadata>();
		//get all video files in source and process
		for(VFSNode file : fileIndexer.findVideos(filesystem, src)) {
			user.scanning(file.getName());
			List<SerializableMovieMetadata> results = movieSearch.searchMovies(file);
			if(results.size() < 1) {
				user.noResultsFor(file.getName());
			} else if(results.size() == 1) {
				user.found(file, results.get(0));
				dict.put(file, results.get(0));
			} else {
				searchResults : for(SerializableMovieMetadata cm : results) {
					if(cm.getTitle() != null
							&& cm.getPoster_path() != null
							&& cm.getOverview() != null) {
						user.found(file, cm);
						dict.put(file, cm);
						break searchResults;
					}
				}
			}
		}
		//movieSearch.searchMovies(file);
		return dict;
	}
}
