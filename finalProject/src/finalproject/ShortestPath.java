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
        ArrayList<Tile> allVertices = GraphTraversal.DFS(startingTile); // not sure if i'm probably missing some
        // tiles by calling this method
        allVertices.add(startingTile);

        Graph graphGenerated = new Graph(allVertices);

        for (int i=0 ; i<allVertices.size() ; i++) {

            Tile curVertex = allVertices.get(i);
            ArrayList<Tile> curAdjacentVertices = findAdjacent(curVertex, allVertices);

            for (int j=0 ; j<curAdjacentVertices.size() ; j++){

                Tile curDestination = curAdjacentVertices.get(j);

                graphGenerated.addEdge(curVertex, curDestination, curDestination.distanceCost); // not sure this is
                // what we're supposed to do with the weight.
                graphGenerated.addEdge(curDestination, curVertex, curVertex.distanceCost);

            } // end of j for loop
        } // end of i for loop

        this.g = graphGenerated;

    } // end of the generateGraph() method

    private ArrayList<Tile> findAdjacent(Tile centerTile, ArrayList<Tile> graphVertices){

        Tile up;
        Tile down;
        Tile right;
        Tile left;

        ArrayList<Tile> adjacentAndWalkable = new ArrayList<>();

        for (int i=0 ; i<graphVertices.size() ; i++) {

            Tile curVertex = graphVertices.get(i);

            if (curVertex.isWalkable()) {

                if ( (curVertex.xCoord == centerTile.xCoord) && (curVertex.yCoord == centerTile.yCoord-1) ){
                    up = curVertex;
                    adjacentAndWalkable.add(up);
                }

                else if ( (curVertex.xCoord == centerTile.xCoord) && (curVertex.yCoord == centerTile.yCoord+1) ){
                    down = curVertex;
                    adjacentAndWalkable.add(down);
                }

                else if ( (curVertex.xCoord == centerTile.xCoord+1) && (curVertex.yCoord == centerTile.yCoord) ){
                    right = curVertex;
                    adjacentAndWalkable.add(right);
                }

                else if ( (curVertex.xCoord == centerTile.xCoord-1) && (curVertex.yCoord == centerTile.yCoord) ){
                    left = curVertex;
                    adjacentAndWalkable.add(left);
                }

            } // end of if Walkable
        } // end of for loop

        return adjacentAndWalkable;

    } // end of findAdjacent



}
