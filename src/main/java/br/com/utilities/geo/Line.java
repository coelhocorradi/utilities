package br.com.utilities.geo;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line extends Line2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2035417144027155771L;

	private double slope = java.lang.Double.NaN;
	private double intercect = java.lang.Double.NaN;
	private boolean vertical = false;

	public Line(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
		slope = Geometry.slope(this);
		intercect = Geometry.yIntercept(this);
		vertical = Geometry.isVertical(this);
	}

	public Line(Point2D.Double start, Point2D.Double end) {
		super(start, end);
	}

	public double getSlope() {
		return slope;
	}

	public double getIntercect() {
		return intercect;
	}

	public boolean isVertical() {
		return vertical;
	}
}
