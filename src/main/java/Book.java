
public class Book {

    String name;
    String author;
    int ownerId;
    int year;

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
        //  this.name = this.name.replace(" (alugável)", "");
    }

    public void devolução() {
        this.ownerId = 0;
    }
}
