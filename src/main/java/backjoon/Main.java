package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // N명의 학생
        int M = Integer.parseInt(st.nextToken());       // M번의 비교
        // 진입차수 배열
        int[] indegree = new int[N + 1];
        // 관련차수 Map
        Map<Integer, List<Integer>> relatedDegree = new TreeMap<>();
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());   // 자식 노드
            int b = Integer.parseInt(st.nextToken());   // 부모 노드
            indegree[a]++;                              // 자식 노드의 진입차수 증가시키기
            // 관련차수 추가하기
            if (relatedDegree.containsKey(b)){
                relatedDegree.get(b).add(a);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(a);
                relatedDegree.put(b, list);
            } // if end
        } // for end

        // 진입차수가 0인 학생부터 줄세우기
        Queue<Integer> graph = new LinkedList<>();
        for (int i = 1; i <= N; i++){
            if (indegree[i] == 0){
                graph.offer(i);
            } // if end
        } // for end

        Stack<Integer> result = new Stack<>();
        while (!graph.isEmpty()){
            int degree = graph.poll();
            result.push(degree);

            if (relatedDegree.containsKey(degree)){
                List<Integer> list = relatedDegree.get(degree);
                for (int i : list){
                    indegree[i]--;
                    if (indegree[i] == 0){
                        graph.offer(i);
                    } // if end
                } // for end
            } // if end
        } // while end

        while(!result.isEmpty()){
            answer.append(result.pop()).append(" ");
        } // while end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end