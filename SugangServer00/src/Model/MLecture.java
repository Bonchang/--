package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VIndex;
import valueObject.VLecture;

public class MLecture {
	
	public Vector<VLecture> getLectureVector(String FileName) {
		Vector<VLecture> vLectureVector = null;
		try {
			//campus index file
			vLectureVector = new Vector<VLecture>();
			File file = new File("data/" + FileName + ".txt");
			Scanner parentFile = new Scanner(file);
			while (parentFile.hasNext()) {
				VLecture vLecture = new VLecture();
				vLecture.load(parentFile);
				vLectureVector.add(vLecture);
			}
			parentFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return vLectureVector;
	}	
}