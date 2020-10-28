package com.cdcars.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cdcars.api.models.Recommend;

public interface IRecommendRepository extends JpaRepository<Recommend, Long> {

}
