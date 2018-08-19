package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsArrayIncrement {
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> A) {
	int carry = 1;
	int i = A.size() - 1;
	while (i >= 0 && carry > 0) {
	    int sum = A.get(i) + carry;
	    A.set(i, sum % 10);
	    carry = sum / 10;
	    i--;
	}

	if (carry == 1) {
	    A.add(0, carry);
	}
	return A;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "IntAsArrayIncrement.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
