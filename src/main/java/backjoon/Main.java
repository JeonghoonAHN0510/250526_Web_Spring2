package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static boolean[] visited = new boolean[101];
    static Map<Integer, Integer> laddersAndSnakes = new HashMap<>();
    static int N, M, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 사다리의 수
        M = Integer.parseInt(st.nextToken());   // 뱀의 수

        for (int i = 0; i < N + M; i++){
            st = new StringTokenizer(br.readLine());
            laddersAndSnakes.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        } // for end

        bfs();
        answer.append(count);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        while (!queue.isEmpty()){
            // 현재 턴에 큐에 들어있는 데이터만큼 반복
            int size = queue.size();
            for (int i = 0; i < size; i++){
                // 현재 위치 꺼내기
                int current = queue.poll();
                // 마지막에 도착했으면, 탐색 종료
                if (current == 100) return;
                for (int j = 1; j < 7; j++){
                    // 주사위 굴려서, 다음 위치 탐색
                    int next = current + j;
                    if (next > 100) continue;
                    // 이동할 위치에 사다리나 뱀이 있으면 이동
                    if (laddersAndSnakes.containsKey(next)){
                        next = laddersAndSnakes.get(next);
                    } // if end
                    // 방문하지 않은 곳이면, 큐에 추가
                    if (!visited[next]){
                        visited[next] = true;
                        queue.offer(next);
                    } // if end
                } // for end
            } // for end
            count++;
        } // while end
    } // func end
} // class end