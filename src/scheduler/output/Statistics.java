package scheduler.output;

import scheduler.model.Process;
import java.util.List;

public class Statistics {

    public static void display(List<Process> processes) {
        double totalWaiting = 0;
        double totalTurnaround = 0;

        System.out.println("\n--- Process Statistics ---");
        System.out.printf("%-5s %-12s %-10s %-10s %-10s %-12s%n",
                "PID", "Name", "Arrival", "Burst", "Waiting", "Turnaround");
        System.out.println("-".repeat(65));

        for (Process process : processes) {
            totalWaiting += process.getWaitingTime();
            totalTurnaround += process.getTurnaroundTime();
            System.out.printf("%-5d %-12s %-10d %-10d %-10d %-12d%n",
                    process.getPid(),
                    process.getName(),
                    process.getArrivalTime(),
                    process.getBurstTime(),
                    process.getWaitingTime(),
                    process.getTurnaroundTime());
        }

        System.out.println("-".repeat(65));
        System.out.printf("Average Waiting Time    : %.2f%n", totalWaiting / processes.size());
        System.out.printf("Average Turnaround Time : %.2f%n", totalTurnaround / processes.size());
    }
}
