package com.WebApplicationTwitter.repositories;

import com.WebApplicationTwitter.models.ElasticSearchModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ElasticSearchRepo extends ElasticsearchRepository<ElasticSearchModel, String> {
}