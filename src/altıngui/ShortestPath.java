package altıngui;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class ShortestPath {

     int ar[] = new int[3];
    public int[] pathExists(char[][] matrix,int baslaX,int baslaY) {

        Node source = new Node(baslaX, baslaY, 0);
        Queue<Node> queue = new LinkedList<Node>();

        queue.add(source);

        while (!queue.isEmpty()) {
            Node poped = queue.poll();

            if (matrix[poped.x][poped.y] == 'X') {
                ar[0]=poped.distanceFromSource;
                ar[1]=poped.x;
                ar[2]=poped.y;
                //System.out.println(ar[0]);
                return ar;
           
            } else {
                matrix[poped.x][poped.y] = '0';

                List<Node> neighbourList = addNeighbours(poped, matrix);
                queue.addAll(neighbourList);
                 /*for(int i=0; i<neighbourList.size(); i++) {
                         System.out.print(neighbourList.get(i));
        }*/

            }

        }
        
        return ar;
    }

    private static List<Node> addNeighbours(Node poped, char[][] matrix) {

        List<Node> list = new LinkedList<>();

        if ((poped.x - 1 > 0 && poped.x - 1 < matrix.length) && matrix[poped.x - 1][poped.y] != '0') {
            list.add(new Node(poped.x - 1, poped.y, poped.distanceFromSource + 1));
        }
        if ((poped.x + 1 > 0 && poped.x + 1 < matrix.length) && matrix[poped.x + 1][poped.y] != '0') {
            list.add(new Node(poped.x + 1, poped.y, poped.distanceFromSource + 1));
        }
        if ((poped.y - 1 > 0 && poped.y - 1 < matrix.length) && matrix[poped.x][poped.y - 1] != '0') {
            list.add(new Node(poped.x, poped.y - 1, poped.distanceFromSource + 1));
        }
        if ((poped.y + 1 > 0 && poped.y + 1 < matrix.length) && matrix[poped.x][poped.y + 1] != '0') {
            list.add(new Node(poped.x, poped.y + 1, poped.distanceFromSource + 1));
        }
       
        return list;
    }

}
////https://www.careercup.com/question?id=5725353829990400
class Node {

    int x;
    int y;
    int distanceFromSource;
    Node parent;

    Node(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.distanceFromSource = dis;
    }
}
/* public static void main(String args[])
	    {
	        char[][] matrix = {
	            {'S', '0', '1', '1'},
	            {'1', '1', '0', '1'},
	            {'0', '1', '1', '1'},
	            {'1', '0', 'D', '1'}
	        };
	        
	        int path = pathExists(matrix);
	        
	       System.out.println(path);
	    }*/
   /* private static int printPath(Node node) {
		if (node == null) {
			return 0;
		}
		int len = printPath(node.parent);
		System.out.print(node + " ");
		return len + 1;
	}*/