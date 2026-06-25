public class PasswordAnalyzer {

    public static void main(String[] args) {

        String mdp = args[0];

        int score = evaluate(mdp);

        switch (score) {
            case 0 -> System.out.println("TRES_FAIBLE");
            case 1 -> System.out.println("FAIBLE");
            case 2 -> System.out.println("MOYEN");
            case 3 -> System.out.println("FORT");
            default -> System.out.println("TRES_FORT");
        }
    }

    private static int evaluate(String mdp) {

        int score = 0;

        if (mdp.length() >= 8) score++;
        if (mdp.length() >= 12) score++;
        if (mdp.matches(".*[A-Z].*")) score++;
        if (mdp.matches(".*[0-9].*")) score++;
        if (mdp.matches(".*[^a-zA-Z0-9].*")) score++;

        return Math.min(score, 4);
    }
}