import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ4195 {
    static Map<String,Integer> map = new HashMap<>();
    static int[] parents;
    static int[] heights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        int id = 0;
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            parents = new int[2*n];
            heights = new int[2*n];
            Arrays.fill(parents,-1);
            Arrays.fill(heights,0);

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String from = st.nextToken();
                String to = st.nextToken();

                if(!map.containsKey(from))
                    map.put(from,id++);
                if(!map.containsKey(to))
                    map.put(to,id++);

                union(map.get(from),map.get(to));
                sb.append(-1 * parents[findParent(map.get(from))]).append("\n");
            }
        }
        System.out.println(sb);
    }
    private static int findParent(int x) {
        if(parents[x] < 0)
            return x;
        else
            return parents[x] = findParent(parents[x]);
    }

    private static void union(int x, int y) {
        int px = findParent(x);
        int py = findParent(y);

        if(px==py)
            return;

        if(heights[px] < heights[py]) {
            int tmp = px;
            px = py;
            py = tmp;
        }

        if(heights[px] == heights[py])
            heights[px]++;

        heights[py] = 0;
        parents[px] += parents[py];
        parents[py] = px;
    }
}
