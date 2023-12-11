package finalproject;


import finalproject.system.Tile;

import java.util.ArrayList;

public class ShortestPath extends PathFindingService {

    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
        generateGraph();
    }

	@Override
	public void generateGraph() {

		// TODO Auto-generated method stub

//        The method creates a weighted graph using the distance cost as weight. This graph should be then stored in
//        the appropriate field.

//        To make sure that the graph is generated each time a ShortestPath object is created, you should
//        add a call to this method inside the constructor.

//        Note: You can use BFS or DFS to help you get a list of all reachable tiles. Remember also that the
//        graph you want to build should only connect tiles that are designed to be travelled through. You can
//        use the method isWalkable() to help you figure out which tiles are not just obstacles.

        Tile startingTile = this.source;
        ArrayList<Tile> tilesReachedBySource = GraphTraversal.DFS(startingTile);

        ArrayList<Tile> graphVertices = GraphTraversal.DFS(startingTile);
        graphVertices.add(startingTile);

        Graph createdGraph = new Graph(graphVertices);

        for (int i=0 ; i<tilesReachedBySource.size() ; i++) {

            Tile destination = tilesReachedBySource.get(i);
            createdGraph.addEdge(startingTile, destination, destination.distanceCost);
            createdGraph.addEdge(destination, startingTile, startingTile.distanceCost); // not sure this is what
            // we're supposed to do with the weight.
        }

        this.g = createdGraph; // storing the created graph in the appropriate field

	}
    
}
