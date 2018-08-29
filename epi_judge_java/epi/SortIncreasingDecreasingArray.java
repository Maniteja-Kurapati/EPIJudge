package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortIncreasingDecreasingArray {
    @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {

	List<VirtualArray> virtualArrays = new ArrayList<>();

	int currentPattern = A.get(0) < A.get(1) ? 1 : -1;
	int currentPatternStart = 0;
	// initialize virtual arrays.
	for (int i = 0; i < A.size() - 1; i++) {
	    int pattern = A.get(i) < A.get(i + 1) ? 1 : -1;
	    if (pattern != currentPattern) {
		virtualArrays.add(new VirtualArray(currentPatternStart, i));
		currentPatternStart = i + 1;
	    }
	}
	virtualArrays.add(new VirtualArray(currentPatternStart, A.size() - 1));

	List<List<Integer>> sortedArrays = new ArrayList<>();

	for (VirtualArray virtualArray : virtualArrays) {
	    List<Integer> list = A.subList(virtualArray.start, virtualArray.end + 1);
	    if (A.get(virtualArray.start) < A.get(virtualArray.end)) {
		sortedArrays.add(list);
	    } else {
		Collections.reverse(list);
		sortedArrays.add(list);
	    }
	}

	return SortedArraysMerge.mergeSortedArrays(sortedArrays);
    }

    private static class VirtualArray {
	public int start;
	public int end;

	public VirtualArray(int start, int end) {
	    this.start = start;
	    this.end = end;
	}
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "SortIncreasingDecreasingArray.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
