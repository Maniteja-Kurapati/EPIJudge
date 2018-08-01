package epi;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SpiralOrderingSegments {
    @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
	// TODO - you fill in here.
	List<Integer> spiralOrder = new ArrayList<>();

	List<Integer> topLeftList = new ArrayList<>();
	List<Integer> topRightList = new ArrayList<>();
	List<Integer> bottomRightList = new ArrayList<>();
	List<Integer> bottomLeftList = new ArrayList<>();

	for (int i = 0; i < squareMatrix.size() / 2; i++) {

	    for (int j = i; j < squareMatrix.size() - i - 1; j++) {
		int topLeft = squareMatrix.get(i).get(j);
		int topRight = squareMatrix.get(j).get(squareMatrix.size() - 1 - i);
		int bottomRight = squareMatrix.get(squareMatrix.size() - 1 - i).get(squareMatrix.size() - 1 - j);
		int bottomLeft = squareMatrix.get(squareMatrix.size() - 1 - j).get(i);

		topLeftList.add(topLeft);
		topRightList.add(topRight);
		bottomRightList.add(bottomRight);
		bottomLeftList.add(bottomLeft);
	    }
	    spiralOrder.addAll(topLeftList);
	    spiralOrder.addAll(topRightList);
	    spiralOrder.addAll(bottomRightList);
	    spiralOrder.addAll(bottomLeftList);

	    topLeftList.clear();
	    topRightList.clear();
	    bottomRightList.clear();
	    bottomLeftList.clear();
	}

	if (squareMatrix.size() % 2 != 0) {
	    spiralOrder.add(squareMatrix.get((int) Math.floor(squareMatrix.size() / 2))
		    .get((int) Math.floor(squareMatrix.size() / 2)));
	}

	return spiralOrder;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "SpiralOrderingSegments.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
