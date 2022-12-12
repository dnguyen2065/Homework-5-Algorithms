import java.util.Scanner;

public class Dijkstras {
    public int[] askForInput() {
        Scanner input = new Scanner(System.in);
        boolean isWrong = false;
        int start = 0;
        while (!isWrong) {
            System.out.print("Please enter a value 0-9 to select a starting node: ");
            start = input.nextInt();
            System.out.println("");
            if (start >= 0 && start <= 9) {
                isWrong = true;
            }
        }
        int end = 0;
        isWrong = false;
        while (!isWrong) {
            System.out.print("Please enter a value 0-9 to select an ending node: ");
            end = input.nextInt();
            System.out.println("");
            if (end >= 0 && end <= 9) {
                isWrong = true;
            }
        }

        int[] startEnd = new int[2];
        startEnd[0] = start;
        startEnd[1] = end;
        return startEnd;
    }

    public int[][] shortestPath(int[][] grid, int start, int end) {
        int[][] path = new int[2][10];
        int[] distance = new int[10];
        int[] visited = new int[10];
        int[] previous = new int[10];
        for (int i = 0; i < 10; i++) {
            distance[i] = 1000;
            visited[i] = 0;
            previous[i] = -1;
        }
        distance[start] = 0;
        int current = start;
        while (visited[end] == 0) {
            for (int i = 0; i < 10; i++) {
                if (grid[current][i] != 0 && visited[i] == 0) {
                    if (distance[i] > distance[current] + grid[current][i]) {
                        distance[i] = distance[current] + grid[current][i];
                        previous[i] = current;
                    }
                }
            }
            visited[current] = 1;
            int min = 1000;
            for (int i = 0; i < 10; i++) {
                if (visited[i] == 0 && distance[i] < min) {
                    min = distance[i];
                    current = i;
                }
            }
        }
        int[] pathArray = new int[10];
        int pathLength = 0;
        int temp = end;
        while (temp != -1) {
            pathArray[pathLength] = temp;
            pathLength++;
            temp = previous[temp];
        }
        for (int i = 0; i < pathLength; i++) {
            path[0][i] = pathArray[pathLength - i - 1];
        }
        path[1][0] = distance[end];
        return path;

    }

    public void printPath(int[][] path) {
        System.out.println("The shortest path is: ");
        System.out.println("Starting at node: " + path[0][0]);
        for (int i = 0; i < 10; i++) {
            if (path[0][i] != 0) {
                System.out.println("Next node: " + path[0][i] + " ");
            }
        }
        System.out.println("Arrived!");
        System.out.println("The shortest distance is: " + path[1][0]);
    }
}
