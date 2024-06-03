package model;

import java.util.List;

public class Role {
	private int id;
    private String name;

    private List<User> users;

    // Constructor
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
    
    
}
