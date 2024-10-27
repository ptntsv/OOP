package org.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Toposort {

    public static <T> List<T> toposort(IGraph<T> graph) {
        HashMap<T, Boolean> visited = new HashMap<>();
        List<T> topoSorted = new ArrayList<>();
        for (var v : graph.getVertices()) {
            visited.put(v, false);
        }
        Stack<Pair<Boolean, T>> stack = new Stack<>();
        graph.getVertices();
        for (var v : graph.getVertices()) {
            if (visited.get(v)) {
                continue;
            }
            stack.push(new Pair<>(false, v));
            while (!stack.isEmpty()) {
                var u = stack.pop();
                if (u.first) {
                    topoSorted.add(0, u.second);
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
        }
        return topoSorted;
    }
}
