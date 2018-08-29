package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

public class KLargestInHeap {
    @EpiTest(testDataFile = "k_largest_in_heap.tsv")

    public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {

	PriorityQueue<Integer> minheap = new PriorityQueue<>();
	for (int i = 0; i < k; i++) {
	    minheap.add(A.get(i));
	}

	for (int i = k; i < A.size(); i++) {
	    if (A.get(i) > minheap.peek()) {
		minheap.remove();
		minheap.add(A.get(i));
	    }
	}
	return new ArrayList<>(minheap);
    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp = (expected, result) -> {
	if (result == null) {
	    return false;
	}
	Collections.sort(expected);
	Collections.sort(result);
	return expected.equals(result);
    };

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "KLargestInHeap.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
