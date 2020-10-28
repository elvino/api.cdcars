package com.cdcars.core.recommender.filters;

import com.cdcars.api.models.ResultRecommend;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.model.AbstractDataModel;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import com.cdcars.core.recommender.algorithms.ContextualRecommenderBuilder;
import com.cdcars.core.recommender.algorithms.PostFilteringContextualBuildRecommender;
import com.cdcars.core.recommender.algorithms.PreFilteringContextualBuildRecommender;
import com.cdcars.core.recommender.algorithms.PreFilteringContextualBuildRecommenderByron;
import com.cdcars.core.recommender.algorithms.RecommenderBuilderUserBasedNearestNeighbor;
//import br.cin.tbookmarks.recommender.algorithms.RecommenderBuilderUserBasedNearestNeighborCremonesi;
import com.cdcars.core.recommender.algorithms.RecommenderBuilderUserBasedNearestNeighborCremonesiRicardo;
import com.cdcars.core.recommender.algorithms.RecommenderBuilderUserBasedTracer;
import com.cdcars.core.recommender.algorithms.Recommenders;
import com.cdcars.infra.dataset.AbstractDataset;
import com.cdcars.infra.dataset.AmazonCrossDataset;
import com.cdcars.core.recommender.scopes.contextual.AbstractContextualAttribute;
import com.cdcars.core.recommender.scopes.contextual.CompanionContextualAttribute;
import com.cdcars.core.recommender.scopes.contextual.ContextualCriteria;
import com.cdcars.core.recommender.scopes.contextual.DayContextualAttribute;
import com.cdcars.core.recommender.scopes.contextual.DayTypeContextualAttribute;
import com.cdcars.core.recommender.scopes.contextual.PeriodOfDayContextualAttribute;
import com.cdcars.core.recommender.scopes.item.ItemDomain;
import com.cdcars.core.recommender.similarity.ItemDomainRescorer;

public class SampleRecommender {
    
