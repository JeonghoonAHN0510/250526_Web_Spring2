package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[] indegree;
    static boolean[][] adjMatrix;   // 인접 행렬
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        int T = Integer.parseInt(br.readLine());        // 테스트 케이스 T
        for (int i = 0; i < T; i++){
            N = Integer.parseInt(br.readLine());        // 팀의 수
            adjMatrix = new boolean[N + 1][N + 1];
            indegree = new int[N + 1];
            int[] origin_ranking = new int[N + 1];

            st = new StringTokenizer(br.readLine());    // 작년 팀 순위
            for (int j = 1; j <= N; j++){
                origin_ranking[j] = Integer.parseInt(st.nextToken());
            } // for end

            // 진입차수 설정
            for (int j = 1; j <= N; j++){
                int from = origin_ranking[j];
                for (int k = j + 1; k <= N; k++){
                    int to = origin_ranking[k];
                    adjMatrix[from][to] = true;
                    indegree[to]++;
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
                    indegree[x]++;
                    indegree[y]--;
                } else {
                    adjMatrix[x][y] = true;
                    adjMatrix[y][x] = false;
                    indegree[x]--;
                    indegree[y]++;
                } // if end
            } // for end
            topologicalSort();
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void topologicalSort(){
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++){
            if (indegree[i] == 0){
                queue.add(i);
            } // if end
        } // for end

        for (int i = 0; i < N; i++){
            // 큐가 N번 돌기 전에 비어있으면, 사이클 존재
            if (queue.isEmpty()){
                answer.append("IMPOSSIBLE").append("\n");
                return;
            } // if end

            // 큐에 2개 이상이 들어있으면, 순서 확정 불가
            if (queue.size() > 1){
                answer.append("?").append("\n");
                return;
            } // if end

            int current = queue.poll();
            result.add(current);
            for (int next = 1; next <= N; next++){
                if (adjMatrix[current][next]){
                    indegree[next]--;
                    if (indegree[next] == 0){
                        queue.add(next);
                    } // if end
                } // if end
            } // for end
        } // for end
        // return되지 않고 끝났으면, 출력
        for (int i = 0; i < N; i++){
            answer.append(result.get(i)).append(" ");
        } // for end
        answer.append("\n");
    } // func end
} // class end