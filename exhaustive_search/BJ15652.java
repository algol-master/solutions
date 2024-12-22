package exhaustive_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 문제 :BJ15652
 * 문제 링크 : https://www.acmicpc.net/problem/15652
 * 난이도 : 실버 3
 * 걸린 시간 : 15분
 * 알고리즘 : 순열(N of M)
 *
 * 풀이
 * n of m 문제이되, 중복된 숫자, 그러니까 1 1 와 같은 선택이 가능하다.
 * 사실 DFS도 아닌게, 이걸 트리 - 탐색 구조로 볼 수 없다. 그냥 재귀 호출 시
 * 시작 지점을 설정해 주도록 하고 끝냈다.
 * </pre>
 */
public class BJ15652 {

    public static int N;
    public static int M;
    public static int[] arr;
    public static int[] output;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        output = new int[M];
        for(int i = 1; i <= N; i++) {
            arr[i - 1] = i;
        }
        dfs(0, 0);
    }

    public static void dfs(int depth, int start) {
        if(depth == M) {
            for(int i = 0; i < M; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
        } else {
            for(int i = start; i < N; i++) {
                output[depth] = arr[i];
                dfs(depth + 1, i);
            }
        }
    }
}
