package com.cdcars.api.models;

public class ResultRecommend {
    private String Algorithm;
    private String Strategy;
    private String Recommendation;

    public String getAlgorithm() {
        return Algorithm;
    }

    public void setAlgorithm(String Algorithm) {
        this.Algorithm = Algorithm;
    }

    public String getStrategy() {
        return Strategy;
    }

    public void setStrategy(String Strategy) {
        this.Strategy = Strategy;
    }

    public String getRecommendation() {
        return Recommendation;
    }

    public void setRecommendation(String Recommendation) {
        this.Recommendation = Recommendation;
    }
    
    public ResultRecommend(){}
}
