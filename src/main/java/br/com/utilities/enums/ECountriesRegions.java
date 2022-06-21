package br.com.utilities.enums;

import java.util.LinkedList;
import java.util.List;

import br.com.utilities.utils.CollectionUtils;

public enum ECountriesRegions {

	UNKNOW((short) 0, false, false, false),

	BR((short) 55, true, false, false),

	Mercosul((short) (Short.MIN_VALUE + 1), false, false, true);

	private short code;

	private boolean country;

	private boolean stateProvince;

	private boolean region;

	/**
	 * 
	 * @param code
	 * @param country
	 * @param stateProvince
	 * @param region
	 */
	private ECountriesRegions(short code, boolean country, boolean stateProvince, boolean region) {
		this.code = code;
		this.country = country;
		this.stateProvince = stateProvince;
		this.region = region;
	}

	/**
	 * 
	 * @return
	 */
	public short getCode() {
		return this.code;
	}

	/**
	 * format code to string +{code}
	 * 
	 * @return
	 */
	public String getCodeS() {
		return "+" + Short.toString(this.code);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isCountry() {
		return this.country;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isStateProvince() {
		return this.stateProvince;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRegion() {
		return this.region;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public boolean validateByCode(short code) {
		return this.code == code;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static final ECountriesRegions searchByCode(short code) {
		ECountriesRegions result = ECountriesRegions.UNKNOW;
		boolean found = false;
		int i = 0;
		while (!found && i < values().length) {
			result = values()[i];
			if (result.validateByCode(code)) {
				found = true;
			} else {
				i++;
			}
		}
		return found ? result : ECountriesRegions.UNKNOW;
	}

	/**
	 * return only countries
	 * 
	 * @return
	 */
	public static final ECountriesRegions[] countries() {
		List<ECountriesRegions> list = new LinkedList<ECountriesRegions>();
		for (ECountriesRegions e : values()) {
			if (e.isCountry()) {
				list.add(e);
			}
		}
		return CollectionUtils.toArray(list);
	}

	/**
	 * return only states or provinces
	 * 
	 * @return
	 */
	public static final ECountriesRegions[] statesProvinces() {
		List<ECountriesRegions> list = new LinkedList<ECountriesRegions>();
		for (ECountriesRegions e : values()) {
			if (e.isStateProvince()) {
				list.add(e);
			}
		}
		return CollectionUtils.toArray(list);
	}

	/**
	 * return only regions
	 * 
	 * @return
	 */
	public static final ECountriesRegions[] regions() {
		List<ECountriesRegions> list = new LinkedList<ECountriesRegions>();
		for (ECountriesRegions e : values()) {
			if (e.isRegion()) {
				list.add(e);
			}
		}
		return CollectionUtils.<ECountriesRegions>toArray(list);
	}
}
