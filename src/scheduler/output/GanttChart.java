package scheduler.output;

import scheduler.model.Process;
import java.util.List;

public class GanttChart {

    public static void display(List<Process> processes) {
        System.out.println("\n--- Gantt Chart ---");

        StringBuilder chart = new StringBuilder("|");
        StringBuilder timeline = new StringBuilder("0");

        for (Process process : processes) {
            int width = Math.max(process.getBurstTime() * 2, process.getName().length() + 2);
            String label = " " + process.getName() + " ";
            int padding = width - label.length();
            int left = padding / 2;
            int right = padding - left;

            chart.append(" ".repeat(left)).append(label).append(" ".repeat(right)).append("|");

            String time = String.valueOf(process.getCompletionTime());
            int prevLen = timeline.length();
            int position = chart.length() - 1;
            while (timeline.length() < position - time.length()) {
                timeline.append(" ");
            }
            timeline.append(time);
        }

        System.out.println(chart);
        System.out.println(timeline);
    }
}
