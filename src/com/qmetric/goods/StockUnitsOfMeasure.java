/**
 * 
 */
package com.qmetric.goods;

/**
 * @author Richard Livingstone
 *
 */
public interface StockUnitsOfMeasure {
	public static StockUnitsOfMeasure LBS = new StockUnitsOfMeasure() {
		public String getName() {
			return "Pound(s)";
		}

		public StockUnitsOfMeasure convertTo(StockUnitsOfMeasure otherUnit) {
			return otherUnit; // but in real life we will try and convert if
								// there is a need
		}
	};
	public static StockUnitsOfMeasure KGS = new StockUnitsOfMeasure() {
		public String getName() {
			return "Kilo(s)";
		}

		public StockUnitsOfMeasure convertTo(StockUnitsOfMeasure otherUnit) {
			return otherUnit; // but in real life we will try and convert if
								// there is a need
		}
	};
	public static StockUnitsOfMeasure NUMBER = new StockUnitsOfMeasure() {
		public String getName() {
			return "Unit(s)";
		}

		public StockUnitsOfMeasure convertTo(StockUnitsOfMeasure otherUnit) {
			return otherUnit; // but in real life we will try and convert if
								// there is a need
		}
	};
	public static StockUnitsOfMeasure LITRE = new StockUnitsOfMeasure() {
		public String getName() {
			return "Litre(s)";
		}

		public StockUnitsOfMeasure convertTo(StockUnitsOfMeasure otherUnit) {
			return otherUnit; // but in real life we will try and convert if
								// there is a need
		}
	};
	public static StockUnitsOfMeasure PINT = new StockUnitsOfMeasure() {
		public String getName() {
			return "Pint(s)";
		}

		public StockUnitsOfMeasure convertTo(StockUnitsOfMeasure otherUnit) {
			return otherUnit; // but in real life we will try and convert if
								// there is a need
		}
	};

	public String getName();

	public StockUnitsOfMeasure convertTo(StockUnitsOfMeasure otherUnit);
}
