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
        ArrayList<Tile> tilesReached = GraphTraversal.DFS(startingTile);
        ArrayList<Tile> edges; // use edgesFromTiles to build the edges for the graph, and then do something
                // to store the weight of the graph jcomprends pas bro

        Graph createdGraph = new Graph(tilesReached);

        this.g = createdGraph; // storing the created graph in the appropriate field

	}
    
}
