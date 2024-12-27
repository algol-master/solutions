package sleepyhoon.dp;

class PG43165 {
    static char[] members = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        visited = new boolean[members.length];
        answer = 0;

        // DFS로 순열 생성 및 조건 검사
        dfs(new char[members.length], 0, data);
        System.out.println(answer); // 결과 출력
    }

    private static void dfs(char[] tmp, int depth, String[] data) {
        if (depth == members.length) {
            // 조건 검사
            if (isValid(tmp, data)) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < members.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            tmp[depth] = members[i];
            dfs(tmp, depth + 1, data);
            visited[i] = false;
        }
    }

    private static boolean isValid(char[] arrangement, String[] data) {
        for (String condition : data) {
            char from = condition.charAt(0);
            char to = condition.charAt(2);
            char operator = condition.charAt(3);
            int limit = condition.charAt(4) - '0';

            int fromIndex = findIndex(arrangement, from);
            int toIndex = findIndex(arrangement, to);
            int distance = Math.abs(fromIndex - toIndex) - 1;

            // 조건 검사
            if (operator == '=' && distance != limit) return false;
            if (operator == '>' && distance <= limit) return false;
            if (operator == '<' && distance >= limit) return false;
        }
        return true;
    }

    private static int findIndex(char[] arrangement, char target) {
        for (int i = 0; i < arrangement.length; i++) {
            if (arrangement[i] == target) {
                return i;
            }
        }
        return -1;
    }
}