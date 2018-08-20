package epi;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class TreePreorder {
    @EpiTest(testDataFile = "tree_preorder.tsv")

    public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
	Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();

	List<Integer> traversal = new ArrayList<>();

	BinaryTreeNode<Integer> current = tree;

	while (current != null || !stack.isEmpty()) {

	    if (current != null) {
		traversal.add(current.data);

		stack.addFirst(current);

		current = current.left;
	    } else {
		current = stack.removeFirst();

		current = current.right;
	    }
	}
	return traversal;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "TreePreorder.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
