/**
 * 
 */
package com.qmetric.model.pricingmodels;

import java.io.Serializable;

/**
 * @author Richard Livingstone
 *
 */
public interface Currency extends Serializable {
	public static final Currency GBP = new Currency() {

		@Override
		public int getNumberOfDecimalPlaces() {
			return 2;
		}

		@Override
		public String getCurrencySymbol() {
			return "£";
		}

		@Override
		public int getIso4217NumericCode() {
			return 826;
		}

		@Override
		public String getIso4217AlpabeticCode() {
			return "GBP";
		}

	};

	/**
	 * @return
	 */
	int getNumberOfDecimalPlaces();

	/**
	 * @return
	 */
	String getCurrencySymbol();

	/**
	 * @return
	 */
	int getIso4217NumericCode();

	/**
	 * @return
	 */
	String getIso4217AlpabeticCode();

}
