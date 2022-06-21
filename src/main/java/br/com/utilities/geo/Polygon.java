/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.utilities.geo;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends Path2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5482123392347557377L;

	private List<Point> points;

	private List<Line> lines;

	private String descricao;

	public Polygon() {
		super();
		setWindingRule(WIND_EVEN_ODD);
		this.points = null;
		// a principio não necessita traçar as linhas, mas caso seja necessário será
		// traçado uma única vez com o getBounduary()
		this.lines = null;
		if (validate())
			create();
	}

	public Polygon(List<Point> points) {
		super();
		this.points = points;
		if (validate())
			create();
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public List<Point> getPoints() {
		return points;
	}

	private boolean validate() {
		return points != null && points.size() > 2;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private void create() {
		boolean append = false;
		for (Point p : points) {
			if (append == false) {
				moveTo(p.x, p.y);
				append = true;
			} else {
				lineTo(p.x, p.y);
			}
		}
		closePath();
	}

	public boolean validateAndCreate() {
		boolean result = validate();
		if (result)
			create();
		return result;
	}

	public boolean intersec(Polygon polygon) {
		boolean result = false;
		for (Point p : polygon.getPoints()) {
			result |= super.contains(p);
			if (result)
				break;
		}
		return result;
	}

	public List<Line> getBounduary() {
		if (lines == null) {
			lines = new ArrayList<Line>();
			Point p1 = null;
			Point p2 = null;
			for (int index = 0; index < points.size(); index++) {
				if (index == 0) {
					// apartir do primeiro ponto
					p1 = points.get(index);
					p2 = null;
				} else if (index == points.size() - 1) {
					// chegando ao ultimo ponto e fechando com o primeiro
					p1 = points.get(index);
					p2 = points.get(0);
				} else {
					// sempre assim, primeiro com o segundo, segundo com o terceito... ultimo com o
					// primeiro
					p2 = points.get(index);
					Line l = new Line(p1, p2);
					lines.add(l);
					p1 = p2;
				}
			}
		}
		return lines;
	}

	public boolean intersect(Path2D p) {
		return Geometry.instersect(this, p);
	}

	public Path2D intersectionPath(Path2D p) {
		return Geometry.intersectionPath(this, p);
	}
}
