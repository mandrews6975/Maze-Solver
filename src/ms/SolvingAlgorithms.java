package ms;

import javafx.util.Pair;
import java.util.*;
import java.lang.IllegalArgumentException;

public class SolvingAlgorithms {

    public static int[][] BFS(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){
        if(origin.getKey() < 0 || origin.getValue() < 0 || origin.getKey() > maze.length - 1 || origin.getValue() > maze.length - 1 || maze[origin.getKey()][origin.getValue()] != 0){
            throw new IllegalArgumentException("ERROR: Origin is outside of maze limits.");
        }
        if(destination.getKey() < 0 || destination.getValue() < 0 || destination.getKey() > maze.length - 1 || destination.getValue() > maze.length - 1 || maze[destination.getKey()][destination.getValue()] != 0){
            throw new IllegalArgumentException("ERROR: Destination is outside of maze limits.");
        }
        System.out.println(" via BFS...");
        int[][] mazeSolved = maze.clone();
        ArrayDeque<Node> q = new ArrayDeque<>();
        HashMap<Pair<Integer, Integer>, Node> nodes = new HashMap<>();
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                Node cur = new Node(new Pair<>(i, j), Integer.MAX_VALUE, null);
                nodes.put(cur.getCoord(), cur);
            }
        }
        mazeSolved[origin.getKey()][origin.getValue()] = 3;
        q.add(new Node(new Pair<>(origin.getKey(), origin.getValue()), 0, null));
        q.peek().visit();
        System.out.println("Starting at origin: [" + origin.getKey() + "," + origin.getValue() + "]...");
        while(!q.isEmpty()){
            Node cur = q.pop();
            Node top = nodes.get(new Pair<>(cur.getCoord().getKey(), cur.getCoord().getValue() - 1));
            Node bottom = nodes.get(new Pair<>(cur.getCoord().getKey(), cur.getCoord().getValue() + 1));
            Node left = nodes.get(new Pair<>(cur.getCoord().getKey() - 1, cur.getCoord().getValue()));
            Node right = nodes.get(new Pair<>(cur.getCoord().getKey() + 1, cur.getCoord().getValue()));
            ArrayList<Node> adj = new ArrayList<>();
            if(top != null && top.getCoord().getKey() <= maze.length && top.getCoord().getValue() <= maze[0].length){
                adj.add(top);
            }
            if(bottom != null && bottom.getCoord().getKey() < maze.length && bottom.getCoord().getValue() < maze[0].length){
                adj.add(bottom);
            }
            if(left != null && left.getCoord().getKey() < maze.length && left.getCoord().getValue() < maze[0].length){
                adj.add(left);
            }
            if(right != null && right.getCoord().getKey() < maze.length && right.getCoord().getValue() < maze[0].length){
                adj.add(right);
            }
            for(Node node : adj){
                if(node.getCoord().getKey() >= 0 && node.getCoord().getValue() >= 0 && maze[node.getCoord().getKey()][node.getCoord().getValue()] != 1 && !node.getVisited()){
                    node.visit();
                    if(node.getCoord().equals(destination)){
                        System.out.print("Found destination: [" + destination.getKey() + "," + destination.getValue() + "]");
                        mazeSolved[node.getCoord().getKey()][node.getCoord().getValue()] = 3;
                        return mazeSolved;
                    }else{
                        mazeSolved[node.getCoord().getKey()][node.getCoord().getValue()] = 2;
                        q.add(node);
                    }
                }
            }
        }
        return mazeSolved;
    }

    public static int[][] DFS(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){
        if(origin.getKey() < 0 || origin.getValue() < 0 || origin.getKey() > maze.length - 1 || origin.getValue() > maze.length - 1 || maze[origin.getKey()][origin.getValue()] != 0){
            throw new IllegalArgumentException("ERROR: Origin is outside of maze limits.");
        }
        if(destination.getKey() < 0 || destination.getValue() < 0 || destination.getKey() > maze.length - 1 || destination.getValue() > maze.length - 1 || maze[destination.getKey()][destination.getValue()] != 0){
            throw new IllegalArgumentException("ERROR: Destination is outside of maze limits.");
        }
        System.out.println(" via DFS...");
        int[][] mazeSolved = maze.clone();
        ArrayDeque<Node> stack = new ArrayDeque<>();
        HashMap<Pair<Integer, Integer>, Node> nodes = new HashMap<>();
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                Node cur = new Node(new Pair<>(i, j), Integer.MAX_VALUE, null);
                nodes.put(cur.getCoord(), cur);
            }
        }
        mazeSolved[origin.getKey()][origin.getValue()] = 3;
        stack.addFirst(new Node(new Pair<>(origin.getKey(), origin.getValue()), 0, null));
        System.out.println("Starting at origin: [" + origin.getKey() + "," + origin.getValue() + "]...");
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            cur.visit();
            Node top = nodes.get(new Pair<>(cur.getCoord().getKey(), cur.getCoord().getValue() - 1));
            Node bottom = nodes.get(new Pair<>(cur.getCoord().getKey(), cur.getCoord().getValue() + 1));
            Node left = nodes.get(new Pair<>(cur.getCoord().getKey() - 1, cur.getCoord().getValue()));
            Node right = nodes.get(new Pair<>(cur.getCoord().getKey() + 1, cur.getCoord().getValue()));
            ArrayList<Node> adj = new ArrayList<>();
            if(top != null && top.getCoord().getKey() <= maze.length && top.getCoord().getValue() <= maze[0].length){
                adj.add(top);
            }
            if(bottom != null && bottom.getCoord().getKey() < maze.length && bottom.getCoord().getValue() < maze[0].length){
                adj.add(bottom);
            }
            if(left != null && left.getCoord().getKey() < maze.length && left.getCoord().getValue() < maze[0].length){
                adj.add(left);
            }
            if(right != null && right.getCoord().getKey() < maze.length && right.getCoord().getValue() < maze[0].length){
                adj.add(right);
            }
            for(Node node : adj){
                if(node.getCoord().getKey() >= 0 && node.getCoord().getValue() >= 0 && maze[node.getCoord().getKey()][node.getCoord().getValue()] != 1 && !node.getVisited()){
                    node.visit();
                    if(node.getCoord().equals(destination)){
                        System.out.print("Found destination: [" + destination.getKey() + "," + destination.getValue() + "]");
                        mazeSolved[node.getCoord().getKey()][node.getCoord().getValue()] = 3;
                        return mazeSolved;
                    }else{
                        mazeSolved[node.getCoord().getKey()][node.getCoord().getValue()] = 2;
                        stack.addFirst(node);
                    }
                }
            }
        }
        return mazeSolved;
    }

    public static int[][] dijkstra(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){
        if(origin.getKey() < 0 || origin.getValue() < 0 || origin.getKey() > maze.length - 1 || origin.getValue() > maze.length - 1 || maze[origin.getKey()][origin.getValue()] != 0){
            throw new IllegalArgumentException("ERROR: Origin is outside of maze limits.");
        }
        if(destination.getKey() < 0 || destination.getValue() < 0 || destination.getKey() > maze.length - 1 || destination.getValue() > maze.length - 1 || maze[destination.getKey()][destination.getValue()] != 0){
            throw new IllegalArgumentException("ERROR: Destination is outside of maze limits.");
        }
        System.out.println(" via Dijkstra's...");
        int[][] mazeSolved = maze.clone();
        PriorityQueue<Node> q = new PriorityQueue<>(new NodeComparator());
        HashMap<Pair<Integer, Integer>, Node> nodes = new HashMap<>();
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                Node cur = new Node(new Pair<>(i, j), Integer.MAX_VALUE, null);
                nodes.put(cur.getCoord(), cur);
            }
        }
        mazeSolved[origin.getKey()][origin.getValue()] = 3;
        nodes.get(new Pair<>(origin.getKey(), origin.getValue())).setDist(0);
        q.add(nodes.get(new Pair<>(origin.getKey(), origin.getValue())));
        System.out.println("Starting at origin: [" + origin.getKey() + "," + origin.getValue() + "]...");
        while(!q.isEmpty()){
            Node cur = q.poll();
            Node top = nodes.get(new Pair<>(cur.getCoord().getKey(), cur.getCoord().getValue() - 1));
            Node bottom = nodes.get(new Pair<>(cur.getCoord().getKey(), cur.getCoord().getValue() + 1));
            Node left = nodes.get(new Pair<>(cur.getCoord().getKey() - 1, cur.getCoord().getValue()));
            Node right = nodes.get(new Pair<>(cur.getCoord().getKey() + 1, cur.getCoord().getValue()));
            ArrayList<Node> adj = new ArrayList<>();
            if(top != null && top.getCoord().getKey() <= maze.length && top.getCoord().getValue() <= maze[0].length){
                adj.add(top);
            }
            if(bottom != null && bottom.getCoord().getKey() < maze.length && bottom.getCoord().getValue() < maze[0].length){
                adj.add(bottom);
            }
            if(left != null && left.getCoord().getKey() < maze.length && left.getCoord().getValue() < maze[0].length){
                adj.add(left);
            }
            if(right != null && right.getCoord().getKey() < maze.length && right.getCoord().getValue() < maze[0].length){
                adj.add(right);
            }
            for(Node node : adj){
                if(node.getCoord().getKey() >= 0 && node.getCoord().getValue() >= 0 && maze[node.getCoord().getKey()][node.getCoord().getValue()] != 1){
                    int altDist = cur.getDist() + 1;
                    if(altDist < node.getDist()){
                        node.setDist(altDist);
                        node.setPrev(cur);
                        if(!q.contains(node)){
                            q.add(node);
                        }
                    }
                }
            }
        }
        Node cur = nodes.get(new Pair<>(destination.getKey(), destination.getValue()));
        while(cur != null){
            if(!cur.getCoord().equals(new Pair<>(origin.getKey(), origin.getValue())) && !cur.getCoord().equals(new Pair<>(destination.getKey(), destination.getValue()))){
                mazeSolved[cur.getCoord().getKey()][cur.getCoord().getValue()] = 2;
            }else{
                mazeSolved[cur.getCoord().getKey()][cur.getCoord().getValue()] = 3;
            }
            cur = cur.getPrev();
        }
        System.out.print("Found shortest path to [" + destination.getKey() + "," + destination.getValue() + "]");
        return mazeSolved;
    }

    /*public static int[][] greedy(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){

    }

    public static int[][] aStar(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){

    }*/

    private static class Node {

        private Pair<Integer, Integer> coord;

        private int dist;

        private Node prev;

        private boolean visited;

        private Node(Pair<Integer, Integer> coord, int dist, Node prev){
            this.coord = coord;
            this.dist = dist;
            this.prev = prev;
            visited = false;
        }

        private Pair<Integer, Integer> getCoord(){
            return coord;
        }

        private int getDist(){
            return dist;
        }

        private Node getPrev(){
            return prev;
        }

        private boolean getVisited(){
            return visited;
        }

        private void setDist(int dist){
            this.dist = dist;
        }

        private void setPrev(Node prev){
            this.prev = prev;
        }

        private void visit(){
            visited = true;
        }

    }

    private static class NodeComparator implements Comparator<Node>{

        public int compare(Node n1, Node n2){
            if(n1.getDist() < n2.getDist()){
                return -1;
            }else if(n1.getDist() > n2.getDist()){
                return 1;
            }else{
                return 0;
            }
        }

    }

}
