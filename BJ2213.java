import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2213 {
    static class Node {
        int index;
        int cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
    static List<List<Integer>> list = new ArrayList<>();
    static int[][] dp;
    static Node[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new Node[n+1];
        dp = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            int cost = Integer.parseInt(st.nextToken());
            arr[i] = new Node(i,cost);
        }

        for(int i=0;i<n;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list.get(from).add(to);
            list.get(to).add(from);
        }

        if(n==1) {
            System.out.println(arr[1].cost);
            System.out.println(arr[1].index);
            return;
        }


    }

}
