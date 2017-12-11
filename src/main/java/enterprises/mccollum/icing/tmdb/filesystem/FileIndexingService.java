package enterprises.mccollum.icing.tmdb.filesystem;

import java.util.List;
import java.util.stream.Collectors;

import enterprises.mccollum.icing.tmdb.filesystem.VFSNode.Type;
import enterprises.mccollum.icing.tmdb.media.MediaFileUtils;
import enterprises.mccollum.icing.tmdb.required.VFSBrowser;

public class FileIndexingService {
	/**
	 * Recursively scan for videos given a starting node
	 * @param filesystem
	 * @param source
	 * @return
	 */
	public List<VFSNode> findVideos(VFSBrowser filesystem, VFSNode source) {
		List<VFSNode> tmp = filesystem.browseDir(source);
		List<VFSNode> files = tmp.stream()
				.filter(n -> n.getType() == Type.FILE
					&& MediaFileUtils.getMimeTypeByFileName(n.getName()).startsWith("video/"))
				.collect(Collectors.toList());
		List<VFSNode> dirs = tmp.stream().filter(n -> n.getType() == Type.DIRECTORY).collect(Collectors.toList());
		for(VFSNode dir : dirs)
			files.addAll(findVideos(filesystem, dir));
		return files;
	}
}
