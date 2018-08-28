package epi;

import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class KthLargestInArray {
    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
    // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
    @EpiTest(testDataFile = "kth_largest_in_array.tsv")
    public static int findKthLargest(int k, List<Integer> A) {
	int lo = 0;
	int hi = A.size() - 1;

	while (lo <= hi) {
	    int pivot = lo;
	    int storeIndex = pivot + 1;

	    for (int j = pivot + 1; j <= hi; j++) {
		if (A.get(j) > A.get(pivot)) {
		    Collections.swap(A, storeIndex, j);
		    storeIndex = storeIndex + 1;
		}
	    }
	    Collections.swap(A, storeIndex - 1, pivot);

	    int numberOfelementsBeforePivot = storeIndex;

	    if (numberOfelementsBeforePivot == k - 1) {
		return A.get(storeIndex - 1);
	    } else if (numberOfelementsBeforePivot > k - 1) {
		hi = storeIndex - 1;
	    } else {
		hi = storeIndex - 1 + k;
	    }

	}

// TODO - you fill in here.
	return 0;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "KthLargestInArray.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
