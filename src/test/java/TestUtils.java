import org.example.database.Conexao;

import java.sql.Connection;
import java.sql.Statement;

public class TestUtils {

    public static void inserirEquipamentosFalhasEAcoes() {
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                    INSERT INTO Equipamento
                    (id BIGINT AUTO_INCREMENT PRIMARY KEY, 
                    nome VARCHAR(255) NOT NULL, 
                    numeroDeSerie VARCHAR(100) NOT NULL UNIQUE, 
                    areaSetor VARCHAR(100) NOT NULL, 
                    statusOperacional VARCHAR(50) NOT NULL,
                    """);

            stmt.execute("""
                INSERT INTO Equipamento (nome, numeroDeSerie, areaSetor, statusOperacional)
                VALUES
                ('Motor Principal', 'MP-001', 'Linha 1', 'OPERACIONAL'),
                ('Esteira 01', 'EST-001', 'Linha 1', 'OPERACIONAL'),
                ('Caldeira', 'CALD-001', 'Sala de Caldeiras', 'OPERACIONAL');
            """);

            stmt.execute("""
                    INSERT INTO Falha 
                    ( id BIGINT AUTO_INCREMENT PRIMARY KEY, 
                    equipamentoId BIGINT NOT NULL, 
                    dataHoraOcorrencia DATETIME NOT NULL, 
                    descricao TEXT NOT NULL, 
                    criticidade VARCHAR(50) NOT NULL, 
                    status VARCHAR(50) NOT NULL, 
                    tempoParadaHoras DECIMAL(10, 2) DEFAULT 0.00,
                    """);

            stmt.execute("""
                INSERT INTO Falha (equipamentoId, dataHoraOcorrencia, descricao, criticidade, status, tempoParadaHoras)
                VALUES
                (1, NOW(), 'Falha leve', 'BAIXA', 'ABERTA', 1.5),
                (1, NOW(), 'Falha crítica', 'CRITICA', 'RESOLVIDA', 5.0),
                (2, NOW(), 'Falha média', 'MEDIA', 'ABERTA', 2.0);
            """);

            stmt.execute("""
                    INSERT INTO AcaoCorretiva 
                    ( id BIGINT AUTO_INCREMENT PRIMARY KEY, 
                    falhaId BIGINT NOT NULL, 
                    dataHoraInicio DATETIME NOT NULL, 
                    dataHoraFim DATETIME NOT NULL, 
                    responsavel VARCHAR(255) NOT NULL, 
                    descricaoAcao TEXT NOT NULL,
                    """);

            stmt.execute("""
                INSERT INTO AcaoCorretiva (falhaId, dataHoraInicio, dataHoraFim, responsavel, descricaoAcao)
                VALUES (2, NOW(), NOW(), 'Carlos', 'Reparo completo');
            """);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}