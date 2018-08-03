package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstKey {
    @EpiTest(testDataFile = "search_first_key.tsv")

    public static int searchFirstOfK(List<Integer> A, int k) {
	int start = 0;
	int end = A.size() - 1;
	int firstOccurence = Integer.MAX_VALUE;

	while (start <= end) {
	    int mid = (start + end) / 2;
	    if (A.get(mid) == k) {
		firstOccurence = mid < firstOccurence ? mid : firstOccurence;
		if (mid - 1 >= 0 && A.get(mid - 1) == k) {
		    end = mid - 1;
		} else {
		    return firstOccurence;
		}
	    } else if (A.get(mid) > k) {
		end = mid - 1;
	    } else {
		start = mid + 1;
	    }
	}
	return firstOccurence == Integer.MAX_VALUE ? -1 : firstOccurence;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "SearchFirstKey.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
