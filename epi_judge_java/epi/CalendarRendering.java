package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

public class CalendarRendering {
    @EpiUserType(ctorParams = { int.class, int.class })

    public static class Event {
	public int start, finish;

	public Event(int start, int finish) {
	    this.start = start;
	    this.finish = finish;
	}
    }

    private static class Endpoint {
	public int time;
	public boolean isStart;

	Endpoint(int time, boolean isStart) {
	    this.time = time;
	    this.isStart = isStart;
	}
    }

    @EpiTest(testDataFile = "calendar_rendering.tsv")

    public static int findMaxSimultaneousEvents(List<Event> A) {
	List<Endpoint> endPoints = new ArrayList<>(A.size() * 2);

	// Populate end points
	for (Event event : A) {
	    endPoints.add(new Endpoint(event.start, true));
	    endPoints.add(new Endpoint(event.finish, false));
	}

	Collections.sort(endPoints, new Comparator<Endpoint>() {
	    @Override
	    public int compare(Endpoint o1, Endpoint o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o1.time, o2.time);
	    }
	});

	return 0;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "CalendarRendering.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
