package exhaustive_search;

/**
 * <pre>
 * 문제 : 프로그래머스 43165
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/43165?language=java
 * 난이도 : ??
 * 걸린 시간 : 15분
 * 알고리즘 : DFS
 *
 * 풀이
 * 전형적인 DFS 문제. 그냥 어렵지 않은, 대충 보기에 실버 4 정도 되는 문제인거 같다.
 * DFS와 재귀호출을 사용하여 풀었다. 각 재귀 호출의 반환값을 더하는 방식으로 구현하였다.
 * </pre>
 */
public class PG43165 {



    public static void main(String[] args) {
        int[] number = {1,1,1,1,1};
        int target = 3;

        System.out.println(solution(number, target));
    }

    public static int solution(int[] numbers, int target) {
        return dfs(numbers, 0, target, 0);
    }

    public static int dfs(int[] arr, int index, int target, int value) {
        if(index == arr.length) {
            if(target == value) {
                return 1;
            } else {
                return 0;
            }
        }

        int left = dfs(arr, index + 1, target, value - arr[index]);
        int right = dfs(arr, index + 1, target, value + arr[index]);
        return left + right;
    }

}
