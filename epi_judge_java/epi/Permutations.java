package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

public class Permutations {
    @EpiTest(testDataFile = "permutations.tsv")

    public static List<List<Integer>> permutations(List<Integer> A) {
	List<List<Integer>> permutations = new ArrayList<>();
	solvePermutation(0, A, permutations);

	return permutations;
    }

    public static void solvePermutation(int i, List<Integer> solution, List<List<Integer>> permutations) {

	if (i == solution.size() - 1) {
	    permutations.add(new ArrayList<>(solution));
	    return;
	}

	for (int j = i; j < solution.size(); j++) {
	    Collections.swap(solution, i, j);

	    solvePermutation(i + 1, solution, permutations);

	    Collections.swap(solution, i, j);
	}

    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = (expected, result) -> {
	if (result == null) {
	    return false;
	}
	for (List<Integer> l : expected) {
	    Collections.sort(l);
	}
	expected.sort(new LexicographicalListComparator<>());
	for (List<Integer> l : result) {
	    Collections.sort(l);
	}
	result.sort(new LexicographicalListComparator<>());
	return expected.equals(result);
    };

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "Permutations.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
