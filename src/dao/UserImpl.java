package dao;

import entity.Role;
import entity.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements IUser {
    Db db = new Db();
    private ResultSet rs;
    private int ok;

    @Override
    public int addUser(User u) {
        String sql = "INSERT INTO user VALUES (NULL , ? , ? , ? , ?)";
        try {
            db.initPrepar(sql);
            //passage des valeurs
            db.getPstm().setString(1, u.getEmail());
            db.getPstm().setString(2, u.getPassword());
            db.getPstm().setString(3, hashPassword(u.getPassword()));
            //db.getPstm().setString(4, u.getRole());
            Role role = u.getRole();
            if (role !=null){
                int idRole =role.getIdRole();
                db.getPstm().setInt(4,idRole);
            }else {
                    db.getPstm().setInt(4,2);
                }

            ok = db.executeMaj();
            db.closeCOnncetion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }
    @Override
    // Méthode pour lister tous les utilisateurs de la base de données
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        //String sql = "SELECT * FROM user";
        String sql ="SELECT * FROM user u JOIN role r ON u.idRole = r.idRole";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();

            while (rs.next()) {
                // Récupérer les informations de l'utilisateur depuis le résultat de la requête
                int userId = rs.getInt("idUser");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String hashedPassword = hashPassword(password);

                // Créer un objet User avec les informations récupérées
                User user = new User(userId, email, password, hashedPassword);
                // Ajouter l'utilisateur à la liste

                Role role = new Role();
                role.setIdRole(rs.getInt("r.idRole"));
                role.setName(rs.getString("r.name"));
                user.setRole(role);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Assurez-vous de fermer le ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return userList;
    }
    public static String hashPassword(String password) {
        try {
            // Utilisez SHA-256 pour le hachage (ou tout autre algorithme de hachage sécurisé)
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convertissez le mot de passe en tableau d'octets et appliquez le hachage
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convertissez les octets hachés en format hexadécimal
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Gestion d'erreur - À remplacer par une logique appropriée
            return password;
        }
    }

    public void ajouterUtilisateur(IUser iUser, Scanner scanner) {
        User u = new User();
        // Saisie des informations de l'utilisateur
        System.out.println("\n Donner votre email : ");
        u.setEmail(scanner.nextLine());
        System.out.println("\n Donner votre mot de passe : ");
        String plainPassword = scanner.nextLine();
        // Hashage du mot de passe
        String hashedPassword = UserImpl.hashPassword(plainPassword);
        // Utilisation du mot de passe hashé
        u.setPassword(plainPassword); // Vous pouvez toujours stocker le mot de passe non hashé si nécessaire
        u.setPasswordHashed(hashedPassword);
        // Ajout de l'utilisateur
        int ok = iUser.addUser(u);
        // Vérification de l'ajout de l'utilisateur
        if (ok == 1)
            System.out.println("Utilisateur ajouté avec succès !");
        else
            System.out.println("Erreur lors de l'ajout de l'utilisateur.");
    }

    @Override
    public void listerUtilisateurs(IUser iUser) {
        List<User> userList = iUser.getAllUsers();
        System.out.println("\nListe des utilisateurs :");
        Role role = new Role();
        for (User user : userList) {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("ID: " + user.getId() );
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("Mail: " + user.getEmail() );
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("Mot de Passe: " + user.getPassword() );
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("Mot de passe hashé: " + user.getPasswordHashed() );
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("Role: " + user.getRole().getName());
            System.out.println("*********************************************************************************************");
            System.out.println("*********************************************************************************************");
        }
    }



}