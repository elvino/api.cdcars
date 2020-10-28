package com.cdcars.infra.dataset;

import java.io.IOException;
import java.util.ArrayList;

import com.cdcars.core.recommender.scopes.item.ItemDatasetInformation;


public abstract class AbstractCrossDomainDataset extends AbstractDataset {
	
	protected ArrayList<AbstractDataset> datasets;
	
	public AbstractCrossDomainDataset() {
		datasets = new ArrayList<AbstractDataset>();
	}
	
	protected void initializeCrossDomainDataset(){

		try {
			//initializeDataModel();
			initializeDBInfo();
		} /*catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	protected void initializeDBInfo() {
		this.itemDatasetInformation = new ItemDatasetInformation();
		for (AbstractDataset dataset : datasets) {
			this.itemDatasetInformation.getItens().addAll(
					dataset.getItemDatasetInformation().getItens());
		}
	}

}
