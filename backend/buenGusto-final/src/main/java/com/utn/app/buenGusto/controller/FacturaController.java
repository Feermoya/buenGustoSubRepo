package com.utn.app.buenGusto.controller;
 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.utn.app.buenGusto.DTO.FacturaDTO;
import com.utn.app.buenGusto.services.CommonIService;
import com.utn.app.buenGusto.services.FacturaService; 

@RestController
@RequestMapping(path = "api/factura")
public class FacturaController extends CommonController<FacturaDTO>{
	private FacturaService service;

	public FacturaController(CommonIService<FacturaDTO> service, FacturaService fservice) {
		super(service);
		this.service=fservice;
	}
	
	
}