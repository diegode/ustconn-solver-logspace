ustconn-solver-logspace
=======================

In 2005, Omer Reingold proved that L = SL by showing that the SL−Complete problem Undirected−S−T−Connectivity is in fact in L (Omer Reingold, Undirected ST-Connectivity in Log-Space, STOC 2005). We have implemented the mentioned algorithm.

The program gets as an input either an adjacencies matrix of a graph, or alternately, it lets the user draw a graph. In addition, two vertices are selected (denote s, t). The algorithm will then decide whether there is a path between s and t, and if so, it will display this path. All this is being done by using only logarithmic space.

Practically speaking, although the algorithm is in L, implying that the algorithm runs in polynomial time, it turns out, after a careful analysis, that the polynomial which represents the runtime is very large, rendering the algorithm practically intractable: we’ve managed to find a very loose lower bound of Ω(nd∗109) where d ≥ 4 (there is no time complexity analysis in the paper, and the exact analysis is very tedious, and depends on the specific base expander that is being used). Thus our program does not actually halt in reasonable time.
