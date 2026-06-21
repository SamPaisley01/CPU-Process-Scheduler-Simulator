package scheduler.output;

import scheduler.model.Process;
import java.util.List;

public class Statistics {

    public static void display(List<Process> processes) {
        double totalWaiting = 0;
        double totalTurnaround = 0;

        String separator = "+-----+------------+---------+-------+---------+------------+";
        String header    = "| PID | Name       | Arrival | Burst | Waiting | Turnaround |";

        System.out.println("\n+--------------------------------------------+");
        System.out.println("|              PROCESS STATISTICS            |");
        System.out.println("+--------------------------------------------+\n");
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);

        for (Process process : processes) {
            totalWaiting += process.getWaitingTime();
            totalTurnaround += process.getTurnaroundTime();
            System.out.printf("| %-3d | %-10s | %-7d | %-5d | %-7d | %-10d |%n",
                    process.getPid(),
                    process.getName(),
                    process.getArrivalTime(),
                    process.getBurstTime(),
                    process.getWaitingTime(),
                    process.getTurnaroundTime());
        }

        System.out.println(separator);
        System.out.printf("  Avg Waiting Time    : %.2f%n", totalWaiting / processes.size());
        System.out.printf("  Avg Turnaround Time : %.2f%n", totalTurnaround / processes.size());
        System.out.println();
    }
}
