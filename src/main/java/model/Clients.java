package main.java.model;

public class Clients {

    private int id;
    private String name;
    private String address;
    private int age;
    private String email;

    //default constructor
    public Clients(){};

    public Clients(int id, String name, String address, int age, String email){
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
    }

    public Clients(String name, String address, int age, String email){
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "name=" + name + "\n" +
                "address=" + address + "\n" +
                "age=" + age + "\n" +
                "email=" + email + "\n";
    }
}
