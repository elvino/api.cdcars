package com.cdcars.infra.dataset;

import java.util.HashMap;

import com.cdcars.core.recommender.scopes.contextual.ContextualCriteria;
import com.cdcars.core.recommender.scopes.item.ItemCategory;
import com.cdcars.core.recommender.scopes.item.ItemDomain;

public class UserCategoriesPrefsInContexts {
	private static UserCategoriesPrefsInContexts INSTANCE;
	
	//private static HashMap<Long,HashMap<ContextualCriteria,HashMap<ItemCategory,Integer>>> userPrefs;
	private static HashMap<Long,HashMap<ItemDomain,HashMap<ContextualCriteria,HashMap<ItemCategory,Integer>>>> userPrefsWithGoodRatingsOnly;
	
	private UserCategoriesPrefsInContexts() {
		//userPrefs = new HashMap<Long, HashMap<ContextualCriteria,HashMap<ItemCategory,Integer>>>();
		userPrefsWithGoodRatingsOnly = new HashMap<Long,HashMap<ItemDomain,HashMap<ContextualCriteria,HashMap<ItemCategory,Integer>>>>();
	}
	
	public static synchronized UserCategoriesPrefsInContexts getInstance() {
		if (INSTANCE == null) {
			return new UserCategoriesPrefsInContexts();
		}
		return INSTANCE;
	}
	
	/*public HashMap<Long, HashMap<ContextualCriteria, HashMap<ItemCategory, Integer>>> getUserPrefs() {
		return userPrefs;
	}
	
	public void setUserPrefsToUser(Long userID, HashMap<ContextualCriteria, HashMap<ItemCategory, Integer>> preferences){
		userPrefs.put(userID, preferences);
	}*/
	
	public HashMap<Long,HashMap<ItemDomain,HashMap<ContextualCriteria,HashMap<ItemCategory,Integer>>>> getUserPrefsWithGoodRatingsOnly() {
		return userPrefsWithGoodRatingsOnly;
	}
	
	public void setUserPrefsWithGoodRatingsOnlyToUser(Long userID, HashMap<ItemDomain,HashMap<ContextualCriteria,HashMap<ItemCategory,Integer>>> preferences){
		userPrefsWithGoodRatingsOnly.put(userID, preferences);
	}

}
