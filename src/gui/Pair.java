package gui;


public class Pair<T, S>{
	
  public Pair(T f, S s){ 
    first = f;
    second = s;   
  }
 
  public T getFirst(){
    return first;
  }
 
  public S getSecond()   {
    return second;
  }
 
  public String toString()  { 
    return "(" + first.toString() + ", " + second.toString() + ")"; 
  }
  
  public void print(){
	  System.out.println(first+" "+second);
  }
 
  private final T first;
  private final S second;
}
