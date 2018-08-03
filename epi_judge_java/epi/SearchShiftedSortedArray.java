package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchShiftedSortedArray {
    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

    public static int searchSmallest(List<Integer> A) {
	int start = 0;
	int end = A.size() - 1;
	int smallest = 0;
	while (start <= end) {
	    int mid = start + (end - start) / 2;
	    if (mid - 1 > 0 && A.get(mid) < A.get(mid - 1)) {
		smallest = A.get(mid);
	    } else if (A.get(mid) > A.get(start)) {
		start = mid + 1;
	    } else if (A.get(mid) < A.get(start)) {
		end = mid - 1;
	    }
	}
	return smallest;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "SearchShiftedSortedArray.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
