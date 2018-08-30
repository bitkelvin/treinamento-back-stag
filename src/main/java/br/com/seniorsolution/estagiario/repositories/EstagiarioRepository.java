package br.com.seniorsolution.estagiario.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.seniorsolution.estagiario.model.entities.EstagiarioEntity;

public interface EstagiarioRepository extends MongoRepository<EstagiarioEntity, String>, QueryDslPredicateExecutor<EstagiarioEntity> {

}