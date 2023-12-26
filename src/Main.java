import dao.IUser;
import dao.UserImpl;
import entity.User;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IUser iUser = new UserImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Affichage du menu
            System.out.println("\nMenu:");
            System.out.println("1. Lister tous les utilisateurs");
            System.out.println("2. Ajouter un utilisateur");
            System.out.println("0. Quitter");

            // Saisie du choix de l'utilisateur


            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne
            switch (choix) {
                case 1:
                    // Lister tous les utilisateurs
                    iUser.listerUtilisateurs(iUser);
                    break;
                case 2:
                    // Ajouter un utilisateur
                    iUser.ajouterUtilisateur(iUser, scanner);
                    break;
                case 0:
                    // Quitter le programme
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }


}
