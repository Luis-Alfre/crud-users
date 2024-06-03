package model;

public class User {
	private Integer id;
    private Long documentId;
    private String name;
    private String email;
    private String address;
    private Integer rol_id;
    private Role role;

    // Constructor
    public User() {
    }
    public User(Long documentId, String name, String email, String address, Integer rol_id) {
    	this.setParams(documentId, name, email, address, rol_id);
    }

    public User(Integer id, Long documentId,  String name, String email, String address, Integer rol_id) {
    	this.id = id;
    	this.setParams(documentId, name, email, address, rol_id);
    }
    
    private void setParams(Long documentId, String name, String email, String address, Integer rol_id) {
    	this.documentId = documentId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.rol_id = rol_id;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    public long getDocumentId() {
        return documentId;
    }

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
    
    
}
