/**
 * 
 */
package com.qmetric.model.pricingmodels;

/**
 * @author Richard Livingstone
 *
 */
public interface Currency {
	public static Currency GBP = new Currency() {

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
	public int getNumberOfDecimalPlaces();
	public String getCurrencySymbol();
	public int getIso4217NumericCode();
	public String getIso4217AlpabeticCode();
}
