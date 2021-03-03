package com.lawencon.klinik.repo;
/**
 * 
 * @author WILLIAM
 *
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lawencon.klinik.model.InvoicesHdr;

public interface InvoiceRepo extends JpaRepository<InvoicesHdr, Long>{
	
	@Modifying
	@Query(value = " UPDATE InvoicesHdr SET sickFee = (SELECT SUM(m.price) from MedicinesDtl as dm INNER JOIN dm.idMedicine as m INNER JOIN dm.idDtlDisease as dd INNER JOIN dd.idInvoice as i WHERE i.invoiceCode = ?1 group by i.invoiceCode) where invoiceCode = ?1")
	void updateTotalCost(String invoiceCode);
	
	InvoicesHdr findByInvoiceCode(String invoiceCode); 
	
	List<InvoicesHdr> findAllByOrderByCheckUpDateDesc();
	
}
