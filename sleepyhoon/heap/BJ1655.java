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

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());

            if (smaller.isEmpty() || input <= smaller.peek()) {
                smaller.offer(input);
            } else {
                bigger.offer(input);
            }

            if (smaller.size() > bigger.size() + 1) {
                bigger.offer(smaller.poll());
            } else if (bigger.size() > smaller.size()) {
                smaller.offer(bigger.poll());
            }

            sb.append(smaller.peek()).append("\n");
        }

        System.out.print(sb);
    }
}
