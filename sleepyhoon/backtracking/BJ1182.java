package sleepyhoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1182 {
    static int[] arr;
    static int n;
    static int s;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=1;i<=n;i++){
            dfs(tmp,0,0,i);
        }
        System.out.println(answer);
    }
    private static void dfs(ArrayList<Integer> tmp, int start, int count, int depth) {
        if(depth==count && sum(tmp) == s) {
            answer++;
        }
        else {
            for(int i=start;i<n;i++) {
                tmp.add(arr[i]);
                dfs(tmp,i+1,count+1,depth);
                tmp.remove(tmp.size()-1);
            }
        }
    }

    private static int sum(ArrayList<Integer> tmp) {
        int sum = 0;
        for (int i : tmp) {
            sum += i;
        }
        return sum;
    }
}
