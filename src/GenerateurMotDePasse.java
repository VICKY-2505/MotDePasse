import java.security.SecureRandom;

public class GenerateurMotDePasse {

    private static final String MAJ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MIN = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM = "0123456789";
    private static final String SYM = "!@#$%^&*()-_=+";

    // longueur minimale et maximale
    private static final int MIN_LONGUEUR = 8;
    private static final int MAX_LONGUEUR = 32;

    public static String generer(int longueur,
                                 boolean maj,
                                 boolean min,
                                 boolean num,
                                 boolean sym) {

        // vérification longueur
        if (longueur < MIN_LONGUEUR || longueur > MAX_LONGUEUR) {

            throw new IllegalArgumentException(
                    "La longueur doit être comprise entre "
                            + MIN_LONGUEUR +
                            " et " +
                            MAX_LONGUEUR
            );
        }

        StringBuilder chars = new StringBuilder();

        if (maj) chars.append(MAJ);
        if (min) chars.append(MIN);
        if (num) chars.append(NUM);
        if (sym) chars.append(SYM);

        // si aucun choix utilisateur
        if (chars.isEmpty()) {

            throw new IllegalArgumentException(
                    "Vous devez sélectionner au moins un type de caractère."
            );
        }

        SecureRandom random = new SecureRandom();

        StringBuilder mdp = new StringBuilder();

        for (int i = 0; i < longueur; i++) {

            int index = random.nextInt(chars.length());

            mdp.append(chars.charAt(index));
        }

        return mdp.toString();
    }
}