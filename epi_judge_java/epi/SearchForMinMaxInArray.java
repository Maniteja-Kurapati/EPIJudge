package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

public class SearchForMinMaxInArray {
    @EpiUserType(ctorParams = { Integer.class, Integer.class })

    public static class MinMax {
	public Integer smallest;
	public Integer largest;

	public MinMax(Integer smallest, Integer largest) {
	    this.smallest = smallest;
	    this.largest = largest;
	}

	private static MinMax minMax(Integer a, Integer b) {
	    return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) {
		return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
		return false;
	    }

	    MinMax minMax = (MinMax) o;

	    if (!smallest.equals(minMax.smallest)) {
		return false;
	    }
	    return largest.equals(minMax.largest);
	}

	@Override
	public String toString() {
	    return "min: " + smallest + ", max: " + largest;
	}
    }

    @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")

    public static MinMax findMinMax(List<Integer> A) {

	if (A.size() < 2) {
	    return new MinMax(A.get(0), A.get(0));
	}

	int smallest = Integer.MAX_VALUE;
	int largest = Integer.MIN_VALUE;

	for (int i = 0; i + 1 < A.size(); i = i + 2) {
	    if (A.get(i) <= A.get(i + 1)) {
		smallest = A.get(i) < smallest ? A.get(i) : smallest;
		largest = A.get(i + 1) > largest ? A.get(i + 1) : largest;
	    } else {
		largest = A.get(i) > largest ? A.get(i) : largest;
		smallest = A.get(i + 1) < smallest ? A.get(i + 1) : smallest;
	    }
	}

	if (A.size() % 2 != 0) {
	    if (A.get(A.size() - 1) < smallest) {
		smallest = A.get(A.size() - 1);
	    } else if (A.get(A.size() - 1) > largest) {
		largest = A.get(A.size() - 1);
	    }
	}

	return new MinMax(smallest, largest);
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "SearchForMinMaxInArray.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
