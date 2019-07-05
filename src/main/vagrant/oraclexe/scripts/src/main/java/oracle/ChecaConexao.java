package oracle;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChecaConexao {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        String oracle_xe_host = System.getenv("ORACLE_XE_HOST");
        if (oracle_xe_host == null) {
            oracle_xe_host = "localhost";
        }
        String jdbc_url = String.format("jdbc:oracle:thin:@%s:1521:xe", oracle_xe_host);
        System.out.println("JDBC URL: " + jdbc_url);


        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = null;
        int MAX_TENTATIVA = 20;
        int tentativa = 1;
        boolean sem_conexao = true;

        while (tentativa <= MAX_TENTATIVA && sem_conexao) {
            System.out.println("Tentando conectar ao Oracle... " + tentativa + " de " + MAX_TENTATIVA);
            try {
                connection = DriverManager.getConnection(jdbc_url,"PTOLOMEU","PTOLOMEU");
                System.out.println("Conexao estabelecida com sucesso!");
                sem_conexao = false;
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
                TimeUnit.SECONDS.sleep(20);
            }
            tentativa++;
        }

        if (sem_conexao) {
            System.out.println("Nao foi possivel conectar ao banco de dados.");
            System.exit(1);
        }
    }

}
