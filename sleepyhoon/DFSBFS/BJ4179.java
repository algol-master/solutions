package sleepyhoon.DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4179 {
    static char[][] arr;
    static boolean[][] visited;
    static int n, m;

    static class Man {
        int x;
        int y;
        int time;

        Man(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] costFire;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int startManX = 0;
        int startManY = 0;

        arr = new char[n][m];
        visited = new boolean[n][m];
        costFire = new int[n][m];
        Queue<int[]> fires = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = input[j];
                if (arr[i][j] == 'J') {
                    startManX = i;
                    startManY = j;
                }
                if (arr[i][j] == 'F') {
                    fires.offer(new int[]{i, j});
                    costFire[i][j] = 0;
                } else {
                    costFire[i][j] = Integer.MAX_VALUE/2;
                }
            }
        }

        while (!fires.isEmpty()) {
            int[] fire = fires.poll();
            int x = fire[0];
            int y = fire[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || arr[nx][ny] == '#' || arr[nx][ny] == 'J') {
                    continue;
                }

                if (costFire[nx][ny] > costFire[x][y] + 1) {
                    costFire[nx][ny] = costFire[x][y] + 1;
                    fires.offer(new int[]{nx, ny});
                }
            }
        }

        int answer = bfs(startManX, startManY);
        if (answer == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }

    private static int bfs(int startManX, int startManY) {
        Queue<Man> q = new ArrayDeque<>();
        q.offer(new Man(startManX, startManY, 0));
        int answer = -1;

        while (!q.isEmpty()) {
            Man poll = q.poll();

            if (costFire[poll.x][poll.y] != Integer.MAX_VALUE/2 && costFire[poll.x][poll.y] <= poll.time) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    answer = poll.time + 1;
                    return answer;
                }

                if (arr[nx][ny] == '#' || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new Man(nx, ny, poll.time + 1));
            }
        }
        return answer;
    }
}
