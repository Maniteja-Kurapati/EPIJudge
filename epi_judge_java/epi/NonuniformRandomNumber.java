package epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TimedExecutor;

public class NonuniformRandomNumber {

    public static int nonuniformRandomNumberGeneration(List<Integer> values, List<Double> probabilities) {
	List<Interval> intervals = new ArrayList<>();
	double probabilitySum = 0;
	for (int i = 0; i < probabilities.size(); i++) {
	    intervals.add(new Interval(probabilitySum, probabilitySum + probabilities.get(i)));
	    probabilitySum += probabilities.get(i);
	}

	Random rand = new Random();
	int randomNumber = 0;

	for (int i = 0; i < values.size(); i++) {
	    double next = (rand.nextInt(11) / 10);
	    randomNumber = randomNumber * 10 + values.get(BinarySearchCustom(intervals, next, 0, intervals.size() - 1));
	}
	return randomNumber;
    }

    public static int BinarySearchCustom(List<Interval> intervals, double num, int start, int end) {
	int midpoint = (int) Math.floor((start + end) / 2);

	if (start > end) {
	    return -1;
	}

	if (num > intervals.get(midpoint).start && num <= intervals.get(midpoint).end) {
	    return midpoint;
	} else if (num < intervals.get(midpoint).start) {
	    return BinarySearchCustom(intervals, num, start, midpoint - 1);
	} else {
	    return BinarySearchCustom(intervals, num, midpoint + 1, end);
	}
    }

    private static class Interval {
	public double start;
	public double end;

	Interval(double start, double end) {
	    this.start = start;
	    this.end = end;
	}
    }

    private static boolean nonuniformRandomNumberGenerationRunner(TimedExecutor executor, List<Integer> values,
	    List<Double> probabilities) throws Exception {
	final int N = 1000000;
	List<Integer> results = new ArrayList<>(N);

	executor.run(() -> {
	    for (int i = 0; i < N; ++i) {
		results.add(nonuniformRandomNumberGeneration(values, probabilities));
	    }
	});

	Map<Integer, Integer> counts = new HashMap<>();
	for (Integer result : results) {
	    counts.put(result, counts.getOrDefault(result, 0) + 1);
	}
	for (int i = 0; i < values.size(); ++i) {
	    final int v = values.get(i);
	    final double p = probabilities.get(i);
	    if (N * p < 50 || N * (1.0 - p) < 50) {
		continue;
	    }
	    final double sigma = Math.sqrt(N * p * (1.0 - p));
	    if (Math.abs(counts.get(v) - (p * N)) > 5 * sigma) {
		return false;
	    }
	}
	return true;
    }

    @EpiTest(testDataFile = "nonuniform_random_number.tsv")
    public static void nonuniformRandomNumberGenerationWrapper(TimedExecutor executor, List<Integer> values,
	    List<Double> probabilities) throws Exception {
	RandomSequenceChecker
		.runFuncWithRetries(() -> nonuniformRandomNumberGenerationRunner(executor, values, probabilities));
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "NonuniformRandomNumber.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
