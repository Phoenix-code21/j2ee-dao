package br.com.phoenix.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

import br.com.phoenix.model.User;
import br.com.phoenix.utils.DB;

public class UserDAO {

	private static String entity = "users";

	/**
	 * Autentica usuário
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean authentication(String user, String password) {
		try {
			PreparedStatement pst = DB.getConnection()
					.prepareStatement("SELECT id FROM " + entity + " WHERE user = ? AND password = ?");
			pst.setString(1, user);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException err) {
			System.out.println(err);
		}

		return false;
	}

	/**
	 * Verifica se um usuário existe
	 * 
	 * @param user
	 * @return
	 */
	public boolean exists(String user) {
		try {
			PreparedStatement pst = DB.getConnection().prepareStatement("SELECT * FROM " + entity + " WHERE user = ?");
			pst.setString(1, user);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	/**
	 * Usuário diferente do ID
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	public boolean differentFromID(int id, String user) {
		try {
			PreparedStatement pst = DB.getConnection()
					.prepareStatement("SELECT * FROM " + entity + " WHERE id <> ? AND user = ?");
			pst.setInt(1, id);
			pst.setString(2, user);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	/**
	 * Registra um usuário
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean register(String user, String password) {
		try {
			PreparedStatement pst = DB.getConnection()
					.prepareStatement("INSERT INTO " + entity + " (user,password) VALUES(?,?)");

			pst.setString(1, user);
			pst.setString(2, password);

			int rs = pst.executeUpdate();

			return rs > 0 ? true : false;
		} catch (SQLException e) {
			System.out.println(e);
		}

		return false;
	}

	/**
	 * Atualiza usuário
	 * 
	 * @param data
	 * @return
	 */
	public boolean update(List<User> data) {
		if (data.size() == 0 || data.size() > 1) {
			return false;
		}

		try {
			PreparedStatement pst = DB.getConnection()
					.prepareStatement("UPDATE " + entity + " SET user = ?, password = ? WHERE id = ?");
			pst.setString(1, data.get(0).getUser());
			pst.setString(2, data.get(0).getPassword());
			pst.setInt(3, data.get(0).getId());

			return pst.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			System.out.println(e);
		}

		return false;
	}

	/**
	 * Deleta usuário
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		try {
			PreparedStatement pst = DB.getConnection().prepareStatement("DELETE FROM " + entity + " WHERE id = ?");
			pst.setInt(1, id);

			int rs = pst.executeUpdate();

			return rs > 0 ? true : false;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	/**
	 * Obtem usuário especifico
	 * @param id
	 * @return
	 */
	public List<User> findById(int id) {
		List<User> user = new ArrayList<User>();

		try {
			PreparedStatement pst = DB.getConnection().prepareStatement("SELECT * FROM " + entity + " WHERE id = ?");
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				user.add(new User(rs.getInt("id"), rs.getString("user"), rs.getString("password")));
			}

			return user;
		} catch (SQLException e) {
			System.out.println(e);
		}

		return user;
	}

	/**
	 * Obtem todos os usuários
	 * 
	 * @return
	 */
	public List<User> alls() {
		List<User> alls = new ArrayList<User>();
		try {
			PreparedStatement pst = DB.getConnection().prepareStatement("SELECT * FROM " + entity);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				alls.add(new User(rs.getInt("id"), rs.getString("user"), rs.getString("password")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return alls;
	}

}
