package com.utn.app.buenGusto.DTO;

import java.util.Date;

public class FacturaDTO extends CommonDTO {

	private static final long serialVersionUID = 1L;

	private int número;
	private Date fecha;
	private double montoDescuento;
	private double total;
	private String formaPago;
	private String nroTarjeta;

	public int getNúmero() {
		return número;
	}

	public void setNúmero(int número) {
		this.número = número;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
}
