package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ14891 {
    static String[] arr = new String[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            arr[i] = br.readLine();
        }
        int loop = Integer.parseInt(br.readLine());
        for (int i = 0; i < loop; i++) {
            String[] split = br.readLine().split(" ");
            int index = Integer.parseInt(split[0]) - 1;
            int direction = Integer.parseInt(split[1]);
            doing(index, direction);
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i].charAt(0) == '1') {
                answer += (int) Math.pow(2, i);
            }
        }
        System.out.println(answer);
    }

    private static void doing(int index, int direction) {
        int[] rotate = new int[4]; // 각 톱니바퀴의 회전 방향 저장
        rotate[index] = direction;
        // 왼쪽 톱니바퀴들 회전 방향 결정
        for (int j = index; j > 0; j--) {
            if (arr[j].charAt(6) != arr[j - 1].charAt(2)) {
                rotate[j - 1] = -rotate[j]; // 반대 방향 회전
            } else {
                break; // 같은 극이면 회전 X
            }
        }

        // 오른쪽 톱니바퀴들 회전 방향 결정
        for (int j = index; j < 3; j++) {
            if (arr[j].charAt(2) != arr[j + 1].charAt(6)) {
                rotate[j + 1] = -rotate[j]; // 반대 방향 회전
            } else {
                break; // 같은 극이면 회전 X
            }
        }

        // 계산된 방향대로 회전 적용
        for (int j = 0; j < 4; j++) {
            if (rotate[j] != 0) {
                turn(j, rotate[j]);
            }
        }

    }

    private static void turn(int index, int direction) {
        String s = arr[index];
        if (direction == 1) {
            arr[index] = s.charAt(7) + s.substring(0, 7);
        } else {
            arr[index] = s.substring(1) + s.charAt(0);
        }
    }
}
