package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VLogin;
import valueObject.VUserInfo;

public class MAccount {

	public VUserInfo login(VLogin vLogin){
		VUserInfo vUserInfo = null;		
		
		try {
			Scanner scanner = new Scanner(new File("data/Account.txt"));
			while (scanner.hasNext()) {  
				String line = scanner.nextLine();
				String[] tokens = line.split(" ");			
				if (tokens[0].equals(vLogin.getUserId()) ) {
					if (tokens[1].equals(vLogin.getPassword()) ) {
						vUserInfo = new VUserInfo();
						vUserInfo.setName(tokens[2]);	
						break;
					}
				}
			}
			scanner.close();	
			
		
		
	
	
	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return vUserInfo;
	}
}
