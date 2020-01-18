package ms;

import ms.SolvingAlgorithms;
import javafx.util.Pair;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.Buffer;
import javax.imageio.*;
import javax.swing.*;
import static java.awt.Color.*;
import java.lang.IllegalArgumentException;

public class Main {

    public static void main(String[] args) throws IOException, IllegalArgumentException{

        if(args.length != 6){
            throw new IllegalArgumentException("ERROR: To few program arguments.");
        }
        int oX, oY, dX, dY;
        try{
            oX = Integer.parseInt(args[2]);
            oY = Integer.parseInt(args[3]);
            dX = Integer.parseInt(args[4]);
            dY = Integer.parseInt(args[5]);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("ERROR: Invalid coordinate arguments: " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] + ".");
        }
        Pair<Integer, Integer> origin = new Pair<Integer, Integer>(oX, oY);
        Pair<Integer, Integer> destination = new Pair<Integer, Integer>(dX, dY);

        BufferedImage img;
        System.out.println("Loading image...");
        try {
            img = ImageIO.read(new File(args[0]));
        }catch(IOException e) {
            throw new IllegalArgumentException("ERROR: Invalid maze file: " + args[0] + ".");
        }

        int[][] maze = new int[img.getWidth()][img.getHeight()];
        for(int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                if (img.getRGB(i, j) == BLACK.getRGB()) {
                    maze[i][j] = 0;
                } else if (img.getRGB(i, j) == WHITE.getRGB()) {
                    maze[i][j] = 1;
                } else {
                    throw new IllegalArgumentException("ERROR: Invalid maze file coloration.");
                }
            }
        }

        System.out.print("Solving maze");
        int[][] mazeSolved;
        long startTime = System.nanoTime();
        switch(args[1]){
            case "BFS":
               mazeSolved = SolvingAlgorithms.BFS(maze, origin, destination);
               createSolvedImage(mazeSolved, args[0].substring(0, args[0].lastIndexOf('.')) + "_solved.bmp");
               break;
            case "DFS":
                mazeSolved = SolvingAlgorithms.DFS(maze, origin, destination);
                createSolvedImage(mazeSolved, args[0].substring(0, args[0].lastIndexOf('.')) + "_solved.bmp");
                break;
            case "Dijkstra":
                mazeSolved = SolvingAlgorithms.dijkstra(maze, origin, destination);
                createSolvedImage(mazeSolved, args[0].substring(0, args[0].lastIndexOf('.')) + "_solved.bmp");
                break;
            /*case "Greedy":
                mazeSolved = greedy(maze, origin, destination);
                createSolvedImage(mazeSolved, args[0].substring(0, args[0].lastIndexOf('.')) + "_solved.bmp");
                break;
            case "A*":
                mazeSolved = aStar(maze, origin, destination);
                createSolvedImage(mazeSolved, args[0].substring(0, args[0].lastIndexOf('.')) + "_solved.bmp");
                break;*/
            default:
                throw new IllegalArgumentException("ERROR: Invalid solving algorithm: " + args[1] + ".");
        }
        System.out.println(" in " + (double)(System.nanoTime() - startTime) / 1000000000 + " sec.");

    }

    private static void createSolvedImage(int[][] maze, String filename) throws IOException{
        BufferedImage imgSolved = new BufferedImage(maze.length, maze[0].length, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                if(maze[i][j] == 0){
                    imgSolved.setRGB(i, j, Color.BLACK.getRGB());
                }else if(maze[i][j] == 1){
                    imgSolved.setRGB(i, j, Color.WHITE.getRGB());
                }else if(maze[i][j] == 2){
                    imgSolved.setRGB(i, j, Color.RED.getRGB());
                }else{
                    imgSolved.setRGB(i, j, Color.BLUE.getRGB());
                }
            }
        }
        File solvedImageFile = new File(filename);
        ImageIO.write(imgSolved, "bmp", solvedImageFile);
        //Runtime.getRuntime().exec("python viewImage.py " + filename);
    }

}
