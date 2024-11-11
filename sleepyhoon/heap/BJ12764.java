package sleepyhoon.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// 한 1시간쓴듯.
public class BJ12764 {
    static class Human {
        int start;
        int end;

        Human(int start,int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class Computer {
        Human man;
        int index;
        int count;

        Computer(Human man, int index, int count) {
            this.man = man;
            this.index = index;
            this.count = count;
        }

        public Integer getIndex() {
            return this.index;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        // 최대 몇 개의 컴퓨터가 필요한지 구해야 한다.
        PriorityQueue<Human> humans = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        PriorityQueue<Computer> wait = new PriorityQueue<>((o1,o2)->o1.index - o2.index);
        Queue<Computer> use = new ArrayDeque<>();
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            humans.offer(new Human(start,end));
            wait.offer(new Computer(null,i+1,0));
        }

        while(!humans.isEmpty()) {
            Human human = humans.poll();
            int currentTime = human.start;
            for(int i=0;i<use.size();i++){
                Computer polled = use.poll();
                if(polled.man.end < currentTime) {
                    polled.man = null;
                    wait.offer(polled);
                    continue;
                }
                use.offer(polled);
            }
            Computer computer = wait.poll();
            computer.man = human;
            computer.count++;
            use.offer(computer);
        }
    }
}
