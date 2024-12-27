package sleepyhoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=n-1;i>=0;i--){
            while(!deque.isEmpty() && arr[i] >= deque.peek()) {
                deque.pop();
            }
            if(deque.isEmpty())
                answer[i] = -1;
            else
                answer[i] = deque.peek();
            deque.push(arr[i]);
        }

        for (int i : answer) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
