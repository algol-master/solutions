package sleepyhoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ14888 {
    static int n;
    static int[] arr;
    static ArrayList<String> ex;
    static int answerMax = Integer.MIN_VALUE;
    static int answerMin = Integer.MAX_VALUE;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        ex = new ArrayList<>();
        visited = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int repeat = Integer.parseInt(st.nextToken());
        for(int i=0;i<repeat;i++){
            ex.add("+");
        }
        repeat = Integer.parseInt(st.nextToken());
        for(int i=0;i<repeat;i++){
            ex.add("-");
        }
        repeat = Integer.parseInt(st.nextToken());
        for(int i=0;i<repeat;i++){
            ex.add("*");
        }
        repeat = Integer.parseInt(st.nextToken());
        for(int i=0;i<repeat;i++){
            ex.add("/");
        }
        dfs(new String[n-1],0);
        System.out.println(answerMax);
        System.out.println(answerMin);

    }
    private static void dfs(String[] tmp,int depth) {
        if(depth==n-1) {
            int value = arr[0];
            for(int i=0;i<tmp.length;i++){
                value = calculate(tmp[i],value,i);
            }
            answerMax = Math.max(answerMax,value);
            answerMin = Math.min(answerMin,value);
        }
        else {
            for(int i=0;i<n-1;i++){
                if(visited[i]) continue;
                visited[i] = true;
                tmp[depth] = ex.get(i);
                dfs(tmp,depth+1);
                visited[i] = false;
            }
        }
    }

    private static int calculate(String execute,int target, int i) {
        int tmp = 0;
        if(execute.equals("+"))
            tmp = target + arr[i+1];
        if(execute.equals("-"))
            tmp = target - arr[i+1];
        if(execute.equals("*"))
            tmp = target * arr[i+1];
        if(execute.equals("/"))
            tmp = target / arr[i+1];
        return tmp;
    }
}
