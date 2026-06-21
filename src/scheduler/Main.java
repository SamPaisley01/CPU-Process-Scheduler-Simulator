package scheduler;

import scheduler.algorithms.*;
import scheduler.model.Process;
import scheduler.output.GanttChart;
import scheduler.output.Statistics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();

        boolean running = true;
        while (running) {
            List<Process> processes = inputProcesses();
            SchedulingAlgorithm algorithm = selectAlgorithm();
            List<Process> completed = algorithm.schedule(processes);

            GanttChart.display(completed);
            Statistics.display(completed);

            System.out.print("Run again? (y/n): ");
            running = scanner.nextLine().trim().equalsIgnoreCase("y");
        }

        System.out.println("\nExiting scheduler. Goodbye.");
        scanner.close();
    }

    private static void printBanner() {
        System.out.println("+=========================================+");
        System.out.println("|       CPU PROCESS SCHEDULER             |");
        System.out.println("|   FCFS  |  SJF  |  Priority  |  RR     |");
        System.out.println("+=========================================+");
    }

    private static List<Process> inputProcesses() {
        List<Process> processes = new ArrayList<>();

        System.out.println("\n+-----------------------------------------+");
        System.out.println("|           ENTER PROCESSES               |");
        System.out.println("+-----------------------------------------+");
        System.out.println("  Type 'done' when finished.\n");

        int pid = 1;
        while (true) {
            System.out.print("  Process name : ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            int arrivalTime = readInt("  Arrival time : ");
            int burstTime   = readInt("  Burst time   : ");
            int priority    = readInt("  Priority     : ");

            processes.add(new Process(pid++, name, arrivalTime, burstTime, priority));
            System.out.println("  + Process added.\n");
        }

        return processes;
    }

    private static SchedulingAlgorithm selectAlgorithm() {
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|        SELECT ALGORITHM                 |");
        System.out.println("+-----------------------------------------+");
        System.out.println("  1. First Come First Served (FCFS)");
        System.out.println("  2. Shortest Job First (SJF)");
        System.out.println("  3. Priority Scheduling");
        System.out.println("  4. Round Robin");
        System.out.println("+-----------------------------------------+");

        int choice = readInt("  Choice: ");
        switch (choice) {
            case 1:
                System.out.println("  > Running FCFS...\n");
                return new FCFS();
            case 2:
                System.out.println("  > Running SJF...\n");
                return new SJF();
            case 3:
                System.out.println("  > Running Priority Scheduling...\n");
                return new PriorityScheduler();
            case 4:
                int quantum = readInt("  Time quantum : ");
                System.out.println("  > Running Round Robin...\n");
                return new RoundRobin(quantum);
            default:
                System.out.println("  Invalid choice, defaulting to FCFS.\n");
                return new FCFS();
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }
}
