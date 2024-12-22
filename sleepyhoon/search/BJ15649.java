package sleepyhoon.search;

import java.util.Scanner;

public class BJ15649 {
    static int n,r;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        r = sc.nextInt();
        visited = new boolean[n+1];
        dfs(new int[r],0);
    }

    private static void dfs(int[] tmp, int depth) {
        if(depth==r) {
            for (int i : tmp) {
                System.out.print(i+" ");
            }
            System.out.println();
        } else {
            for(int i=1;i<=n;i++){
                if(visited[i]) continue;
                visited[i] = true;
                tmp[depth] = i;
                dfs(tmp,depth+1);
                visited[i] = false;
            }
        }
    }
}
