package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String address;
    private Rol rol_id;

    // Constructor
    public User() {
    }

    public User(int id, String name, String email, String address, Rol rol_id) {
        this.id = id;
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

    public Rol getRol_id() {
        return rol_id;
    }

    public void setRol_id(Rol rol_id) {
        this.rol_id = rol_id;
    }

}

