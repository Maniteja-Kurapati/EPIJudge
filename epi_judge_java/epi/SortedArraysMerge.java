package epi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedArraysMerge {
    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
	List<Integer> mergeResult = new ArrayList<>();
	PriorityQueue<ElementAndItsList> minHeap = new PriorityQueue<>(new Comparator<ElementAndItsList>() {
	    @Override
	    public int compare(ElementAndItsList o1, ElementAndItsList o2) {
		return Integer.compare(o1.element, o2.element);
	    }
	});

	// intialize heap
	for (int i = 0; i < sortedArrays.size(); i++) {
	    minHeap.add(new ElementAndItsList(i, sortedArrays.get(i).get(0), 0));
	}

	ElementAndItsList smallest = null;
	while (!minHeap.isEmpty()) {
	    smallest = minHeap.poll();
	    mergeResult.add(smallest.element);

	    if (sortedArrays.get(smallest.listIndex).size() > smallest.elementIndex + 1) {
		smallest.element = sortedArrays.get(smallest.listIndex).get(smallest.elementIndex + 1);
		smallest.elementIndex = smallest.elementIndex + 1;
		minHeap.add(smallest);
	    }
	}
	return mergeResult;
    }

    private static class ElementAndItsList {
	public int listIndex;
	public Integer element;
	public int elementIndex;

	public ElementAndItsList(int listIndex, Integer element, int elementIndex) {
	    this.element = element;
	    this.listIndex = listIndex;
	    this.elementIndex = elementIndex;
	}
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "SortedArraysMerge.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
