package sleepyhoon.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class BJ2800 {
    static class Position {
        int left;
        int right;

        Position(int left) {
            this.left = left;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }

    static List<Position> list = new ArrayList<>();
    static Set<String> set = new HashSet<>();
    static String s;
    static boolean[] ban;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.nextLine();
        Stack<Position> st = new Stack<>();
        // 괄호의 위치를 알아야 한다.
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(new Position(i));
            }
            if (s.charAt(i) == ')') {
                Position pop = st.pop();
                pop.setRight(i);
                list.add(pop);
            }
        }

        int loop = list.size();
        ban = new boolean[s.length()];
        for (int i = 1; i <= loop; i++) {
            List<Position> tmp = new ArrayList<>();
            dfs(tmp, i, 0);
        }
        List<String> answer = new ArrayList<>(set);
        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (String string : answer) {
            sb.append(string).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(List<Position> tmp, int target, int start) {
        if (target == tmp.size()) {
            Arrays.fill(ban, false);
            StringBuilder sb = new StringBuilder();
            for (Position p : tmp) {
                ban[p.left] = true;
                ban[p.right] = true;
            }

            for (int i = 0; i < s.length(); i++) {
                if (!ban[i]) {
                    sb.append(s.charAt(i));
                }
            }

            set.add(sb.toString());
        } else {
            for (int i = start; i < list.size(); i++) {
                tmp.add(list.get(i));
                dfs(tmp, target, i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
