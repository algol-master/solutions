package sleepyhoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17406 {
    static int n, m, k, answer;
    static int[][] arr, save, origin;
    static boolean[] visited;

    // 순열을 사용해서 가능한 모든 경우 수를 구해야 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[k];
        save = new int[k][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            save[i][0] = r;
            save[i][1] = c;
            save[i][2] = s;
        }
        origin = new int[n][m];
        for(int i=0;i<n;i++){
            if (m >= 0)
                System.arraycopy(arr[i], 0, origin[i], 0, m);
        }
        permutation(new int[k], 0);
        System.out.println(answer);
    }

    private static int[][] play(int r, int c, int s) {
        int[][] tmp = new int[n][m];
        for (int k = 0; k <= s; k++) {
            int startX = r - k;
            int startY = c - k;

            int endX = r + k;
            int endY = c + k;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i >= startX && j >= startY && i <= endX && j <= endY) {
                        continue;
                    }
                    tmp[i][j] = arr[i][j];
                }
            }
            if (k == 0) {
                tmp[r][c] = arr[r][c];
            } else {
                rotate(tmp, startX, startY, endX, endY);
            }
        }
        return tmp;
    }

    private static void rotate(int[][] tmp, int startX, int startY, int endX, int endY) {
        int currentX = startX;
        int currentY = startY;

        while (currentY < endY) {
            tmp[currentX][currentY + 1] = arr[currentX][currentY];
            currentY++;
        }
        while (currentX < endX) {
            tmp[currentX + 1][currentY] = arr[currentX][currentY];
            currentX++;
        }
        while (currentY > startY) {
            tmp[currentX][currentY - 1] = arr[currentX][currentY];
            currentY--;
        }
        while (currentX > startX) {
            tmp[currentX - 1][currentY] = arr[currentX][currentY];
            currentX--;
        }
    }

    private static int solve(int[][] play) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += play[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    private static void copy(int[][] tmp) {
        for (int i = 0; i < n; i++) {
            if (m >= 0) {
                System.arraycopy(tmp[i], 0, arr[i], 0, m);
            }
        }
    }

    private static void permutation(int[] tmp, int depth) {
        if (depth == k) {
            for(int i=0;i<n;i++){
                if (m >= 0)
                    System.arraycopy(origin[i], 0, arr[i], 0, m);
            }
            int[][] play = new int[n][m];
            for (int i = 0; i < k; i++) {
                play = play(save[tmp[i]][0], save[tmp[i]][1], save[tmp[i]][2]);
                copy(play);
            }
            int solve = solve(play);
            answer = Math.min(solve, answer);
        } else {
            for (int i = 0; i < k; i++) {
                if (!visited[i]) {
                    tmp[depth] = i;
                    visited[i] = true;
                    permutation(tmp, depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
