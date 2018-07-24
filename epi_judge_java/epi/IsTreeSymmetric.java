package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if(!postOrderLeft(tree).data.equals(postOrderRight(tree).data)) {
    	return false;
    }
    return true;
  }
  
  public static BinaryTreeNode<Integer> postOrderLeft(BinaryTreeNode<Integer> tree){
	  
	  if(tree == null) {
		  return null;
	  }
	  
	  postOrderLeft(tree.left);
	  postOrderLeft(tree.right);
	  
	  return tree;
  }
  
  public static BinaryTreeNode<Integer> postOrderRight(BinaryTreeNode<Integer> tree){
	  
	  if(tree == null) {
		  return null;
	  }
	  
	  postOrderRight(tree.right);
	  postOrderRight(tree.left);
	  
	  return tree;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
