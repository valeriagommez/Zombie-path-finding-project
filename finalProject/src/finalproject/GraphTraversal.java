package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphTraversal {


	//TODO level 1: implement BFS traversal starting from s
	public static ArrayList<Tile> BFS(Tile s) {

//		This method takes a Tile as input which represents the starting point of the traversal.
//		It will then traverse the map and find all the reachable tiles from the given input tile using BFS.
//		It returns an ArrayList containing the Tiles in the same order as they have been visited.

//		NOTE: Some tiles are not designed to be traveled through, thus you can use the method isWalkable()
//		from the Tile class to avoid these obstacle tiles in your traversals.

		return null;
	}


	//TODO level 1: implement DFS traversal starting from s

//	private static ArrayList<Tile> depthRecursive(Tile start, ArrayList<Tile> visitedTiles) {
//
//		visitedTiles.add(start);
//
//		int numberOfNeighbors = start.neighbors.size();
//		boolean alreadyVisited = false;
//
//		for (int i=0 ; i<numberOfNeighbors ; i++) {
//
//			if (start.neighbors.get(i).isWalkable()) {
//
//				for (int j=0 ; j<visitedTiles.size() ; j++) {
//
//                    if (!(visitedTiles.get(j).equals(start.neighbors.get(i)))) {
//                        alreadyVisited = true;
//                        break;
//                    }
//				}
//
//				if (!alreadyVisited){
//					depthRecursive(start.neighbors.get(i), visitedTiles);
//				}
//			}
//		}
//
//		return visitedTiles;
//	}

	private static ArrayList<Tile> depthRecursive(Tile start, ArrayList<Tile> visitedArray){

		ArrayList<Tile> allWalkable = removeNonWalkable(start.neighbors);

		visitedArray.add(start);

		for (int i=0 ; i<allWalkable.size() ; i++){

			if ( !(alreadyVisited(allWalkable.get(i), visitedArray)) ){
				visitedArray = depthRecursive(allWalkable.get(i), visitedArray);
			}
		}

		return visitedArray;
	}

	private static boolean alreadyVisited(Tile tileToCheck, ArrayList<Tile> visitedArray){

		boolean alreadyVisitedTile = false;

		for (int i=0 ; i<visitedArray.size(); i++){

			if (visitedArray.get(i).equals(tileToCheck)){
				alreadyVisitedTile = true;
				break;
			}
		}

		return alreadyVisitedTile;
	}

	private static ArrayList<Tile> removeNonWalkable(ArrayList<Tile> neighborArray) {

		ArrayList<Tile> allWalkable = new ArrayList<>();

		for (int i=0 ; i<neighborArray.size() ; i++){
			allWalkable.add(neighborArray.get(i));
		}

		for (int i=0 ; i<allWalkable.size() ; i++){

			if ( !(allWalkable.get(i).isWalkable()) ) {

				allWalkable.remove(allWalkable.get(i));
			}
		}

		return allWalkable;

	}

	public static ArrayList<Tile> DFS(Tile s) {

//		This method takes a Tile as input which represents the starting point of the traversal.
//		It will then traverse the map and find all the reachable tiles from the given input tile using DFS.
//		It returns an ArrayList containing the Tiles in the same order as they have been visited.

// 		NOTE: Some tiles are not designed to be traveled through, thus you can use the method isWalkable()
//		from the Tile class to avoid these obstacle tiles in your traversals.

		ArrayList<Tile> visitedTiles = new ArrayList<>();

		return depthRecursive(s, visitedTiles);
	}

}  