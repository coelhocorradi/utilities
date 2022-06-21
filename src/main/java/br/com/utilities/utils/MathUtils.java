package br.com.utilities.utils;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.commons.math3.util.Pair;

/**
 * 
 * @author gustavo
 *
 */
public abstract class MathUtils {

	public static double round(double value, int precisao) {

		if (precisao < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(precisao, RoundingMode.HALF_UP);

		return bd.doubleValue();
	}

	public static byte getMin(byte... values) {
		byte u = values.length > 1 ? values[0] : 0;
		for (byte i : values) {
			u = u < i ? u : i;
		}
		return u;
	}

	public static short getMin(short... values) {
		short u = values.length > 1 ? values[0] : 0;
		for (short i : values) {
			u = u < i ? u : i;
		}
		return u;
	}

	public static int getMin(int... values) {
		int u = values.length > 1 ? values[0] : 0;
		for (int i : values) {
			u = u < i ? u : i;
		}
		return u;
	}

	public static long getMin(long... values) {
		long u = values.length > 1 ? values[0] : 0;
		for (long i : values) {
			u = u < i ? u : i;
		}
		return u;
	}

	public static float getMin(float... values) {
		float u = values.length > 1 ? values[0] : 0;
		for (float i : values) {
			u = u < i ? u : i;
		}
		return u;
	}

	public static double getMin(double... values) {
		double u = values.length > 1 ? values[0] : 0;
		for (double i : values) {
			u = u < i ? u : i;
		}
		return u;
	}

	public static byte getMax(byte... values) {
		byte u = values.length > 1 ? values[0] : 0;
		for (byte i : values) {
			u = u > i ? u : i;
		}
		return u;
	}

	public static short getMax(short... values) {
		short u = values.length > 1 ? values[0] : 0;
		for (short i : values) {
			u = u > i ? u : i;
		}
		return u;
	}

	public static int getMax(int... values) {
		int u = values.length > 1 ? values[0] : 0;
		for (int i : values) {
			u = u > i ? u : i;
		}
		return u;
	}

	public static long getMax(long... values) {
		long u = values.length > 1 ? values[0] : 0;
		for (long i : values) {
			u = u > i ? u : i;
		}
		return u;
	}

	public static float getMax(float... values) {
		float u = values.length > 1 ? values[0] : 0;
		for (float i : values) {
			u = u > i ? u : i;
		}
		return u;
	}

	public static double getMax(double... values) {
		double u = values.length > 1 ? values[0] : 0;
		for (double i : values) {
			u = u > i ? u : i;
		}
		return u;
	}

	/**
	 * Fatorial de "n"
	 * 
	 * @param n
	 * @return
	 */
	public static long Fat(long n) {
		long result = 0;
		if (n <= 0) {
			result = 1;
		} else {
			result = n * MathUtils.Fat(n - 1);
		}
		return result;
	}

	/**
	 * Arranjo de "a" a cada "b"
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long Arg(long a, long b) {
		long result = 0;
		if (a > 0 && b == 0) {
			result = 1;
		} else if (a <= 0 || b < 0) {
			result = 0;
			System.out.println("Valores inv�lidos");
		} else if (a >= b) {
			result = MathUtils.Fat(a) / MathUtils.Fat(b);
		} else {
			result = 0;
			System.out.println("condi��o desconhecida!");
		}
		return result;
	}

	/**
	 * Combina��o de "a" a cada "b";
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long Com(long a, long b) {
		long result = 0;
		if (a > 0 && b == 0) {
			result = 1;
		} else if (a <= 0 || b < 0) {
			result = 0;
			System.out.println("Valores inv�lidos");
		} else if (a >= b) {
			result = MathUtils.Fat(a) / (MathUtils.Fat(b) * MathUtils.Fat(a - b));
		} else {
			result = 0;
			System.out.println("condi��o desconhecida!");
		}
		return result;
	}

	/**
	 * polin�mio de newton
	 * 
	 * @param exp
	 * @return
	 */
	public static String newton(long exp) {
		String result = "";
		try {
			if (exp < 1) {
				result = "1";
			} else if (exp == 1) {
				result = "x + y";
			} else {
				String plus = "";
				int u = 0;
				while (u <= exp) {
					long v = exp - u;
					long cmb = MathUtils.Com(exp, u);
					result += plus;
					if (cmb > 1) {
						result += Long.toString(cmb) + "*";
					}
					if (v > 0) {
						result += "x";
						if (v > 1) {
							result += "x^" + Long.toString(v);
						}
					}
					if (v > 1 && u > 1) {
						result += "*";
					}
					if (u > 0) {
						result += "y";
						if (u > 1) {
							result += "^" + Long.toString(u);
						}
					}
					plus = " + ";
					u++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 */
	public static void showAllNewton() {
		long p = 0;
		String result = null;
		do {
			System.out.println("Para exp = " + Long.toString(p));
			result = newton(p++);
		} while (result != null);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public static double averageU(double... values) {
		return average(values);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public static double average(double[] values) {
		double result = 0.0;
		if (values != null && values.length > 1) {
			for (double d : values) {
				result += d;
			}
			result = result / values.length;
		}
		return result;
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static double weightedAverageU(Pair<Double, Double>... values) {
		return weightedAverage(values);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public static double weightedAverage(Pair<Double, Double>[] values) {
		double result = 0.0, sum = 0.0;
		if (values != null && values.length > 1) {
			for (Pair<Double, Double> d : values) {
				result += d.getKey() * d.getValue();
				sum += d.getKey();
			}
			if (sum != 0) {
				result = result / sum;
			} else {
				if (result > 0.0) {
					result = Double.POSITIVE_INFINITY;
				} else {
					result = Double.NEGATIVE_INFINITY;
				}
			}
		}
		return result;
	}

	public static double weightedAverageU(double[]... values) {
		return weightedAverage(values);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public static double weightedAverage(double[][] values) {
		double result = 0.0, sum = 0.0;
		if (values != null && values.length > 1) {
			for (double[] dd : values) {
				if (dd.length == 1) {
					System.out
							.println("The position has only one value, assume this value and set weight equals to 1!");
					result += dd[0];
					sum += 1;
				} else if (dd.length >= 2) {
					if (dd.length > 2) {
						System.out.println(
								"The position has more of two values, assume first value like weight and second like value! The other positions will be discarted!");
					}
					result += dd[0] * dd[1];
					sum += dd[1];
				} else {
					System.out.println("Position is empty, can not be used on process!");
				}
			}
			if (sum != 0) {
				result = result / sum;
			} else {
				if (result > 0.0) {
					result = Double.POSITIVE_INFINITY;
				} else {
					result = Double.NEGATIVE_INFINITY;
				}
			}
		}
		return result;
	}

	/**
	 * progress�o aritm�tica
	 * 
	 * @param p1
	 * @param r
	 * @param n
	 * @return
	 */
	public static double pa(double p1, double r, int n) {
		return p1 + (n - 1) * r;
	}

	/**
	 * progress�o geom�trica
	 * 
	 * @param p1
	 * @param r
	 * @param n
	 * @return
	 */
	public static double pg(double p1, double r, int n) {
		return p1 * Math.pow(r, n);
	}

	/**
	 * used to calculate area for all convex 2d poligons
	 * 
	 * @param points
	 * @return
	 */
	public static double area2dU(Point2D... points) {
		return area2d(points);
	}

	/**
	 * used to calculate area for all convex 2d poligons
	 * 
	 * @param points
	 * @return
	 */
	public static double area2d(Point2D[] points) {
		double result = 0.0;
		if (points.length > 2) {
			double[][] m = new double[3][3];
			// Point a = points[0];
			m[0][0] = points[0].getX();
			m[0][1] = points[0].getY();
			m[0][2] = 1.0;
			for (int i = 1; i < points.length - 2; i++) {
				// Point b = points[i];
				m[1][0] = points[i].getX();
				m[1][1] = points[i].getY();
				m[1][2] = 1.0;
				// Point c = points[i+1];
				m[2][0] = points[i + 1].getX();
				m[2][1] = points[i + 1].getY();
				m[2][2] = 1.0;
				result += (Math.abs(MatrixUtils.matrixDeterminant(m)) * 0.5);
			}
		}
		return result;
	}

	/**
	 * Considerando sempre o X0, Y0 como centro do circulo, calcula se o ponto (x,y)
	 * informado est� dentro ou no limiar do circulo, e retorna a diferen�a da
	 * dist�ncia entre o ponto e o limiar do circulo.
	 * 
	 * @param x abscissa do ponto a ser avaliado
	 * @param y ordenada do ponto a ser avaliado
	 * @param r raio do circulo
	 * @return 0 se no limitar, > 0 se dentro do circulo e < 0 se fora do circulo;
	 */
	public static Double distCircle(Double x, Double y, Double r) {
		r = r < 0 ? -1 * r : r;
		Double rcalculado = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return r - rcalculado;
	}

	/**
	 * Considerando sempre o X0, Y0 como centro do circulo, retorna se o ponto
	 * informado est� dentro ou fora do circulo
	 * 
	 * @param x abscissa do ponto a ser avalidado
	 * @param y ordenada do ponto a ser avaliado
	 * @param r raio do circulo
	 * @return true se dentro do circulo, false de fora
	 */
	public static Boolean innerCircle(Double x, Double y, Double r) {
		return distCircle(x, y, r) >= 0;
	}

	public static String pointToString(Double... args) {
		String result = "", vg = "";
		if (args != null) {
			result += "[";
			for (Double a : args) {
				result += vg + Double.toString(a);
				vg = ",";
			}
			result += "]";
		}
		return result;
	}

	public static String matrixToString(Double[][] args) {
		String result = "", vg = "";
		if (args != null) {
			result += "[" + SystemUtils.lineSeparator;
			for (Double[] ar : args) {
				vg = "";
				result += "[";
				for (Double a : ar) {
					result += vg + Double.toString(a);
					vg = ",";
				}
				result += "]" + SystemUtils.lineSeparator;
			}
			result += "]";
		}
		return result;
	}

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
				: Double.NaN;
	}

	/**
	 * interse��o da reta que passa pelo seguimento de reta com o eixo y
	 * 
	 * @param line
	 * @return
	 */
	public static double yIntercept(Line2D line) {
		return !isVertical(line) ? line.getP1().getY() - slope(line) * line.getP1().getX() : Double.NaN;
	}

	public static final double SILVER = 1d + Math.sqrt(2);

	/**
	 * @see FI
	 */
	public static final double AUREA = 1.61803398875;

	/**
	 * @see AUREA
	 */
	public static final double FI = AUREA;

	public static int MDC(int a, int b) {
		int v1 = Math.max(a, b), v2 = Math.min(a, b);
		return v2 != 0 ? MDC(v2, v1 % v2) : v1;
	}

	public static int MDC(int... v) {
		if (v.length < 2) {
			throw new IllegalArgumentException("A lista deve conter pelo menos dois n�meros");
		}
		Arrays.sort(v);
		int mdcResultado = MDC(v[v.length - 1], v[v.length - 2]);
		for (int i = v.length - 3; i >= 0; i--) {
			mdcResultado = MDC(v[i], mdcResultado);
		}
		return mdcResultado;
	}

	public static int MMC(int... v) {
		if (v.length < 2) {
			throw new IllegalArgumentException("A lista deve conter pelo menos dois n�meros");
		}
		Arrays.sort(v);
		int mmcResultado = v[v.length - 1];
		for (int i = v.length - 2; i >= 0; i--) {
			mmcResultado = mmcResultado * (v[i] / MDC(v[i], mmcResultado));
		}
		return mmcResultado;
	}
}
