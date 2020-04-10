package com.utn.app.buenGusto.articuloManufacturado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.utn.app.buenGusto.commons.commonEntity;
import com.utn.app.buenGusto.detallePedido.detallePedidoEntity;
import com.utn.app.buenGusto.rubroGeneral.rubroGeneralEntity;

@Entity
@Table(name = "articulo_manufacturado")
public class articuloManufacturadoEntity extends commonEntity implements Serializable {

	private static final long serialVersionUID = -8356649232468048872L;

	@NotEmpty
	private int tiempoEstimadoCocina;
	
	@NotEmpty
	private String denominacion;
	
	@NotEmpty
	private double precioVenta;
	
	// relacion OneToMany bidireccional ArticuloManufacturado -- detallePedido
	 @OneToMany(mappedBy = "articuloManufacturado",cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<detallePedidoEntity> detallePedido = new ArrayList<>();
	    
		// relacion ManyToOne bidireccional detallePedido --- ArticuloManufacturado
	    @ManyToOne(fetch = FetchType.EAGER) //trae atributos y sus correspondientes relaciones
	    @JoinColumn(name= "articuloManufacturado_fk_rubroGeneral")
	    private rubroGeneralEntity rubroGeneral;    
	    

	public int getTiempoEstimadoCocina() {
		return tiempoEstimadoCocina;
	}

	public void setTiempoEstimadoCocina(int tiempoEstimadoCocina) {
		this.tiempoEstimadoCocina = tiempoEstimadoCocina;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	
	
}
