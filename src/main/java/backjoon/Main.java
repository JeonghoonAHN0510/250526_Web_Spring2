package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static Map<Integer, List<Integer>> map;
    static boolean isChecked;
    static int[] visited;       // 방문안함과 양 그래프 표현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());    // 테스트 케이스 개수 K

        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());   // 정점의 개수 V
            int E = Integer.parseInt(st.nextToken());   // 간선의 개수 E
            map = new HashMap<>();
            isChecked = true;
            visited = new int[V + 1];

            for (int j = 0; j < E; j++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                map.computeIfAbsent(X, k -> new ArrayList<>()).add(Y);
                map.computeIfAbsent(Y, k -> new ArrayList<>()).add(X);
            } // for end

            for (int j = 1; j <= V; j++){
                if (visited[j] == 0){
                    bfs(j);
                    if (!isChecked) break;
                } // if end
            } // for end

            if (isChecked){
                answer.append("YES").append("\n");
            } else {
                answer.append("NO").append("\n");
            } // if end
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void bfs(int node){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        visited[node] = 1;
        while (!queue.isEmpty()){
            int current = queue.poll();
            if (map.containsKey(current)){
                List<Integer> list = map.get(current);
                for (Integer integer : list){
                    if (visited[integer] == 0){
                        visited[integer] = visited[current] * -1;
                        queue.offer(integer);
                    } else if (visited[integer] == visited[current]){
                        isChecked = false;
                        return;
                    } // if end
                } // for end
            } // if end
        } // while end
    } // func end
} // class end