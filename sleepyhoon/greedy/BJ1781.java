package sleepyhoon.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ1781 {
    static class Problem {
        int deadLine;
        int ramens;

        Problem(int deadLine, int ramens) {
            this.deadLine = deadLine;
            this.ramens = ramens;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        PriorityQueue<Problem> problems = new PriorityQueue<>((o1,o2) -> {
            if(o1.deadLine == o2.deadLine)
                return o2.ramens - o1.ramens;
            else
                return o1.deadLine - o2.deadLine;
        });

        PriorityQueue<Integer> answer = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            int first = sc.nextInt();
            int second = sc.nextInt();
            problems.offer(new Problem(first,second));
        }

        while(!problems.isEmpty()) {
            Problem poll = problems.poll();

            if(answer.size() < poll.deadLine) {
                answer.offer(poll.ramens);
            } else {
                if(answer.peek() < poll.ramens) {
                    answer.poll();
                    answer.offer(poll.ramens);
                }
            }
        }
        int count = 0;
        while(!answer.isEmpty()) {
            count += answer.poll();
        }
        System.out.println(count);
    }

}
