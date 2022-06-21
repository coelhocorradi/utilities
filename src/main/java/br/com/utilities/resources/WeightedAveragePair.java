package br.com.utilities.resources;

/**
 * 
 * @author gustavo
 *
 */
public class WeightedAveragePair extends PairExt<Double, Double> {

	/**
	 * 
	 */
	public WeightedAveragePair() {
		super(0.0, 0.0);
	}

	/**
	 * 
	 * @param weight
	 * @param value
	 */
	public WeightedAveragePair(Double weight, Double value) {
		super(weight, value);
	}

	/**
	 * 
	 * @param weight
	 */
	public void setWeight(Double weight) {
		super.setKey(weight);
	}

	/**
	 * 
	 * @return
	 */
	public Double getWeigth() {
		return super.getKey();
	}
}
