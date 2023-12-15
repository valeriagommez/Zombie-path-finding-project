package finalproject;

import java.util.ArrayList;

import finalproject.system.Tile;

public class Graph {

    // TODO level 2: Add fields that can help you implement this data type

    private ArrayList<Tile> verticesArrayG;

    private ArrayList<Edge> allEdges;


    // TODO level 2: initialize and assign all variables inside the constructor
    public Graph(ArrayList<Tile> vertices) {

        //  a constructor that builds the graph given a list containing all of its vertices.
        //  This graph should NOT contain any edges. The constructor should be used to initialize
        //  the vertices of the graph and whichever fields you decided to use in this class.

        this.verticesArrayG = vertices;
        this.allEdges = new ArrayList<>();
    }

    public ArrayList<Tile> getVertices() {
        return this.verticesArrayG;
    }

    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight){
        Edge newEdge = new Edge(origin, destination, weight);
        allEdges.add(newEdge);
    }

    //     TODO level 2: return a list of all edges in the graph
    public ArrayList<Edge> getAllEdges() {
        return this.allEdges;
    }

    // TODO level 2: return list of tiles adjacent to t
    public ArrayList<Tile> getNeighbors(Tile t) { // is this ok???

        // a method that takes a Tile as input and returns an ArrayList containing all the Tiles adjacent
        // to it in this graph.

        ArrayList<Tile> neighborsOfTile = new ArrayList<>();

        for (int i=0 ; i<this.allEdges.size() ; i++){

            Edge curEdge = this.allEdges.get(i);

            if (curEdge.origin.equals(t)){
                Tile tDestination = curEdge.destination;
                neighborsOfTile.add(tDestination);
            }

        }

        return neighborsOfTile;
    }

    public Edge getEdge(Tile origin, Tile destination){

        for (int i=0 ; i<this.allEdges.size() ; i++){

            Edge curEdge = this.allEdges.get(i);

            if (curEdge.getStart().equals(origin) && curEdge.getEnd().equals(destination)){
                return curEdge;
            }
        }

        return null;
    }

    public double computePathCost(ArrayList<Tile> path) {

        double totalCost = 0.0;

        for (int i=0 ; i<path.size()-1 ; i++) {

            Tile origin = path.get(i);
            Tile destination = path.get(i+1);

            Edge edgeBetween = getEdge(origin, destination);
            totalCost = totalCost + edgeBetween.weight;

        }

        return totalCost;
    }

    public static class Edge{
        Tile origin;
        Tile destination;
        double weight;

        // TODO level 2: initialize appropriate fields
        public Edge(Tile s, Tile d, double cost){
            this.origin = s;
            this.destination = d;
            this.weight = cost;
        }

        // TODO level 2: getter function 1
        public Tile getStart(){
            return this.origin;
        }

        // TODO level 2: getter function 2
        public Tile getEnd() {
            return this.destination;
        }

    }

}
