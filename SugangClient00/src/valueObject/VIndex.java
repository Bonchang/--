package valueObject;

import java.util.Scanner;

public class VIndex {
	
		private int code;
		private String name;
		private String filename;

		
		public int getCode() {return code;}
		public void setCode(int code) {this.code = code;}
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
		public String getFileName() {return filename;}
		public void setFileName(String filename) {this.filename = filename;}

		
		public void load(Scanner parentFile) {
		    
				this.setCode(parentFile.nextInt());
				this.setName(parentFile.next());		    		    
		        this.setFileName(parentFile.next());

		}
		public void show() {
			System.out.println (this.getCode () + " " + this.getName () ) ;			
		}
}
		

