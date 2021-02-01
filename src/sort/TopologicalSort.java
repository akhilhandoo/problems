package sort;

import datadef.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

public class TopologicalSort {

  public static void main(String[] args) {
    TopologicalSort ts = new TopologicalSort();
    List<Map.Entry<Integer, Integer>> input = new ArrayList<>();
    input.add(new AbstractMap.SimpleEntry(0, 1));
    input.add(new AbstractMap.SimpleEntry(0, 2));
    input.add(new AbstractMap.SimpleEntry(0, 5));
    input.add(new AbstractMap.SimpleEntry(1, 4));
    input.add(new AbstractMap.SimpleEntry(5, 2));
    input.add(new AbstractMap.SimpleEntry(3, 2));
    input.add(new AbstractMap.SimpleEntry(3, 4));
    input.add(new AbstractMap.SimpleEntry(3, 5));
    input.add(new AbstractMap.SimpleEntry(3, 6));
    input.add(new AbstractMap.SimpleEntry(6, 0));
    input.add(new AbstractMap.SimpleEntry(6, 4));
    System.out.println(ts.ordered(input));
  }

  public <T> List<T> ordered(List<Map.Entry<T, T>> input) {
    Map<T, GraphNode<T>> handles = new HashMap<>();
    for (Map.Entry<T, T> dependency: input) {
      GraphNode<T> keyNodeRepresentation = handles.get(dependency.getKey());
      if (null == keyNodeRepresentation) {
        keyNodeRepresentation = new GraphNode<>(dependency.getKey());
        handles.put(dependency.getKey(), keyNodeRepresentation);
      }
      GraphNode<T> valueNodeRepresentation = handles.get(dependency.getValue());
      if (null == valueNodeRepresentation) {
        valueNodeRepresentation= new GraphNode<>(dependency.getValue());
        handles.put(dependency.getValue(), valueNodeRepresentation);
      }
      keyNodeRepresentation.addChild(valueNodeRepresentation);
    }
    return topSort(handles.values().stream().collect(Collectors.toList()))
            .stream().map(x -> x.getData()).collect(Collectors.toList());
  }

  public <T> List<GraphNode<T>> topSort(List<GraphNode<T>> input) {
    Set<GraphNode<T>> marked = new HashSet<>(input.size());
    List<GraphNode<T>> toReturn = new ArrayList<>();
    for (GraphNode<T> node: input) {
      dfs(node, toReturn, marked);
    }
    return toReturn;
  }

  public <T> void dfs(GraphNode<T> node, List<GraphNode<T>> container, Set<GraphNode<T>> marked) {
    if (marked.contains(node)) {
      return;
    }
    List<GraphNode<T>> children = node.getChildren();
    if (null != children && children.size() > 0) {
      for (GraphNode<T> child: children) {
        dfs(child, container, marked);
      }
    }
    container.add(node);
    marked.add(node);
  }
}
