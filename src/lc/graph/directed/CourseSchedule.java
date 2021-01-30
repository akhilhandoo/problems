package lc.graph.directed;

import java.util.*;

public class CourseSchedule {

    public static void main(String[] args) {
        int[][] deps = new int[][] {
                {3, 7},
                /*{4, 2},*/
                {1, 9},
                {5, 4},
                {2, 3},
                {6, 4},
                {7, 6}
        };
        System.out.println(canFinish(10, deps));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, GraphNode> nodes = new HashMap<>();
        List<GraphNode> courseList = new ArrayList<>();
        for (int index = 0; index < numCourses; index++) {
            GraphNode node = new GraphNode(index);
            nodes.put(index, node);
            courseList.add(node);
        }
        for (int index = 0; index < prerequisites.length; index++) {
            int[] parentChild = prerequisites[index];
            nodes.get(parentChild[0]).getDependency().add(nodes.get(parentChild[1]));
        }
        return hasCycle(courseList);
    }

    static class GraphNode {
        Integer data;
        Set<GraphNode> dependency;

        public GraphNode(Integer value) {
            data = value;
            dependency = new HashSet<>();
        }

        public Set<GraphNode> getDependency() {
            return dependency;
        }

        public Integer getData() {
            return data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GraphNode graphNode = (GraphNode) o;
            return data.equals(graphNode.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

    public static boolean hasCycle (List<GraphNode> nodes) {

        for (GraphNode node: nodes) {
            Set<GraphNode> white = new HashSet<>();
            Set<GraphNode> grey = new HashSet<>();
            Set<GraphNode> black = new HashSet<>();
            white.add(node);
            try {
                _hasCycle(node, white, grey, black);
            } catch (CycleDetectedException ce) {
                return false;
            }
        }
        return true;
    }

    public static void _hasCycle(GraphNode node, Set<GraphNode> white, Set<GraphNode> grey, Set<GraphNode> black) {
        if (null == node) {
            return;
        } else {
            if (grey.contains(node)) {
                throw new CycleDetectedException();
            } else {
                white.remove(node);
                grey.add(node);
                for (GraphNode dependency: node.getDependency()) {
                    _hasCycle(dependency, white, grey, black);
                }
                grey.remove(node);
                black.add(node);
            }
        }
    }

    static class CycleDetectedException extends RuntimeException {
    }
}
