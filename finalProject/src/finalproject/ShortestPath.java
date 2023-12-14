package finalproject;

import finalproject.system.Tile;

public class ShortestPath extends PathFindingService {

    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
        this.generateGraph();
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
//        System.out.println(allVertices); // works

        Graph graphGenerated = new Graph(GraphTraversal.DFS(startingTile));
//        System.out.println(graphGenerated.getVertices()); // works


        for (int i=0 ; i<GraphTraversal.DFS(startingTile).size() ; i++) {

            Tile curVertex = GraphTraversal.DFS(startingTile).get(i);

            for (int j=0 ; j<curVertex.neighbors.size() ; j++){

                Tile curDestination = curVertex.neighbors.get(j);

                if (curDestination.isWalkable()){
                    graphGenerated.addEdge(curVertex, curDestination, curDestination.distanceCost); // not sure this is
                    // what we're supposed to do with the weight.
                }

            } // end of j for loop
        } // end of i for loop

        this.g = graphGenerated;

    } // end of the generateGraph() method


}
