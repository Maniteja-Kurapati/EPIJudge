package epi;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsArrayMultiply {
    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
	List<Integer> sum = new ArrayList<>();
	List<Integer> result = new ArrayList<>();

	for (int i = num2.size() - 1; i >= 0; i--) {
	    int carry = 0;
	    for (int j = num1.size() - 1; j >= 0; j--) {
		int multiplication = num2.get(i) * num1.get(j) + carry;
		result.set(j, multiplication % 10);
		carry = multiplication / 10;
	    }

	    if (carry == 1) {
		result.add(0, carry);
	    }

	    for (int k = num2.size() - 1 - i; i > 0; i--) {
		result.add(0);
	    }
	}

	return null;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "IntAsArrayMultiply.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
