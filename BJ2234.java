import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2234 {
    static int[][] rooms;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int n, m;
    static int answer1, answer2, answer3, roomId;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        visited = new boolean[n][m];
        rooms = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    int value = bfs(arr, i, j);
                    answer2 = Math.max(answer2, value);
                    answer1++;
                    map.put(roomId, value);
                    roomId++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    if (rooms[i][j] == rooms[nx][ny]) {
                        continue;
                    }
                    answer3 = Math.max(answer3, map.get(rooms[i][j]) + map.get(rooms[nx][ny]));
                }
            }
        }

        sb.append(answer1).append("\n").append(answer2).append("\n").append(answer3);
        System.out.println(sb);
    }

    private static int bfs(int[][] arr, int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        rooms[x][y] = roomId;
        int count = 1;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            boolean[] wall = findWalls(arr, poll[0], poll[1]);
            for (int i = 0; i < 4; i++) {
                if (wall[i]) {
                    continue;
                }
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                    continue;
                }
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                rooms[nx][ny] = roomId;
                count++;
            }
        }
        return count;
    }

    private static boolean[] findWalls(int[][] arr, int x, int y) {
        boolean[] wall = new boolean[4];
        Arrays.fill(wall, false);
        int point = arr[x][y];

        for (int i = 3; i >= 0; i--) {
            if (point >= Math.pow(2, i)) {
                point -= (int) Math.pow(2, i);
                wall[i] = true;
            }
        }
        return wall;
    }
}
