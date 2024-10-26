package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TransferenciaBancariaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String agenciaOrigem;
	private String numeroContaOrigem;
	private String agenciaDestino;
	private String numeroContaDestino;
	private double valor;

	public String getAgenciaOrigem() {
		return agenciaOrigem;
	}

	public void setAgenciaOrigem(String agenciaOrigem) {
		this.agenciaOrigem = agenciaOrigem;
	}

	public String getNumeroContaOrigem() {
		return numeroContaOrigem;
	}

	public void setNumeroContaOrigem(String numeroContaOrigem) {
		this.numeroContaOrigem = numeroContaOrigem;
	}

	public String getAgenciaDestino() {
		return agenciaDestino;
	}

	public void setAgenciaDestino(String agenciaDestino) {
		this.agenciaDestino = agenciaDestino;
	}

	public String getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(String numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
