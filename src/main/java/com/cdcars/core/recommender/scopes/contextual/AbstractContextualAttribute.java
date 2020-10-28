package com.cdcars.core.recommender.scopes.contextual;

import java.util.List;

public interface AbstractContextualAttribute {
		
	
	public long getCode();
	public String name();
	//public AbstractContextualAttribute[] values();
	public List<AbstractContextualAttribute> valuesForTest();
}
