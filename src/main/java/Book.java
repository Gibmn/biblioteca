

public class Book {

    String name;
    String author;
    int ownerId;
    int year;
    boolean alugado = false;

    Book(
            String name,
            String author,
            int ownerId,
            int year
    ) {
        this.name = name;
        this.ownerId = ownerId;
        this.author = author;
        this.year = year;
    }

    public void alugar(int ownerId) {
        this.ownerId = ownerId;
        this.alugado = true;
    }

    public void devolução() {
        this.alugado = false;
    }
}
