package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class JSONUtils {

    public static Graph readGraphFromFile(InputStream inputStream) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new InputStreamReader(inputStream)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray graphsArray = jsonObject.getAsJsonArray("graphs");

            JsonObject graphJson = graphsArray.get(0).getAsJsonObject();
            List<String> nodes = gson.fromJson(graphJson.getAsJsonArray("nodes"), List.class);

            Type edgeListType = new TypeToken<List<Graph.Edge>>(){}.getType();
            List<Graph.Edge> edges = gson.fromJson(graphJson.getAsJsonArray("edges"), edgeListType);

            return new Graph(nodes, edges);
        }
    }

    public static void writeResultToFile(String filename, Prim.Result primResult, Kruskal.Result kruskalResult) {
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = new Gson();

            ResultData resultData = new ResultData(primResult, kruskalResult);

            gson.toJson(resultData, writer);
            System.out.println("Results written to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ResultData {
        Prim.Result prim;
        Kruskal.Result kruskal;

        public ResultData(Prim.Result prim, Kruskal.Result kruskal) {
            this.prim = prim;
            this.kruskal = kruskal;
        }
    }
}
