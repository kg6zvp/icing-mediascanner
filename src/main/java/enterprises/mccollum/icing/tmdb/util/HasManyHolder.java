package enterprises.mccollum.icing.tmdb.util;

import java.util.List;

public class HasManyHolder<T,N> {
	T owner;
	List<N> items;
	
	public HasManyHolder() {}
	
	public HasManyHolder(T owner, List<N> items) {
		this.owner = owner;
		this.items = items;
	}

	public T getOwner() {
		return owner;
	}
	public void setOwner(T owner) {
		this.owner = owner;
	}
	public List<N> getItems() {
		return items;
	}
	public void setItems(List<N> items) {
		this.items = items;
	}
}
