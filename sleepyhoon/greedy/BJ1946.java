package sleepyhoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1946 {
    static class Point {
        int first;
        int second;
        Point(int first,int second) {
            this.first = first;
            this.second = second;
        }
    }
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Point[] arr = new Point[n];
            visited = new boolean[n];
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                arr[j] = new Point(first,second);
            }
            Arrays.sort(arr,(o1,o2)-> o1.first - o2.first);

            int pivot = arr[0].second;
            int count = 1;
            for(int j=1;j<n;j++){
                if(pivot > arr[j].second) {
                    count++;
                    pivot = arr[j].second;
                }
            }
            System.out.println(count);
        }
    }
}
