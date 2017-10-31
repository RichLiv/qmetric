package com.qmetric.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qmetric.actors.Supplier;
import com.qmetric.goods.InStoreShoppingBasket;
import com.qmetric.goods.ShoppingBasket;
import com.qmetric.goods.StockItem;
import com.qmetric.goods.StockUnitsOfMeasure;
import com.qmetric.model.dealrules.DealPackage;
import com.qmetric.model.dealrules.OrderlyDealPackage;
import com.qmetric.model.dealrules.XForPriceOfY;
import com.qmetric.model.dealrules.XItemsWithDiscountY;
import com.qmetric.model.pricingmodels.CostPricingModel;
import com.qmetric.model.pricingmodels.Currency;
import com.qmetric.model.pricingmodels.SimplePricingModel;

public class UnitTest {

	private Supplier fruitSupplier;
	private Supplier tinnedGoodsSupplier;
	private StockItem oranges;
	private StockItem coke;
	private StockItem bakedBeans;
	private Calendar lastStockCycleDate;
	private Calendar expiryDate; // refers to the price though we could just as easily enhance to check use by dates etc

	private static final String basicReceiptLine = "{0}\t\t {1,number,#####0.00}"; // refine this to take account of number of decimals in currency if more time available 

	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(UnitTest.class);
	}


	/*
	 * All this would come from central store systems
	 */
	@Before
	public void setUp() throws Exception {
		// for the sake of testing we will not use this field for now but it would come froma stock control system and could be used to compare potential profit margins in a real system with different dates for tranches of the same stock orders 
		this.lastStockCycleDate = Calendar.getInstance(TimeZone.getDefault()); // ensure we observe timezone properly
		this.lastStockCycleDate.add(Calendar.MONTH, -3);// three months ago

		this.expiryDate = Calendar.getInstance(TimeZone.getDefault()); // ensure we observe timezone properly
		this.expiryDate.add(Calendar.MONTH, 3);// three months time

		this.fruitSupplier = new Supplier() {
			@Override
			public String getCompanyName() {
				return "Spanish Fruit Inc";
			}

			@Override
			public String getPrimaryContact() {
				return "Carles Puigdemont";
			}

			@Override
			public String getAddress() {
				return "Somewhere in Belgium";
			}

			@Override
			public String getPrimaryEmail() {
				return "carles@spanishfruitinc.com";
			}

			@Override
			public String getPrimaryPhoneContact() {
				return "003412345678";
			}
			
		};
		
		this.tinnedGoodsSupplier = new Supplier() {
			@Override
			public String getCompanyName() {
				return "West Bromwich Tinnies";
			}

			@Override
			public String getPrimaryContact() {
				return "Fred Bloggs";
			}

			@Override
			public String getAddress() {
				return "Somewhere in Birmingham";
			}

			@Override
			public String getPrimaryEmail() {
				return "fredb@tinsrus.com";
			}

			@Override
			public String getPrimaryPhoneContact() {
				return "023222333";
			}
			
		};

		this.oranges = new StockItem() {

			@Override
			public CostPricingModel getCostOfSupply() {
				return new CostPricingModel() {
					public StockUnitsOfMeasure getUnits() {
						return StockUnitsOfMeasure.KGS;
					}

					@Override
					public Currency getCurrency() {
						return Currency.GBP; // in future we might want to specify in euros and include exchange rate in Currency
						// we could then do up to the minute mark to market valuations on sales.  
					}

					@Override
					public int getPriceInCents() {
						return 23; // I made this up ;-)
					}
					
					public Supplier getSupplier() {
						return UnitTest.this.fruitSupplier;
					}
					
					@Override
					public Calendar getDateInForce() {
						return UnitTest.this.lastStockCycleDate;
					}

				};
			}
			@Override
			public SimplePricingModel getPriceAtTill() {
				return new SimplePricingModel() {
					@Override
					public StockUnitsOfMeasure getUnits() {
						return StockUnitsOfMeasure.KGS; // or LBS but then we would need to introduce weight conversions too
					}

					@Override
					public Currency getCurrency() {
						return Currency.GBP;
					}

					@Override
					public int getPriceInCents() {
						return 199;
					}

					@Override
					public Calendar getDateInForce() {
						return UnitTest.this.lastStockCycleDate;
					}

					public Calendar getExpiryDate() {
						return expiryDate;
					}
				};
			}
			@Override
			public String getName() {
				return "Oranges";
			}
			@Override
			public String getReceiptLine() {
				// TODO change so it matches the receipt,not too difficult
				BigDecimal amount = new BigDecimal(getPriceAtTill().getPriceInCents());
				BigDecimal amountLocal = amount.setScale(getPriceAtTill().getCurrency().getNumberOfDecimalPlaces(),RoundingMode.HALF_UP);
				amountLocal = amountLocal.scaleByPowerOfTen(-1*getPriceAtTill().getCurrency().getNumberOfDecimalPlaces());
				String receiptLine = MessageFormat.format(basicReceiptLine, new Object[] {getName(), amountLocal});
			}
			@Override
			public Double getQuantity() {
				return Double.valueOf(0.2F);
			}
		};

		this.coke = new StockItem() {

			@Override
			public CostPricingModel getCostOfSupply() {
				return new CostPricingModel() {
					public StockUnitsOfMeasure getUnits() {
						return StockUnitsOfMeasure.NUMBER;
					}

					@Override
					public Currency getCurrency() {
						return Currency.GBP;
					}

					@Override
					public int getPriceInCents() {
						return 13; // I made this up ;-)
					}
					
					public Supplier getSupplier() {
						return UnitTest.this.tinnedGoodsSupplier;
					}
				};
			}
			@Override
			public SimplePricingModel getPriceAtTill() {
				return new SimplePricingModel() {
					@Override
					public StockUnitsOfMeasure getUnits() {
						return StockUnitsOfMeasure.NUMBER; // coke might be in supplied in packs of N, yet another modelling twist  
					}

					@Override
					public Currency getCurrency() {
						return Currency.GBP;
					}

					@Override
					public int getPriceInCents() {
						return 70;
					}

					@Override
					public Calendar getDateInForce() {
						return UnitTest.this.lastStockCycleDate;
					}

					public Calendar getExpiryDate() {
						return expiryDate;
					}
				};
			}
			@Override
			public String getName() {
				return "Coke";
			}
			@Override
			public String getReceiptLine() {
				// avoids rounding errors when formatting
				BigDecimal amount = new BigDecimal(getPriceAtTill().getPriceInCents());
				BigDecimal amountLocal = amount.setScale(getPriceAtTill().getCurrency().getNumberOfDecimalPlaces(),RoundingMode.HALF_UP);
				amountLocal = amountLocal.scaleByPowerOfTen(-1*getPriceAtTill().getCurrency().getNumberOfDecimalPlaces());
				String receiptLine = MessageFormat.format(basicReceiptLine, new Object[] {getName(), amountLocal});
			}
			@Override
			public Double getQuantity() {
				return 1D;
			}
		};

		this.bakedBeans = new StockItem() {

			@Override
			public CostPricingModel getCostOfSupply() {
				return new CostPricingModel() {
					public StockUnitsOfMeasure getUnits() {
						return StockUnitsOfMeasure.NUMBER;
					}

					@Override
					public Currency getCurrency() {
						return Currency.GBP;
					}

					@Override
					public int getPriceInCents() {
						return 8; // I made this up ;-)
					}
					
					public Supplier getSupplier() {
						return UnitTest.this.tinnedGoodsSupplier;
					}
				};
			}
			@Override
			public SimplePricingModel getPriceAtTill() {
				return new SimplePricingModel() {
					@Override
					public StockUnitsOfMeasure getUnits() {
						return StockUnitsOfMeasure.NUMBER; // coke might be in supplied in packs of N, yet another modelling twist  
					}

					@Override
					public Currency getCurrency() {
						return Currency.GBP;
					}

					@Override
					public int getPriceInCents() {
						return 50;
					}

					@Override
					public Calendar getDateInForce() {
						return UnitTest.this.lastStockCycleDate;
					}

					public Calendar getExpiryDate() {
						return expiryDate;
					}
				};
			}
			@Override
			public String getName() {
				return "Beans";
			}
			@Override
			public String getReceiptLine() {
				// avoids rounding errors when formatting
				BigDecimal amount = new BigDecimal(getPriceAtTill().getPriceInCents());
				BigDecimal amountLocal = amount.setScale(getPriceAtTill().getCurrency().getNumberOfDecimalPlaces(),RoundingMode.HALF_UP);
				amountLocal = amountLocal.scaleByPowerOfTen(-1*getPriceAtTill().getCurrency().getNumberOfDecimalPlaces());
				String receiptLine = MessageFormat.format(basicReceiptLine, new Object[] {getName(), amountLocal});
			}
			@Override
			public Double getQuantity() {
				return 1D;
			}
		};
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() { 
		
		// now the basic scaffolding is in place, next thing is to add the tests before implementing the 
		// classes in full
		
		ShoppingBasket myBasket = new InStoreShoppingBasket();
		
		myBasket.addItemToBasket(this.oranges);
		myBasket.addItemToBasket(this.coke);
		myBasket.addItemToBasket(this.coke);
		myBasket.addItemToBasket(this.bakedBeans);
		myBasket.addItemToBasket(this.bakedBeans);
		myBasket.addItemToBasket(this.bakedBeans);
		
		// order of receipt
		myBasket.getItems().sort(new Comparator<StockItem>() {

			@Override
			public int compare(StockItem o1, StockItem o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		// again, from store systems, these would be fairly fluid in reality
		// represents one way of applying the rules only
		DealPackage dealRules = new OrderlyDealPackage();
		dealRules.addRules(new XForPriceOfY() {
			public Collection<StockItem> getRelatedItems() {
				return Arrays.asList(new StockItem[] {
						UnitTest.this.bakedBeans,
						UnitTest.this.bakedBeans,
						UnitTest.this.bakedBeans
						});
			};
			
			public int getTotalDealSaving(Currency requiredCurrency) {
				return UnitTest.this.bakedBeans.getPriceAtTill().getPriceInCents(); // convert - or we could use a CurrencyAmount class as an enhancement to encapsulate the currency a bit more if we were really calculating conversions 
			}
			
			public int getBaseCost(Currency requiredCurrency) {
				return UnitTest.this.bakedBeans.getCostOfSupply().getPriceInCents() * 3;
			}
		});
		dealRules.addRules(new XItemsWithDiscountY() {
			public Collection<StockItem> getRelatedItems() {
				return Arrays.asList(new StockItem[] {
						UnitTest.this.coke,
						UnitTest.this.coke
						});
			};
			
			public int getTotalDealSaving(Currency requiredCurrency) {
				return 40; // fixed discount in this case 
			}
			
			public int getBaseCost(Currency requiredCurrency) {
				return UnitTest.this.coke.getCostOfSupply().getPriceInCents() * 2;
			}
		});

		double subTotalInCents = 0D;
		List<String> savingsLines = new ArrayList<String>();
		for (StockItem nextItem : myBasket.getItems()) {
			System.out.println(nextItem.getReceiptLine());
			subTotalInCents += nextItem.getQuantity() * nextItem.getPriceAtTill().getPriceInCents(); 
		}
		System.out.println("");
		System.out.println("Sub-total");
		System.out.println("");
		System.out.println("Savings");

		// TODO Retrieve applicable savings etc and put on receipt
		
		assertTrue(true); // we are not especially testing values as we go along here as the exercise is 
		// fairly simple but we could do.
		
	}

}