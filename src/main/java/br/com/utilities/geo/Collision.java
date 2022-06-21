package br.com.utilities.geo;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public abstract class Collision {

	/**
	 * colição entre retangulos
	 * @param r1
	 * @param r2
	 * @return
	 */
	public static boolean isColliding(Rectangle2D r1, Rectangle2D r2) {
		return r1.intersects(r2);
	}

	/**
	 * serve para colisões de diversos tipos de polígonos
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isColliding(Shape s1, Shape s2) {
		Area area1 = new Area(s1);
		Area area2 = new Area(s2);
		area1.intersect(area2);
		return !area1.isEmpty();
	}
	
	/**
	 * transforma um path2d em polígono e verificar se existe interseção ente eles
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static boolean isColligind(Path2D p1, Path2D p2) {
		Area area1 = new Area(p1.createTransformedShape(AffineTransform.getScaleInstance(1,  -1)));
		Area area2 = new Area(p2.createTransformedShape(AffineTransform.getScaleInstance(1,  -1)));
		area1.intersect(area2);
		return !area1.isEmpty();
	}
}
