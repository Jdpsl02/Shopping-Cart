package com.eshoppingzone.ewallet.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eshoppingzone.ewallet.models.Statement;

@Repository
public interface StatementRepository extends MongoRepository<Statement, Integer>{
	
	List<Statement> findByStatementId(int profileId);

}