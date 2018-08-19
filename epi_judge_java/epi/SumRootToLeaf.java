package epi;
import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    Integer sum = 0;
    computeSum(tree, "", sum);   
    return sum;
  }
  
  public static void computeSum(BinaryTreeNode<Integer> tree, String number, Integer sum ) {
	  
	  if(tree != null) {
		  number = number + tree.data;
		  
		  if(tree.left == null && tree.right == null) {
			  sum = sum + Integer.valueOf(number);
		  }
		  
		  computeSum(tree.left, number, sum);
		  computeSum(tree.right, number, sum);
	  }	  
  }
  

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
