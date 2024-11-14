package sleepyhoon.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BJ1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> bigger = new PriorityQueue<>();
        int mid = 0;

        for(int i=0;i<n;i++){
            int input = Integer.parseInt(br.readLine());
            // 초기값 초기화
            if(mid==0) {
                mid = input;
                sb.append(mid).append("\n");
                continue;
            }

            if(mid <= input) {
                bigger.offer(input);
                if(bigger.size() - smaller.size() >= 2) {
                    smaller.offer(mid);
                    mid = bigger.poll();
                }
            }
            else {
                smaller.offer(input);
                if(smaller.size() - bigger.size() >= 2) {
                    bigger.offer(mid);
                    mid = smaller.poll();
                }
            }

            sb.append(mid).append("\n");
        }
        System.out.println(sb);
    }
}
