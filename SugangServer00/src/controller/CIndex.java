package controller;

import java.util.Vector;

import Model.MIndex;
import valueObject.VIndex;

public class CIndex {
	private MIndex mIndex;
	
	public CIndex() {
		this.mIndex = new MIndex();
	}
	public Vector<VIndex> getIndexVector(String parentFileName) {
	Vector<VIndex> vIndexVector = mIndex.getIndexVector(parentFileName);
	return vIndexVector;
	}
}
