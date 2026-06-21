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
        System.out.println("=================================");
        System.out.println("  CPU Process Scheduler");
        System.out.println("=================================");

        boolean running = true;
        while (running) {
            List<Process> processes = inputProcesses();
            SchedulingAlgorithm algorithm = selectAlgorithm();
            List<Process> completed = algorithm.schedule(processes);

            GanttChart.display(completed);
            Statistics.display(completed);

            System.out.print("\nRun again? (y/n): ");
            running = scanner.nextLine().trim().equalsIgnoreCase("y");
        }

        System.out.println("\nExiting scheduler. Goodbye.");
        scanner.close();
    }

    private static List<Process> inputProcesses() {
        List<Process> processes = new ArrayList<>();
        System.out.println("\nEnter processes (type 'done' when finished):");

        int pid = 1;
        while (true) {
            System.out.print("Process name (or 'done'): ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            int arrivalTime = readInt("Arrival time: ");
            int burstTime   = readInt("Burst time: ");
            int priority    = readInt("Priority (lower = higher priority): ");

            processes.add(new Process(pid++, name, arrivalTime, burstTime, priority));
            System.out.println("Process added.\n");
        }

        return processes;
    }

    private static SchedulingAlgorithm selectAlgorithm() {
        System.out.println("\nSelect scheduling algorithm:");
        System.out.println("  1. First Come First Served (FCFS)");
        System.out.println("  2. Shortest Job First (SJF)");
        System.out.println("  3. Priority Scheduling");
        System.out.println("  4. Round Robin");
        System.out.print("Choice: ");

        int choice = readInt("");
        switch (choice) {
            case 1: return new FCFS();
            case 2: return new SJF();
            case 3: return new PriorityScheduler();
            case 4:
                int quantum = readInt("Time quantum: ");
                return new RoundRobin(quantum);
            default:
                System.out.println("Invalid choice, defaulting to FCFS.");
                return new FCFS();
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
