import java.util.ArrayList;
import java.util.Scanner;

public class Administrador {

    private String nomeUsuario;
    private String senha;

    public Administrador(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public static void cadastrarFilme(ArrayList<Filme> filmes) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Nome do filme: ");
        String titulo = teclado.nextLine();
        System.out.print("Duração (minutos): ");
        int duracao = teclado.nextInt();
        System.out.print("Ano de lançamento: ");
        int anoLancamento = teclado.nextInt();
        teclado.nextLine();
        Filme novoFilme = new Filme(titulo, duracao, anoLancamento); // Cria uma nova instância do filme com os dados fornecidos
        filmes.add(novoFilme); // Adiciona o filme à lista de filmes
        System.out.println("\nFilme cadastrado com sucesso: " + titulo + " (" + anoLancamento + ")");
        App.menuAdministrador();
    }

    public static void cadastrarAtor(ArrayList<Ator> atores) {
        Scanner teclado = new Scanner(System.in);
        List<String> cadastrar = new ArrayList<>();
        System.out.println("Quantos atores quer cadastar?");
        int quantidade = teclado.nextInt();
        teclado.nextLine();
        for(int i = 0; i<quantidade; i++){
            System.out.print("Digite o nome do " + (i+1) + "° ator: ");
            String nome = teclado.nextLine();
            boolean valid = false;
            String input = "";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while (!valid){
                System.out.print("Data de nascimento (dd/MM/yyyy): ");
                input = teclado.nextLine();
                if (input.matches("\\d{2}/\\d{2}/\\d{4}")){
                    try{
                        LocalDate date = LocalDate.parse(input,formatter);
                        valid = true;
                        atores.add(new Ator(nome, input));
                    } catch (DateTimeParseException e){
                        System.out.println("Erro: Data inválida. Por favor, digite uma data válida.");
                    }
                } else{
                    System.out.println("Erro: Formato invalido. Por favor, use o formato DD/MM/AA.");
                }
            }
            System.out.println("\nAtor cadastrado com sucesso: " + nome + "- (" + input + ")");
        }
        App.menuAdministrador();

    }

    public static void cadastrarDiretor(ArrayList<Diretor> diretores) {
        Scanner teclado = new Scanner(System.in);
        List<String> cadastrar = new ArrayList<>();
        System.out.println("Quantos diretores quer cadastar?");
        int quantidade = teclado.nextInt();
        teclado.nextLine();
        for(int i = 0; i<quantidade; i++){
            System.out.print("Digite o nome do " + (i+1) + "° diretor: ");
            String nome = teclado.nextLine();
            boolean valid = false;
            String input = "";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while (!valid){
                System.out.print("Data de nascimento (dd/MM/yyyy): ");
                input = teclado.nextLine();
                if (input.matches("\\d{2}/\\d{2}/\\d{4}")){
                    try{
                        LocalDate date = LocalDate.parse(input,formatter);
                        valid = true;
                        diretores.add(new Diretor(nome, input));
                    } catch (DateTimeParseException e){
                        System.out.println("Erro: Data inválida. Por favor, digite uma data válida.");
                    }
                } else{
                    System.out.println("Erro: Formato invalido. Por favor, use o formato DD/MM/AA.");
                }
            }
            
            
            System.out.println("\nDiretor cadastrado com sucesso: " + nome + " - (" + input + ")\n");
        }
        App.menuAdministrador();
    }

    public static void listarFilmes(ArrayList<Filme> filmes) {
        if (filmes.isEmpty()) {
            System.out.println("\nNenhum filme cadastrado.");
        } else {
            System.out.println("\n--- FILMES CADASTRADOS ---");
            for (Filme filme : filmes) {
                System.out.println("- " + filme.getTitulo() + " (" + filme.getAnoLancamento() + ")");
            }
        }
        App.menuAdministrador();
    }

    public static void listarAtores(ArrayList<Ator> atores) {
        if (atores.isEmpty()) {
            System.out.println("\nNenhum ator cadastrado.");
        } else {
            System.out.println("\n--- ATORES CADASTRADOS ---");
            for (Ator ator : atores) {
                System.out.println("- " + ator.getNome() + " (Nascido em: " + ator.getDataNascimento() + ")");
            }
        }
        App.menuAdministrador();
    }

    public static void listarDiretores(ArrayList<Diretor> diretores) {
        if (diretores.isEmpty()) {
            System.out.println("\nNenhum diretor cadastrado.");
        } else {
            System.out.println("\n--- DIRETORES CADASTRADOS ---");
            for (Diretor diretor : diretores) {
                System.out.println("- " + diretor.getNome() + " (Nascido em: " + diretor.getDataNascimento() + ")");
            }
        }
        App.menuAdministrador();
    }

    public static void removerFilme(ArrayList<Filme> filmes) {
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            for (Filme filme : filmes) {
                System.out.println("- " + filme.getTitulo() + " (" + filme.getAnoLancamento() + ")");
            }
            Scanner teclado = new Scanner(System.in);
            System.out.print("Informe o nome do filme a ser removido: ");
            String nome = teclado.nextLine();

            boolean removido = filmes.removeIf(filme -> filme.getTitulo().equalsIgnoreCase(nome)); // Remove o filme da lista com base no nome
    
            if (removido) {
                System.out.println("\nFilme: " + nome + " removido com sucesso!");
            } else {
                System.out.println("\nFilme: '"+ nome + "' não encontrado!");
                removerFilme(filmes);
            }
        }
        App.menuAdministrador();
    }

    public static void removerAtor(ArrayList<Ator> atores) {
        if (!atores.isEmpty()) {
            Scanner teclado = new Scanner(System.in);
            System.out.print("Informe o nome do ator a ser removido: ");
            String nome = teclado.nextLine();
            atores.removeIf(ator -> ator.getNome().equalsIgnoreCase(nome));
            System.out.println("\nAtor: " + nome + " removido com sucesso!");
        }
        App.menuAdministrador();
    }

    public static void removerDiretor(ArrayList<Diretor> diretores) {
        if (!diretores.isEmpty()) {
            Scanner teclado = new Scanner(System.in);
            System.out.print("Informe o nome do diretor a ser removido: ");
            String nome = teclado.nextLine();
            diretores.removeIf(diretor -> diretor.getNome().equalsIgnoreCase(nome));
            System.out.println("\n Diretor: " + nome + " removido com sucesso!");
        }
        App.menuAdministrador();
    }    
}
