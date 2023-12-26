package dao;

import entity.User;

import java.util.List;
import java.util.Scanner;

public interface IUser{
    public int addUser(User u) ;
    // Méthode pour lister tous les utilisateurs de la base de données
    List<User> getAllUsers();

    public void ajouterUtilisateur(IUser iUser, Scanner scanner);
    public void listerUtilisateurs(IUser iUser);

}
