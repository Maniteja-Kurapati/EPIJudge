package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
   return hasNodes(tree, node0, node1).node;
  }
  
  public static StatusItem hasNodes(BinaryTreeNode<Integer> tree,
          BinaryTreeNode<Integer> node0,
          BinaryTreeNode<Integer> node1) {
	  
	  if(tree == null) {
		  return new StatusItem(null, 0);
	  }
	  
	  StatusItem leftResult = hasNodes(tree.left, node0, node1);
	  if(leftResult.count == 2) {
		  return leftResult;
	  }
	  StatusItem rightResult = hasNodes(tree.right, node0, node1);
	  if(rightResult.count ==2) {
		  return rightResult;
	  }
	  
	  if(leftResult.count == 1 && rightResult.count == 1) {
		  return new StatusItem(tree, 2);
	  }
	  
	  if(leftResult.count == 1 || rightResult.count == 1) {
		  if(tree == node0 || tree == node1) {
			  return new StatusItem(tree, 2);
		  }
		  return leftResult.count == 1 ? leftResult : rightResult;
	  }
	  
	  if(tree == node0 || tree == node1) {
		  if(tree == node0 && tree == node1) {
			  return new StatusItem(tree, 2);
		  }
		  return new StatusItem(null, 1);
	  }
	    
	  return new StatusItem(null,0);	  
  }
  
  private static class StatusItem{
	  public BinaryTreeNode<Integer> node;
	  public int count;
	  public StatusItem(BinaryTreeNode<Integer> node, int count) {
		this.node = node;
		this.count = count;
	}
  }
  
  
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
