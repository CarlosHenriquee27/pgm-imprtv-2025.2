
import java.util.Scanner;

class Pessoa {
    String nome;
    int idade;
    double peso;
    double altura;
}

public class Avaliacao2 {

    static int buscaPorNome(Pessoa[] v, int qtd, String nome) {
        for (int i = 0; i < qtd; i++) {
            if (v[i].nome.equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    static int cadastrarPessoa(Pessoa[] v, int qtd, Scanner sc) {
        if (qtd == v.length) {
            System.out.println("Vetor cheio!");
            return qtd;
        }

        sc.nextLine();

        String nome;
        while (true) {
            System.out.print("Nome: ");
            nome = sc.nextLine().trim();

            if (buscaPorNome(v, qtd, nome) == -1) break;
            System.out.println("Já existe pessoa com esse nome!");
        }

        System.out.print("Idade: ");
        int idade = sc.nextInt();

        System.out.print("Peso: ");
        double peso = sc.nextDouble();

        System.out.print("Altura: ");
        double altura = sc.nextDouble();

        Pessoa p = new Pessoa();
        p.nome = nome;
        p.idade = idade;
        p.peso = peso;
        p.altura = altura;

        v[qtd] = p;
        return qtd + 1;
    }

    static double imc(Pessoa p) {
        return p.peso / (p.altura * p.altura);
    }

    static void imprimirPessoas(Pessoa[] v, int qtd) {
        for (int i = 0; i < qtd; i++) {
            Pessoa p = v[i];
            System.out.printf("Nome: %s | Idade: %d | IMC: %.2f\n",
                    p.nome, p.idade, imc(p));
        }
    }

    static int maisVelhaMagreza(Pessoa[] v, int qtd) {
        int idx = -1;
        int maiorIdade = -1;

        for (int i = 0; i < qtd; i++) {
            if (imc(v[i]) < 18.5) {
                if (v[i].idade > maiorIdade) {
                    maiorIdade = v[i].idade;
                    idx = i;
                }
            }
        }
        return idx;
    }

    static void insertionSort(Pessoa[] v, int qtd) {
        for (int i = 1; i < qtd; i++) {
            Pessoa aux = v[i];
            int j = i - 1;

            while (j >= 0 && v[j].nome.compareToIgnoreCase(aux.nome) > 0) {
                v[j + 1] = v[j];
                j--;
            }
            v[j + 1] = aux;
        }
    }

    static int contarCategoria(Pessoa[] v, int qtd, int tipo) {
        int cont = 0;

        for (int i = 0; i < qtd; i++) {
            double x = imc(v[i]);

            if (tipo == 1 && x < 18.5) cont++;
            else if (tipo == 2 && x >= 18.5 && x < 25) cont++;
            else if (tipo == 3 && x >= 25 && x < 30) cont++;
            else if (tipo == 4 && x >= 30) cont++;
        }

        return cont;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Pessoa[] v = new Pessoa[50];
        int qtd = 0;

        while (true) {
            System.out.println("\n1 - Cadastrar");
            System.out.println("2 - Imprimir");
            System.out.println("3 - Mais velha magreza");
            System.out.println("4 - Ordenar por nome");
            System.out.println("5 - Contar por categoria IMC");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            int op = sc.nextInt();

            if (op == 0) break;

            switch (op) {
                case 1:
                    qtd = cadastrarPessoa(v, qtd, sc);
                    break;

                case 2:
                    imprimirPessoas(v, qtd);
                    break;

                case 3:
                    int idx = maisVelhaMagreza(v, qtd);
                    if (idx == -1) System.out.println("Nenhuma pessoa com magreza.");
                    else System.out.println("Pessoa: " + v[idx].nome);
                    break;

                case 4:
                    insertionSort(v, qtd);
                    System.out.println("Ordenado!");
                    break;

                case 5:
                    System.out.println("1-Magreza  2-Normal  3-Sobrepeso  4-Obesidade");
                    int t = sc.nextInt();
                    int c = contarCategoria(v, qtd, t);
                    System.out.println("Quantidade: " + c);
                    break;
            }
        }

        sc.close();
    }
}
