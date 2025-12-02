package org.example.service.falha;

import org.example.model.Equipamento;
import org.example.model.Falha;
import org.example.repository.falha.FalhaRepository;
import org.example.service.equipamento.EquipamentoService;

import java.sql.SQLException;
import java.util.List;

public class FalhaServiceImpl implements FalhaService{

    private final FalhaRepository repository;
    private EquipamentoService equipamentoService;

    public FalhaServiceImpl(FalhaRepository repository, EquipamentoService equipamentoService) {
        this.repository = repository;
        this.equipamentoService = equipamentoService;
    }

    @Override
    public Falha registrarNovaFalha(Falha falha, String resolvida) throws SQLException{

        Equipamento equipamento = achaEquipamentoOuErro(falha.getEquipamentoId());

        falha.setStatus("ABERTA");

        if (falha.getCriticidade().equals("CRITICA")) {

            equipamentoService.alterarStatusEquipamento(equipamento, "EM_MANUTENCAO");
        }

        return repository.save(falha);
    }

    public Equipamento achaEquipamentoOuErro(long id) throws SQLException {

        try {

            return equipamentoService.buscarEquipamentoPorId(id);
        } catch (RuntimeException ex) {

            throw new IllegalArgumentException("Equipamento não encontrado!");
        }
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {

        return repository.encontrarFalha();
    }

    @Override
    public Falha buscarFalhaPorId(Long id) throws SQLException {
        return null;
    }


    public Falha verificarFalha (long id) throws SQLException{

        return null;
    }
}
