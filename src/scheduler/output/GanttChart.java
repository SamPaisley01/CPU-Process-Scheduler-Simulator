package scheduler.output;

import scheduler.model.Process;
import java.util.Arrays;
import java.util.List;

public class GanttChart {

    private static final int MIN_WIDTH = 6;

    public static void display(List<Process> processes) {
        if (processes.isEmpty()) {
            return;
        }

        System.out.println("\n+---------------------------------+");
        System.out.println("|           GANTT CHART           |");
        System.out.println("+---------------------------------+\n");

        int[] widths = new int[processes.size()];
        for (int i = 0; i < processes.size(); i++) {
            widths[i] = Math.max(MIN_WIDTH, processes.get(i).getName().length() + 2);
        }

        StringBuilder border = new StringBuilder("+");
        for (int w : widths) {
            border.append("-".repeat(w)).append("+");
        }

        StringBuilder names = new StringBuilder("|");
        for (int i = 0; i < processes.size(); i++) {
            String name = processes.get(i).getName();
            int padding = widths[i] - name.length();
            int left = padding / 2;
            int right = padding - left;
            names.append(" ".repeat(left)).append(name).append(" ".repeat(right)).append("|");
        }

        char[] timeline = new char[border.length() + 5];
        Arrays.fill(timeline, ' ');

        int startTime = processes.get(0).getCompletionTime() - processes.get(0).getBurstTime();
        String startStr = String.valueOf(startTime);
        for (int k = 0; k < startStr.length(); k++) {
            timeline[k] = startStr.charAt(k);
        }

        int borderPos = 0;
        for (int i = 0; i < processes.size(); i++) {
            borderPos += widths[i] + 1;
            String time = String.valueOf(processes.get(i).getCompletionTime());
            int startPos = borderPos - time.length() + 1;
            for (int k = 0; k < time.length() && startPos + k < timeline.length; k++) {
                timeline[startPos + k] = time.charAt(k);
            }
        }

        System.out.println(border);
        System.out.println(names);
        System.out.println(border);
        System.out.println(new String(timeline).stripTrailing());
        System.out.println();
    }
}
