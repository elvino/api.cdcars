package com.cdcars.infra.dataset;

import java.util.HashMap;
import java.util.HashSet;

import com.cdcars.core.recommender.scopes.contextual.ContextualCriteria;
import com.cdcars.core.recommender.scopes.item.ItemCategory;
import com.cdcars.core.recommender.scopes.item.ItemDomain;
import com.cdcars.core.recommender.scopes.user.RuleTuple;

public class GenreRulesByContextMap {
	private static GenreRulesByContextMap INSTANCE;
	
	private static HashMap<RuleTuple,HashSet<RuleTuple>> genreRulesByContextMap;
	
	private GenreRulesByContextMap() {
		genreRulesByContextMap = new HashMap<RuleTuple,HashSet<RuleTuple>>();
	}
	
	public static synchronized GenreRulesByContextMap getInstance() {
		if (INSTANCE == null) {
			return new GenreRulesByContextMap();
		}
		return INSTANCE;
	}

	
	public HashMap<RuleTuple,HashSet<RuleTuple>> getGenreRulesByContextMap() {
		return genreRulesByContextMap;
	}
	
	public void setGenreRulesByContextMap(RuleTuple condition, HashSet<RuleTuple> inferred){
		genreRulesByContextMap.put(condition, inferred);
	}

}
