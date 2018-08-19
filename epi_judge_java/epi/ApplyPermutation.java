package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ApplyPermutation {

    public static void applyPermutation(List<Integer> perm, List<Integer> A) {
	int i = 0;
	int nextValue = A.get(i);
	while (i < perm.size() && perm.get(i) >= 0) {
	    int temp = A.get(perm.get(i));
	    A.set(perm.get(i), nextValue);

	    int permPointer = perm.get(i);
	    perm.set(i, perm.get(i) - perm.size());

	    nextValue = temp;
	    if (perm.get(permPointer) >= 0) {
		i = perm.get(permPointer);
	    } else if (i == perm.size() - 1) {
		i = i + 1;
	    } else {
		i = i + 1;
		while (i < perm.size() && perm.get(i) < 0) {
		    i = i + 1;
		}
	    }
	}
	return;
    }

    @EpiTest(testDataFile = "apply_permutation.tsv")
    public static List<Integer> applyPermutationWrapper(List<Integer> perm, List<Integer> A) {
	applyPermutation(perm, A);
	return A;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "ApplyPermutation.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
