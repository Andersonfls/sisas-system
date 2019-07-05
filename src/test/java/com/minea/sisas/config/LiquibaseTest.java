package com.minea.sisas.config;

import com.minea.sisas.SisasApp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.core.NestedCheckedException;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
public class LiquibaseTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testDefaultSettings() throws Exception {

        List<String> params = new ArrayList<>();
        params.add("--spring.profiles.active=default");

        String oracle_xe_host = System.getenv("ORACLE_XE_HOST");
        if (oracle_xe_host != null) {
            String param = "--spring.datasource.url=" + String.format("jdbc:oracle:thin:@%s:1521:xe", oracle_xe_host);
            System.out.println("JDBC URL: " + param);
            params.add(param);
        }

        try {
            SisasApp.main(params.toArray(new String[]{}));
        }
        catch (IllegalStateException ex) {
            if (serverNotRunning(ex)) {
                return;
            }
        }
        String output = this.outputCapture.toString();
        assertThat(output)
            .contains("Successfully acquired change log lock")
            .contains("Successfully released change log lock")
            .doesNotContain("ERROR");


    }

    @SuppressWarnings("serial")
    private boolean serverNotRunning(IllegalStateException ex) {
        NestedCheckedException nested = new NestedCheckedException("failed", ex) {
        };
        if (nested.contains(ConnectException.class)) {
            Throwable root = nested.getRootCause();
            if (root.getMessage().contains("Connection refused")) {
                return true;
            }
        }
        return false;
    }
}
