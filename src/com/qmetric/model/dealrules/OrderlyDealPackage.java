/**
 * 
 */
package com.qmetric.model.dealrules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.qmetric.goods.ShoppingBasket;

/**
 * @author Richard Livingstone
 *
 */
public class OrderlyDealPackage implements DealPackage {
	// could order using TreeSet but more likely that explicit ordering will
	// take place either due to basket contents
	// or from store staff
	private final List<DealRules> rulesForDeal = new ArrayList<DealRules>();

	public OrderlyDealPackage(DealRules deal) {
		addRules(deal);
	}

	public void addRules(DealRules deal) {
		this.rulesForDeal.add(deal);
	}

	/*
	 * Returns deals that can be applied to this basket in the order of most
	 * advantageous (most saving to the consumer). SImplistic in that it doesn't
	 * try and combine deals to give the best result - just tests each deal in
	 * isolation. A real world solution would do the former
	 */
	public List<DealRules> getApplicableRules(ShoppingBasket basket) {
		final HashMap<DealRules, BigDecimal> valueOfDealsForThisBasket = new HashMap<DealRules, BigDecimal>();
		for (DealRules nextDealToTest : this.rulesForDeal) {
			valueOfDealsForThisBasket.put(nextDealToTest,
					nextDealToTest.getTotalDealSaving(
							basket.getCurrency(),
							basket.numberOfMatches(nextDealToTest)));
		}

		Map<DealRules, BigDecimal> topTenDeals = valueOfDealsForThisBasket.entrySet().stream()
				.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).limit(10)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		return new ArrayList<DealRules>(topTenDeals.keySet());
	}

}
