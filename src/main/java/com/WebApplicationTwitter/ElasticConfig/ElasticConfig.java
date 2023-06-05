package com.WebApplicationTwitter.ElasticConfig;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Configuration;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.WebApplicationTwitter.repositories"})
public class ElasticConfig {
    RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
    ElasticsearchTransport elasticsearchTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());
    ElasticsearchClient elasticsearchClient = new ElasticsearchClient(elasticsearchTransport);
}