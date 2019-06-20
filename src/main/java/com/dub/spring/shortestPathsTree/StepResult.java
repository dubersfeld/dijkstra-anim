package com.dub.spring.shortestPathsTree;

/** container for AJAX response */
public class StepResult {
	
	private ResponseVertex[] vertices;
	private Status status;
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ResponseVertex[] getVertices() {
		return vertices;
	}

	public void setVertices(ResponseVertex[] vertices) {
		this.vertices = vertices;
	}



	enum Status {
		STEP, FINISHED, OK
	}
}
