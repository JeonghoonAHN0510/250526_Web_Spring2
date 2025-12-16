package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static boolean[] visited;
    static boolean[][] adjMatrix;   // 인접 행렬
    static int N;
    static List<Integer> order;     // 위상정렬 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        int T = Integer.parseInt(br.readLine());        // 테스트 케이스 T
        for (int i = 0; i < T; i++){
            N = Integer.parseInt(br.readLine());        // 팀의 수
            adjMatrix = new boolean[N + 1][N + 1];
            visited = new boolean[N + 1];
            order = new ArrayList<>();
            int[] origin_ranking = new int[N + 1];

            st = new StringTokenizer(br.readLine());    // 작년 팀 순위
            for (int j = 1; j <= N; j++){
                origin_ranking[j] = Integer.parseInt(st.nextToken());
            } // for end

            // 그래프 그리기
            for (int j = 1; j <= N; j++){
                for (int k = j + 1; k <= N; k++){
                    adjMatrix[origin_ranking[j]][origin_ranking[k]] = true;
                } // for end
            } // for end

            int M = Integer.parseInt(br.readLine());
            for (int j = 0; j < M; j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());   // 바뀐 팀 x
                int y = Integer.parseInt(st.nextToken());   // 바뀐 팀 y
                if (adjMatrix[x][y]){
                    adjMatrix[x][y] = false;
                    adjMatrix[y][x] = true;
                } else {
                    adjMatrix[x][y] = true;
                    adjMatrix[y][x] = false;
                } // if end
            } // for end
            topologicalSort();
            answer.append("\n");
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void dfs(int current){
        visited[current] = true;
        for (int i = 1; i <= N; i++){
            if (adjMatrix[current][i] && !visited[i]){
                dfs(i);
            } // if end
        } // for end
        order.add(current);
    } // func end
    static boolean isPossible(){
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (adjMatrix[order.get(j)][order.get(i)]) return false;
            } // for end
        } // for end
        return true;
    } // func end
    static void topologicalSort(){
        for (int i = 1; i <= N; i++){
            if (!visited[i]) dfs(i);
        } // for end
        // 위상 정렬의 끝부분부터 담기기 때문에, 순서 뒤집기
        Collections.reverse(order);
        if (isPossible()){
            for (int i = 0; i < N; i++){
                answer.append(order.get(i)).append(" ");
            } // for end
        } else {
            answer.append("IMPOSSIBLE");
        } // if end
    } // func end
} // class end