package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.InvoicesHdr;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="invoiceshibernate")
public class InvoicesHdrDaoHibernateImpl extends BaseDao implements InvoicesHdrDao {
	
	@Override
	public void insertHdr(InvoicesHdr invoicesHdr) throws Exception {
			em.persist(invoicesHdr);
	}

	@Override
	public List<InvoicesHdr> getInvoicesHdr() throws Exception {
		List<InvoicesHdr> listResult = em.createQuery("from InvoicesHdr", InvoicesHdr.class).getResultList();
		return listResult;
	}

	@Override
	public void updateTotalCost(String invoiceCode) throws SQLException {
		em.createQuery(bBuilder("UPDATE InvoicesHdr SET sickFee = "
				, "(SELECT SUM(m.price) from MedicinesDtl as dm ",
				"INNER JOIN dm.idMedicine as m ",
				"INNER JOIN dm.idDtlDisease as dd ",
				"INNER JOIN dd.idInvoice as i ",
				"WHERE i.invoiceCode = ?1 ",
				"GROUP BY i.invoiceCode) WHERE invoiceCode = ?2").toString())
			.setParameter(1, invoiceCode)
			.setParameter(2, invoiceCode)
			.executeUpdate();
	}

	@Override
	public InvoicesHdr getInvoicesHdrByCode(String invoiceCode) {
		List<InvoicesHdr> listResult = em.createQuery("FROM InvoicesHdr WHERE invoiceCode = ?1 ", InvoicesHdr.class).setParameter(1, invoiceCode)
					.getResultList(); 
		return listResult.size() > 0 ? listResult.get(0) : null;
	}
}
