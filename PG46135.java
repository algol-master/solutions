import java.util.*;

class PG46135 {
    static int[] parents;
    static int[] heights;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        parents = new int[n];
        heights = new int[n];
        Arrays.fill(parents,-1);
        Arrays.fill(heights,0);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(computers[i][j] == 1)
                    union(i,j);
            }
        }

        for(int x : parents) {
            if(x<0) answer++;
        }

        return answer;
    }

    private static int findParent(int x) {
        if(parents[x] < 0)
            return x;
        else
            return parents[x] = findParent(parents[x]);

    }

    private static void union(int x,int y) {
        int px = findParent(x);
        int py = findParent(y);

        if(px==py) {
            return;
        }
        if(heights[px] < heights[py]) {
            int tmp = px;
            px = py;
            py = tmp;
        }

        if(heights[px] == heights[py])
            heights[px]++;
        heights[py] = 0;
        parents[px] += parents[py];
        parents[py] = px;
    }
}