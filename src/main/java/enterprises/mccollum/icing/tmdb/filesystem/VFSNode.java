package enterprises.mccollum.icing.tmdb.filesystem;

/**
 * Virtual FileSystem Node, will be used functionally by the scanner
 * 
 * It can represent a file, folder or path.
 * 
 * @author smccollum
 *
 */
public class VFSNode {
	protected VFSNode parent;
	
	protected String name;

	protected char separator;
	
	public static enum Type{
		FILE("enum.Node.Type.FILE"),
		DIRECTORY("enum.Node.Type.DIRECTORY"),
		PATH("enum.Node.Type.PATH"); //where name is a complete path
		
		String key;
		
		Type(String key) {
			this.key = key;
		}
		
		public String getKey() {
			return key;
		}
	};
	
	protected Type type;
	
	public VFSNode(char separator) {
		this.separator = separator;
		
	}
	public VFSNode() { //default separator is forward-slash as on *NIX systems
		this('/');
	}
	
	public VFSNode getParent() {
		return parent;
	}
	public void setParent(VFSNode parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public char getSeparator() {
		return separator;
	}
	public String getCompletePath() {
		return (parent != null ? parent.getCompletePath() + separator + name : name);
	}
}
