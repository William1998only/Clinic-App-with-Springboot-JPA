package com.lawencon.klinik.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.InvoicesHdr;

/**
 * 
 * @author WILLIAM
 *
 */
public interface InvoicesHdrService {
	List<InvoicesHdr> getInvoiceHdr() throws Exception;

	void updateTotalCost(String invoiceCode) throws SQLException;

	InvoicesHdr getInvoiceHdrByCode(String invoiceCode) throws Exception;

	void insertHdr(Long id, String patientCode) throws Exception;
}
