//    /**
//     * Method for graph deserialization. Format: v1 v2 v3 ... vn | w(v1->v2) w(v1->v3) ... w(v1->wn)
//     * where w is weight (lines are separated by \n) Example: 1 2 3|10 11 2 5 6|9 77 Means that
//     * there are 2 edges on the first line: 1 -> 2 with weight 10 and 1 -> 3 with weight 11 2 -> 5
//     * with weight 9 and 2 -> 6 with weight 77 on the second.
//     *
//     * @param str            Source string.
//     * @param representation The way to implement graph.
//     * @return Deserialized graph.
//     */
//    public static Graph<Integer> deserializeIntGraph(String str,
//        GraphRepresentation representation) {
//        ArrayList<Pair<Integer, Pair<Integer, Double>>> edges = new ArrayList<>();
//        Set<Integer> vertices = new HashSet<>();
//        String[] lines = str.split("\n");
//        for (var line : lines) {
//            var es = line.split("\\|")[0];
//            var weights = line.split("\\|")[1];
//            ArrayList<Integer> vs = new ArrayList<>(
//                Arrays.stream(es.split(" ")).map(Integer::parseInt).toList());
//            vertices.addAll(vs);
//            ArrayList<Double> ws = new ArrayList<>(
//                Arrays.stream(weights.split(" ")).map(Double::parseDouble).toList());
//            for (int i = 1; i < vs.size(); i++) {
//                edges.add(new Pair<>(vs.get(0), new Pair<>(vs.get(i), ws.get(i - 1))));
//            }
//        }
//        return new Graph<>(vertices.stream().toList(), edges, representation);
//    }