        public static List<ResultRecommend> recommendByAlgorithm(long userId, int numberOfRecommendations,
			ArrayList<ContextualRecommenderBuilder> rbs, AbstractDataset dataset, IDRescorer idrescorer, ContextualCriteria contextualCriteria) throws TasteException {

	//public static String recommendByAlgorithm(long userId, int numberOfRecommendations,
	//		ArrayList<ContextualRecommenderBuilder> rbs, AbstractDataset dataset, IDRescorer idrescorer, ContextualCriteria contextualCriteria) throws TasteException {
		
		StringBuffer sb = new StringBuffer();
                StringBuffer sbRecom = new StringBuffer();
                
                List<ResultRecommend> res = new ArrayList<ResultRecommend>();
                ResultRecommend rec = null;
		
		for(ContextualRecommenderBuilder rb : rbs){
			List<RecommendedItem> recommendedItems;
                        rec = new ResultRecommend();
			/*if(rb instanceof PreFilteringContextualBuildRecommender){
				
				DataModel filteredDM = ((PreFilteringContextualBuildRecommender) rb).preFilterDataModel(dataset.getModel(),contextualCriteria);
				
				recommendedItems = rb.buildRecommender(filteredDM).recommend(
						userId, numberOfRecommendations,idrescorer);
			}else if(rb instanceof PostFilteringContextualBuildRecommender){
				((PostFilteringContextualBuildRecommender) rb).setContextAndDataset(contextualCriteria, dataset);
				recommendedItems = rb.buildRecommender(dataset.getModel()).recommend(
						userId, numberOfRecommendations,idrescorer);
			}else{*/
				recommendedItems = rb.buildRecommender(dataset.getModel(), contextualCriteria, idrescorer, dataset).recommend(
					userId, numberOfRecommendations,idrescorer);
			//}
				
			StringBuffer sbAux = new StringBuffer();
                        StringBuffer sbAuxRecom = new StringBuffer();
                        StringBuffer sbAuxAlgor = new StringBuffer();
			
			sbAux.append("\nAlgorithm:"+rb+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
                        sbAuxAlgor.append("\nAlgorithm:"+rb+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
			int position = 1;
                        
                        rec.setAlgorithm(sbAuxAlgor.toString());
                        
			for (RecommendedItem recommendation : recommendedItems) {				
				sbAux.append(position + ": " + recommendation.getItemID()
						+ " - "
						+ dataset.getItemInformationByID(recommendation.getItemID()).getName()
						+ " - " + recommendation.getValue()
						+ " - " + dataset.getItemInformationByID(recommendation.getItemID()).getCategories()
						+ " - " + dataset.getItemInformationByID(recommendation.getItemID()).getItemDomain()+"\n");
				
                                sbAuxRecom.append(position + ": " + recommendation.getItemID()
						+ " - "
						+ dataset.getItemInformationByID(recommendation.getItemID()).getName()
						+ " - " + recommendation.getValue()
						+ " - " + dataset.getItemInformationByID(recommendation.getItemID()).getCategories()
						+ " - " + dataset.getItemInformationByID(recommendation.getItemID()).getItemDomain()+"\n");
                                
                                position++;
			}
			System.out.println(sbAux.toString()+"\n");
			sb.append(sbAux);
                        sbRecom.append(sbAuxRecom);
		}
                rec.setRecommendation(sbRecom.toString());
		//return sb.toString();
                res.add(rec);
                return res;
	}


	//public static void main(String[] args) {
        public static List<ResultRecommend> resultByRecommender() {
		
/* TRACER Configuration */
		
		double userClusterTransfer = 15.0;
		double itemClusterTransfer = 15.0;
		double trainingIterations = 15;
		double alpha = 1.0;
		double beta = 0.0001;	
		
		ArrayList<ContextualRecommenderBuilder> recommenders = new ArrayList<ContextualRecommenderBuilder>();
//	    recommenders.add(new RecommenderBuilderUserBasedNearestNeighborCremonesiRicardo(475, EuclideanDistanceSimilarity.class));
		//recommenders.add(new PreFilteringContextualBuildRecommenderByron(new RecommenderBuilderUserBasedNearestNeighborCremonesiRicardo(475, EuclideanDistanceSimilarity.class)));
		
		//PostFilteringStrategyRecommendation pfStrategy4 = new PostFilteringStrategyRecommendation(PostFilteringStrategyRecommendation.PossibleFilteringStrategies.AT_LEAST_MEDIA_OF_OCCURRENCIES,true,5.0f,(float)1/2,0.5,0.01);
		//recommenders.add(new PostFilteringContextualBuildRecommender(new RecommenderBuilderUserBasedNearestNeighborCremonesiRicardo(225, EuclideanDistanceSimilarity.class),pfStrategy4));
		//recommenders.add(new PostFilteringContextualBuilderRecommenderTracer(new RecommenderBuilderUserBasedTracer(userClusterTransfer , itemClusterTransfer,  alpha, beta, trainingIterations,1.0),pfStrategy4));
		
		
		PostFilteringStrategyRecommendation pfStrategy4 = new PostFilteringStrategyRecommendation(PostFilteringStrategyRecommendation.PossibleFilteringStrategies.AT_LEAST_MEDIA_OF_OCCURRENCIES,true,4.0f,(float)2/3,0.5,0.01);
		recommenders.add(new PostFilteringContextualBuildRecommender(new RecommenderBuilderUserBasedNearestNeighborCremonesiRicardo(475, EuclideanDistanceSimilarity.class),pfStrategy4));
		
		HashSet<Class<? extends AbstractContextualAttribute>> testedContextualAttributes = new HashSet<Class<? extends AbstractContextualAttribute>>();
//		testedContextualAttributes.add(LocationCityContextualAttribute.class);
		
		testedContextualAttributes.add(DayContextualAttribute.class);
		
		//testedContextualAttributes.add(CompanionContextualAttribute.class);
		
		AbstractDataset dataset = AmazonCrossDataset.getInstance();
		//dataset = AmazonCrossDataset.getInstance(true, ItemDomain.MUSIC,"/datasets/Books_MUSIC/", "FULL-overlapping-database.dat");
		
		ItemDomain sourceDomain = ItemDomain.BOOK;
		
		HashSet<ItemDomain> domainsFilter = new HashSet<ItemDomain>();
		domainsFilter.add(sourceDomain);
		domainsFilter.add(ItemDomain.MOVIE);
	
		IDRescorer idrescorer = new ItemDomainRescorer(null,domainsFilter, dataset);
		
		//ContextualCriteria criteria = new ContextualCriteria(null,null,null,null,null,CompanionContextualAttribute.ACCOMPANIED,CompanionContextualAttribute.COUPLE,null);
		ContextualCriteria criteria = new ContextualCriteria(null,DayContextualAttribute.SUNDAY,null,null,null,null,null,null);
		ContextualCriteria criteria2 = new ContextualCriteria(null,null,null,null,null,null,null,null);
		
		try {
			var xx = recommendByAlgorithm(2, 20, recommenders, dataset, idrescorer, criteria);
			return xx;
                        //return recommendByAlgorithm(2, 20, recommenders, dataset, idrescorer, criteria);
			//recommendByAlgorithm(2, 20, recommenders, dataset, idrescorer, criteria2);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                        return null;
		}
		
		/*HashSet<ItemDomain> domainsDataset = new HashSet<ItemDomain>();
		domainsDataset.add(ItemDomain.BOOK);
		domainsDataset.add(ItemDomain.MOVIE);
		//domainsFilter.add(ItemDomain.BOOK);
		
		AbstractDataset absDataset = AmazonCrossDataset.getInstance();	
		
		HashSet<ItemDomain> domainsFilter = new HashSet<ItemDomain>();
		domainsFilter.add(ItemDomain.BOOK);
		
		IDRescorer idrescorer = new ItemDomainRescorer(null,domainsFilter, absDataset);
		
		//ContextualCriteria criteria = new ContextualCriteria(DayTypeContextualAttribute.WEEKEND,PeriodOfDayContextualAttribute.DAWN);
		ContextualCriteria criteria = new ContextualCriteria(DayTypeContextualAttribute.WEEKEND,null);
		
		SampleRecommender sr = new SampleRecommender(absDataset, idrescorer,criteria);
		

		ArrayList<RecommenderBuilder> list = sr.recommenders
				.getRecommenderBuilders();

		try {
			int userId =41; //6041

			for (RecommenderBuilder recommenderBuilder : list) {
				System.out.println("\n"+recommenderBuilder.getClass()
						.getSimpleName() + ">>>>>>>>>>>>>>>");
				sr.recommendByAlgorithm(userId, sr.maxOfRecommendedItems,
						recommenderBuilder);
				sr.printInfoRecommendations();
			}
			System.out.println("FINISHED!!");

		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
	}

}
