package com.dub.site.shortestPathsTree;

import java.util.List;

/** A simple encapsulating class used for Ajax response */

public class SPTResponseColl {
	
	private Status status;
	private List<JSONSnapshot> snapshots;
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	

	public List<JSONSnapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<JSONSnapshot> snapshots) {
		this.snapshots = snapshots;
	}



	public static enum Status {
		OK, ERROR
	}
}
