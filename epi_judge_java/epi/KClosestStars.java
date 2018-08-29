package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.EpiTestExpectedType;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

public class KClosestStars {
    @EpiUserType(ctorParams = { double.class, double.class, double.class })

    public static class Star implements Comparable<Star> {
	private double x, y, z;

	public Star(double x, double y, double z) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	}

	public double distance() {
	    return Math.sqrt(x * x + y * y + z * z);
	}

	@Override
	public int compareTo(Star that) {
	    return Double.compare(this.distance(), that.distance());
	}

	@Override
	public String toString() {
	    return String.valueOf(distance());
	}
    }

    public static List<Star> findClosestKStars(Iterator<Star> stars, int k) {

	PriorityQueue<Star> maxheap = new PriorityQueue<>(new Comparator<Star>() {
	    @Override
	    public int compare(Star o1, Star o2) {
		return -(Double.compare(o1.distance(), o2.distance()));
	    }
	});

	for (int i = 0; i < k; i++) {
	    maxheap.add(stars.next());
	}

	while (stars.hasNext()) {
	    Star next = stars.next();
	    if (next.distance() < maxheap.peek().distance()) {
		maxheap.remove();
		maxheap.add(next);
	    }
	}
	return new ArrayList<>(maxheap);
    }

    @EpiTest(testDataFile = "k_closest_stars.tsv")
    public static List<Star> findClosestKStarsWrapper(List<Star> stars, int k) {
	return findClosestKStars(stars.iterator(), k);
    }

    @EpiTestExpectedType
    public static List<Double> expectedType;

    @EpiTestComparator
    public static BiPredicate<List<Double>, List<Star>> comp = (expected, result) -> {
	if (expected.size() != result.size()) {
	    return false;
	}
	Collections.sort(result);
	for (int i = 0; i < result.size(); i++) {
	    if (result.get(i).distance() != expected.get(i)) {
		return false;
	    }
	}
	return true;
    };

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "KClosestStars.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
