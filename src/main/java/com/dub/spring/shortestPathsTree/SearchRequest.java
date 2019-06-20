package com.dub.spring.shortestPathsTree;

/** used in AJAX request */
public class SearchRequest {
	private Type type;
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}


	enum Type {
		SCCSEARCH, STEP, COLLECTION
	}
}
