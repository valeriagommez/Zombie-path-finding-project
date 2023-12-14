package finalproject;

import java.util.ArrayList;

import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type

	private ArrayList<Tile> priorityQueue;

	private int size;

	public ArrayList<Tile> getPriorityQueue(){
		return this.priorityQueue;
	}

	public int getSize(){
		return this.size;
	}

	private void swap(int index1, int index2, ArrayList<Tile> array) {
		Tile temp = array.get(index2);
		array.set(index2, array.get(index1));
		array.set(index1, temp);
	}

	private void addUpHeap(Tile tileToAdd, ArrayList<Tile> priorityQueue) {
//		System.out.println(priorityQueue); // debugging

		this.size = size + 1;
		priorityQueue.add(size, tileToAdd);
		int i = size;

		while ( (i<1) && (priorityQueue.get(i).costEstimate < priorityQueue.get(i/2).costEstimate) ) {

			swap(i, (i/2), priorityQueue); // swapping object at i and i/2

			i = i/2;
		}
	}

	private ArrayList<Tile> buildHeap(ArrayList<Tile> vertices) {

		ArrayList<Tile> builtHeap = new ArrayList<>();

		builtHeap.add(0, null);

		for (int i=0 ; i<vertices.size() ; i++){
			addUpHeap(vertices.get(i), builtHeap); // do this or the buildHeapFast() method in the slides
			// (30 - Heaps, slide 85)
		}

		return builtHeap;
	}

	public TilePriorityQ (ArrayList<Tile> vertices) {

		// a constructor that builds a priority queue with the Tiles received as input.

		this.priorityQueue = new ArrayList<>();
//		this.size = 0;
		this.priorityQueue = buildHeap(vertices);
	}

	// TODO level 3: implement remove min as seen in class

	private void downHeap(int startIndex, int maxIndex, ArrayList<Tile> priorityQueue) {

		int i = startIndex;

		while ( 2*i <= maxIndex ){

			int child = 2*i;

			if (child < maxIndex) {

//				System.out.println(this.size); // size is 9, so why is everything null?
//				System.out.println(this.priorityQueue.size()); // here size is 10 (good bc index 0 is null)
//				System.out.println(priorityQueue.size());
//				System.out.println(this.priorityQueue);
//				System.out.println(priorityQueue);

				if (priorityQueue.get(child + 1).costEstimate < priorityQueue.get(child).costEstimate){  // this line is causing the error
					child = child + 1;
				}
			}

			if (priorityQueue.get(child).costEstimate < priorityQueue.get(i).costEstimate) {

				swap(i, child, priorityQueue) ;// swapping object at i and child

				i = child;
			}

			else {
				break;
			}
		}
	}


	public Tile removeMin() {

		// a method that takes no inputs and removed the Tile with highest priority (i.e. minimum estimate cost)
		// from the queue.

		if (priorityQueue.size() == 1) {
			return null;
		}

//		System.out.println(this.priorityQueue);

		Tile removedTile = this.priorityQueue.get(1);

		swap(1, this.size, this.priorityQueue); // swapping first and last

		this.priorityQueue.remove(size);
		this.size = size - 1;

		downHeap(1, size, this.priorityQueue);

		return removedTile;
	}

	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {

		boolean tileBelongs = false;
		ArrayList<Tile> queueBefore = this.priorityQueue;
		Tile tileToModify = null;

		for (int i=1 ; i<queueBefore.size() ; i++) {

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

			TilePriorityQ updatedQueue = new TilePriorityQ(newVertices);

			this.priorityQueue = updatedQueue.priorityQueue;

//			If such tile belongs to the queue, the method updates which Tile is predicted
//			to be the predecessor of t in the minimum weight path that leads from a source tile to t
//			as well as the estimated cost for this path. Note that this information should be stored
//			in the appropriate fields from the Tile class, and after these updates, the queue should
//			remain a valid min heap


		}

		return;
	}

}
