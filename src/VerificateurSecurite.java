import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VerificateurSecurite {

    public static String verifier(String mdp) {

        try {

            ProcessBuilder pb = new ProcessBuilder(
                    "docker",
                    "run",
                    "--rm",
                    "password",
                    mdp
            );

            pb.redirectErrorStream(true);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String result = reader.readLine();

            process.waitFor();

            // erreurs Docker
            if (result == null) {
                return "ERREUR_DOCKER";
            }

            if (result.contains("failed to connect")) {
                return "ERREUR_DOCKER";
            }

            if (result.contains("Unable to find image")) {
                return "IMAGE_DOCKER_INTROUVABLE";
            }

            return result;

        } catch (Exception e) {

            return "DOCKER_NON_DEMARRE";
        }
    }
}