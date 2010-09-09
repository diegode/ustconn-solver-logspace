package gui;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.graphics.Point;


public class Matrix {
	
	private List<Point> vertexList = new LinkedList<Point>();
	private List<Pair<Point, Point>> edgeList = new LinkedList<Pair<Point, Point>>();
	private double[][] matrix;
	
	public void addVertex(Point p){
		vertexList.add(p);
	}
	
	public void addEdge(Point p1, Point p2){
		Pair<Point, Point> p = new Pair<Point, Point>(p1, p2);
		edgeList.add(p);
		p = new Pair<Point, Point>(p2, p1);
		edgeList.add(p);
	}
	
	public double[][] toArray(){
		int len = vertexList.size();
		int i,j;
		matrix = new double[len][len];
		for(Pair<Point, Point> p : edgeList){
			i = vertexList.indexOf(p.getFirst());
			j = vertexList.indexOf(p.getSecond());
			matrix[i][j]=1;
		}
		return matrix;
	}
	
	public void printV(){
		for(Point p : vertexList)
			System.out.println(p.x);
	}
	
	public void printEdge(){
		for(Pair<Point,Point> p: edgeList)
			p.print();
	}
	
}
