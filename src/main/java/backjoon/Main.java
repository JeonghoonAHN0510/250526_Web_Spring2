package backjoon;

import java.io.*;
import java.util.*;

class Bus {
    int start;
    int end;
    int time;
    public Bus(int start, int end, int time){
        this.start = start;
        this.end = end;
        this.time = time;
    } // func end
} // class end

public class Main {
    static StringBuilder answer = new StringBuilder();
    static ArrayList<Bus> buses = new ArrayList<>();
    static long[] dist;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 도시의 개수 N
        M = Integer.parseInt(st.nextToken());   // 버스 노선 개수 M


        dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int startCity = Integer.parseInt(st.nextToken());
            int endCity = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            buses.add(new Bus(startCity, endCity, time));
        } // for end

        if (bellmanFord()){
            answer.append("-1");
        } else {
            for (int i = 2; i <= N; i++){
                if (dist[i] == Integer.MAX_VALUE){
                    // 도달할 수 없으면, -1 출력
                    answer.append("-1\n");
                } else {
                    answer.append(dist[i]).append("\n");
                } // if end
            } // for end
        } // if end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static boolean bellmanFord(){
        dist[1] = 0;

        for (int i = 0; i < N; i++){
            for (Bus bus : buses){
                if (dist[bus.start] == Integer.MAX_VALUE) continue;

                if (dist[bus.end] > dist[bus.start] + bus.time){
                    dist[bus.end] = dist[bus.start] + bus.time;

                    if (i == N - 1) return true;
                } // if end
            } // for end
        } // for end
        return false;
    } // func end
} // class end