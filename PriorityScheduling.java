//This is OS Assignment number 02
//Prepared by AHAMMED ABDUL AOUAL; SPRING 21, AUST-CSE.
//Practical implimentation of Priority Scheduling (pre-emptive) 
//using JAVA
package Assignments;

import java.util.Arrays;
import java.util.Scanner;

class Process {
    int pid;
    int burstTime;
    int priority;
    int arrivalTime;

    
    public Process(int pid, int burstTime, int priority, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for process " + (i + 1) + ":");
            System.out.print("Burst time: ");
            int burstTime = scanner.nextInt();
            System.out.print("Priority: ");
            int priority = scanner.nextInt();
            System.out.print("Arrival time: ");
            int arrivalTime = scanner.nextInt();

            processes[i] = new Process(i + 1, burstTime, priority, arrivalTime);
        }

        
        Arrays.sort(processes, (a, b) -> b.priority - a.priority);

        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] completionTime = new int[n];
        int[] responseTime = new int[n];

        waitingTime[0] = 0;
        for (int i = 1; i < n; i++) {
            waitingTime[i] = processes[i - 1].burstTime + waitingTime[i - 1];
        }

        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = processes[i].burstTime + waitingTime[i];
            completionTime[i] = turnaroundTime[i] + processes[i].arrivalTime;
            responseTime[i] = waitingTime[i]; 
        }

        System.out.println("\nOrder of execution:");
        for (Process p : processes) {
            System.out.print(p.pid + " ");
        }

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time\tCompletion Time\tResponse Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].pid + "\t\t" + processes[i].burstTime +
                               "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i] +
                               "\t\t" + completionTime[i] + "\t\t" + responseTime[i]);
        }

        double avgWaitingTime = Arrays.stream(waitingTime).average().orElse(0);
        double avgTurnaroundTime = Arrays.stream(turnaroundTime).average().orElse(0);

        System.out.println("\nAverage waiting time = " + avgWaitingTime);
        System.out.println("Average turnaround time = " + avgTurnaroundTime);

        scanner.close();
    }
}
