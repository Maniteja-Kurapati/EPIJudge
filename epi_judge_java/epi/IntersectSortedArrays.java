package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntersectSortedArrays {
    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

    public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
	if (A.size() == B.size()) {
	    return equalLengthApproach(A, B);
	}
	return differentLengthApproach(A, B);
    }

    public static List<Integer> differentLengthApproach(List<Integer> A, List<Integer> B) {
	List<Integer> result = new ArrayList<>();

	int resultIndex = -1;
	List<Integer> smallList;
	List<Integer> largeList;
	if (A.size() < B.size()) {
	    smallList = A;
	    largeList = B;
	} else {
	    smallList = B;
	    largeList = A;
	}

	for (Integer curElement : smallList) {
	    if (Collections.binarySearch(largeList, curElement) >= 0) {
		if (result.isEmpty() || !result.get(resultIndex).equals(curElement)) {
		    result.add(curElement);
		    resultIndex++;
		}
	    }
	}

	return result;
    }

    public static List<Integer> equalLengthApproach(List<Integer> A, List<Integer> B) {
	List<Integer> result = new ArrayList<>();
	int pointerA = 0;
	int pointerB = 0;
	int resultIndex = -1;

	while (pointerA < A.size() && pointerB < B.size()) {
	    if (A.get(pointerA).equals(B.get(pointerB))) {

		if (result.isEmpty() || !result.get(resultIndex).equals(A.get(pointerA))) {
		    result.add(A.get(pointerA));
		    resultIndex++;
		}
		pointerA++;
		pointerB++;
	    } else if (A.get(pointerA) < B.get(pointerB)) {
		pointerA++;
	    } else {
		pointerB++;
	    }
	}
	return result;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "IntersectSortedArrays.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
