package sleepyhoon.graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BJ1525 {
    static Set<String> set = new HashSet<>();
    static String target = "123456780";

    static class Puzzle {
        String input;
        int count;

        Puzzle(String input, int count) {
            this.input = input;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(sc.nextInt());
            }
        }
        String input = sb.toString();
        set.add(input);

        Queue<Puzzle> q = new ArrayDeque<>();
        q.offer(new Puzzle(input, 0));
        int answer = -1;
        while (!q.isEmpty()) {
            Puzzle poll = q.poll();
            if (target.equals(poll.input)) {
                answer = poll.count;
                break;
            }
            int zeroIndex = findZero(poll.input);
            int[] dx = findIndexLimit(zeroIndex);
            for (int i = 0; i < dx.length; i++) {
                int index = zeroIndex + dx[i];
                String swap = swap(poll.input, index, zeroIndex);
                if (!set.contains(swap)) {
                    q.offer(new Puzzle(swap, poll.count + 1));
                    set.add(swap);
                }
            }
        }
        System.out.println(answer);
    }

    private static int[] findIndexLimit(int zeroIndex) {
        if(zeroIndex == 0) {
            return new int[] {1,3};
        }if(zeroIndex == 1) {
            return new int[] {-1,1,3};
        }if(zeroIndex == 2) {
            return new int[] {-1,3};
        }if(zeroIndex == 3) {
            return new int[] {1,-3,3};
        }if(zeroIndex == 4) {
            return new int[] {-1,1,3,-3};
        }if(zeroIndex == 5) {
            return new int[] {-1,-3,3};
        }if(zeroIndex == 6) {
            return new int[] {-3,1};
        }if(zeroIndex == 7) {
            return new int[] {-3,-1,1};
        }if(zeroIndex == 8) {
            return new int[]{-1,-3};
        }
        return new int[3];
    }

    private static int findZero(String poll) {
        for (int i = 0; i < poll.length(); i++) {
            if (poll.charAt(i) == '0') {
                return i;
            }
        }
        return 0;
    }

    private static String swap(String string, int a, int b) {
        char[] tmps = string.toCharArray();
        char tmp = tmps[a];
        tmps[a] = tmps[b];
        tmps[b] = tmp;
        return String.valueOf(tmps);
    }
}
