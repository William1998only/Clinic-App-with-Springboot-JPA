package com.lawencon.klinik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.klinik.model.InvoicesHdr;
import com.lawencon.klinik.service.InvoicesHdrService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/invoice")
public class InvoicesHdrController {

	@Autowired
	private InvoicesHdrService invoicesHdrService;

	@GetMapping("/getall")
	public ResponseEntity<?> getAllInvoice() {
		List<InvoicesHdr> invoiceList = new ArrayList<>();
		try {
			invoiceList = invoicesHdrService.getInvoiceHdr();
			return new ResponseEntity<>(invoiceList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getInvoiceByCode(@PathVariable("kode") String kode) {
		InvoicesHdr invoice = new InvoicesHdr();
		try {
			invoice = invoicesHdrService.getInvoiceHdrByCode(kode);
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		
	}

	@PostMapping
	public ResponseEntity<?> insertHdr(@RequestBody String body) {
		try {
			InvoicesHdr invoice = new ObjectMapper().readValue(body, InvoicesHdr.class);
			invoicesHdrService.insertHdr(invoice.getIdUser().getId(), invoice.getIdPatient().getPatientCode());
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Input gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
