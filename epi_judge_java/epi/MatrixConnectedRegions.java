package epi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class MatrixConnectedRegions {
    public static void flipColor(int x, int y, List<List<Boolean>> image) {
	Boolean flipCellcolor = image.get(x).get(y);
	int[][] shift = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	Queue<Coordinate> bfsQueue = new LinkedList<Coordinate>();
	bfsQueue.add(new Coordinate(x, y));

	while (!bfsQueue.isEmpty()) {
	    Coordinate current = bfsQueue.remove();
	    image.get(current.x).set(current.y, !flipCellcolor);

	    for (int[] nextShift : shift) {
		Coordinate cell = new Coordinate(current.x + nextShift[0], current.y + nextShift[1]);
		if (isValid(cell, image, flipCellcolor)) {
		    bfsQueue.add(cell);
		}
	    }
	}

	return;
    }

    public static boolean isValid(Coordinate cell, List<List<Boolean>> image, Boolean flipCellcolor) {
	if (cell.x >= 0 && cell.x < image.size() && cell.y >= 0 && cell.y < image.get(cell.x).size()
		&& image.get(cell.x).get(cell.y).equals(flipCellcolor)) {
	    return true;
	}
	return false;
    }

    private static class Coordinate {
	public int x;
	public int y;

	public Coordinate(int x, int y) {
	    this.x = x;
	    this.y = y;
	}
    }

    @EpiTest(testDataFile = "painting.tsv")
    public static List<List<Integer>> flipColorWrapper(TimedExecutor executor, int x, int y, List<List<Integer>> image)
	    throws Exception {
	List<List<Boolean>> B = new ArrayList<>();
	for (int i = 0; i < image.size(); i++) {
	    B.add(new ArrayList<>());
	    for (int j = 0; j < image.get(i).size(); j++) {
		B.get(i).add(image.get(i).get(j) == 1);
	    }
	}

	executor.run(() -> flipColor(x, y, B));

	image = new ArrayList<>();
	for (int i = 0; i < B.size(); i++) {
	    image.add(new ArrayList<>());
	    for (int j = 0; j < B.get(i).size(); j++) {
		image.get(i).add(B.get(i).get(j) ? 1 : 0);
	    }
	}

	return image;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "MatrixConnectedRegions.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
