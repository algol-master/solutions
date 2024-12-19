package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17070 {
    static int n;
    static int[][] arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS 탐색 시작 (초기 위치 (0, 1) 상태: 가로)
        dfs(0, 1, 0);

        System.out.println(answer);
    }

    static void dfs(int x, int y, int state) {
        // 도착지점에 도달하면 정답 증가
        if (x == n - 1 && y == n - 1) {
            answer++;
            return;
        }

        // 현재 상태(state)에 따라 이동 가능한 위치 확인
        if (state == 0) { // 가로 상태
            if (isValid(x, y + 1) && arr[x][y + 1] == 0) { // 가로 이동
                dfs(x, y + 1, 0);
            }
            if (isValid(x + 1, y + 1) && canMoveDiagonally(x, y)) { // 대각선 이동
                dfs(x + 1, y + 1, 2);
            }
        } else if (state == 1) { // 세로 상태
            if (isValid(x + 1, y) && arr[x + 1][y] == 0) { // 세로 이동
                dfs(x + 1, y, 1);
            }
            if (isValid(x + 1, y + 1) && canMoveDiagonally(x, y)) { // 대각선 이동
                dfs(x + 1, y + 1, 2);
            }
        } else if (state == 2) { // 대각선 상태
            if (isValid(x, y + 1) && arr[x][y + 1] == 0) { // 가로 이동
                dfs(x, y + 1, 0);
            }
            if (isValid(x + 1, y) && arr[x + 1][y] == 0) { // 세로 이동
                dfs(x + 1, y, 1);
            }
            if (isValid(x + 1, y + 1) && canMoveDiagonally(x, y)) { // 대각선 이동
                dfs(x + 1, y + 1, 2);
            }
        }
    }

    static boolean isValid(int x, int y) {
        // 유효한 범위인지 확인
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static boolean canMoveDiagonally(int x, int y) {
        // 대각선으로 이동할 수 있는지 확인 (3칸이 모두 0이어야 함)
        return arr[x][y + 1] == 0 && arr[x + 1][y] == 0 && arr[x + 1][y + 1] == 0;
    }
}