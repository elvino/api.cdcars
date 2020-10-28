package com.cdcars.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import java.util.HashSet;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdcars.api.models.Recommend;
import com.cdcars.api.models.ResultRecommend;
import com.cdcars.api.repository.IRecommendRepository;
import com.cdcars.core.recommender.filters.SampleRecommender;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



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
import com.cdcars.core.recommender.filters.PostFilteringStrategyRecommendation;
import static com.cdcars.core.recommender.filters.SampleRecommender.recommendByAlgorithm;
import static com.cdcars.core.recommender.filters.SampleRecommender.resultByRecommender;
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





@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST CD-CARS")
public class RecommendResource {
    
    
    
    
    //public String Teste(){
        //String aa = "";
	
        //return teste();
        //return null;
    //}
    
    
    
    
    
    
    
    
    
    
    
    
    
	@Autowired
	IRecommendRepository recommendRepository;
	
	@ApiOperation(value="Retorna uma lista de Recomendacao")
	@GetMapping("/recommend")
	public List<ResultRecommend> listRecommend(){
            //String aa = null;
            var res = resultByRecommender();
            
		//List<ResultRecommend> c = new ArrayList<ResultRecommend>();//recommendRepository.findAll();
		//Recommend d = new Recommend();
		//d.setId(1);
		//d.setName(res);
		//c.add(d);
		return res;
	}
	
	@ApiOperation(value="Retorna uma Recomendacao")
	@GetMapping("/recommend/{id}")
	public Optional<Recommend> listUnityRecommend(@PathVariable(value="id") long id){
		return recommendRepository.findById(id);
	}
	
	@ApiOperation(value="Salva uma Recommend")
	@PostMapping("/recommend")
	public Recommend saveRecommend(@RequestBody @Valid Recommend recommend) {
		return recommendRepository.save(recommend);
	}
	
	@ApiOperation(value="Deleta uma Recommend")
	@DeleteMapping("/recommend")
	public void deleteRecommend(@RequestBody @Valid Recommend recommend) {
		recommendRepository.delete(recommend);
	}
	
	@ApiOperation(value="Atualiza uma Recommend")
	@PutMapping("/recommend")
	public Recommend updateRecommend(@RequestBody @Valid Recommend recommend) {
		return recommendRepository.save(recommend);
	}
}
