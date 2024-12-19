package sleepyhoon.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1992 {
    static char[][] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        func(0, 0, n); // n은 전체 크기
        System.out.println(sb);
    }

    private static void func(int x, int y, int size) {
        if (checkUniform(x, y, size)) {
            sb.append(arr[x][y]); // 모든 값이 같다면 해당 값 추가
            return;
        }

        sb.append('('); // 값이 다르면 분할 정복
        int newSize = size / 2;
        func(x, y, newSize); // 왼쪽 위
        func(x, y + newSize, newSize); // 오른쪽 위
        func(x + newSize, y, newSize); // 왼쪽 아래
        func(x + newSize, y + newSize, newSize); // 오른쪽 아래
        sb.append(')');
    }

    // 해당 영역이 모두 같은 값인지 확인
    private static boolean checkUniform(int x, int y, int size) {
        char value = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}
