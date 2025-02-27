package sleepyhoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14889 {
    static int[][] arr;
    static int n;
    static int answer = Integer.MAX_VALUE;

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
        List<Integer> tmp = new ArrayList<>();
        dfs(tmp, 1);
        System.out.println(answer);
    }

    private static void dfs(List<Integer> tmp, int start) {
        if (tmp.size() == n / 2) {
            List<Integer> another = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (tmp.contains(i)) {
                    continue;
                }
                another.add(i);
            }
            int value1 = getRate(tmp);
            int value2 = getRate(another);
            answer = Math.min(answer, Math.abs(value2 - value1));
        } else {
            for (int i = start; i <= n; i++) {
                tmp.add(i);
                dfs(tmp, i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private static int getRate(List<Integer> tmp) {
        int result = 0;
        for (int i = 0; i < tmp.size() - 1; i++) {
            int first = tmp.get(i) - 1;
            for (int j = i + 1; j < tmp.size(); j++) {
                int second = tmp.get(j) - 1;
                result += arr[first][second] + arr[second][first];
            }
        }
        return result;
    }
}
