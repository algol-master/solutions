package sleepyhoon.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ15663 {
    static int[] arr;
    static int n,r;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n];
        st= new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(new int[r],0);
    }

    private static void dfs(int[] tmp, int depth) {
        if (depth == r) {
            for (int i : tmp) {
                System.out.print(i+" ");
            }
            System.out.println();
        } else {
            int before = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i] || before == arr[i]) continue;
                visited[i] = true;
                tmp[depth] = arr[i];
                before = arr[i];
                dfs(tmp, depth + 1);
                visited[i] = false;
            }
        }
    }
}
