package com.cdcars.core.recommender.scopes.user;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.cdcars.core.recommender.scopes.contextual.AbstractContextualAttribute;
import com.cdcars.core.recommender.scopes.contextual.ContextualCriteria;
import com.cdcars.core.recommender.scopes.item.ItemCategory;
import com.cdcars.core.recommender.scopes.item.ItemDomain;

public class RuleTuple{
	private final ItemDomain itemDomain;
	private final ContextualCriteria context;
	private final ItemCategory itemCategory;
	
	public RuleTuple(ItemDomain itemDomain, ContextualCriteria context,
			ItemCategory itemCategory) {

		this.itemDomain = itemDomain;
		this.context = context;
		this.itemCategory = itemCategory;
	}
	
	public ContextualCriteria getContext() {
		return context;
	}
	
	public ItemCategory getItemCategory() {
		return itemCategory;
	}
	
	public ItemDomain getItemDomain() {
		return itemDomain;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RuleTuple){
			RuleTuple test = (RuleTuple) obj;
			
			if(this.getItemDomain().equals(test.getItemDomain()) && 
					this.getContext().equals(test.getContext()) &&
					this.getItemCategory().equals(test.getItemCategory())){
				return true;
			}
			
		}
		return false;
	}
	
	@Override
	public int hashCode() {

		
		HashCodeBuilder hcb = new HashCodeBuilder(17, 31);
		
		//for(AbstractContextualAttribute contextualAttr : this.contextualAttributes){
			hcb.append(this.getItemDomain().getCode());
			hcb.append(this.getItemCategory().getCode());
			hcb.append(this.getContext().hashCode());
		//}
		
		/*return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
	            // if deriving: appendSuper(super.hashCode()).
	            append(dayType).
	            append(periodOfDay).
	            toHashCode();*/
		return hcb.toHashCode();
	}
	
	@Override
	public String toString() {
		
		/*String dayType = this.getDayTypeContextualAttribute() != null ? this.getDayTypeContextualAttribute().name() : "null";
		String periodOfDay = this.getPeriodOfDayContextualAttribute() != null ? this.getPeriodOfDayContextualAttribute().name() : "null";*/
		
		StringBuffer contextualCriteriaString = new StringBuffer();
		
		//for(AbstractContextualAttribute contextualAtt : this.contextualAttributes){
			contextualCriteriaString.append(this.getItemDomain().name());
			contextualCriteriaString.append(", ");
			contextualCriteriaString.append(this.getContext());
			contextualCriteriaString.append(", ");
			contextualCriteriaString.append(this.getItemCategory().name());
		//}
		
		return contextualCriteriaString.toString();
	}
}
