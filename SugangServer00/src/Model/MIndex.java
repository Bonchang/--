package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VIndex;

public class MIndex {
	
	public Vector<VIndex> getIndexVector(String parentFileName) {
		Vector<VIndex> vIndexVector = null;
		try {
			//campus index file
			vIndexVector = new Vector<VIndex>();
			File file = new File("data/" + parentFileName + ".txt");
			Scanner parentFile = new Scanner(file);
			while (parentFile.hasNext()) {
				VIndex vIndex = new VIndex();
				vIndex.load(parentFile);
				vIndexVector.add(vIndex);
			}
			parentFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return vIndexVector;
	}	
}