package epi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

public class NQueens {
    @EpiTest(testDataFile = "n_queens.tsv")

    public static List<List<Integer>> nQueens(int n) {
	List<List<Integer>> resultSet = new ArrayList<>();
	List<Integer> solution = new ArrayList<>();

	solveNQueens(0, n, solution, resultSet);
	return resultSet;
    }

    public static void solveNQueens(int rowId, int n, List<Integer> solution, List<List<Integer>> resultSet) {

	if (rowId == n) {
	    resultSet.add(new ArrayList<>(solution));
	    return;
	}

	for (int i = 0; i < n; i++) {
	    if (isValidPosition(rowId, i, solution)) {
		solution.add(i);
		solveNQueens(rowId + 1, n, solution, resultSet);
		solution.remove(solution.size() - 1);
	    }

	}

    }

    public static boolean isValidPosition(int rowId, int column, List<Integer> solution) {
	for (int i = 0; i < rowId; i++) {
	    int diff = Math.abs(column - solution.get(i));
	    if (diff == 0 || diff == Math.abs(rowId - i)) {
		return false;
	    }
	}
	return true;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = (expected, result) -> {
	if (result == null) {
	    return false;
	}
	expected.sort(new LexicographicalListComparator<>());
	result.sort(new LexicographicalListComparator<>());
	return expected.equals(result);
    };

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "NQueens.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
