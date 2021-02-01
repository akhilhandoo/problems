package algo.famous;

import java.util.*;
import java.util.stream.Collectors;

public class TopSort {

  public static void main(String[] args) {
    List<Integer> jobs = Arrays.asList(new Integer[]{1, 2, 3, 4});
    List<Integer[]> deps = new ArrayList<>();
    deps.add(new Integer[]{1, 2});
    deps.add(new Integer[]{1, 3});
    deps.add(new Integer[]{3, 2});
//    deps.add(new Integer[]{4, 2});
    deps.add(new Integer[]{4, 3});
//    deps.add(new Integer[]{2, 1});
    deps.add(new Integer[]{2, 4});
    System.out.println(TopSort.topologicalSort(jobs, deps));
  }

  public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {

    Map<Integer, DGraph> handles = new HashMap<>();
    jobs.forEach(x -> handles.put(x, new DGraph(x)));

    for (Integer[] dependency: deps) {
      DGraph parent = handles.get(dependency[1]);
      DGraph child = handles.get(dependency[0]);
      parent.children.add(child);
    }
    List<DGraph> nodes = handles.values().stream().collect(Collectors.toList());
    List<DGraph> container = new ArrayList<>();
    if (!hasCycle(nodes)) {
      Set<DGraph> marked = new HashSet<>();
      for (DGraph node : nodes) {
        dfs(node, marked, container);
      }
    }
    return container.stream().map(x -> new Integer(x.value)).collect(Collectors.toList());
  }

  static class DGraph {
    int value;
    List<DGraph> children;

    public DGraph(int value) {
      this.value = value;
      children = new ArrayList<>();
    }
  }

  public static void dfs(DGraph node, Set<DGraph> marked, List<DGraph> container) {
    if (marked.contains(node)) {
        return;
    } else {
      marked.add(node);
      for (DGraph child: node.children) {
        dfs(child, marked, container);
      }
      container.add(node);
    }
  }

  public static boolean hasCycle(List<DGraph> nodes) {
    Set<DGraph> white = new HashSet<>();
    Set<DGraph> grey = new HashSet<>();
    Set<DGraph> black = new HashSet<>();
    white.addAll(nodes);
    Iterator<DGraph> iterator = white.iterator();
    while (iterator.hasNext()) {
      DGraph node = iterator.next();
      iterator.remove();
      try {
        coloredDFS(node, white, grey, black);
      } catch (CycleDetectedException cde) {
        return true;
      }
    }
    return false;
  }

  private static void coloredDFS(DGraph node, Set<DGraph> white, Set<DGraph> grey, Set<DGraph> black) {
    if (black.contains(node)) {
      return;
    } else if (grey.contains(node)) {
      throw new CycleDetectedException();
    } else {
      grey.add(node);
      for (DGraph child : node.children) {
        coloredDFS(child, white, grey, black);
      }
      grey.remove(node);
      black.add(node);
    }
  }

  static class CycleDetectedException extends RuntimeException {
  }
}
