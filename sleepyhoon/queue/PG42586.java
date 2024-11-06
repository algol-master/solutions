package sleepyhoon.queue;

import java.util.*;

class PG42586 {

    static class Program {
        int progress;
        int speed;

        Program(int progress,int speed) {
            this.progress = progress;
            this.speed = speed;
        }
    }

    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        Queue<Program> q = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            q.offer(new Program(progresses[i], speeds[i]));
        }
        int day = 0;
        int sum = 0;
        boolean flag = false;
        while (!q.isEmpty()) {
            Program poll = q.peek();
            int currentProgress = poll.progress + (poll.speed * day);
            if(currentProgress >= 100) {
                q.remove();
                sum++;
                flag = true;
                continue;
            }
            if(flag) {
                flag = false;
                answer.add(sum);
                sum = 0;
            }
            day++;
        }
        answer.add(sum);

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
