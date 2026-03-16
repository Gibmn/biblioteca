public class Book {

  String name;
  String author;
  int ownerId;
  int year;
  boolean alugado = true;

  Book(
      String name,
      String author,
      int ownerId,
      int year,
      boolean alugado) {
    this.name = name;
    this.ownerId = ownerId;
    this.author = author;
    this.year = year;
    this.alugado = alugado;
  }

  public void alugar(int ownerId) {
    this.alugado = true;
    this.ownerId = ownerId;
//    this.name = this.name.replace(" (alugável)", "");
  }
}
