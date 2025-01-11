package sleepyhoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2213 {
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static int[] arr;
    static int[][] dp;
    static int n;
    static int max;
    static List<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n+1];
        visited = new boolean[n+1];
        dp = new int[n+1][2];

        for(int i=1;i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list.get(from).add(to);
            list.get(to).add(from);
        }

        dfs(1);
        System.out.println(max);
        if(dp[1][1] > dp[1][0]) {
            trace(1,1);
        } else {
            trace(1,0);
        }

        Collections.sort(answer);
        for (Integer i : answer) {
            System.out.print(i+" ");
        }
    }

    private static void dfs(int start) {
        int childCount = list.get(start).size();
        if(childCount==0) return;

        dp[start][0] = 0;
        dp[start][1] = arr[start];

        visited[start] = true;

        for(int child : list.get(start)) {
            if(!visited[child]) {
                dfs(child);

                dp[start][0] += Math.max(dp[child][0],dp[child][1]);
                dp[start][1] += dp[child][0];

                max = Math.max(dp[start][0],dp[start][1]);
            }
        }
        visited[start] = false;
    }

    private static void trace(int start, int attend) {
        visited[start] = true;
        if(attend==1) {
            answer.add(start);
            for(int i=0;i<list.get(start).size();i++){
                int next = list.get(start).get(i);
                if(!visited[next]){
                    trace(next,0);
                }
            }
        } else {
            for(int i=0;i<list.get(start).size();i++){
                int next = list.get(start).get(i);
                if(!visited[next]) {
                    if(dp[next][1] > dp[next][0]) {
                        trace(next,1);
                    } else
                        trace(next,0);
                }
            }
        }
        visited[start] = false;
    }
}
