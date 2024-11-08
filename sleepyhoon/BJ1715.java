package sleepyhoon;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            pq.offer(sc.nextInt());
        }

        while(pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            sum += first + second;
            pq.offer(first + second);
        }
        System.out.println(sum);
    }
}
