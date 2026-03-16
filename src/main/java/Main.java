
import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[] nextUser = {1}; // para passar nas funções por referencia
        int[] nextBook = {1};
        // obra do tinhoso

        int choice = -1;
        ArrayList<User> usuarios = new ArrayList<>();
        ArrayList<Book> estante = base();
        while (true) {
            System.out.println("ESCOLHA UMA AÇÃO");
            System.out.println(" ");
            System.out.println("0. Sair do programa");
            System.out.println("1. Adcionar usuario");
            System.out.println("2. Adcionar livro");
            System.out.println("3. Alugar");
            System.out.println("4. Devolução");
            choice = input.nextInt();
            switch (choice) {
                case 0:
                    System.exit(1);
                    break;
                case 1:
                    usuarios.add(createUser());
                    break;
                case 2:

                default:
                    throw new AssertionError();
            }
            input.close();
        }
    }

    public static void printBook(Book Titulo) {
        System.out.println("O nome do seu livro é: " + Titulo.name);
        System.out.println("O autor do livro é: " + Titulo.author);
        System.out.println("O leitor atual é: " + Titulo.ownerId);
        System.out.println("O livro foi publicado em: " + Titulo.year);
        System.out.println("O livro está alugado: " + ((Titulo.ownerId != 0) ? "sim" : "não"));
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
        String nomePessoa;
        String password;
        System.out.println("\nQual o seu nome?");
        nomePessoa = input.next();

        System.out.println("digite uma senha para sua conta: ");
        password = input.next();

        return new User(nomePessoa, password);
    }

    public static void alugar(ArrayList<Book> estante, int[] id) {
        char vaiAlugar;
        int index;
        int temporario;
        boolean estaEmprestado;
        System.out.println("em qual lugar da estante está o seu livro(aperte zero para ver os lugares)");
        index = input.nextInt();
        index = index - 1;
        System.out.println("\n");

        if (index > -1 && index < estante.size()) {
            if (estante.get(index).ownerId > 0) {
                System.exit(1);
            }

            System.out.println("deseja alugar este livro?(s/sim ou n/não): \n");
            estante.get(index).ownerId = id[0];
            printBook(estante.get(index));
            vaiAlugar = input.next().charAt(0);
            estaEmprestado = (vaiAlugar == 's');

            if (!estaEmprestado) {
                System.exit(1);
            }

        } else if (index < 0) {
            for (int i = 0; i < estante.size(); i++) {
                temporario = i + 1;
                System.out.println("livro: " + estante.get(i).name + ", index: " + temporario);
            }
        }
        if (index > -1) {
            printBook(estante.get(index));
        }
    }
}
