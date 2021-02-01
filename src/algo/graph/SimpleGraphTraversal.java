package algo.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class SimpleGraphTraversal {

  static class Node {
    String name;
    List<Node> children = new ArrayList<>();

    public Node(String name) {
      this.name = name;
    }

    public List<String> depthFirstSearch(List<String> array) {
      Queue<String> results = new ArrayDeque<>();
      recursiveDFS(this, results);
      results.forEach(x -> array.add(x));
      return array;
    }

    private void recursiveDFS(Node n, Queue<String> results) {
      if (null != n) {
        results.add(n.name);
        for (Node child: n.children) {
          recursiveDFS(child, results);
        }
      }
    }

    public List<String> breadthFirstSearch(List<String> array) {
      Queue<Node> processQueue = new ArrayDeque<>();
      processQueue.add(this);
      while (!processQueue.isEmpty()) {
        Node toProcess = processQueue.remove();
        array.add(toProcess.name);
        for (Node child: toProcess.children) {
          processQueue.add(child);
        }
      }
      return array;
    }

    public Node addChild(String name) {
      Node child = new Node(name);
      children.add(child);
      return this;
    }
  }
}
