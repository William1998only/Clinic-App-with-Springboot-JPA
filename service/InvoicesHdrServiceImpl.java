package com.lawencon.klinik.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.InvoicesHdrDao;
import com.lawencon.klinik.model.InvoicesHdr;
import com.lawencon.klinik.model.Patients;
import com.lawencon.klinik.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class InvoicesHdrServiceImpl extends BaseService implements InvoicesHdrService {

	@Autowired
	@Qualifier(value="invoicesjpa")
	private InvoicesHdrDao invoicesHdrDao;

	@Autowired
	private PatientsService patientsService;

	@Override
	public void insertHdr(Long idUser, String patientCode) throws Exception {
		List<InvoicesHdr> invoiceList = new ArrayList<>();
		invoiceList = invoicesHdrDao.getInvoicesHdr();
		InvoicesHdr invoicesHdr = new InvoicesHdr();
		invoicesHdr.setQueueNumber(invoiceList.size() + 1);
		Users user = new Users();
		user.setId(idUser);
		invoicesHdr.setIdUser(user);
		Patients patient = new Patients();
		patient = patientsService.getPatientsByCode(patientCode);
		invoicesHdr.setIdPatient(patient);
		invoicesHdr.setCheckUpDate(java.time.LocalDate.now());
		invoicesHdr.setInvoiceCode("tr" + (invoiceList.size() + 1));
		invoicesHdr.setSickFee(new BigDecimal(0));
		invoicesHdrDao.insertHdr(invoicesHdr);

	}

	@Override
	public List<InvoicesHdr> getInvoiceHdr() throws Exception {
		return invoicesHdrDao.getInvoicesHdr();
	}

	@Override
	public void updateTotalCost(String invoiceCode) throws SQLException {
		invoicesHdrDao.updateTotalCost(invoiceCode);

	}

	@Override
	public InvoicesHdr getInvoiceHdrByCode(String invoiceCode) throws Exception{
		InvoicesHdr data = invoicesHdrDao.getInvoicesHdrByCode(invoiceCode);
		if(null == data) {
			throw new Exception("Data registrasi tidak ada");
		}else {
			return data;
		}
	}
}
