package com.dub.spring.shortestPathsTree;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class GraphServices {
	
	public JSONSnapshot sptToJSON(SPTGraph graph) {
		int N = graph.getVertices().length;
		
		JSONSnapshot snapshot = new JSONSnapshot(N); 
		
		for (int i = 0; i < N; i++) {// for each vertex
			snapshot.getVertices()[i] 
								= new JSONVertex((SPTVertex)graph.getVertices()[i]);
		}
		
		for (int i = 0; i < N; i++) {// for each vertex of the original graph
			List<WeightedEdge> adjacency = graph.getVertices()[i].getAdjacency();
			int Nadj = adjacency.size();
			JSONAdjacency enclume = new JSONAdjacency(Nadj);
			
			for (int k = 0; k < Nadj; k++) {
				enclume.getAdjacency()[k] 
						= new WeightedEdge(adjacency.get(k));
			}
			
			snapshot.getAdjacencies()[i] = enclume;
			
		}// for
		
		return snapshot;
	} 

	public JSONSnapshot dfsToJSON(DFSGraph graph) {
		int N = graph.getVertices().length;
		
		JSONSnapshot snapshot = new JSONSnapshot(N); 
		
		for (int i = 0; i < N; i++) {// for each vertex
			snapshot.getVertices()[i] 
								= new JSONVertex((DFSVertex)graph.getVertices()[i]);
		}
		
		for (int i = 0; i < N; i++) {// for each vertex of the original graph
			List<WeightedEdge> adjacency = graph.getVertices()[i].getAdjacency();
			int Nadj = adjacency.size();
			JSONAdjacency enclume = new JSONAdjacency(Nadj);
			
			for (int k = 0; k < Nadj; k++) {
				enclume.getAdjacency()[k] 
						= new WeightedEdge(adjacency.get(k));
			}
			
			snapshot.getAdjacencies()[i] = enclume;
			
		}// for
		
		return snapshot;
	} 

	
	public DFSGraph jsonToDFS(
			List<JSONEdge> jsonEdges,
			List<JSONVertex> jsonVertices) 
	{
		DFSGraph graph = new DFSGraph(jsonVertices.size());
		
		for (int i1 = 0; i1 < jsonVertices.size(); i1++) {
			DFSVertex v = new DFSVertex();
			v.setName(jsonVertices.get(i1).getName());
			v.setColor(VertexColor.BLACK);
			graph.getVertices()[i1] = v;
		}// for
		
		for (int i1 = 0; i1 < jsonEdges.size(); i1++) {
			JSONEdge edge = jsonEdges.get(i1);
			int from = edge.getFrom();
			int to = edge.getTo();
			DFSVertex v1 = (DFSVertex)graph.getVertices()[from];
			
			v1.getAdjacency().add(new WeightedEdge(to));
		}
		
		Integer[] indices = new Integer[graph.getVertices().length];
		for (int i = 0; i < graph.getVertices().length; i++) {
			indices[i] = i;
		}
	
		graph.setIndices(indices);
		
		return graph;
		
	}// jsonToDFS
	
	public void getLargestSCC(DFSGraph graph) {
			
		int maxTreeNum = 0; 
		
		for (int i = 0; i < graph.getN(); i++) {// for each vertex
	
			DFSVertex dfsv = (DFSVertex)graph.getVertices()[i];
			System.out.println("for "  + i + " " + dfsv.getTree());
			
			if (dfsv.getTree() > maxTreeNum) {
				maxTreeNum = dfsv.getTree();
			}
		}// for
		
		System.out.println("maxTreeNum " + maxTreeNum);
		
	}
	
}
