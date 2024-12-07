package controller;

import java.util.Vector;

import Model.MLecture;
import valueObject.VLecture;

public class CLecture {
	private MLecture mLecture;
	
	public CLecture() {
		this.mLecture = new MLecture();
	}

	public Vector<VLecture> getLectureVector(String fileName) {
		Vector<VLecture> vLectureVector = mLecture.getLectureVector(fileName);
		return vLectureVector;
	}

}
