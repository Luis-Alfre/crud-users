package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.dao.UserDAO;
import model.Role;
import model.User;
import view.UsersManagerView;

public class UserController {
	private UsersManagerView userManagerView;
	private RoleController roleController;
	private UserDAO userDAO;
	private List<User> users;
	
	public UserController() {
		this.roleController = new RoleController();
		this.userDAO = new UserDAO();
		this.users = new ArrayList<User>();
	}
	
	public UserController(UsersManagerView userManagerView) {
		this.users = new ArrayList<User>();
		this.userDAO = new UserDAO();
		this.userManagerView = userManagerView;
		this.roleController = new RoleController();
		this.loadRoles(this.userManagerView.getComboRole(), "Seleccione un rol");
		this.loadRoles(this.userManagerView.getComboFilterRole(), "Seleccione un rol para filtrar");
		configureEvents();
	}
	
	//Metodos de acceso a datos
	public boolean save (User user) {
		try {
			this.userDAO.save(user);
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}
	public boolean delete (Long documentId) {
		return this.userDAO.delete(documentId);
	}
	
	public boolean update (User user) {
		return this.userDAO.update(user);
	}
	
	public List<User> list () {
		return this.userDAO.list();
	}
	
	public List<User> listByRole(Integer roleId) {
		return this.userDAO.listByRole(roleId);
	}
	public User getByDocument(Long document) {
		return this.userDAO.getByDocument(document);
	}
	//---------------------
	
	// ------------ Creación de eventos ----------------
	private void configureEvents() {
		this.userManagerView.getBtnSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSave();
			}
		});	
		
		this.userManagerView.getBtnClear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				userManagerView.getBtnUpdate().setEnabled(true);
				userManagerView.getBtnDelete().setEnabled(true);
			}
		});
		this.userManagerView.getBtnDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actiondelete();
			}
		});
		
		this.userManagerView.getBtnUpdate().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionUpdate();
			}
		});
		
		this.userManagerView.getComboFilterRole().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadData((Role) e.getItem());
			}
		});
	}
	
	// -------------------------------------------------------------------------
	
	//--------------- Acciones del formulario -----------------------
	private void actionSave() {
		if (checkFields()) {
			User user = this.getFromFields(false);
			try {
				if (this.userManagerView.getLbId().getText() != "") {
					user.setId(Integer.valueOf(this.userManagerView.getLbId().getText()));
					this.update(user);
					this.userManagerView.getLbId().setText("");
					this.userManagerView.getBtnDelete().setEnabled(true);
					this.userManagerView.getBtnUpdate().setEnabled(true);
					JOptionPane.showMessageDialog(this.userManagerView, "Usuario editado con exito");
					this.clear();
					this.loadData(null);
				}else {
					JOptionPane.showMessageDialog(this.userManagerView, "Usuario agregado con exito");
					this.loadData(null);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(this.userManagerView, "Ha ocurrido un error");
			}
			
			
		} else {
			JOptionPane.showMessageDialog(this.userManagerView, "Campos requeridos sin llenar");
		}
	}
	
	protected void actionUpdate() {
		if (this.isSelectedRow()) {
			try {
				User user = getUserFromTable();
				this.userManagerView.getFieldAddress().setText(user.getAddress());
				this.userManagerView.getFieldDocument().setText(String.valueOf(user.getDocumentId()));
				this.userManagerView.getFieldEmail().setText(user.getEmail());
				this.userManagerView.getFieldName().setText(user.getName());
				this.userManagerView.getComboRole().setSelectedIndex(0);
				this.userManagerView.getLbId().setText(String.valueOf(user.getId()));
				
				this.userManagerView.getBtnUpdate().setEnabled(false);
				this.userManagerView.getBtnDelete().setEnabled(false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(this.userManagerView, "Error al cargar datos");
			}
		}else {
			JOptionPane.showMessageDialog(this.userManagerView, "Ningun Item Seleccionado");
		}
	}	
	
	public User getUserFromTable() {
		DefaultTableModel model = this.userManagerView.getModelTable();
		JTable table = this.userManagerView.getTableUsers();
		if(model.getValueAt(table.getSelectedRow(), 
		table.getSelectedColumn()) != null){
        	return this.getByDocument((Long.valueOf(model.getValueAt(table.getSelectedRow(), 0).toString())));
        }
		return null;
	}

	protected void actiondelete() {
		if (this.isSelectedRow()) {
			try {
				User user = getUserFromTable();
				this.delete(user.getDocumentId());
				JOptionPane.showMessageDialog(this.userManagerView, "Eliminado correctamente");
				this.loadData(null);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(this.userManagerView, "Error al eliminar");
			}
		}else {
			JOptionPane.showMessageDialog(this.userManagerView, "Ningun Item Seleccionado");
		}
	}

	// -----------------------------------------------
	
	// ---------- Manejo de los componentes del formulario -----------
	
	public boolean isSelectedRow() {
		return this.userManagerView.getTableUsers().getSelectedRow() >= 0;
	}
	
	public void clear () {
		this.userManagerView.getFieldDocument().setText("");
		this.userManagerView.getLbId().setText("");
		this.userManagerView.getFieldName().setText("");
		this.userManagerView.getFieldAddress().setText("");
		this.userManagerView.getFieldEmail().setText("");
		this.userManagerView.getComboRole().setSelectedIndex(0);
		
	}

	public User getFromFields (boolean hasId) {
		User user = new User();
		user.setName(this.userManagerView.getFieldName().getText());
		user.setDocumentId(Long.valueOf(this.userManagerView.getFieldDocument().getText()));
		user.setEmail(this.userManagerView.getFieldEmail().getText());
		user.setAddress(this.userManagerView.getFieldAddress().getText());
		
		user.setRol_id(((Role)this.userManagerView.getComboRole().getSelectedItem()).getId());
		if (hasId) {
			user.setId(Integer.valueOf(this.userManagerView.getLbId().getText()));
		}
		return user;
	}
	
	public void loadData (Role role) {
		try {
			if (role == null || role.getId() == 0) {
				this.users = this.list();
			} else {
				this.users = this.listByRole(role.getId());
			}
			DefaultTableModel model = this.userManagerView.getModelTable();			
			model.getDataVector().clear();
			users.forEach(user -> {
				model.addRow(new Object[] {
						user.getDocumentId(), user.getName(), user.getEmail(), user.getAddress(), user.getRole().getName()
				});
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.userManagerView, "Ha ocurrido un error: "+e.getMessage());
		}
	}
	
	public boolean checkFields() {
		if (this.userManagerView.getFieldName().getText().isBlank() || 
			this.userManagerView.getFieldDocument().getText().isBlank() ||
			this.userManagerView.getFieldAddress().getText().isBlank() ||
			this.userManagerView.getFieldEmail().getText().isBlank() ||
			((Role)this.userManagerView.getComboRole().getSelectedItem()).getId() == 0) {
			
			return false;
		}
		
		return true;
	}
	
	public void loadRoles (JComboBox<Role> cb, String text) {
		cb.removeAllItems();
		cb.addItem(new Role(0, text));
		this.roleController.list().forEach(role -> {
			cb.addItem(role);
		});
	}
	
	// ----------------------------------------------
	
	// ------------- Mostrar Vista -------------
	
	public void render () {
		if (this.userManagerView != null) {
			userManagerView.setVisible(true);
			this.loadData(null);
		}
	}
}
