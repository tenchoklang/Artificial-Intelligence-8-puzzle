import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/* Name: Tenzin Choklang
 * Project: Artificial Intelligence search Algorithms
 * Date: 9/29/2017
 */
public class Main {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();		

		System.out.println("******************************");
		System.out.println("MISPLACED Algorithm");
		System.out.println("******************************");
		Algorithm misplaced = new Algorithm("astar misplaced");
		misplaced.init();
		
		System.out.println("******************************");
		System.out.println("MANHATTAN Algorithm");
		System.out.println("******************************");
		Algorithm manhattan = new Algorithm("astar manhattan");
		manhattan.init();
		
		
		System.out.println("******************************");
		System.out.println("IDA* Algorithm");
		System.out.println("******************************");
		Algorithm ida = new Algorithm("astar ida");
		ida.init();
		
		System.out.println("******************************");
		System.out.println("DFBnF Algorithm");
		System.out.println("******************************");
		Algorithm dfbnb = new Algorithm("dfbnb");
		dfbnb.init();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Total Time: "+totalTime);
	}

}
