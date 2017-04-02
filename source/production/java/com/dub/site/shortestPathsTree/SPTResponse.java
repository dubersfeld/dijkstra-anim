package com.dub.site.shortestPathsTree;

/** A simple encapsulating class used for Ajax response */

public class SPTResponse {
	
	private Status status;
	private JSONSnapshot snapshot;
	

	public Status getStatus() {
		return status;
	}




	public void setStatus(Status status) {
		this.status = status;
	}




	public JSONSnapshot getSnapshot() {
		return snapshot;
	}




	public void setSnapshot(JSONSnapshot snapshot) {
		this.snapshot = snapshot;
	}




	public static enum Status {
		OK, ERROR, INIT, STEP, FINISHED
	}
}
