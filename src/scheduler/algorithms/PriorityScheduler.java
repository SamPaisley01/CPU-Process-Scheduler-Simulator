package scheduler.algorithms;

import scheduler.model.Process;
import scheduler.structures.MinHeap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityScheduler implements SchedulingAlgorithm {

    @Override
    public List<Process> schedule(List<Process> processes) {
        List<Process> remaining = new ArrayList<>(processes);
        MinHeap<Process> heap = new MinHeap<>(Comparator.comparingInt(Process::getPriority));
        List<Process> completed = new ArrayList<>();
        int currentTime = 0;

        while (!remaining.isEmpty() || !heap.isEmpty()) {
            for (Process process : new ArrayList<>(remaining)) {
                if (process.getArrivalTime() <= currentTime) {
                    heap.insert(process);
                    remaining.remove(process);
                }
            }

            if (heap.isEmpty()) {
                currentTime = remaining.get(0).getArrivalTime();
                continue;
            }

            Process process = heap.extractMin();
            process.setState(Process.State.RUNNING);
            process.setWaitingTime(currentTime - process.getArrivalTime());
            currentTime += process.getBurstTime();
            process.setCompletionTime(currentTime);
            process.setTurnaroundTime(currentTime - process.getArrivalTime());
            process.setState(Process.State.TERMINATED);
            completed.add(process);
        }

        return completed;
    }
}
