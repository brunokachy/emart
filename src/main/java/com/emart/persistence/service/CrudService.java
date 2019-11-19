package com.emart.persistence.service;

/**
 * @author Bruno Okafor 2019-11-19
 */
public interface CrudService<T> {

	T getRecordById(Long id);

	T saveRecord(T record);
}
