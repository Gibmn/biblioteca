import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    Random numRandom = new Random();
    int random1 = numRandom.nextInt(400);
    int random2 = numRandom.nextInt(400);
    int id = random1 * random2;
    String nomePessoa;
    String password;

    char vaiAlugar;
    int index;
    int temporario;
    boolean estaEmprestado;

    System.out.println("\nQual o seu nome?");
    nomePessoa = input.next();

    System.out.println("digite uma senha para sua conta: ");
    password = input.next();

    user Usuario = new user(id, nomePessoa, password);

    Book domCasmurro = new Book("Dom Casmurro", "Machado de Assis", 1123, 1899, false);
    Book assimFalouZaratrusta = new Book("Assim falou Zaratrusta","Nietchesze", 3122, 1883, false);
    Book pequenoPrincipe = new Book("O Pequeno Principe", "Antoine de Saint-Exupéry", 0, 1943, false);
    Book ansiedade = new Book("Ansiedade", "Augusto Cury", 1231, 2013, false);
    Book irmãoskaramazov = new Book("Os Irmãos Karamazov", "Fyodor Dostoyevski", 0, 1879, false);
    Book harrypotterps = new Book("Harry Potter e a Pedra Filosofal", "J. K. Rowling", 0, 1997, false);

    Book[] estante = { domCasmurro, assimFalouZaratrusta, pequenoPrincipe, ansiedade, irmãoskaramazov,
        harrypotterps };

    System.out.println("em qual lugar da estante está o seu livro(aperte zero para ver os lugares)");
    index = input.nextInt();
    index = index - 1;
    System.out.println("\n");

    for (int i = 0; i < estante.length; i++) {
      if (estante[i].ownerId == 0) {
//        estante[i].name = estante[i].name + " (alugável)";
        estante[i].alugado = false;
      }
    }

    if (index > -1 && index < estante.length) {
      if (estante[index].alugado) {
        System.exit(1);
      }

      System.out.println("deseja alugar este livro?(s/sim ou n/não): \n");
      estante[index].ownerId = id;
      printBook(estante[index]);
      vaiAlugar = input.next().charAt(0);
      estaEmprestado = (vaiAlugar == 's') ? true : false;

      if (!estaEmprestado) {
        System.exit(1);
      }

    } else if (index < 0) {
      for (int i = 0; i < estante.length; i++) {
        temporario = i + 1;
        System.out.println("livro: " + estante[i].name + ", index: " + temporario);
      }
    }
    if (index > -1) {
      printBook(estante[index]);
    }
    input.close();
  }

  public static void printBook(Book Titulo) {
    System.out.println("O nome do seu livro é: " + Titulo.name);
    System.out.println("O autor do livro é: " + Titulo.author);
    System.out.println("O leitor atual é: " + Titulo.acOwner);
    System.out.println("O livro foi publicado em: " + Titulo.year);
    System.out.println("O livro está alugado: " + Titulo.alugado);
  }
}
