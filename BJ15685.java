import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15685 {
    static boolean[][] visited = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            List<Integer> direction = new ArrayList<>();
            direction.add(d);
            visited[x][y] = true;
            int current = 0;
            while (g > current) {
                int size = direction.size();
                for (int j = size - 1; j >= 0; j--) {
                    direction.add((direction.get(j) + 1) % 4);
                }
                current++;
            }
            for (int dist : direction) {
                x += dx[dist];
                y += dy[dist];

                visited[x][y] = true;
            }
        }
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (visited[i][j] && visited[i + 1][j] && visited[i][j + 1] && visited[i + 1][j + 1]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
