package vo;

import java.io.Serializable;

public class Order implements Serializable {
	
	private int id;
	private Book b;
	private int student;
	private boolean completed = false;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public Book getBook(){
		return b;
	}
	
	public void setBook(Book b){
		this.b = b;
	}
	
	public int getStudent(){
		return student;
	}
	
	public void setStudent(int student){
		this.student = student;
	}
	
	public boolean isCompleted(){
		return completed;
	}
	
	public void setCompleted(boolean b){
		this.completed = b;
	}
	
	public String toString(){
		return "("+id + ", " + b.toString() +", "+student+", "+completed+")";
	}

}
