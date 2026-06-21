package scheduler.algorithms;

import scheduler.model.Process;
import scheduler.structures.CustomQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCFS implements SchedulingAlgorithm {

    @Override
    public List<Process> schedule(List<Process> processes) {
        List<Process> sorted = new ArrayList<>(processes);
        sorted.sort(Comparator.comparingInt(Process::getArrivalTime));

        CustomQueue<Process> queue = new CustomQueue<>();
        for (Process process : sorted) {
            queue.enqueue(process);
        }

        List<Process> completed = new ArrayList<>();
        int currentTime = 0;

        while (!queue.isEmpty()) {
            Process process = queue.dequeue();

            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }

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
