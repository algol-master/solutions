import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * <pre>
 * 문제 : 백준 1715
 * 링크 : https://www.acmicpc.net/problem/1715
 * 난이도 : 골드 4
 * 처음엔 그냥 작은 수로 정렬을 하여 아래 에서 부터 2개 씩 더하여 구했는데 문제의 예시에서는 성공하였으나 동일한 숫자가
 * 제공되는 경우에 대해서는 원하는 값을 얻지 못함.
 * 그래서 작은 수부터 구하되, 이를 다시 새로운 카드집이라고 생각하면 된다고 봐서 작은 2개를 더한 값을 다시 정렬 대상에
 * 포함 시키면 됨. -> 우선순위 큐로 정렬하되, 2개를 poll해서 더하고, 이를 다시 삽입하도록 하였다.
 * </pre>
 */
public class BJ1715 {

    public static void main(String[] args) throws Exception {
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
