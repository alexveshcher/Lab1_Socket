package vo;

import java.io.Serializable;

public class Book implements Serializable {
	private int id;
	private String authors = "";
	private String title = "";
	private int year;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public int getYear(){
		return year;
	}
	
	public void setYear(int year){
		this.year = year;
	}

	public String toString(){
		return "("+id + ", " + authors +", "+title+", "+year+")";
	}
	
	public String[] toStringArray(){
		String res[]= {authors, title, Integer.toString(year)};
		return res;
	}
}
