package com.dub.site.shortestPathsTree;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dub.config.annotation.WebController;


@WebController
public class SPTController {
	
	@Inject
	private GraphServices graphServices;
	
	/** Initialize graph for SCC search */
	@RequestMapping(value="initGraph")
	@ResponseBody
	public SPTResponse initGraph(@RequestBody GraphInitRequest message, 
				HttpServletRequest request) 
	{	
		List<JSONEdge> jsonEdges = message.getJsonEdges();
		List<JSONVertex> jsonVertices = message.getJsonVertices();
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("graph") != null) {
			session.removeAttribute("graph");
		}
		
		DFSGraph graph = graphServices.jsonToDFS(jsonEdges, jsonVertices);
		
		session.setAttribute("graph", graph);
			
		SPTResponse sptResponse = new SPTResponse();
		sptResponse.setStatus(SPTResponse.Status.OK);
		
		// here the graph is ready for the search loop
	
		System.out.println("initGraph completed");
			
		return sptResponse;
	}
		
	@RequestMapping(value="findLargestSCC")
	@ResponseBody
	public SPTResponse findLargestSCC(@RequestBody SearchRequest message, 
											HttpServletRequest request) 
	{	
		System.out.println("controller: findLargestSCC begin");
		
		SPTResponse sptResponse = new SPTResponse();
		
		/** retrieve the graph from the session context */
		HttpSession session = request.getSession();
		
		if (session.getAttribute("comp") != null) {
			session.removeAttribute("comp");
		}
		
		DFSGraph graph = (DFSGraph)session.getAttribute("graph");
						
		SPTGraph comp = graph.getLargestSCC();
			
		comp.randomizeWeights();
		
		/** equip the component with random weights */
		comp.displayWeights();
		
		/** initialize the component fro Dijkstra algorithm */
		comp.initDijkstra();
		
		JSONSnapshot snapshot = graphServices.sptToJSON(comp);
		
		sptResponse.setSnapshot(snapshot);
		sptResponse.setStatus(SPTResponse.Status.OK);
		
		/** attach component to session context */
		session.setAttribute("comp", comp);
		
		System.out.println("controller: findSCC return");
		return sptResponse;
	}// findSCC
	
	@RequestMapping(value="searchGraph")
	@ResponseBody
	public SPTResponseColl searchGraph(@RequestBody SearchRequest message, 
											HttpServletRequest request) 
	{	
		/** retrieve component from session context */
		HttpSession session = request.getSession();
		SPTGraph comp = (SPTGraph)session.getAttribute("comp");
		
		SPTResponseColl sptResponse = new SPTResponseColl();
		
		List<JSONSnapshot> snapshots = new ArrayList<>();
		
		//int count = 0;
		while (!comp.isFinished()) {
			comp.searchStep();
			snapshots.add(graphServices.sptToJSON(comp));
		}
		
		sptResponse.setSnapshots(snapshots);
		sptResponse.setStatus(SPTResponseColl.Status.OK);
		
		System.out.println("controller: searchGraph return");
		return sptResponse;
	}// searchGraph
	
}
