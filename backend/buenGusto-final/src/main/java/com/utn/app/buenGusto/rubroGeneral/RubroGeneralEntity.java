package com.utn.app.buenGusto.rubroGeneral;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.app.buenGusto.common.CommonEntity;

@Entity
@Table(name = "rubro_general")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RubroGeneralEntity extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 4801679657904614999L;
	
	private String denominacion;

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
