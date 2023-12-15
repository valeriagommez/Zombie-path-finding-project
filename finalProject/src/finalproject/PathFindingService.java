package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class PathFindingService {
    Tile source;
    Graph g;

    public PathFindingService(Tile start) {
        this.source = start;
    }

    public abstract void generateGraph();

    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination

    private void initialization(ArrayList<Tile> vertices, Tile start){

        for (int i=0 ; i<vertices.size() ; i++){
            Tile curVertex = vertices.get(i);
            curVertex.costEstimate = Double.MAX_VALUE;
            curVertex.predecessor = null;
        }

        start.costEstimate = 0;
    }


    private void dijkstra(ArrayList<Tile> vertices, Tile start){

        initialization(vertices, start);

        ArrayList<Tile> S = new ArrayList<>(); // array with fully analyzed vertices
        TilePriorityQ Q = new TilePriorityQ(vertices);

        for (int i=0 ; i<Q.getSize() ; i++){

            Tile tile1 = Q.removeMin();
//            System.out.println(S); // works
            S.add(tile1);

            for (int j=0 ; j < this.g.getNeighbors(tile1).size() ; j++) {

                Tile tile2 = this.g.getNeighbors(tile1).get(j);

                double curEdgeWeight = tile2.distanceCost; // I DON'T THINK THIS IS RIGHT

//                System.out.println();
//                System.out.println("tile2.costEstimate : " + tile2.costEstimate);
//                System.out.println("tile1.costEstimate : " + tile1.costEstimate);
//                System.out.println("tile2.distanceCost : " + curEdgeWeight);
//                System.out.println(tile2.costEstimate + " > " + tile1.costEstimate + " + " + curEdgeWeight); // This seems to work

                if (tile2.costEstimate > tile1.costEstimate + curEdgeWeight) { // RELAXING THE EDGES
                    double newEstimate = tile1.costEstimate + curEdgeWeight;

//                    System.out.println("If yes!! then newEstimate = " + newEstimate);

                    Q.updateKeys(tile2, tile1, newEstimate); // tile2 predecessor = t1 and costEstimate = newEstimate
                }

            } // end of 2nd for loop
        } // end of 1st for loop

    } // end of findPath()

    private void reverseArray(ArrayList<Tile> arr){

        if (arr.size() > 1){
            Tile temp = arr.remove(0);
            reverseArray(arr);
            arr.add(temp);
        }
    }

    public ArrayList<Tile> findPath(Tile startNode) {

        // The method uses Dijkstra’s algorithm
        // on the Graph stored in the field g to find a minimum weight path to the destination from the
        // input Tile. Note that the result of running Dijkstra’s algorithm is that each node in the graph will
        // contain the information needed to find the minimum weight path from the source to this node. So,
        // after running the algorithm you will need to use the information stored in the predecessor field
        // to backtrack and find the list of Tiles that make up the path to be traversed from the start node to
        // the destination.

        ShortestPath shortestPath = new ShortestPath(startNode);
        shortestPath.generateGraph();

        dijkstra(this.g.getVertices(), startNode);

        Tile destination = null;

        System.out.println("origin : " + startNode); // Works

        for (int i=0 ; i<this.g.getVertices().size() ; i++) {

            if (this.g.getVertices().get(i).isDestination) { // ??
                destination = this.g.getVertices().get(i);
                System.out.println("destination : " + destination); // works
                break;
            }
        }

        ArrayList<Tile> path = new ArrayList<>();
        Tile temp = destination;

        path.add(destination);
        System.out.println("array with the destination : " + path); // works
        System.out.println("destination predecessor : " + destination.predecessor); // works for 1, but null for 2. Why?

        while (temp != null && temp != startNode){
            path.add(temp.predecessor);
            temp = temp.predecessor;
        }

        reverseArray(path);
        System.out.println("path with the correct order : " + path); // works

        return path;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {

//        This method is very similar to the one implemented in Level 4. The only change would be to generate
//        the path to the specific destination tile received as input. For this purpose, notice that Dijkstra’s
//        algorithm will never visit each node in the graph (reachable from the source) exactly once. This means
//        that once a node has been visited by the algorithm, one is already able to figure out what is the
//        shortest path from the source to this node.

        return null;

    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){

//        This method builds the shortest paths from the source to the destination, making sure to visit each
//        of the waypoints in the order in which they have been provided as input. Use the other methods that
//        you have already implemented to help you find such path. Please note that: the destination tile will
//        not be provided within the list of waypoints. You can figure out which one is the destination tile by
//        accessing the field isDestination from the Tile class.

        return null;
    }

}
