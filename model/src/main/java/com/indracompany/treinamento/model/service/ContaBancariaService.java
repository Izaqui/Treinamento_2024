package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void depositar(DepositoDTO dto) {
        if (dto.getValor() <= 0) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_VALOR_INVALIDO);
        }

        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta())
                .orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INEXISTENTE));

        conta.setSaldo(conta.getSaldo() + dto.getValor());
        super.salvar(conta);
    }

    @Transactional(rollbackFor = Exception.class)
    public void sacar(SaqueDTO dto) {
        if (dto.getValor() <= 0) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_VALOR_INVALIDO);
        }

        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(dto.getAgencia(), dto.getNumeroConta())
                .orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INEXISTENTE));

        if (conta.getSaldo() < dto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
        }

        conta.setSaldo(conta.getSaldo() - dto.getValor());
        super.salvar(conta);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferir(TransferenciaBancariaDTO transferenciaDTO) {
        if (transferenciaDTO.getValor() <= 0) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_VALOR_INVALIDO);
        }

        SaqueDTO saqueDTO = new SaqueDTO();
        saqueDTO.setAgencia(transferenciaDTO.getAgenciaOrigem());
        saqueDTO.setNumeroConta(transferenciaDTO.getNumeroContaOrigem());
        saqueDTO.setValor(transferenciaDTO.getValor());
        this.sacar(saqueDTO);

        DepositoDTO depositoDTO = new DepositoDTO();
        depositoDTO.setAgencia(transferenciaDTO.getAgenciaDestino());
        depositoDTO.setNumeroConta(transferenciaDTO.getNumeroContaDestino());
        depositoDTO.setValor(transferenciaDTO.getValor());
        this.depositar(depositoDTO);
    }
}