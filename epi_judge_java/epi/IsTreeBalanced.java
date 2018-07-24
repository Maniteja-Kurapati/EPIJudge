package epi;
import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
	  
	Map<BinaryTreeNode<Integer>,Integer> map = new HashMap<>();	  
    computeHeight(tree, map);
    map.put(null, -1);
    return checkIfBalanced(tree,map);
  }
  
  public static boolean checkIfBalanced(BinaryTreeNode<Integer> tree, Map<BinaryTreeNode<Integer>,Integer> map) {
	  
	  if(tree == null) {
		  return true;
	  }
	  
	  boolean leftRes = checkIfBalanced(tree.left, map);
	  if(!leftRes) {
		  return leftRes;
	  }
	  boolean rightRes =checkIfBalanced(tree.right, map);
	  if(!rightRes) {
		  return rightRes;
	  }
	  
	  int leftHeight = map.get(tree.left);
	  int rightHeight = map.get(tree.right);
	  return Math.abs(leftHeight-rightHeight) <=1;	  
  }
  
  public static int computeHeight(BinaryTreeNode<Integer> tree, Map<BinaryTreeNode<Integer>,Integer> map) {
	  if(tree == null) {
		  return -1;
	  }
	  
	  int leftTreeHeight = computeHeight(tree.left,map);
	  int rightTreeHeight = computeHeight(tree.right,map);
	  
	  int height = Math.max(leftTreeHeight, rightTreeHeight)+1;
	  map.put(tree, height);
	  return height;
  }
  
   
  public static  BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
	  //Base Case
	  if(tree == null) {
		  return new BalanceStatusWithHeight(true, -1);
	  }
	  
	  BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
	  if(!leftResult.isBalanced) {
		  return leftResult;
	  }
	  
	  BalanceStatusWithHeight rightResult =checkBalanced(tree.right);
	  if(!rightResult.isBalanced) {
		  return rightResult;
	  }
	  
	  boolean balanced = Math.abs(leftResult.height - rightResult.height) <=1;
	  int height = Math.max(leftResult.height, rightResult.height)+1;
	  return new BalanceStatusWithHeight(balanced, height);
  }
  
  private static class BalanceStatusWithHeight{
	  public boolean isBalanced;
	  public int height;
	  
	  public BalanceStatusWithHeight(boolean isBalanced, int height) {
		this.isBalanced = isBalanced;
		this.height = height;
	}
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
