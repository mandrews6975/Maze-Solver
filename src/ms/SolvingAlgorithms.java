package ms;

import javafx.util.Pair;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.IllegalArgumentException;

public class SolvingAlgorithms {

    @SuppressWarnings("unchecked")
    public static int[][] BFS(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){
        if(origin.getKey() < 0 || origin.getValue() < 0 || origin.getKey() > maze.length - 1 || origin.getValue() > maze.length - 1 || maze[origin.getKey()][origin.getValue()] != 0){
            throw new IllegalArgumentException("ERROR: Origin is outside of maze limits.");
        }
        if(destination.getKey() < 0 || destination.getValue() < 0 || destination.getKey() > maze.length - 1 || destination.getValue() > maze.length - 1 || maze[destination.getKey()][destination.getValue()] != 0){
            throw new IllegalArgumentException("ERROR: Destination is outside of maze limits.");
        }
        int[][] mazeSolved = maze.clone();
        ArrayDeque<Pair<Integer, Integer>> q = new ArrayDeque<Pair<Integer, Integer>>();
        HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>> visited = new HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>>();
        mazeSolved[origin.getKey()][origin.getValue()] = 3;
        q.add(origin);
        System.out.println("Origin: " + q.peekFirst().getKey() + "," + q.peekFirst().getValue());
        visited.put(origin, origin);
        while(!q.isEmpty()){
            Pair<Integer, Integer> cur = q.pop();
            Pair<Integer, Integer> top = new Pair<Integer, Integer>(cur.getKey(), cur.getValue() - 1);
            Pair<Integer, Integer> bottom = new Pair<Integer, Integer>(cur.getKey(), cur.getValue() + 1);
            Pair<Integer, Integer> left = new Pair<Integer, Integer>(cur.getKey() - 1, cur.getValue());
            Pair<Integer, Integer> right = new Pair<Integer, Integer>(cur.getKey() + 1, cur.getValue());
            ArrayList<Pair<Integer, Integer>> adj = new ArrayList<Pair<Integer, Integer>>();
            if(top.getKey() <= maze.length && top.getValue() <= maze[0].length){
                adj.add(top);
            }
            if(bottom.getKey() < maze.length && bottom.getValue() < maze[0].length){
                adj.add(bottom);
            }
            if(left.getKey() < maze.length && left.getValue() < maze[0].length){
                adj.add(left);
            }
            if(right.getKey() < maze.length && right.getValue() < maze[0].length){
                adj.add(right);
            }
            for(Pair<Integer, Integer> coord : adj){
                if(coord.getKey() >= 0 && coord.getValue() >= 0 && maze[coord.getKey()][coord.getValue()] != 1 && !visited.containsValue(coord)){
                    visited.put(coord, coord);
                    if(coord.equals(destination)){
                        System.out.print("Found destination: " + coord.getKey() + "," + coord.getValue());
                        mazeSolved[coord.getKey()][coord.getValue()] = 3;
                        return mazeSolved;
                    }else{
                        mazeSolved[coord.getKey()][coord.getValue()] = 2;
                        q.push(coord);
                    }
                }
            }
        }
        return mazeSolved;
    }

    /*public int[][] DFS(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){

    }

    public int[][] dijkstra(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){

    }

    public int[][] greedy(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){

    }

    public int[][] aStar(int[][] maze, Pair<Integer, Integer> origin, Pair<Integer, Integer> destination){

    }*/

}
