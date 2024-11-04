package sleepyhoon.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringJoiner;

public class BJ1158 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        StringJoiner stringJoiner = new StringJoiner(", ","<",">");
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=n;i++){
            queue.offer(i);
        }
        int count = 1;
        while(!queue.isEmpty()) {
            Integer poll = queue.poll();
            if(count==k) {
                count=1;
                stringJoiner.add(poll.toString());
                continue;
            }
            queue.offer(poll);
            count++;
        }
        System.out.println(stringJoiner);
    }
}
