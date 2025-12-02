package org.example.service.falha;

import org.example.model.Falha;

import java.sql.SQLException;
import java.util.List;

public interface FalhaService {

    Falha registrarNovaFalha(Falha falha, String resolvida) throws SQLException;

    List<Falha> buscarFalhasCriticasAbertas() throws SQLException;

    Falha buscarFalhaPorId (Long id) throws SQLException;
}
