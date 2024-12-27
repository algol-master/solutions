package sleepyhoon.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1717 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        for(int i=0;i<n+1;i++){
            parents[i] = i;
        }
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            if(operator == 0) {
                union(first,second);
            } else {
                if (check(first, second)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static int findParent(int x) {
        if(x == parents[x])
            return x;
        else
            return findParent(parents[x]);
    }

    private static void union(int a, int b) {
        int first = findParent(a);
        int second = findParent(b);
        parents[second] = first;
    }

    private static boolean check(int a, int b) {
        return findParent(a) == findParent(b);
    }
}
