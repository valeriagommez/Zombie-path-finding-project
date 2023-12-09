package finalproject;

import java.util.ArrayList;


import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type

	private ArrayList<Tile> priorityQueue;

	private int size = priorityQueue.size();

	// TODO level 3: implement the constructor for the priority queue

	private void addUpHeap(Tile tileToAdd, ArrayList<Tile> priorityQueue){
		this.size = size + 1;
		priorityQueue.add(size, tileToAdd);
		double tileCost = tileToAdd.costEstimate;
		int i = size;

		while ( (i<1) && (priorityQueue.get(i).costEstimate < priorityQueue.get(i/2).costEstimate) ) {

			Tile temp =  priorityQueue.get(i/2); // swapping object at i and i/2
			priorityQueue.add(i/2, priorityQueue.get(i));
			priorityQueue.add(i, temp);

			i = i/2;
		}
	}

	private ArrayList<Tile> buildHeap(ArrayList<Tile> vertices){

		ArrayList<Tile> priorityQueue = new ArrayList<>();

		for (int i=0 ; i<vertices.size() ; i++){
			addUpHeap(vertices.get(i), priorityQueue); // do this or the buildHeapFast() method in the slides
			// (30 - Heaps, slide 85)
		}

		return priorityQueue;
	}

	public TilePriorityQ (ArrayList<Tile> vertices) {

		// a constructor that builds a priority queue with the Tiles received as input.

		this.priorityQueue = buildHeap(vertices);
	}
	
	// TODO level 3: implement remove min as seen in class

	private void downHeap(int startIndex, int maxIndex, ArrayList<Tile> priorityQueue) {
		int i = startIndex;

		while(2*i <= maxIndex){

			int child  = 2*i;

			if (child < maxIndex) {

				if (priorityQueue.get(child + 1).costEstimate < priorityQueue.get(child).costEstimate){
						child = child + 1;
				}
			}

			if (priorityQueue.get(child).costEstimate < priorityQueue.get(i).costEstimate) {

				Tile temp =  priorityQueue.get(child); // swapping object at i and child
				priorityQueue.add(child, priorityQueue.get(i));
				priorityQueue.add(i, temp);

				i = child;
			}

			else {
				break;
			}
		}
	}


	public Tile removeMin() {

		// a method that takes no inputs and removed the Tile with highest priority (i.e. minimun estimate cost)
		// from the queue.

		Tile temp = this.priorityQueue.get(1);
		this.priorityQueue.add(1, this.priorityQueue.get(size));
		this.priorityQueue.remove(size);

		this.size = size - 1;

		downHeap(1, size, this.priorityQueue);

		return temp;
	}
	
	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {

		// a method that takes as input a Tile t. If such tile belongs to the queue, the method updates which Tile is
		// predicted to be the predecessor of t in the minimum weight path that leads from a source tile to t as well
		// as the estimated cost for this path. Note that this information should be stored in the appropriate fields
		// from the Tile class, and after these updates, the queue should remain a valid min heap.

		boolean tileBelongs = false;
		ArrayList<Tile> queueBefore = this.priorityQueue;
		Tile tileToModify = null;

		for (int i=0 ; i<queueBefore.size() ; i++) {

			if (queueBefore.get(i).equals(t)){
				tileToModify = queueBefore.get(i);
				tileBelongs = true;
				break;
			}
		}

		if (tileBelongs) {
			
			tileToModify.predecessor = newPred;
			tileToModify.costEstimate = newEstimate;

			ArrayList<Tile> newVertices = new ArrayList<>();
			
			for (int i=0 ; i<queueBefore.size() ; i++){
				if (queueBefore.get(i) != null){
					newVertices.add(queueBefore.get(i));
				}
			}
			
			this.priorityQueue = buildHeap(newVertices);
		}

		return;
	}
	
}
