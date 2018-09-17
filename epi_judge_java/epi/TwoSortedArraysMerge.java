package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class TwoSortedArraysMerge {

    public static void mergeTwoSortedArrays(List<Integer> A, int m, List<Integer> B, int n) {
	List<Integer> smallList;
	List<Integer> largeList;

	largeList = A;
	smallList = B;

	int largeListPoniter = m - 1;
	int smallListPointer = n - 1;
	int insertPoint = m + n - 1;

	while (smallListPointer >= 0) {
	    if (largeListPoniter >= 0) {
		if (smallList.get(smallListPointer) > largeList.get(largeListPoniter)) {
		    largeList.set(insertPoint, smallList.get(smallListPointer));
		    smallListPointer--;
		} else {
		    largeList.set(insertPoint, largeList.get(largeListPoniter));
		    largeListPoniter--;
		}
		insertPoint--;
	    } else {
		largeList.set(insertPoint, smallList.get(smallListPointer));
		smallListPointer--;
		insertPoint--;
	    }
	}

    }

    @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
    public static List<Integer> mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
	mergeTwoSortedArrays(A, m, B, n);
	return A;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "TwoSortedArraysMerge.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
