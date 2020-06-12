package com.utn.app.buenGusto.localidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utn.app.buenGusto.provincia.ProvinciaEntity;

@Entity
@Table(name = "localidad")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LocalidadEntity implements Serializable {

	private static final long serialVersionUID = -3414135937301976153L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	private String nombre;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "provincia_id")
	private ProvinciaEntity provincia;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ProvinciaEntity getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaEntity provincia) {
		this.provincia = provincia;
	}

}
