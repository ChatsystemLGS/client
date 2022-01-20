package client;

import javax.swing.*;
import java.util.ArrayList;


/*
This class will generate static users list, it also supports method to add new user.
 */
public class UsersList {
    //data members
    private ArrayList<User> users;

    //constructor
    public UsersList(){
        users = new ArrayList<>();

        // TODO: get users info from db
        //adding static data
        addUser("Mary Jane", "Mary@Yahoo.com", "marry1234", "/marry.jpg");
        addUser("Tom Cruise", "tom@gmail.com", "tom1234", "/tom.jpg");
        addUser("Peter", "peter@Yahoo.com", "peter1234", "/peter.jpg");
        addUser("Tony", "tony@Yahoo.com", "tony1234", "/tony.jpg");
        addUser("Thanos", "thanos@hotmail.com", "thanos1234", "/thanos.jpg");
    }


    //setter and getters

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    //behaviours
    //you can add new users in list using this method
    public void addUser(String userName, String userEmail, String userPassword, String userProfileImage){
        if(this.users==null){
            System.out.println("Please Initialize Array List");
        }
        else{
            User newUser = new User(userName, userEmail, userPassword, userProfileImage);
            if(!exists(newUser)){
                users.add(new User(userName, userEmail, userPassword, userProfileImage));
            }
            else{
                JOptionPane.showMessageDialog(null, "Nutzer existiert bereits");
            }

        }
    }

    //method overrided
    public void addUser(User user){
        this.users.add(user);
    }

    //this method will return true if user exists, else it will return false
    public boolean exists(User user){
        for(int i=0; i<users.size(); i++){
            if(users.get(i).equals(user)) return true;
        }
        return false;
    }


}
