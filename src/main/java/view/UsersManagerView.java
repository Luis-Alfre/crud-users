package view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Role;

public class UsersManagerView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Definimos los controles para la vista
	private JLabel lbId, lbName, lbEmail, lbDocument, lbAddress, lbRole, lbFilter;
	private JTextField fieldName, fieldEmail, fieldDocument, fieldAddress;
	private JComboBox<Role> comboRole; //Es necesario sobrescribir el metodo toString() en el model
	private JButton btnSave, btnUpdate, btnClear, btnDelete;
	private JComboBox<Role> comboFilterRole;
	private JTable tableUsers;
	private DefaultTableModel modelTable;
	private Container rootPane;
	
	public UsersManagerView() {
		super("Gestor Usuarios");
		setLayout(null);
		
		this.rootPane = this.getContentPane();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initComponents();
	}

	private void initComponents() {
		lbId = new JLabel("");
		lbName = new JLabel("Nombre");
		lbEmail = new JLabel("Dirección Emial");
		lbDocument = new JLabel("Documento");
		lbAddress = new JLabel("Dirección");
		lbRole = new JLabel("Cargo");
		
		lbId.setBounds(0,0, 0,0);
		lbDocument.setBounds(10,10, 240, 15);
		lbName.setBounds(10, 50, 240, 15);
		lbEmail.setBounds(10, 90, 240, 15);
		lbAddress.setBounds(10, 130, 240, 15);
		lbRole.setBounds(10, 180, 240, 15);
		
		lbId.setForeground(Color.BLACK);
		lbDocument.setForeground(Color.BLACK);
		lbName.setForeground(Color.BLACK);
		lbEmail.setForeground(Color.BLACK);
		lbAddress.setForeground(Color.BLACK);
		lbRole.setForeground(Color.BLACK);
		
		// Campos de texto
		fieldName = new JTextField();
		fieldDocument = new JTextField();
		fieldEmail = new JTextField();
		fieldAddress = new JTextField();
		
		comboRole = new JComboBox<Role>();
		comboRole.addItem(new Role(0, "Seleccione Cargo"));
		comboFilterRole = new JComboBox<Role>();
		comboFilterRole.addItem(new Role(0, "Todos"));
		
		fieldDocument.setBounds(10, 25, 265, 20);
		fieldName.setBounds(10, 65, 265, 20);
		fieldEmail.setBounds(10, 105, 265, 20);
		fieldAddress.setBounds(10, 145, 265, 20);
		comboRole.setBounds(10, 194, 265, 20);
		
		btnSave = new JButton("Guardar");
		btnUpdate = new JButton("Modificar");
		btnDelete = new JButton("Eliminar");
		btnClear = new JButton("Limpiar");
		btnSave.setBounds(10, 224, 150, 20);
		btnUpdate.setBounds(170, 224, 150, 20);
		btnDelete.setBounds(330, 224, 150, 20);
		btnClear.setBounds(490, 224, 150, 20);
		
		//Tabla
		lbFilter = new JLabel("Filtrar Por Cargo");
		lbFilter.setBounds(10, 254, 265, 20);
		comboFilterRole.setBounds(10, 284, 265, 20);
		
		
		tableUsers = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int fila,int col){
                return false;
            }
        };
        tableUsers.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        
        tableUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableUsers.setFocusable(false);
        tableUsers.setRowMargin(4);
        tableUsers.getTableHeader().setReorderingAllowed(false);
		modelTable = (DefaultTableModel) tableUsers.getModel();
		modelTable.addColumn("Documento");
		modelTable.addColumn("Nombre");
		modelTable.addColumn("Emal");
		modelTable.addColumn("Dirección");
		modelTable.addColumn("Cargo");
		
		JScrollPane scrollPane = new JScrollPane(tableUsers);
		scrollPane.setBounds(10, 314, 760, 280);
		
		
		this.rootPane.add(lbId);
		this.rootPane.add(lbDocument);
		this.rootPane.add(lbName);
		this.rootPane.add(lbEmail);
		this.rootPane.add(lbAddress);
		this.rootPane.add(lbRole);
		
		this.rootPane.add(fieldName);
		this.rootPane.add(fieldDocument);
		this.rootPane.add(fieldEmail);
		this.rootPane.add(fieldAddress);
		
		this.rootPane.add(comboRole);
		this.rootPane.add(comboRole);
		
		this.rootPane.add(btnSave);
		this.rootPane.add(btnUpdate);
		this.rootPane.add(btnDelete);
		this.rootPane.add(btnClear);
		
		this.rootPane.add(lbFilter);
		this.rootPane.add(scrollPane);
		this.rootPane.add(comboFilterRole);
		
		this.setSize(800,660);
	}

	public JTextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(JTextField fieldName) {
		this.fieldName = fieldName;
	}

	public JTextField getFieldEmail() {
		return fieldEmail;
	}

	public void setFieldEmail(JTextField fieldEmail) {
		this.fieldEmail = fieldEmail;
	}

	public JTextField getFieldDocument() {
		return fieldDocument;
	}

	public void setFieldDocument(JTextField fieldDocument) {
		this.fieldDocument = fieldDocument;
	}

	public JTextField getFieldAddress() {
		return fieldAddress;
	}

	public void setFieldAddress(JTextField fieldAddress) {
		this.fieldAddress = fieldAddress;
	}

	public JComboBox<Role> getComboRole() {
		return comboRole;
	}

	public void setComboRole(JComboBox<Role> comboRole) {
		this.comboRole = comboRole;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public JButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(JButton btnClear) {
		this.btnClear = btnClear;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JComboBox<Role> getComboFilterRole() {
		return comboFilterRole;
	}

	public void setComboFilterRole(JComboBox<Role> comboFilterRole) {
		this.comboFilterRole = comboFilterRole;
	}

	public JTable getTableUsers() {
		return tableUsers;
	}

	public void setTableUsers(JTable tableUsers) {
		this.tableUsers = tableUsers;
	}

	public JLabel getLbId() {
		return lbId;
	}

	public void setLbId(JLabel lbId) {
		this.lbId = lbId;
	}

	public DefaultTableModel getModelTable() {
		return modelTable;
	}

	public void setModelTable(DefaultTableModel modelTable) {
		this.modelTable = modelTable;
	}
	
	
	
	

}
