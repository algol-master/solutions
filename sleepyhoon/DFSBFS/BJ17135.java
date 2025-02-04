package sleepyhoon.DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17135 {
    static int[][] arr;
    static int n, m, d;
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};
    static int answer;
    static int temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new int[3], 0, 0);
        System.out.println(answer);
    }

    private static void dfs(int[] tmp, int start, int depth) {
        if (depth == 3) {
            int top = -1;
            int[][] copy = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (top == -1 && arr[i][j] == 1) {
                        top = i;
                    }
                    copy[i][j] = arr[i][j];
                }
            }
            while (top < n) {
                List<int[]> enemies = findEnemy(n, tmp, copy);
                for (int[] enemy : enemies) {
                    if (copy[enemy[0]][enemy[1]] == 1) {
                        copy[enemy[0]][enemy[1]] = 0;
                        temp++;
                    }
                }
                moveEnemy(copy);
                top++;
            }
            answer = Math.max(answer, temp);
            temp = 0;
        } else {
            for (int i = start; i < m; i++) {
                tmp[depth] = i;
                dfs(tmp, i + 1, depth + 1);
            }
        }
    }

    private static void moveEnemy(int[][] copy) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 1) {
                    if (i != n - 1) {
                        copy[i + 1][j] = 1;
                    }
                    copy[i][j] = 0;
                }
            }
        }
    }

    private static List<int[]> findEnemy(int x, int[] tmp, int[][] copy) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            boolean[][] visited = new boolean[n][m];
            for (int k = 0; k < n; k++) {
                Arrays.fill(visited[k], false);
            }
            int mostLeft = m;
            int minDistance = Integer.MAX_VALUE;
            int saveRow = -1;
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{x - 1, tmp[i], 1});
            visited[x - 1][tmp[i]] = true;
            while (!q.isEmpty()) {
                int[] poll = q.poll();

                if (poll[2] > d) {
                    continue;
                }

                if (copy[poll[0]][poll[1]] == 1 && minDistance > poll[2] && mostLeft > poll[1]) {
                    saveRow = poll[0];
                    mostLeft = poll[1];
                    minDistance = poll[2];
                }

                for (int j = 0; j < 3; j++) {
                    int nx = poll[0] + dx[j];
                    int ny = poll[1] + dy[j];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                        continue;
                    }
                    q.offer(new int[]{nx, ny, poll[2] + 1});
                    visited[nx][ny] = true;
                }
            }
            if (mostLeft == m && saveRow == -1) {
                continue;
            }
            list.add(new int[]{saveRow, mostLeft});
        }
        return list;
    }
}
