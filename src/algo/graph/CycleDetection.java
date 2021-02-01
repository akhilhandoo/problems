package algo.graph;

import java.util.*;
import java.util.stream.Collectors;

public class CycleDetection {

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                {1, 3},
                {2, 3, 4},
                {0},
                {},
                {2, 5},
                {}
        };

        System.out.println(cycleInGraph(edges));
    }

    //  edges - Adjacency List starting from node #0
    public static boolean cycleInGraph(int[][] edges) {
        Map<Integer, GNode> graphNodes = new HashMap<>();
        int nodeId = 0;
        for (int[] adjacency: edges) {
            GNode node = graphNodes.get(nodeId);
            if (null == node) {
                node = new GNode(nodeId);
            }
            for (int childId: adjacency) {
                GNode childNode = graphNodes.get(childId);
                if (null == childNode) {
                    childNode = new GNode(childId);
                    graphNodes.put(childId, childNode);
                }
                node.addChild(childNode);
            }
            graphNodes.put(nodeId, node);
            nodeId++;
        }
        return hasCycle(graphNodes.values().stream().collect(Collectors.toSet()));
    }

    public static boolean hasCycle (Set<GNode> nodeSet) {
        Set<GNode> white = new HashSet<>(nodeSet);
        Set<GNode> gray = new HashSet<>();
        Set<GNode> black = new HashSet<>();
        try {
            for (GNode node: nodeSet) {
                coloredDFS(node, white, gray, black);
            }
        } catch (CycleDetectedException cde) {
            return true;
        }
        return false;
    }

    public static void coloredDFS (GNode currentNode, Set<GNode> white, Set<GNode> gray, Set<GNode> black) throws CycleDetectedException {
        if (null == currentNode || black.contains(currentNode)) {
            return;
        } else {
            if (gray.contains(currentNode)) {
                throw new CycleDetectedException();
            } else {
                white.remove(currentNode);
                gray.add(currentNode);
                for (GNode child: currentNode.getChildren()) {
                    coloredDFS(child, white, gray, black);
                }
                gray.remove(currentNode);
                black.add(currentNode);
            }
        }
    }

    static class GNode {
        private int val;
        private Set<GNode> children;

        public GNode(int val) {
            this.val = val;
            this.children = new HashSet<>();
        }

        public int getVal() {
            return val;
        }

        public void addChild(GNode node) {
            children.add(node);
        }

        public Set<GNode> getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GNode gNode = (GNode) o;
            return val == gNode.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }

    static class CycleDetectedException extends Exception{}
}
