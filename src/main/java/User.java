

public class User {

    String password;
    String name;
    int bookID = 0;
    boolean alugando = false;

    public User(String name, String password) {
        this.password = password;
        this.name = name;
    }

    public void alugar(int bookID) {
        this.bookID = bookID;
        this.alugando = true;
    }

    public void devolução() {
        this.alugando = false;
    }
    public boolean auth(String inputPassword){
        return inputPassword.equals(this.password);
    }
}
