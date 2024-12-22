package sleepyhoon.DFSBFS;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class PG250136 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int row;
    static int column;
    static Map<Integer,Integer> map = new HashMap<>();
    static int[][] visited;
    static int areaId = 0;
    static class Point {
        int x;
        int y;

        Point(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        row = land.length;
        column = land[0].length;
        visited = new int[row][column];

        for(int j=0;j<column;j++){
            for(int i=0;i<row;i++){
                if(land[i][j] == 1 && visited[i][j] == 0) {
                    areaId++;
                    bfs(i,j,land);
                }
            }
        }

        int answer = 0;
        for(int j=0;j<column;j++){
            Set<Integer> set = new HashSet<>();
            for(int i=0;i<row;i++){
                if(visited[i][j] != 0) {
                    set.add(visited[i][j]);
                }
            }
            int sum = 0;
            for (Integer integer : set) {
                sum += map.get(integer);
            }
            answer = Math.max(answer,sum);
        }
        System.out.println(answer);
    }
    private static void bfs(int x,int y,int[][] land) {
        visited[x][y] = areaId;
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(x,y));

        int count = 1;
        while(!q.isEmpty()) {
            Point poll = q.poll();
            for(int i=0;i<4;i++){
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if(nx<0||nx>=row||ny<0||ny>=column) continue;
                if(visited[nx][ny] != 0) continue;
                if(land[nx][ny] != 1) continue;

                visited[nx][ny] = areaId;
                q.offer(new Point(nx,ny));
                count++;
            }
        }

        map.put(areaId,count);
    }
}
