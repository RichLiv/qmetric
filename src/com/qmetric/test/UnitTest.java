package com.qmetric.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
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
import com.qmetric.audit.AuditLogger;
import com.qmetric.audit.TillAuditLogger;
import com.qmetric.exception.AuditFailureException;
import com.qmetric.goods.InStoreShoppingBasket;
import com.qmetric.goods.ShoppingBasket;
import com.qmetric.goods.StockItem;
import com.qmetric.goods.StockUnitsOfMeasure;
import com.qmetric.model.dealrules.DealPackage;
import com.qmetric.model.dealrules.DealRules;
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
					public BigDecimal getPriceInCents() {
						return BigDecimal.valueOf(23); // I made this up ;-)
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
					public BigDecimal getPriceInCents() {
						return BigDecimal.valueOf(199D * getQuantity().doubleValue());
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
				BigDecimal amountLocal = UnitTest.formatAmountForDisplayWithDecimals(getPriceAtTill().getPriceInCents(), getPriceAtTill().getCurrency());
				return MessageFormat.format(basicReceiptLine, new Object[] {getName(), amountLocal});
			}
			@Override
			public BigDecimal getQuantity() {
				return BigDecimal.valueOf(0.2F);
			}
			
			public String toString() {
				// for logging. Could add units etc etc if we wanted
				return new StringBuffer(getReceiptLine()).append(", price in cents is ").append(getPriceAtTill().getPriceInCents().intValue()).toString();
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
					public BigDecimal getPriceInCents() {
						return BigDecimal.valueOf(13); // I made this up ;-)
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
					public BigDecimal getPriceInCents() {
						return BigDecimal.valueOf(70);
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
				BigDecimal amountLocal = UnitTest.formatAmountForDisplayWithDecimals(getPriceAtTill().getPriceInCents(), getPriceAtTill().getCurrency());
				return MessageFormat.format(basicReceiptLine, new Object[] {getName(), amountLocal});
			}
			@Override
			public BigDecimal getQuantity() {
				return BigDecimal.ONE;
			}
			public String toString() {
				// for logging. Could add units etc etc if we wanted
				return new StringBuffer(getReceiptLine()).append(", price in cents is ").append(getPriceAtTill().getPriceInCents().intValue()).toString();
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
					public BigDecimal getPriceInCents() {
						return BigDecimal.valueOf(8); // I made this up ;-)
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
					public BigDecimal getPriceInCents() {
						return BigDecimal.valueOf(50);
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
				BigDecimal amountLocal = UnitTest.formatAmountForDisplayWithDecimals(getPriceAtTill().getPriceInCents(), getPriceAtTill().getCurrency());
				return MessageFormat.format(basicReceiptLine, new Object[] {getName(), amountLocal});
			}
			@Override
			public BigDecimal getQuantity() {
				return BigDecimal.ONE;
			}
			public String toString() {
				// for logging. Could add units etc etc if we wanted
				return new StringBuffer(getReceiptLine()).append(", price in cents is ").append(getPriceAtTill().getPriceInCents().intValue()).toString();
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
			
			public BigDecimal getTotalDealSaving(Currency requiredCurrency) {
				return UnitTest.this.bakedBeans.getPriceAtTill().getPriceInCents(); // convert - or we could use a CurrencyAmount class as an enhancement to encapsulate the currency a bit more if we were really calculating conversions 
			}
			
			public BigDecimal getBaseCost(Currency requiredCurrency) {
				return UnitTest.this.bakedBeans.getPriceAtTill().getPriceInCents().multiply(BigDecimal.valueOf(getRelatedItems().size()));
			}

			@Override
			public String getReceiptLine() {
				BigDecimal amountLocal = UnitTest.formatAmountForDisplayWithDecimals(getTotalDealSaving(UnitTest.this.bakedBeans.getPriceAtTill().getCurrency()), UnitTest.this.bakedBeans.getPriceAtTill().getCurrency());
				String receiptLine = MessageFormat.format("{0} {1} for {2}\t\t-{3,number,####0.00}", 
						new Object[] {
								UnitTest.this.bakedBeans.getName(),
								getRelatedItems().size(),
								getRelatedItems().size()-1,
								amountLocal
						});
				return receiptLine;
			}
		});
		dealRules.addRules(new XItemsWithDiscountY() {
			public Collection<StockItem> getRelatedItems() {
				return Arrays.asList(new StockItem[] {
						UnitTest.this.coke,
						UnitTest.this.coke
						});
			};
			
			public BigDecimal getTotalDealSaving(Currency requiredCurrency) {
				return BigDecimal.valueOf(40); // fixed discount in this case 
			}
			
			public BigDecimal getBaseCost(Currency requiredCurrency) {
				return UnitTest.this.coke.getPriceAtTill().getPriceInCents().multiply(BigDecimal.valueOf(getRelatedItems().size()));
			}

			@Override
			public String getReceiptLine() {
				// adjust decimal places for printing
				BigDecimal actualAmountPaid = getBaseCost(UnitTest.this.coke.getPriceAtTill().getCurrency()).subtract(getTotalDealSaving(UnitTest.this.coke.getPriceAtTill().getCurrency()));
				actualAmountPaid = UnitTest.formatAmountForDisplayWithDecimals(actualAmountPaid, UnitTest.this.coke.getPriceAtTill().getCurrency());
				BigDecimal totalSaving = getTotalDealSaving(UnitTest.this.coke.getPriceAtTill().getCurrency());
				totalSaving = UnitTest.formatAmountForDisplayWithDecimals(totalSaving, UnitTest.this.coke.getPriceAtTill().getCurrency());
				String receiptLine = MessageFormat.format("{0} {1} for {2}{3}\t\t-{4,number,####0.00}", 
						new Object[] {
								UnitTest.this.coke.getName(),
								getRelatedItems().size(),
								UnitTest.this.coke.getPriceAtTill().getCurrency().getCurrencySymbol(),
								actualAmountPaid,
								totalSaving
						});
				
				return receiptLine;
			}
			
		});

		float subTotalInCents = 0.00F;
		
		for (StockItem nextItem : myBasket.getItems()) {
			System.out.println(nextItem.getReceiptLine());
			BigDecimal incrementToSubTotal = nextItem.getPriceAtTill().getPriceInCents();
			subTotalInCents += incrementToSubTotal.floatValue(); 
		}

		System.out.println("-----------");

		String subTotalReceiptLine = MessageFormat.format("Sub-total\t\t{0,number,####0.00}", 
				new Object[] {
					UnitTest.formatAmountForDisplayWithDecimals(BigDecimal.valueOf(subTotalInCents), myBasket.getCurrency()) // could introduce a basket currency if we wanted
				});

		
		
		System.out.println(subTotalReceiptLine);
		System.out.println("");
		System.out.println("Savings");
		
		// logic in here is not implemented but it is structured such that adding it is fairly trivial
		List<DealRules> appliedRules = dealRules.getApplicableRules(myBasket);

		float cumulativeSavings = 0.00F;
		for (DealRules nextRuleApplied : appliedRules) {
			cumulativeSavings += nextRuleApplied.getTotalDealSaving(myBasket.getCurrency()).floatValue();
			System.out.println(nextRuleApplied.getReceiptLine());
		}
		
		System.out.println("\t\t\t------");

		String totalSavingsReceiptLine = MessageFormat.format("Total savings\t\t-{0,number,####0.00}", 
				new Object[] {
					UnitTest.formatAmountForDisplayWithDecimals(BigDecimal.valueOf(cumulativeSavings), myBasket.getCurrency()) // could introduce a basket currency if we wanted
				});

		System.out.println(totalSavingsReceiptLine);
		
		System.out.println("-----------");

		String totalToPayReceiptLine = MessageFormat.format("Total to pay\t\t{0,number,####0.00}", 
				new Object[] {
					UnitTest.formatAmountForDisplayWithDecimals(BigDecimal.valueOf(subTotalInCents - cumulativeSavings), myBasket.getCurrency()) // could introduce a basket currency if we wanted
				});

		System.out.println(totalToPayReceiptLine);

		System.out.println("Audit log entry will be something like this (though printed to a persistent audit stream somewhere not to stdout):");

		AuditLogger logger = new TillAuditLogger();
		try {
			logger.logSale(myBasket);
		} catch (AuditFailureException e) {
			e.printStackTrace();
		}

		assertTrue(true); // we are not especially testing values as we go along here as the exercise is 
		// fairly simple but we could do.
		
	}
	
	private static BigDecimal formatAmountForDisplayWithDecimals(BigDecimal amount, Currency currency) {
		// avoid rounding issues
		BigDecimal amountLocal = amount.setScale(currency.getNumberOfDecimalPlaces(),RoundingMode.HALF_UP);
		amountLocal = amountLocal.scaleByPowerOfTen(-1*currency.getNumberOfDecimalPlaces());
		return amountLocal;
	}
}