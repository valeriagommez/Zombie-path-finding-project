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

    public ArrayList<Tile> getVertices(){
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
	
	// TODO level 2: return total cost for the input path

    public ArrayList<Edge> edgesFromTiles(ArrayList<Tile> tiles){

        ArrayList<Edge> edgesArray = new ArrayList<>();

        for (int i=0 ; i<tiles.size()-1 ; i++) {

            Tile origin = tiles.get(i);
            Tile destination = tiles.get(i+1);
            double weight = destination.distanceCost + destination.timeCost + destination.damageCost; // Change this
            // according to the graph we're building?? (p.9)

            Edge newEdge = new Edge(origin, destination, weight);

            edgesArray.add(newEdge);
        }

        return edgesArray;

    }

    private ArrayList<Edge> edgesFromTilesDistance(ArrayList<Tile> tiles) {

        ArrayList<Edge> edgesArray = new ArrayList<>();

        for (int i=0 ; i<tiles.size()-1 ; i++) {

            Tile origin = tiles.get(i);
            Tile destination = tiles.get(i+1);
            double weight = destination.distanceCost;

            Edge newEdge = new Edge(origin, destination, weight);

            edgesArray.add(newEdge);
        }

        return edgesArray;

    }

	public double computePathCost(ArrayList<Tile> path) {

        double totalCost = 0.0;
        ArrayList<Edge> edgesArray = edgesFromTiles(path);

        for (int i=0 ; i<edgesArray.size() ; i++){
            totalCost = totalCost + edgesArray.get(i).weight;
        }

		return totalCost;
	}

//    public double computePathCostDistance(ArrayList<Tile> path) {
//
//        double totalCost = 0.0;
//        ArrayList<Edge> edgesArray = edgesFromTilesDistance(path);
//
//        for (int i=0 ; i<edgesArray.size() ; i++){
//            totalCost = totalCost + edgesArray.get(i).weight;
//        }
//
//        return totalCost;
//    }

   
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
