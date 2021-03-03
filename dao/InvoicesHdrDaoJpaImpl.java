package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.InvoicesHdr;
import com.lawencon.klinik.repo.InvoiceRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="invoicesjpa")
public class InvoicesHdrDaoJpaImpl extends BaseDao implements InvoicesHdrDao {
	
	@Autowired 
	private InvoiceRepo invoiceRepo; 
	
	@Override
	public void insertHdr(InvoicesHdr invoicesHdr) throws Exception {
			invoiceRepo.save(invoicesHdr);
	}

	@Override
	public List<InvoicesHdr> getInvoicesHdr() throws Exception {
		return invoiceRepo.findAllByOrderByCheckUpDateDesc();
	}

	@Override
	public void updateTotalCost(String invoiceCode) throws SQLException {
		invoiceRepo.updateTotalCost(invoiceCode);
	}

	@Override
	public InvoicesHdr getInvoicesHdrByCode(String invoiceCode) { 
		return invoiceRepo.findByInvoiceCode(invoiceCode);
	}
}
