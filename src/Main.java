import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int longueur;

        // vérification immédiate
        while (true) {

            System.out.print("Longueur du mot de passe doit etre superieur a 8 : ");

            longueur = sc.nextInt();

            if (longueur >= 8 ) {
                break;
            }

            System.out.println("Erreur : la longueur doit être superieur a 8 ");
        }

        System.out.print("Nombre de mots de passe : ");
        int nombre = sc.nextInt();

        sc.nextLine();

        System.out.print("Inclure majuscules ? (oui/non) : ");
        boolean maj = sc.nextLine().equalsIgnoreCase("oui");

        System.out.print("Inclure minuscules ? (oui/non) : ");
        boolean min = sc.nextLine().equalsIgnoreCase("oui");

        System.out.print("Inclure chiffres ? (oui/non) : ");
        boolean num = sc.nextLine().equalsIgnoreCase("oui");

        System.out.print("Inclure symboles ? (oui/non) : ");
        boolean sym = sc.nextLine().equalsIgnoreCase("oui");

        // vérification choix caractères
        if (!maj && !min && !num && !sym) {

            System.out.println(
                    "Erreur : vous devez sélectionner au moins un type de caractère."
            );

            return;
        }

        for (int i = 1; i <= nombre; i++) {

            String mdp = GenerateurMotDePasse.generer(
                    longueur,
                    maj,
                    min,
                    num,
                    sym
            );

            String force = VerificateurSecurite.verifier(mdp);

            System.out.println("----------------------");
            System.out.println("Mot de passe " + i + " : " + mdp);
            System.out.println("Force : " + force);
        }

        sc.close();
    }
}