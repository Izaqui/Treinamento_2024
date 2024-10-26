package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SaqueDTO implements Serializable{

	private String agencia;
	private String numeroConta;
	private double valor;

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
