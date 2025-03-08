package sleepyhoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17281 {
    static int[][] arr;
    static int[] order = new int[9];
    static boolean[] visited = new boolean[9];
    static int answer, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][9];
        answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int hit = Integer.parseInt(st.nextToken());
                arr[i][j] = hit;
            }
        }
        // 4번 타자는 등번호 1번(인덱스 0)이 서기로 하였음.
        order[3] = 0;
        visited[0] = true;
        permutation(0);
        System.out.println(answer);
    }

    // 1 - 안타
    // 2 - 2루타
    // 3 - 3루타
    // 4 - 홈런
    private static int move(int[] base, int n) {
        int score = 0;
        // 이미 진루한 주자들의 행동
        for (int i = 3; i > 0; i--) {
            if (base[i] == 1) {
                if (i + n >= 4) {
                    score++;
                } else {
                    base[i + n] = 1;
                }
                base[i] = 0;
            }
        }
        // 타자의 행동
        if (n == 4) {
            score++;
        } else {
            base[n] = 1;
        }
        return score;
    }


    private static void permutation(int depth) {
        if (depth == 3) {
            permutation(depth + 1);
            return;
        }
        if (depth == 9) {
            answer = Math.max(playGame(), answer);
        } else {
            for (int i = 1; i < 9; i++) {
                if (!visited[i]) {
                    order[depth] = i;
                    visited[i] = true;
                    permutation(depth + 1);
                    visited[i] = false;
                }
            }
        }
    }

    // 경기를 진행하는 메서드
    private static int playGame() {
        int score = 0;
        int current = 0;  // 타순 인덱스

        for (int inning = 0; inning < n; inning++) {
            int outCount = 0;
            int[] base = new int[4];

            while (outCount < 3) {
                int hitter = order[current];  // 타순에 따른 현재 타자
                int result = arr[inning][hitter];

                if (result == 0) {  // 아웃
                    outCount++;
                } else {
                    score += move(base, result);
                }

                current = (current + 1) % 9;  // 타순 순환
            }
        }

        return score;
    }
}
