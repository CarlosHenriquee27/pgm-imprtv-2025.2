import java.util.*;

public class ControleEstoque {
    static Scanner scanner = new Scanner(System.in);
    static List<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            menu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listar();
                case 3 -> filtrarCategoria();
                case 4 -> ordenarPorNome();
                case 5 -> removerProduto();
                case 6 -> atualizarPreco();
                case 7 -> listarComSubtotalPorCategoria();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    static void menu() {
        System.out.println("""
            1 - Cadastrar produto
            2 - Listar
            3 - Filtrar por categoria
            4 - Ordenar
            5 - Remover elemento
            6 - Atualizar preço
            7 - Listagem com subtotal por categoria
            0 - Sair
        """);
    }

    static void cadastrarProduto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade: ");
        int qtd = scanner.nextInt();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Qtd mínima: ");
        int qtdMin = scanner.nextInt();
        produtos.add(new Produto(nome, qtd, preco, categoria, qtdMin));
        System.out.println("Produto cadastrado!");
    }

    static void listar() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    static void filtrarCategoria() {
        System.out.print("Informe a categoria: ");
        String cat = scanner.nextLine();
        for (Produto p : produtos) {
            if (p.getCategoria().equalsIgnoreCase(cat)) {
                System.out.println(p);
            }
        }
    }

    static void ordenarPorNome() {
        produtos.sort(Comparator.comparing(p -> p.toString()));
        System.out.println("Produtos ordenados.");
    }

    static void removerProduto() {
        listar();
        System.out.print("Digite o nome do produto para remover: ");
        String nome = scanner.nextLine();
        produtos.removeIf(p -> p.toString().contains(nome));
        System.out.println("Produto removido (se existia).");
    }

    static void atualizarPreco() {
        listar();
        System.out.print("Digite o nome do produto para atualizar o preço: ");
        String nome = scanner.nextLine();
        for (Produto p : produtos) {
            if (p.toString().contains(nome)) {
                System.out.print("Novo preço: ");
                double novo = scanner.nextDouble();
                p.setPreco(novo);
                System.out.println("Preço atualizado!");
                return;
            }
        }
    }

    static void listarComSubtotalPorCategoria() {
        Map<String, Double> subtotais = new TreeMap<>();
        for (Produto p : produtos) {
            subtotais.put(p.getCategoria(), subtotais.getOrDefault(p.getCategoria(), 0.0) + p.getValorTotal());
        }

        double totalGeral = 0;
        for (String cat : subtotais.keySet()) {
            System.out.println("Categoria: " + cat);
            for (Produto p : produtos) {
                if (p.getCategoria().equals(cat)) {
                    System.out.println("  " + p);
                }
            }
            double subtotal = subtotais.get(cat);
            totalGeral += subtotal;
            System.out.printf("Subtotal: R$ %.2f%n", subtotal);
        }
        System.out.printf("Total Geral: R$ %.2f%n", totalGeral);
    }
}
