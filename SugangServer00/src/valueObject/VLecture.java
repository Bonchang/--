package valueObject;

import java.util.Scanner;
import java.util.Vector;

public class VLecture {
	private int code;
	private String title;
	private String lecturer;
	private int credit;
	private String time;
	
	
		
	public int getCode() {return code;}
	public void setCode(int code) {this.code = code;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getLecturer() {return lecturer;}
	public void setLecturer(String lecturer) {this.lecturer = lecturer;}
	public int getCredit() {return credit;}
	public void setCredit(int credit) {this.credit = credit;}
	public String getTime() {return time;}
	public void setTime(String timer) {this.time = timer;}


	public void load(Scanner parentFile) {
		this.setCode(parentFile.nextInt());
		this.setTitle(parentFile.next());
		this.setLecturer(parentFile.next());
		this.setCredit(parentFile.nextInt());
		this.setTime(parentFile.next());


	}
	public void show() {
		System.out.println("");
			System.out.println(this.getCode()
					+ " " + this.getTitle()
					+ " " + this.getLecturer()
					+ " " + this.getCredit()
					+ " " + this.getTime()
					);
	}

}
