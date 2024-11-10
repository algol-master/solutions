import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;
import static java.lang.System.out;

/**
  기존 우선순위 큐에서 풀었던 문제 - 풀이 생략 및 이하 구성은 동일
*/

public class BJ1715 {

    public static void Main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        sb.append(solution(n, arr));
        out.println(sb);

    }

    private static int solution(int n, int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            queue.add(arr[i]);
        }

        int answer = 0;

        while(queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();

            int add = a+b;
            answer += add;
            queue.add(add);
        }
        return answer;
    }

}
