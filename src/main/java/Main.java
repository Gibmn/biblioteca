
import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);
    // scanner global

    static String Ignore;

    public static void main(String[] args) {
        boolean running = true;
        int choice = -1; // escolha que o usuario fará
        int bookChoice;
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Book> estante = base();
        // listas de usúarios e livros

        while (running) {
            System.out.println(" ");
            System.out.println("ESCOLHA UMA AÇÃO");
            System.out.println(" ");
            System.out.println("0. Sair do programa");
            System.out.println("1. Adcionar usuario");
            System.out.println("2. Adcionar livro");
            System.out.println("3. Alugar");
            System.out.println("4. Devolução");
            System.out.println("5. Mostrar todos os livros e seus IDS");
            System.out.println("6. Mostrar todos os detalhes de um livro especifico");
            System.out.println("7. Mostrar todos os usuários e seus IDS");
            choice = input.nextInt();

            switch (choice) {
                case 0 -> {
                    running = false;
                }
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
                case 6 -> {
                    mostrarLivros(estante);
                    Ignore = input.nextLine();
                    System.out.println("Qual o Id do seu livro?");
                    bookChoice = input.nextInt();
                    bookChoice = bookChoice - 1;
                    printBook(estante, bookChoice);
                }
                case 7 ->
                    mostrarUsuarios(users);
                default ->
                    System.out.println("Faça uma escolha válida.");
            }
            // enhanced switch para as escolhas

        }
        input.close();
        System.exit(1);
    }

    public static void printBook(ArrayList<Book> estante, int bookChoice) {

        System.out.println("O nome do seu livro é: " + estante.get(bookChoice).name);
        System.out.println("O autor do livro é: " + estante.get(bookChoice).author);
        System.out.println("O leitor atual é: " + estante.get(bookChoice).ownerId);
        System.out.println("O livro foi publicado em: " + estante.get(bookChoice).year);
        System.out.println("O livro está alugado: " + ((estante.get(bookChoice).alugado) ? "sim" : "não"));
    }

    public static ArrayList<Book> base() {
        Book domCasmurro = new Book("Dom Casmurro", "Machado de Assis", 0, 1899);
        Book assimFalouZaratrusta = new Book("Assim falou Zaratrusta", "Nietchesze", 0, 1883);
        Book pequenoPrincipe = new Book("O Pequeno Principe", "Antoine de Saint-Exupéry", 0, 1943);
        Book ansiedade = new Book("Ansiedade", "Augusto Cury", 0, 2013);
        Book irmãoskaramazov = new Book("Os Irmãos Karamazov", "Fyodor Dostoyevski", 0, 1879);
        Book harrypotterps = new Book("Harry Potter e a Pedra Filosofal", "J. K. Rowling", 0, 1997);

        ArrayList<Book> estante = new ArrayList<>();
        estante.addAll(Arrays.asList(new Book[]{domCasmurro, assimFalouZaratrusta, pequenoPrincipe, ansiedade, irmãoskaramazov, harrypotterps}));
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
        int userID;

        System.out.println("Qual é seu ID de usuário?");
        userID = input.nextInt();
        userID = userID - 1;
        if (userID < 0 || userID - 1 >= users.size()) {
            System.out.println("ID de usuário inválido.");
            return false;
        }
        // previne o "Out of bounds" na ArrayList users

        mostrarLivros(estante);
        // printa todos livros e os ids da estante

        System.out.println("Em qual lugar da estante está o seu livro?");
        bookIndex = input.nextInt();
        bookIndex = bookIndex - 1;
        System.out.println("\n");

        if (bookIndex < 0 || bookIndex >= estante.size()) {
            System.err.println("Livro inválido.");
            return false;
        }
        if (estante.get(bookIndex).alugado) {
            if (estante.get(bookIndex).ownerId == userID) {
                System.err.println("Este usuário ja alugou esse livro.");
                return false;
            }
            System.err.println("Livro já alugado por outro usuário");
            return false;
        }
        // tratamento de erros e casos inválidos

        printBook(estante, bookIndex);
        System.out.println("deseja alugar este livro?(s/sim ou n/não): \n");
        vaiAlugar = (input.next()).substring(0, 1);

        if (!vaiAlugar.equalsIgnoreCase("S")) {
            System.out.println("Cancelado.");
            return false;
        }
        // Caso o usuario não queira alugar

        estante.get(bookIndex).alugar(users, userID);
        users.get(userID).alugar(estante, bookIndex);
        printBook(estante, (bookIndex));

        System.out.println(estante.get(bookIndex).name);
        return true;
    }

    public static Book addBook() {
        String nomeLivro;
        String autor;
        int year;

        Ignore = input.nextLine();
        System.out.println("\nQual o nome do livro?");
        nomeLivro = input.nextLine();

        System.out.println("Qual o nome do autor do seu livro?");
        autor = input.nextLine();

        System.out.println("Em qual ano seu livro foi publicado?");
        year = input.nextInt();

        return new Book(nomeLivro, autor, 0, year);
    }

    public static void devolucao(ArrayList<User> users, ArrayList<Book> estante) {
        int userID;
        System.out.println("ID do usuário.");
        userID = input.nextInt();
        userID = userID - 1;
        if (userID < 0 || userID >= users.size()) {
            System.out.println("ID de usuário inválido.");
            return;
        }
        // previne "out of bounds"
        if (!users.get(userID).alugando) {
            System.out.println("não está alugando nenhum livro.");
            return;
        }
        int bookIndex = users.get(userID).bookID;
        users.get(userID).devolução();
        estante.get(bookIndex).devolução();
        System.out.println("Devolução bem-sucedida");

    }

    public static void mostrarLivros(ArrayList<Book> estante) {
        // função para mostrar todos os livros existentes

        for (int i = 0; i < estante.size(); i++) {
            System.out.println("Nome: " + estante.get(i).name + ", ID: " + (i + 1));
        }
    }

    public static void mostrarUsuarios(ArrayList<User> users) {
        // função para mostrar todos os usuarios existentes

        for (int i = 0; i < users.size(); i++) {
            System.out.println("Nome: " + users.get(i).name + ", ID: " + (i + 1));
        }
    }
}
