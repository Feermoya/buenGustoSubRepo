package com.utn.app.buenGusto.common;

import java.util.List;

public interface CommonIService<DTO> {
	List<DTO> findAll(int page, int size) throws Exception;

	DTO findById(long id) throws Exception;

	DTO save(DTO dto) throws Exception;

	DTO update(long id, DTO dto) throws Exception;

	int countPages(int size) throws Exception;

	boolean delete(long id) throws Exception;
}