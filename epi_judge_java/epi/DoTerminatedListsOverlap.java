package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
	    // TODO - you fill in here.
	   ListNode<Integer> list1 = l0;
	   ListNode<Integer> list2 = l1;
	   int list1length = 0;
	   int list2length = 0;   
	   while(list1 != null){
		   list1 =list1.next;
		   list1length++;
	   }
	   
	   while(list2 != null){
		   list2 =list2.next;
		   list2length++;
	   }
	   
	   list1 = l0;
	   list2 = l1;
	   
	   if(list1length > list2length){
		   int diff = list1length - list2length;
		   while(diff-- > 0){
			   list1 = list1.next;
		   }   
	   }else{
		   int diff = list2length - list1length;
		   while(diff-- > 0){
			   list2 = list2.next;
		   }
	   }
	   
		while(list1 != null && list2 != null){
			   if(list1.next == list2.next && list1.next != null){
				   return list1.next;
			   }
			   list1 = list1.next;
			   list2 = list2.next;
		}   

	   return null;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
