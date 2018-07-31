package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
	  int maxReach =Integer.MIN_VALUE;
	  for(int i = 0; i < maxAdvanceSteps.size(); i++) {
		  if(i + maxAdvanceSteps.get(i) > maxReach) {
			  maxReach = i + maxAdvanceSteps.get(i);
		  }		  
		  if(maxReach ==i && i !=maxAdvanceSteps.size()-1) {
			  return false;
		  }
	  }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
