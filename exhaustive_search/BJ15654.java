package exhaustive_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 문제 :BJ15654
 * 문제 링크 : https://www.acmicpc.net/problem/15654
 * 난이도 : 실버 3
 * 걸린 시간 : 5분
 * 알고리즘 : dfs, 순열
 *
 * 풀이
 * 전형적인 dfs / 순열 문제. 왜 똑같은 문제가 있는거지?
 *
 * </pre>
 */
public class BJ15654 {
    public static int N;
    public static int M;
    public static int[] arr;
    public static int[] output;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        output = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            visited[i] = false;
        }

        Arrays.sort(arr);

        dfs(0);


    }

    public static void dfs(int depth) {
        if(depth == M) {
            for(int i = 0; i < M; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
        } else {
            for(int i = 0; i < N; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    output[depth] = arr[i];
                    dfs(depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
