package border.agent.puzzle.ia.t1.ufscar;

import java.util.Comparator;
import java.util.NoSuchElementException;

import game.puzzle.ia.t1.ufscar.SearchNode;

/*
 * Representa a borda em forma de fila de prioridade (heap),
 * no qual a ordem dos elementos é dado pelo comparador
 * passado no contrutor desse objeto.
 * 
 * Essa ordem, resulta em uma busca de custo uniforme ou
 * caso a ordem dos elementos seja decidida por uma funcao heuristica,
 * teremos uma busca guiada de melhor escolha
 * 
 */
public class PriorityQueueBorder extends Border {

	private Comparator<SearchNode> comparator;
	
	public PriorityQueueBorder(Comparator<SearchNode> comparator) {
		this.comparator = comparator;
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
        
        return comparator.compare(elements.get(left(n)), elements.get(right(n))) <= 0 ? left(n) : right(n);
        
    }

    //Add a new element to the end and bubble it up to the appropriate
    //position in the heap.
    private void addElement(SearchNode state) {
        elements.add(state);
        bubbleUp(elements.size() - 1);
    }

    //Remove the element at the root, move the last element up, and
    //bubble it down to the appropriate position.
    private SearchNode remove() {

        if (elements.size() == 0)
        	throw new NoSuchElementException();

        if (!isHeap()) {
            System.err.println("Heap property broken!");
        }

        SearchNode result = elements.get(0);
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
        while (n > 0 && (comparator.compare(elements.get(parIndex), elements.get(n)) > 0)) {
            swap(parIndex, n);
            n = parIndex;
            parIndex = par(n);
        }
    }

    //Move the element down, switching it with its lesser child
    //until it is lower than both of its children.
    private void bubbleDown(int n) {
        int minChildIndex = minChildIndex(n);
        while (minChildIndex != -1 && (comparator.compare(elements.get(minChildIndex), elements.get(n)) < 0)) {
            swap(minChildIndex, n);
            n = minChildIndex;
            minChildIndex = minChildIndex(n);
        }
    }

    //Assert the current structure is a heap.
    public boolean isHeap() {
        for (int i = 1; i < elements.size(); ++i) {
            if (par(i) >= 0) {
                if (comparator.compare(elements.get(par(i)), elements.get(i)) > 0){
                    return false;
                }
            }
        }
        return true;
    }

    //Utility function to swap two elements in the array list.
    private void swap(int i, int j) {
        SearchNode tmp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, tmp);
    }
	
    // adiciona segundo a politica de heap
    @Override
    public void add(SearchNode newElement){
    	addElement(newElement);
    }
    
	// remove segundo a política de heap
	@Override
	public SearchNode get() {
		if(elements.size() == 0){
	       throw new NoSuchElementException();
		}
		
		return remove();
	}

}
