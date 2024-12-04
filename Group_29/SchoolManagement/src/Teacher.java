public class Teacher {
	private String id;
    private String fullName;
    private String department;
    private String email;

    public Teacher(String id, String fullName, String department, String email) {
        this.id = id;
        this.fullName = fullName;
        this.department = department;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }
}
