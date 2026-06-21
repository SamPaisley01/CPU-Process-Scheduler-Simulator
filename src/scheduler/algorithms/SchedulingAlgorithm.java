package scheduler.algorithms;

import scheduler.model.Process;
import java.util.List;

public interface SchedulingAlgorithm {

    List<Process> schedule(List<Process> processes);
}
