package com.brandon.doublecolorball.mapper;

import com.brandon.doublecolorball.domain.Function;
import com.brandon.doublecolorball.domain.RecommendationGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "recommendationGroupMapper")
public interface RecommendationGroupMapper {
    public void insertRecommendationGroup(RecommendationGroup recommendationGroup);
}
