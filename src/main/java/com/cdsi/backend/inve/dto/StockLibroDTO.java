package com.cdsi.backend.inve.dto;

public class StockLibroDTO {
	
	private String codigo;
	private String descripcion;
	private String medida;
	private String marca;
	private Integer precio;
	private Integer stock;
	private Integer compromiso;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getCompromiso() {
		return compromiso;
	}
	public void setCompromiso(Integer compromiso) {
		this.compromiso = compromiso;
	}
	
	
}
