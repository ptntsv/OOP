package org.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Toposort {

    public static <T> void sort(IGraph<T> graph) {
        HashMap<T, Boolean> visited = new HashMap<>();
        List<T> topoSorted = new ArrayList<>();
        for (var v : graph.getVertices()) {
            visited.put(v, false);
        }
        Stack<Pair<Boolean, T>> stack = new Stack<>();
        List<T> vs = graph.getVertices();
        stack.push(new Pair<>(false, graph.getVertices().get(0)));
        while (!stack.isEmpty()) {
            var u = stack.pop();
            if (u.first) {
                topoSorted.add(u.second);
                continue;
            }
            if (visited.get(u.second)) {
                continue;
            }
            visited.put(u.second, true);
            stack.push(new Pair<>(true, u.second));
            for (var adj : graph.getAdjacent(u.second)) {
                if (visited.get(adj)) {
                    continue;
                }
                stack.push(new Pair<>(false, adj));
            }
        }
        graph.reset();
        for (int i = topoSorted.size() - 1; i > 0; i--) {
            graph.addEdge(topoSorted.get(i), topoSorted.get(i - 1), 0.0);
        }
    }
}
