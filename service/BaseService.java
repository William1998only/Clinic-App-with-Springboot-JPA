package com.lawencon.klinik.service;

import org.springframework.transaction.support.TransactionTemplate;

/**
 * 
 * @author WILLIAM
 *
 */
public class BaseService {
	private TransactionTemplate transactionTemplate;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	
}
