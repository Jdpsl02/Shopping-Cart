package com.eshoppingzone.ewallet.models;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document("Statement")
public class Statement {
	
	@Id
	private int statementId;
	private TransactionType transactionType;
	private double amount;

	private LocalDateTime dateTime;
	
	private Ewallet eWallet;
	
	//Default Constructor
	public Statement() {}

	
	

	//Parameterized Constructor without statementId
	public Statement(int statementId,TransactionType transactionType, double amount, LocalDateTime dateTime,
			 Ewallet eWallet) {
		super();
		this.statementId=statementId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.dateTime = dateTime;
		this.eWallet = eWallet;
	}






}