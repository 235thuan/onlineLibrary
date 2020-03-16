package onlineLibrary.manageUser.models;

public class Login {
    private String account;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String email;
    public String getAccount(){
        return account;
    }
    public void setAccount(String account){
        this.account = account;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
