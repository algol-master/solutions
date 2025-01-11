package sleepyhoon.DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14502 {
    static int[][] arr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static int n,m;
    static List<int[]> virus = new ArrayList<>();
    static List<int[]> empty = new ArrayList<>();
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }

                if(arr[i][j] == 0) {
                    empty.add(new int[]{i, j});
                }
            }
        }
        List<int[]> tmp = new ArrayList<>();
        wall(tmp,0);
        System.out.println(answer);
    }

    private static void wall(List<int[]> tmp, int index) {
        if(tmp.size()==3) {
            int[][] copy = copy();
            visited = new boolean[n][m];
            for(int i=0;i<virus.size();i++){
                dfs(virus.get(i)[0],virus.get(i)[1],copy);
            }

            int safe = 0;
            for(int j=0;j<n;j++){
                for(int k=0;k<m;k++){
                    if(copy[j][k] == 0)
                        safe++;
                }
            }
            answer = Math.max(answer,safe);
        } else {
            for(int i=index;i<empty.size();i++){
                tmp.add(empty.get(i));
                arr[empty.get(i)[0]][empty.get(i)[1]] = 1;
                wall(tmp,i+1);
                arr[empty.get(i)[0]][empty.get(i)[1]] = 0;
                tmp.remove(tmp.size()-1);
            }
        }
    }

    private static void dfs(int x,int y, int[][] copy) {
        visited[x][y] = true;
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0||nx>=n||ny<0||ny>=m||visited[nx][ny]) continue;
            if(copy[nx][ny]==0) {
                copy[nx][ny] = 2;
                dfs(nx,ny,copy);
            }
        }
    }

    private static int[][] copy() {
        int[][] tmp = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }
}
