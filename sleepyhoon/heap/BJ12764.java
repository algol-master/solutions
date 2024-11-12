package sleepyhoon.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ12764 {

    static PriorityQueue<Human> humans = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
    static int n;
    static PriorityQueue<Computer> wait = new PriorityQueue<>(((o1, o2) -> o1.index - o2.index));
    static PriorityQueue<Computer> use = new PriorityQueue<>(((o1, o2) -> o1.end - o2.end));
    static int[] arr;

    static class Human {
        int start;
        int end;

        Human(int start,int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class Computer {
        int index;
        int end;

        Computer(int index) {
            this.index = index;
            this.end = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            humans.offer(new Human(start,end));
            Computer newCom = new Computer(i);
            wait.offer(newCom);
        }

        // 초기화
        Human poll = humans.poll();
        Computer computer = wait.poll();
        computer.end = poll.end;
        arr[computer.index]++;
        use.offer(computer);
        while(!humans.isEmpty()) {
            Human man = humans.poll();
            int currentTime = man.start;

            while(!use.isEmpty()) {
                // 시간 정리
                Computer computer1 = use.poll();
                if(computer1.end < currentTime) {
                    computer1.end = -1;
                    wait.offer(computer1);
                }
                else {
                    use.offer(computer1);
                    break;
                }
            }

            Computer com = wait.poll();
            com.end = man.end;
            arr[com.index]++;
            use.offer(com);
        }
        int maxSize = 0;
        for (int i : arr) {
            if(i==0) break;
            maxSize++;
            sb.append(i).append(" ");
        }
        System.out.println(maxSize);
        System.out.println(sb);
    }
}
