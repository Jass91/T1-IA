package util.puzzle.ia.t1.ufscar;

import java.util.NoSuchElementException;

import game.puzzle.ia.t1.ufscar.GameState;

public class PriorityQueue extends Border {

	public PriorityQueue() {
		
	}


    //Define functions for determining the parent, left, and right
    //node from any given node.
    private int par(int n) {
    	return n == 0 ? -1 : (n - 1) >>> 1;
    }
    
    private int left(int n) {
    	return n * 2 + 1; 
    }
    
    private int right(int n) {
    	return n * 2 + 2;
    }
    

    //Determine the index of the lesser-value child of a node taking
    //into account that a node may not have children or may just have
    //one child.
    private int minChildIndex(int n) {
        if (left(n) > elements.size() - 1)
        	return -1;
        if (right(n) > elements.size() - 1)
        	return left(n);
        
        return elements.get(left(n)).getCoastToGetHere() <= elements.get(right(n)).getCoastToGetHere() ? left(n) : right(n);
    }

    //Add a new element to the end and bubble it up to the appropriate
    //position in the heap.
    private void addElement(GameState state) {
        elements.add(state);
        bubbleUp(elements.size() - 1);
    }

    //Remove the element at the root, move the last element up, and
    //bubble it down to the appropriate position.
    private GameState remove() {

        if (elements.size() == 0)
        	throw new NoSuchElementException();

        if (!isHeap()) {
            System.err.println("Heap property broken!");
        }

        GameState result = elements.get(0);
        elements.set(0, elements.get(elements.size() - 1));
        elements.remove(elements.size() - 1);
        bubbleDown(0);

        if (!isHeap()) {
            System.err.println("Heap property broken!");
        }

        return result;
    }

    //Move the element up until it is less than its parent or
    //until it is at the root.
    private void bubbleUp(int n) {
        int parIndex = par(n);
        while (n > 0 && elements.get(parIndex).getCoastToGetHere() > elements.get(n).getCoastToGetHere()) {
            swap(parIndex, n);
            n = parIndex;
            parIndex = par(n);
        }
    }

    //Move the element down, switching it with its lesser child
    //until it is lower than both of its children.
    private void bubbleDown(int n) {
        int minChildIndex = minChildIndex(n);
        while (minChildIndex != -1 && elements.get(minChildIndex).getCoastToGetHere() < elements.get(n).getCoastToGetHere()) {
            swap(minChildIndex, n);
            n = minChildIndex;
            minChildIndex = minChildIndex(n);
        }
    }

    //Assert the current structure is a heap.
    public boolean isHeap() {
        for (int i = 1; i < elements.size(); ++i) {
            if (par(i) >= 0) {
                if (elements.get(par(i)).getCoastToGetHere() > elements.get(i).getCoastToGetHere()) {
                    return false;
                }
            }
        }
        return true;
    }

    //Utility function to swap two elements in the array list.
    private void swap(int i, int j) {
        GameState tmp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, tmp);
    }
	
    @Override
    public void add(GameState newElement){
    	addElement(newElement);
    }
    
	// remove segundo a política de heap
	@Override
	public GameState get() {
		
		if(elements.size() == 0){
	       throw new NoSuchElementException();
		}
		
		return remove();
	}

}
