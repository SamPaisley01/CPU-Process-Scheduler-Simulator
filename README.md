# CPU Process Scheduler Simulator

A Java-based simulator that shows how an operating system schedules CPU processes using scheduling algorithms.

## Algorithms

- **FCFS (First Come First Served)**: processes are executed in the order they arrive
- **SJF (Shortest Job First)**: always picks the process with the shortest burst time next
- **Priority Scheduling**: always picks the highest priority process next (lower number = higher priority)
- **Round Robin**: each process gets a fixed time slice, cycling through until all are complete

## Data Structures

- **CustomQueue** — linked-list backed queue used by FCFS and Round Robin
- **MinHeap** — generic min-heap used by SJF and Priority Scheduling to always retrieve the best candidate in O(log n)

## How to Run

**Compile:**
javac -d bin src/scheduler/.java src/scheduler/**/.java



**Run:**
java -cp bin scheduler.Main



## Usage

1. Enter each process by name, arrival time, burst time, and priority
2. Type "done" when all processes are entered
3. Select a scheduling algorithm from the menu
4. If Round Robin is selected, enter a time quantum
5. View the Gantt chart and statistics table
6. Choose to run again or exit

## Output

- **Gantt Chart**: ASCII timeline showing execution order and completion times
- **Statistics Table**: per-process waiting time and turnaround time, plus averages
