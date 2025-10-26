package org.example;

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.List;

public class GraphReader {

    public static List<Graph> readGraphsFromJson(String filePath) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            GraphData graphData = gson.fromJson(reader, GraphData.class);
            return graphData.getGraphs();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class GraphData {
        private List<Graph> graphs;

        public List<Graph> getGraphs() {
            return graphs;
        }
    }
}
