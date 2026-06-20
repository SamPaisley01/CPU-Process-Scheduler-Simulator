package scheduler.model;

public class Process {

    public enum State {
        NEW, READY, RUNNING, WAITING, TERMINATED
    }

    private int pid;
    private String name;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int priority;
    private int waitingTime;
    private int turnaroundTime;
    private int completionTime;
    private State state;

    public Process(int pid, String name, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.completionTime = 0;
        this.state = State.NEW;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public State getState() {
        return state;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("Process{pid=%d, name='%s', arrival=%d, burst=%d, priority=%d, state=%s}",
                pid, name, arrivalTime, burstTime, priority, state);
    }
}
