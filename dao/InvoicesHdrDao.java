package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.InvoicesHdr;

/**
 * 
 * @author WILLIAM
 *
 */
public interface InvoicesHdrDao {
	
	List<InvoicesHdr> getInvoicesHdr() throws Exception;
	
	void insertHdr(InvoicesHdr invoicesHdr) throws Exception;

	void updateTotalCost(String invoiceCode) throws SQLException;

	InvoicesHdr getInvoicesHdrByCode(String invoiceCode);
}
