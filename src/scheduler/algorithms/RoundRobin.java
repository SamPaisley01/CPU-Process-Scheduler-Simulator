package scheduler.algorithms;

import scheduler.model.Process;
import scheduler.structures.CustomQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoundRobin implements SchedulingAlgorithm {

    private final int timeQuantum;

    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    @Override
    public List<Process> schedule(List<Process> processes) {
        List<Process> remaining = new ArrayList<>(processes);
        remaining.sort(Comparator.comparingInt(Process::getArrivalTime));

        CustomQueue<Process> queue = new CustomQueue<>();
        List<Process> completed = new ArrayList<>();
        int currentTime = 0;
        int index = 0;

        while (index < remaining.size() && remaining.get(index).getArrivalTime() <= currentTime) {
            queue.enqueue(remaining.get(index));
            index++;
        }

        while (!queue.isEmpty()) {
            Process process = queue.dequeue();
            process.setState(Process.State.RUNNING);

            int execTime = Math.min(process.getRemainingTime(), timeQuantum);
            currentTime += execTime;
            process.setRemainingTime(process.getRemainingTime() - execTime);

            while (index < remaining.size() && remaining.get(index).getArrivalTime() <= currentTime) {
                queue.enqueue(remaining.get(index));
                index++;
            }

            if (process.getRemainingTime() == 0) {
                process.setCompletionTime(currentTime);
                process.setTurnaroundTime(currentTime - process.getArrivalTime());
                process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
                process.setState(Process.State.TERMINATED);
                completed.add(process);
            } else {
                process.setState(Process.State.READY);
                queue.enqueue(process);
            }

            if (queue.isEmpty() && index < remaining.size()) {
                currentTime = remaining.get(index).getArrivalTime();
                while (index < remaining.size() && remaining.get(index).getArrivalTime() <= currentTime) {
                    queue.enqueue(remaining.get(index));
                    index++;
                }
            }
        }

        return completed;
    }
}
