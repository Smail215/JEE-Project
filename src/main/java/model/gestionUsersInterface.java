package model;

import java.util.ArrayList;

public interface gestionUsersInterface {
	public ArrayList<user> listAllUsers();
	public user selectUser(String login);
	public boolean saveUser(user user);
	public boolean deleteUser(String login);
	public void editUser(user user);
	public String addUser(user u);
	public ArrayList<user> getUsers();
	public boolean estInscris(String login,String pwd);
}
