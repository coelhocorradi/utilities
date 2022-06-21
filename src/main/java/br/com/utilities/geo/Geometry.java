package br.com.utilities.geo;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.utilities.exceptions.PathIteratorException;

public abstract class Geometry {

	public static boolean isVertical(Line2D line) {
		return (line.getP1().getX() - line.getP2().getX()) == 0;
	}

	/**
	 * coeficiente angular da reta, ou segmento de reta
	 * 
	 * @param line
	 * @return
	 */
	public static double slope(Line2D line) {
		return !isVertical(line)
				? ((line.getP2().getY() - line.getP1().getY()) / (line.getP2().getX() - line.getP2().getX()))
				: java.lang.Double.NaN;
	}

	/**
	 * interseção da reta que passa pelo seguimento de reta com o eixo y
	 * 
	 * @param line
	 * @return
	 */
	public static double yIntercept(Line2D line) {
		return !isVertical(line) ? line.getP1().getY() - slope(line) * line.getP1().getX() : java.lang.Double.NaN;
	}

	public static boolean intersect(Line2D l1, Line2D l2) {
		return l1.intersectsLine(l2);
	}
	
	public static boolean intersect(Polygon p1, Polygon p2) {
		return p1.intersec(p2);
	}

	public static boolean instersect(Path2D p1, Path2D p2) {
		Shape shape1 = p1.createTransformedShape(null), shape2 = p2.createTransformedShape(null);
		Area a1 = new Area(shape1), a2 = new Area(shape2);
		a1.intersect(a2);
		return a1.isEmpty();
	}

	public static Area instersectionArea(Path2D p1, Path2D p2) {
		Shape shape1 = p1.createTransformedShape(null), shape2 = p2.createTransformedShape(null);
		Area a1 = new Area(shape1), a2 = new Area(shape2);
		a1.intersect(a2);
		return a1;
	}

	public static Path2D intersectionPath(Path2D p1, Path2D p2) {
		Path2D path = null;
		Area a = instersectionArea(p1, p2);
		if (!a.isEmpty()) {
			path = new Path2D.Double();
			path.append(a.getPathIterator(null), true);
		}
		return path;
	}

	public static Path2D createRegion(double Latitude, double Longitude, double distanceKM) {

		// referencia:
		// https://stackoverflow.com/questions/7477003/calculating-new-longitude-latitude-from-old-n-meters

		// number of km per degree = ~111km (111.32 in google maps, but range varies
		// between 110.567km at the equator and 111.699km at the poles)
		// 1km in degree = 1 / 111.32km = 0.0089
		// 1m in degree = 0.0089 / 1000 = 0.0000089

		double coeficiente = distanceKM * 1000 * 0.0000089;

		double latInicio = Latitude + coeficiente;
		double latTermino = Latitude - coeficiente;
		double longInicio = Longitude + coeficiente / Math.cos(Latitude * 0.018);
		double longTermino = Longitude - coeficiente / Math.cos(Latitude * 0.018);

		List<Point2D> points = new ArrayList<Point2D>();
		points.add(new Point2D.Double(latInicio, longInicio));
		points.add(new Point2D.Double(latTermino, longInicio));
		points.add(new Point2D.Double(latTermino, longTermino));
		points.add(new Point2D.Double(latInicio, longTermino));

		Path2D path = new Path2D.Double();
		Iterator<Point2D> it = points.iterator();
		Point2D p = it.next();
		path.moveTo(p.getX(), p.getY());
		while (it.hasNext()) {
			path.lineTo(p.getX(), p.getY());
		}
		path.closePath();

		return path;
	}

	public static List<Point2D> getIntersections(Polygon poly, Line2D.Double line) throws PathIteratorException {

		PathIterator polyIt = poly.getPathIterator(null); // Getting an iterator along the polygon path
		double[] coords = new double[6]; // Double array with length 6 needed by iterator
		double[] firstCoords = new double[2]; // First point (needed for closing polygon path)
		double[] lastCoords = new double[2]; // Previously visited point
		List<Point2D> intersections = new ArrayList<Point2D>();// List to hold found intersections
		polyIt.currentSegment(firstCoords); // Getting the first coordinate pair
		lastCoords[0] = firstCoords[0]; // Priming the previous coordinate pair
		lastCoords[1] = firstCoords[1];
		polyIt.next();
		while (!polyIt.isDone()) {
			int type = polyIt.currentSegment(coords);
			switch (type) {
			case PathIterator.SEG_LINETO: {
				Line2D.Double currentLine = new Line2D.Double(lastCoords[0], lastCoords[1], coords[0], coords[1]);
				if (currentLine.intersectsLine(line))
					intersections.add(getIntersection(currentLine, line));
				lastCoords[0] = coords[0];
				lastCoords[1] = coords[1];
				break;
			}
			case PathIterator.SEG_CLOSE: {
				Line2D.Double currentLine = new Line2D.Double(coords[0], coords[1], firstCoords[0], firstCoords[1]);
				if (currentLine.intersectsLine(line))
					intersections.add(getIntersection(currentLine, line));
				break;
			}
			default: {
				throw new PathIteratorException("Unsupported PathIterator segment type.");
			}
			}
			polyIt.next();
		}
		return intersections;
	}

	public static Point2D getIntersection(Line2D.Double line1, Line2D.Double line2) {
		double	x1 = line1.x1, y1 = line1.y1, x2 = line1.x2, y2 = line1.y2, //
				x3 = line2.x1, y3 = line2.y1, x4 = line2.x2, y4 = line2.y2, //
				// Px calculado pela formula matricial
				Px = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4))
						/ ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4)), //
				// Py calculado pela formula matricial
				Py = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4))
						/ ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));//
		return new Point2D.Double(Px, Py);
	}
}
