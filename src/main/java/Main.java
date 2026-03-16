
import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);
    // scanner global

    public static void main(String[] args) {
        int[] nextUser = {1}; // para passar nas funções por referencia
        int[] nextBook = {1};
        // obra do tinhoso

        int choice = -1; // escolha que o usuario fará

        ArrayList<User> users = new ArrayList<>();
        ArrayList<Book> estante = base();
        // listas de usúarios e livros

        while (true) {
            System.out.println("ESCOLHA UMA AÇÃO");
            System.out.println(" ");
            System.out.println("0. Sair do programa");
            System.out.println("1. Adcionar usuario");
            System.out.println("2. Adcionar livro");
            System.out.println("3. Alugar");
            System.out.println("4. Devolução");
            System.out.println("5. Mostrar todos os livros e seus IDS");
            choice = input.nextInt();

            switch (choice) {
                case 0 ->
                    System.exit(1);
                case 1 ->
                    users.add(createUser());
                case 2 ->
                    estante.add(addBook());
                case 3 ->
                    System.out.print(alugar(estante, users) ? "foi alugado." : "\nNão foi possivel alugar.");
                case 4 ->
                    devolucao(users, estante);
                case 5 ->
                    mostrarLivros(estante);
                default ->
                    throw new AssertionError();
            }
            // enhanced switch para as escolhas

            input.close();
        }
    }

    public static void printBook(Book book) {
        System.out.println("O nome do seu livro é: " + book.name);
        System.out.println("O autor do livro é: " + book.author);
        System.out.println("O leitor atual é: " + book.ownerId);
        System.out.println("O livro foi publicado em: " + book.year);
        System.out.println("O livro está alugado: " + ((book.ownerId != 0) ? "sim" : "não"));
    }

    public static ArrayList<Book> base() {
        Book domCasmurro = new Book("Dom Casmurro", "Machado de Assis", 0, 1899);
        Book assimFalouZaratrusta = new Book("Assim falou Zaratrusta", "Nietchesze", 0, 1883);
        Book pequenoPrincipe = new Book("O Pequeno Principe", "Antoine de Saint-Exupéry", 0, 1943);
        Book ansiedade = new Book("Ansiedade", "Augusto Cury", 0, 2013);
        Book irmãoskaramazov = new Book("Os Irmãos Karamazov", "Fyodor Dostoyevski", 0, 1879);
        Book harrypotterps = new Book("Harry Potter e a Pedra Filosofal", "J. K. Rowling", 0, 1997);

        ArrayList<Book> estante = new ArrayList<>();
        Book array[] = {domCasmurro, assimFalouZaratrusta, pequenoPrincipe, ansiedade, irmãoskaramazov,
            harrypotterps};
        estante.addAll(Arrays.asList(array));
        return estante;
    }

    public static User createUser() {
        String name;
        String password;
        System.out.println("\nQual o seu nome?");
        name = input.next();

        System.out.println("digite uma senha para sua conta: ");
        password = input.next();

        return new User(name, password);
    }

    public static boolean alugar(ArrayList<Book> estante, ArrayList<User> users) {
        String vaiAlugar;
        int bookIndex;
        int temporary;
        int userID = 0;

        System.out.println("Qual é seu ID de usuário?");
        userID = input.nextInt();

        if (userID < 1 || userID > users.size()) {
            System.out.println("ID de usuário inválido.");
            return false;
        }
        //previne o "Out of bounds" na ArrayList users

        System.out.println("em qual lugar da estante está o seu livro(aperte zero para ver os lugares)");
        bookIndex = input.nextInt();
        bookIndex = bookIndex - 1;
        System.out.println("\n");

        if (bookIndex == -1) {
            mostrarLivros(estante);
        }
        // printa todos livros e os ids da estante

        if (bookIndex < 0 || bookIndex >= estante.size()) {
            System.err.println("Livro inválido.");
            return false;
        }
        if (estante.get(bookIndex).ownerId == userID) {
            System.err.println("Este usuário ja alugou esse livro.");
            return false;
        }
        if (estante.get(bookIndex).ownerId > 0) {
            System.err.println("Livro já alugado por outro usuário");
            return false;
        }
        // tratamento de erros e casos inválidos

        System.out.println("deseja alugar este livro?(s/sim ou n/não): \n");
        printBook(estante.get(bookIndex));
        vaiAlugar = (input.next()).substring(0, 1);

        if (!vaiAlugar.equalsIgnoreCase("S")) {
            System.out.println("Cancelado.");
            return false;
        }
        // Caso o usuario não queira alugar

        estante.get(bookIndex).ownerId = userID;
        users.get(userID).bookID = bookIndex;
        printBook(estante.get(bookIndex));

        System.out.println(estante.get(bookIndex).name);
        return true;
    }

    public static Book addBook() {
        String nomeLivro;
        String autor;
        int year;

        System.out.println("\nQual o nome do livro?");
        nomeLivro = input.next();

        System.out.println("Qual o nome do autor do seu livro?");
        autor = input.next();

        System.out.println("Em qual ano seu livro foi publicado?");
        year = input.nextInt();

        return new Book(nomeLivro, autor, 0, year);
    }

    public static void devolucao(ArrayList<User> users, ArrayList<Book> estante) {
        int userID;
        userID = input.nextInt();

        if (userID < 0 || userID > users.size()) {
            System.out.println("ID de usuário inválido.");
            return;
        }
        // previne "out of bounds"

        int bookIndex = users.get(userID).bookID;
        users.get(userID).bookID = 0;
        estante.get(bookIndex).devolução();
    }
    
    public static void mostrarLivros(ArrayList<Book> estante) {
    // função para mostrar todos os livros existentes
    
      for (int i = 0; i < estante.size(); i++){
          System.out.println("Livro: " + estante.get(i).name + ", Index: " +(i + 1) );
      }
    }
}
