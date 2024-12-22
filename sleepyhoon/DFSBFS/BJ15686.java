package sleepyhoon.DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15686 {
    static int min = Integer.MAX_VALUE;
    static ArrayList<int[]> chickens;
    static ArrayList<int[]> homes;
    static int count = 0;
    static int[][] distances;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        chickens = new ArrayList<>();
        homes = new ArrayList<>();
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int input = Integer.parseInt(st.nextToken());
                if(input==2)
                    chickens.add(new int[]{i,j});
                if(input==1)
                    homes.add(new int[]{i,j});
            }
        }

        // 집과 치킨집 간 거리 계산 캐싱
        int homeCount = homes.size();
        int chickenCount = chickens.size();
        distances = new int[homeCount][chickenCount];
        for (int i = 0; i < homeCount; i++) {
            int[] home = homes.get(i);
            for (int j = 0; j < chickenCount; j++) {
                int[] chicken = chickens.get(j);
                distances[i][j] = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
            }
        }

        dfs(new ArrayList<>(),m,0,0);
        System.out.println(min);
    }

    private static void dfs(ArrayList<Integer> tmp, int m,int depth,int start) {
        if(depth == m) {
            int totalDistance = 0;
            for(int i=0;i< homes.size();i++){
                int homeDistance = Integer.MAX_VALUE;
                for (Integer integer : tmp) {
                    homeDistance = Math.min(homeDistance,distances[i][integer]);
                }
                totalDistance += homeDistance;
            }
            min = Math.min(min,totalDistance);
        }
        else {
            for(int i=start;i<chickens.size();i++){
                if(count > m) continue;
                count++;
                tmp.add(i);
                dfs(tmp,m,depth+1,i+1);
                tmp.remove(tmp.size()-1);
                count--;
            }
        }
    }
}
