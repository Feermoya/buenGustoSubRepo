package com.utn.app.buenGusto.configuracionGeneral;
 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.utn.app.buenGusto.commons.commonController;
import com.utn.app.buenGusto.commons.commonIService; 

@RestController
@RequestMapping(path = "api/configuracionGenerealNDEAAA")
public class configuracionGeneralController extends commonController<configuracionGeneralDTO>{
	private configuracionGeneralService servicio;
	
	public configuracionGeneralController(commonIService <configuracionGeneralDTO> servicio, configuracionGeneralService cServicio) {
		super(servicio);
		this.servicio=cServicio;
	}
}
