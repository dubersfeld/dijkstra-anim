# dijkstra-anim
Java based animated demonstration of Dijkstra algorithm applied to build a Shortest Paths Tree  on a strongly connected directed graph

I present here a Java based demonstration of the Dijkstra algorithm that builds a Shortest Paths Tree on a given weighted directed graph.

The graph is strongly connected. This is of course not a requirement for using the Dijkstra algorithm, only a convenience choice that makes the demonstration easier because the source is connected to any vertex by a path.

Following are some implementation details:

A directed graph in randomly initialized in the browser (Javascript).

It is sent to the server as a JSON object.

The server extracts the largest strongly connected component and equips it with a positive weight function. This weighted directed graph is sent back to the browser (Java).

Then the Dijkstra algorithm main loop is executed by the server as a response to an Ajax request from the browser (Java).

All intermediate results are save on server side as a collection that is sent back to the browser and used for animation (Javascript).

At a given step an edge that connects a vertex to its predecessor is drawned blue. Note that the actual predecessor of a given vertex can change from one step to the next.

A newly discovered vertex is drawn green. A finalized vertex is drawned blue.

The distance to the source is displayed for each vertex at each step.

All vertices outside the source are initialized with distance 1000.

When deployed on Tomcat the context root is:

dikstra-shortest-path

Dominique Ubersfeld, Cachan, France
