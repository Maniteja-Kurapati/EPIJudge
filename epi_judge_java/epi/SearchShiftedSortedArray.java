package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchShiftedSortedArray {
    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

    public static int searchSmallest(List<Integer> A) {
	int start = 0;
	int end = A.size() - 1;
	while (start < end) {
	    int mid = start + (end - start) / 2;
	    if (A.get(mid) > A.get(end)) {
		start = mid + 1;
	    } else {
		end = mid;
	    }
	}
	return start;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "SearchShiftedSortedArray.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
