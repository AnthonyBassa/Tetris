package com.tetris.bo;

import java.util.ArrayList;
import java.util.List;

public class PiecePoints {
	private List<Point> Points;
	
	public PiecePoints(Point  firstPoint, Point secondPoint, Point thirdPoint, Point FourthPoint) {
		Points = new ArrayList<Point>();
		Points.add(firstPoint);
		Points.add(secondPoint);
		Points.add(thirdPoint);
		Points.add(FourthPoint);
	}

	public List<Point> getPoints() {
		return Points;
	}

	

}
