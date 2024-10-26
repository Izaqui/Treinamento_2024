package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DepositoDTO implements Serializable{

	private String agencia;
	private String numeroConta;
	private double valor;

	public int getValor() {
        return 0;
    }

	public String getAgencia() {
        return "";
    }

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
