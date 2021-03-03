package com.lawencon.klinik.repo;
/**
 * 
 * @author WILLIAM
 *
 */
public abstract class BaseRepo {
	
	protected StringBuilder bBuilder(String... datas) {
		StringBuilder b = new StringBuilder();
		for (String d : datas) {
			b.append(d);
		}
		return b;
	}
	
}
