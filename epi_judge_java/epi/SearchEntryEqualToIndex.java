package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class SearchEntryEqualToIndex {

    public static int searchEntryEqualToItsIndex(List<Integer> A) {
	int start = 0;
	int end = A.size() - 1;

	while (start <= end) {
	    int mid = start + (end - start) / 2;
	    if (A.get(mid) == mid) {
		return mid;
	    } else if (A.get(mid) > mid) {
		end = mid - 1;
	    } else {
		start = mid + 1;
	    }
	}
	return -1;
    }

    @EpiTest(testDataFile = "search_entry_equal_to_index.tsv")
    public static void searchEntryEqualToItsIndexWrapper(TimedExecutor executor, List<Integer> A) throws Exception {
	int result = executor.run(() -> searchEntryEqualToItsIndex(A));

	if (result != -1) {
	    if (A.get(result) != result) {
		throw new TestFailure("Entry does not equal to its index");
	    }
	} else {
	    for (int i = 0; i < A.size(); ++i) {
		if (A.get(i) == i) {
		    throw new TestFailure("There are entries which equal to its index");
		}
	    }
	}
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "SearchEntryEqualToIndex.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
