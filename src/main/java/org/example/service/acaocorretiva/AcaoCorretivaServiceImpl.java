package org.example.service.acaocorretiva;

import org.example.model.AcaoCorretiva;

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{

    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {


      try {


      }catch (RuntimeException ex){
        throw  new IllegalArgumentException("A Falha é Invalida");
      }
        return acao;
    }
}
