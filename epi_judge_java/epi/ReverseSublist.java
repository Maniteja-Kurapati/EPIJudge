package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
	  if(L == null) {
		  return null;
	  }
	  
	  if(start == finish) {
		  return L;
	  }
	  
	  if(start < 0 || finish < 0 || start > finish) {
		  return L;
	  }
	  
	  int k =1;
	  ListNode<Integer> dummyHead = new ListNode<Integer>(0,null);
	  ListNode<Integer> subHead = dummyHead;
	  
	  while(k++ < start){
		  subHead = subHead.next;
	  }
	  
	  ListNode<Integer> subIter = subHead.next;
	  
	  //Start Reversing Process
	  while(start++ < finish){
		  ListNode<Integer> temp = subIter.next;
		  subIter.next = temp.next;
		  temp.next = subHead.next;
		  subHead.next = temp;
	  }
	return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
