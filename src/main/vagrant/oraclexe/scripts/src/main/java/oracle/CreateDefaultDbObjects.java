package oracle;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDefaultDbObjects {

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
            System.out.println("Tentando criar objetos de banco de dados... " + tentativa + " de " + MAX_TENTATIVA);
            try {
                connection = DriverManager.getConnection(jdbc_url,"system","oracle");
                Statement statement = connection.createStatement();
                statement.execute("CREATE USER sisas IDENTIFIED BY sisas");
                statement.execute("grant connect to sisas");
                statement.execute("grant all privileges to sisas");
                System.out.println("Objetos de BD foram criados com sucesso!");
                statement.close();
                connection.close();
                sem_conexao = false;
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
