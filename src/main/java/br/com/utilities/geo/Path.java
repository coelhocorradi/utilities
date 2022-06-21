package br.com.utilities.geo;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Path extends Path2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5482123392347557377L;

	private List<Point> points;

	private List<Line> lines;

	private List<Polygon> poligons;
	private Polygon paths;

	private boolean pathCreated = false;

	public Path() {
		super();
		this.points = null;
		// a principio n�o necessita tra�ar as linhas, mas caso seja necess�rio ser�
		// tra�ado uma �nica vez com o getBounduary()
		this.lines = null;
		if (validate())
			create();
	}

	public Path(List<Point> points) {
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
		boolean result = points != null && points.size() > 2;
		/*
		 * if (result) {
		 * System.out.println("Um poligono deve possuir pelo menos três pontos"); }
		 */
		return result;
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
		// como � caminho, n�o precisa ser fechado
		// closePath();
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

	public List<Polygon> compositeRegionOld(java.lang.Double d) {
		if (poligons == null) {
			poligons = new ArrayList<Polygon>();
			getBounduary();
		}
		if (poligons != null && !pathCreated) {

			pathCreated = true;

			boolean primeiraLinha = true;
			Point p1 = null, p2 = null, p3 = null, p4 = null;

			for (Line l : lines) {

				List<Point> points = null;
				Polygon polygon = null;
				// ===================================
				// ========== ponto inicial ==========
				if (primeiraLinha) {
					p1 = new Point(0, 0);
					p2 = new Point(0, 0);
					p3 = new Point(0, 0);
					p4 = new Point(0, 0);
					p1.x = l.x1 - d * Math.sqrt(2);
					p1.y = l.y1 - d * Math.sqrt(2);
					p2.x = l.x1 - d * Math.sqrt(2);
					p2.y = l.y1 + d * Math.sqrt(2);
					p3.x = l.x1 + d * Math.sqrt(2);
					p3.y = l.y1 + d * Math.sqrt(2);
					p4.x = l.x1 + d * Math.sqrt(2);
					p4.y = l.y1 - d * Math.sqrt(2);

					points = new ArrayList<Point>();
					points.add(p1);
					points.add(p2);
					points.add(p3);
					points.add(p4);
					polygon = new Polygon(points);
					polygon.setDescricao("quadrado");
					poligons.add(polygon);
					primeiraLinha = false;
				}
				// ===================================

				// =================================
				// ========== ponto final ==========
				p1 = new Point(0, 0);
				p2 = new Point(0, 0);
				p3 = new Point(0, 0);
				p4 = new Point(0, 0);
				p1.x = l.x2 - d * Math.sqrt(2);
				p1.y = l.y2 - d * Math.sqrt(2);
				p2.x = l.x2 - d * Math.sqrt(2);
				p2.y = l.y2 + d * Math.sqrt(2);
				p3.x = l.x2 + d * Math.sqrt(2);
				p3.y = l.y2 + d * Math.sqrt(2);
				p4.x = l.x2 + d * Math.sqrt(2);
				p4.y = l.y2 - d * Math.sqrt(2);

				points = new ArrayList<Point>();
				points.add(p1);
				points.add(p2);
				points.add(p3);
				points.add(p4);
				polygon = new Polygon(points);
				polygon.setDescricao("quadrado");
				poligons.add(polygon);
				// =================================

				// inclina��o do seguimento de reta
				java.lang.Double m, m2, angulo;
				if ((l.x2 - l.x1) == 0.0) {
					// reta � vertical
					m = java.lang.Double.POSITIVE_INFINITY;
					m2 = 0.0;
					angulo = Math.PI / 2;
				} else if ((l.y2 - l.y1) == 0.0) {
					// reta � horizontal
					m = 0.0;
					m2 = java.lang.Double.POSITIVE_INFINITY;
					angulo = 0.0;
				} else {
					m = (l.y2 - l.y1) / (l.x2 - l.x1);
					m2 = -1 / m;
					angulo = Math.atan(m2);
				}

				// p1 sempre ser� mais a esquerda e mais abaixo,
				// p2 sempre ser� mais a esquerda e mais acima,
				// p3 sempre ser� mais a direita e mais acima,
				// p4 sempre ser� mais a direita e mais abaixo
				p1 = new Point(0, 0);
				p2 = new Point(0, 0);
				p3 = new Point(0, 0);
				p4 = new Point(0, 0);
				String descricao = "";

				// seprem seguir a oriten��o horaria na regra da m�o direta para calcular o
				// interior do caminho
				// a coordenada x1 est� mais a esquerda que a coordenada x2
				if (m2 == 0) {
					// segmento de reta vertical, tangente infinito
					if (l.y1 > l.y2) {
						p1.x = l.x1 - d;
						p1.y = l.y1;
						p2.x = l.x1 + d;
						p2.y = l.y1;
						p3.x = l.x2 + d;
						p3.y = l.y2;
						p4.x = l.x2 - d;
						p4.y = l.y2;
					} else {
						p1.x = l.x1 - d;
						p1.y = l.y1;
						p2.x = l.x2 - d;
						p2.y = l.y2;
						p3.x = l.x2 + d;
						p3.y = l.y2;
						p4.x = l.x1 + d;
						p4.y = l.y1;
					}
					descricao = "retangulo vertical";
				} else if (m2 == java.lang.Double.POSITIVE_INFINITY) {
					// segmento horizontal, tangente zero
					if (l.x1 > l.x2) {
						p1.x = l.x1;
						p1.y = l.y1 - d;
						p2.x = l.x1;
						p2.y = l.y1 + d;
						p3.x = l.x2;
						p3.y = l.y2 + d;
						p4.x = l.x2;
						p4.y = l.y2 - d;
					} else {
						p1.x = l.x1;
						p1.y = l.y1 - d;
						p2.x = l.x2;
						p2.y = l.y2 - d;
						p3.x = l.x2;
						p3.y = l.y2 + d;
						p4.x = l.x1;
						p4.y = l.y1 + d;
					}
					descricao = "retangulo horizontal";
				} else {
					// coeficientes angulares diferente de zero e infinito
					m = (l.y2 - l.y1) / (l.x2 - l.x1);
					m2 = -1 / m;
					angulo = Math.atan(m2);
					if (l.x1 > l.x2) {
						p1.x = l.x1 + d * Math.cos(angulo);
						p1.y = l.y1 + d * Math.sin(angulo);
						p2.x = l.x2 + d * Math.cos(angulo);
						p2.y = l.y2 + d * Math.sin(angulo);
						p3.x = l.x2 - d * Math.cos(angulo);
						p3.y = l.y2 - d * Math.sin(angulo);
						p4.x = l.x1 - d * Math.cos(angulo);
						p4.y = l.y1 - d * Math.sin(angulo);
					} else if (l.x1 < l.x2) {
						p1.x = l.x1 + d * Math.cos(angulo);
						p1.y = l.y1 + d * Math.sin(angulo);
						p2.x = l.x1 - d * Math.cos(angulo);
						p2.y = l.y1 - d * Math.sin(angulo);
						p3.x = l.x2 - d * Math.cos(angulo);
						p3.y = l.y2 - d * Math.sin(angulo);
						p4.x = l.x2 + d * Math.cos(angulo);
						p4.y = l.y2 + d * Math.sin(angulo);
					}
					descricao = "quadrilatero";
				}

				// montando regi�o com os pontos,
				// os pontos tem que ter sido montados � formar uma regi�o quadril�tire convexa
				// regra da m�o direita, area interna da regi�o.
				points = new ArrayList<Point>();
				points.add(p1);
				points.add(p2);
				points.add(p3);
				points.add(p4);
				polygon = new Polygon(points);
				polygon.setDescricao(descricao);
				poligons.add(polygon);
			}
		}

		return poligons;
	}

	/**
	 * @param d � dist�ncia usada para calculo das regi�es.
	 * @return
	 */
	public Polygon compositeRegion(java.lang.Double d) {

		if (paths == null) {
			paths = new Polygon();
			getBounduary();
		}
		if (paths != null && !pathCreated) {

			pathCreated = true;

			boolean primeiraLinha = true;
			Point p1 = null, p2 = null, p3 = null, p4 = null;

			for (Line l : lines) {

				List<Point> points = null;
				Polygon polygon = null;
				// ===================================
				// ========== ponto inicial ==========
				if (primeiraLinha) {
					p1 = new Point(0, 0);
					p2 = new Point(0, 0);
					p3 = new Point(0, 0);
					p4 = new Point(0, 0);
					p1.x = l.x1 - d * Math.sqrt(2);
					p1.y = l.y1 - d * Math.sqrt(2);
					p2.x = l.x1 - d * Math.sqrt(2);
					p2.y = l.y1 + d * Math.sqrt(2);
					p3.x = l.x1 + d * Math.sqrt(2);
					p3.y = l.y1 + d * Math.sqrt(2);
					p4.x = l.x1 + d * Math.sqrt(2);
					p4.y = l.y1 - d * Math.sqrt(2);

					points = new ArrayList<Point>();
					points.add(p1);
					points.add(p2);
					points.add(p3);
					points.add(p4);
					polygon = new Polygon(points);
					polygon.setDescricao("quadrado");
					paths.append(polygon, true);
					primeiraLinha = false;
				}
				// ===================================

				// =================================
				// ========== ponto final ==========
				p1 = new Point(0, 0);
				p2 = new Point(0, 0);
				p3 = new Point(0, 0);
				p4 = new Point(0, 0);
				p1.x = l.x2 - d * Math.sqrt(2);
				p1.y = l.y2 - d * Math.sqrt(2);
				p2.x = l.x2 - d * Math.sqrt(2);
				p2.y = l.y2 + d * Math.sqrt(2);
				p3.x = l.x2 + d * Math.sqrt(2);
				p3.y = l.y2 + d * Math.sqrt(2);
				p4.x = l.x2 + d * Math.sqrt(2);
				p4.y = l.y2 - d * Math.sqrt(2);

				points = new ArrayList<Point>();
				points.add(p1);
				points.add(p2);
				points.add(p3);
				points.add(p4);
				polygon = new Polygon(points);
				polygon.setDescricao("quadrado");
				paths.append(polygon, true);
				// =================================

				// inclina��o do seguimento de reta
				java.lang.Double m, m2, angulo;
				if ((l.x2 - l.x1) == 0.0) {
					// reta � vertical
					m = java.lang.Double.POSITIVE_INFINITY;
					m2 = 0.0;
					angulo = Math.PI / 2;
				} else if ((l.y2 - l.y1) == 0.0) {
					// reta � horizontal
					m = 0.0;
					m2 = java.lang.Double.POSITIVE_INFINITY;
					angulo = 0.0;
				} else {
					m = (l.y2 - l.y1) / (l.x2 - l.x1);
					m2 = -1 / m;
					angulo = Math.atan(m2);
				}

				// p1 sempre ser� mais a esquerda e mais abaixo,
				// p2 sempre ser� mais a esquerda e mais acima,
				// p3 sempre ser� mais a direita e mais acima,
				// p4 sempre ser� mais a direita e mais abaixo
				p1 = new Point(0, 0);
				p2 = new Point(0, 0);
				p3 = new Point(0, 0);
				p4 = new Point(0, 0);
				String descricao = "";

				// seprem seguir a oriten��o horaria na regra da m�o direta para calcular o
				// interior do caminho
				// a coordenada x1 est� mais a esquerda que a coordenada x2
				if (m2 == 0) {
					// segmento de reta vertical, tangente infinito
					if (l.y1 > l.y2) {
						p1.x = l.x1 - d;
						p1.y = l.y1;
						p2.x = l.x1 + d;
						p2.y = l.y1;
						p3.x = l.x2 + d;
						p3.y = l.y2;
						p4.x = l.x2 - d;
						p4.y = l.y2;
					} else {
						p1.x = l.x1 - d;
						p1.y = l.y1;
						p2.x = l.x2 - d;
						p2.y = l.y2;
						p3.x = l.x2 + d;
						p3.y = l.y2;
						p4.x = l.x1 + d;
						p4.y = l.y1;
					}
					descricao = "retangulo vertical";
				} else if (m2 == java.lang.Double.POSITIVE_INFINITY) {
					// segmento horizontal, tangente zero
					if (l.x1 > l.x2) {
						p1.x = l.x1;
						p1.y = l.y1 - d;
						p2.x = l.x1;
						p2.y = l.y1 + d;
						p3.x = l.x2;
						p3.y = l.y2 + d;
						p4.x = l.x2;
						p4.y = l.y2 - d;
					} else {
						p1.x = l.x1;
						p1.y = l.y1 - d;
						p2.x = l.x2;
						p2.y = l.y2 - d;
						p3.x = l.x2;
						p3.y = l.y2 + d;
						p4.x = l.x1;
						p4.y = l.y1 + d;
					}
					descricao = "retangulo horizontal";
				} else {
					// coeficientes angulares diferente de zero e infinito
					m = (l.y2 - l.y1) / (l.x2 - l.x1);
					m2 = -1 / m;
					angulo = Math.atan(m2);
					if (l.x1 > l.x2) {
						p1.x = l.x1 + d * Math.cos(angulo);
						p1.y = l.y1 + d * Math.sin(angulo);
						p2.x = l.x2 + d * Math.cos(angulo);
						p2.y = l.y2 + d * Math.sin(angulo);
						p3.x = l.x2 - d * Math.cos(angulo);
						p3.y = l.y2 - d * Math.sin(angulo);
						p4.x = l.x1 - d * Math.cos(angulo);
						p4.y = l.y1 - d * Math.sin(angulo);
					} else if (l.x1 < l.x2) {
						p1.x = l.x1 + d * Math.cos(angulo);
						p1.y = l.y1 + d * Math.sin(angulo);
						p2.x = l.x1 - d * Math.cos(angulo);
						p2.y = l.y1 - d * Math.sin(angulo);
						p3.x = l.x2 - d * Math.cos(angulo);
						p3.y = l.y2 - d * Math.sin(angulo);
						p4.x = l.x2 + d * Math.cos(angulo);
						p4.y = l.y2 + d * Math.sin(angulo);
					}
					descricao = "quadrilatero";
				}

				// montando regi�o com os pontos,
				// os pontos tem que ter sido montados � formar uma regi�o quadril�tire convexa
				// regra da m�o direita, area interna da regi�o.
				points = new ArrayList<Point>();
				points.add(p1);
				points.add(p2);
				points.add(p3);
				points.add(p4);
				polygon = new Polygon(points);
				polygon.setDescricao(descricao);
				paths.append(polygon, true);
			}
		}

		return paths;
	}

	public Boolean containsOld(Point p, java.lang.Double minDistance, boolean debug) {
		Boolean result = false;

		List<Polygon> poligons = compositeRegionOld(minDistance);
		for (int i = 0; i < poligons.size() && !result; i++) {
			result = poligons.get(i).contains(p);
		}

		if (debug) {
			System.out.println("ponto est� " + (result ? "dentro" : "fora"));
			System.out.println("");
		}

		return result;
	}

	public Boolean contains(Point p, java.lang.Double minDistance, boolean debug) {
		Boolean result = false;

		Polygon polygon = compositeRegion(minDistance);
		result = polygon.contains(p);

		if (debug) {
			System.out.println("ponto est� " + (result ? "dentro" : "fora"));
			System.out.println("");
		}

		return result;
	}

	public java.lang.Double minDistanceTo(Point p) {
		java.lang.Double d = null;
		// distancia ponto a ponto, e
		for (Point pp : points) {
			double dd = pp.distance(p);
			if (d == null) {
				d = dd;
			} else {
				d = Math.min(d, dd);
			}
		}
		// distancia ponto ao segmento
		for (Line l : lines) {
			double dd = l.ptLineDist(p);
			if (d == null) {
				d = dd;
			} else {
				d = Math.min(d, dd);
			}
		}
		return d;
	}

	public boolean intersect(Path2D p) {
		return Geometry.instersect(this, p);
	}
}