package enterprises.mccollum.icing.tmdb.required;

import java.util.List;

import enterprises.mccollum.icing.tmdb.filesystem.VFSNode;

public interface VFSBrowser {
	/**
	 * Create a VFSNode from the given path
	 * 
	 * @param path
	 * @return
	 */
	public VFSNode createVFSNode(String path);
	
	/**
	 * Get a list of nodes in a directory
	 * @param dir
	 * @return
	 */
	public List<VFSNode> browseDir(String dir);
	
	/**
	 * Get a list of nodes in a directory
	 * @param dir
	 * @return
	 */
	public List<VFSNode> browseDir(VFSNode dir);
	
	/**
	 * Get a list of nodes in a directory indicated by a parent node and directory name
	 * @param parent
	 * @param dir
	 * @return
	 */
	public List<VFSNode> browseRelative(VFSNode parent, VFSNode child);
}
