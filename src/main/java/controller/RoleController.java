package controller;

import java.util.List;

import controller.dao.RoleDAO;
import model.Role;

public class RoleController {
	private RoleDAO roleDAO;
	
	public RoleController() {
		this.roleDAO = new RoleDAO();
		// TODO Auto-generated constructor stub
	}
	
	public List<Role> list () {
		return this.roleDAO.list();
	}
}
