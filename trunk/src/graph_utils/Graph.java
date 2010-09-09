package graph_utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import Jama.Matrix;

import misc.Label;

public class Graph {
	
	private double[][] adj_matrix;
	protected int regularityDegree;
	
	// Constructors
	public Graph(double[][] adj_matrix, int regularityDegree) {
		this.adj_matrix = adj_matrix; 
		this.regularityDegree = regularityDegree;
	}
	
	public Graph(int N) {
		this(N, 0); 
	}
	
	public Graph(int N, int regularityDegree) {
		this(new double[N][N], regularityDegree);
	}
	
	public Graph(double[][] adj_matrix) {
		this(adj_matrix, 0); 
	}
	
	public Graph(File file, int regularityDegree) {
		this.regularityDegree = regularityDegree;
		String line;
		int k=0;
		String[] buffer;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		 
			while((line = reader.readLine()) != null) {
				buffer = line.split(" ");
				if (adj_matrix == null)
					adj_matrix = new double[buffer.length][buffer.length];
				for (int i=0; i < buffer.length; i++)
					adj_matrix[k][i] = Double.parseDouble(buffer[i]);
				k++;
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Graph(File file) {
		this(file, 0);
	}
	
	
	// Getters & Setters
	public double[][] getAdjacenciesMatrix() {
		return adj_matrix;
	}
	
	public double get(int i, int j) {
		return adj_matrix[i][j];
	}
	
	public void set(int i, int j, double val) {
		adj_matrix[i][j] = val;
	}
	
	public void inc(int i, int j) {
		adj_matrix[i][j]++;
	}
	
	public int regularityDegree() {
		return regularityDegree;
	}
	
	public void setDegree(int regularityDegree) {
		this.regularityDegree = regularityDegree;
	}
	
	public long dim() {
		return adj_matrix.length;
	}
	
	public String toString() {
		String s="";
		for(int i=0; i<dim(); i++){
			for(int j=0; j<dim(); j++)
				s = s + adj_matrix[i][j] + "\t";
			s = s + "\n";
		}
		return s;
	}
	
	
	// Methods
	/** Calculates the spectral gap
	*/
	public double calculateSpectralGap() {
		Matrix A = new Matrix(adj_matrix);
		double[] eigenvalues = A.eig().getRealEigenvalues();
		for (int i=0; i<eigenvalues.length; i++)
			eigenvalues[i] = Math.abs(eigenvalues[i]);
		Arrays.sort(eigenvalues);
		return (eigenvalues[eigenvalues.length-1] - eigenvalues[eigenvalues.length-2]);
	}
	

	/** Rot(v,i) = (w,j)
	*	Space Complexity:	the recursion is to the depth of m
	*						thus we only need to store m counters of log-space
	*/
	public Label rot(int v, int i) {
	int w,j=0;
	int k=0;
	
	// Find the destination vetrex
	while (i>=0) {
		if (adj_matrix[v][k] != 0)
			i--;
		k++;
	}
	w = k-1;
	
	// Find the return edge
	for (int c=0; c<v; c++)
		if (adj_matrix[w][c] != 0)
			j++;
	return new Label(w,j);
	}
	
}
 