import java.util.Scanner;

public class ConjuntosSimples {

    public static int buscaSequencial(int[] v, int tam, int x) {
        for (int i = 0; i < tam; i++) {
            if (v[i] == x) return i;
        }
        return -1;
    }

    public static int inserirElemento(int[] v, int tam, int x) {
        if (tam >= v.length) {
            System.out.println("Conjunto cheio!");
            return tam;
        }
        if (buscaSequencial(v, tam, x) != -1) {
            System.out.println("Elemento já existe no conjunto!");
            return tam;
        }
        v[tam] = x;
        return tam + 1;
    }

    public static void imprimir(int[] v, int tam) {
        System.out.print("{ ");
        for (int i = 0; i < tam; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(v[i]);
        }
        System.out.println(" }");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] conjuntoA = new int[30];
        int[] conjuntoB = new int[30];
        int tamA = 0, tamB = 0;

        int opcao;

        do {
            System.out.println("\nMENU:");
            System.out.println("1) Inserir elemento no Conjunto A");
            System.out.println("2) Inserir elemento no Conjunto B");
            System.out.println("3) Imprimir os Conjuntos A e B");
            System.out.println("4) União de A e B");
            System.out.println("5) Interseção entre A e B");
            System.out.println("6) Diferença A - B");
            System.out.println("7) Diferença B - A");
            System.out.println("0) Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o elemento para A: ");
                    tamA = inserirElemento(conjuntoA, tamA, sc.nextInt());
                    break;
                case 2:
                    System.out.print("Digite o elemento para B: ");
                    tamB = inserirElemento(conjuntoB, tamB, sc.nextInt());
                    break;
                case 3:
                    System.out.print("Conjunto A: ");
                    imprimir(conjuntoA, tamA);
                    System.out.print("Conjunto B: ");
                    imprimir(conjuntoB, tamB);
                    break;
                case 4:
                    System.out.print("União: { ");
                    for (int i = 0; i < tamA; i++) {
                        if (i > 0) System.out.print(", ");
                        System.out.print(conjuntoA[i]);
                    }
                    for (int i = 0; i < tamB; i++) {
                        if (buscaSequencial(conjuntoA, tamA, conjuntoB[i]) == -1) {
                            System.out.print(", " + conjuntoB[i]);
                        }
                    }
                    System.out.println(" }");
                    break;
                case 5:
                    System.out.print("Interseção: { ");
                    boolean primeiro = true;
                    for (int i = 0; i < tamA; i++) {
                        if (buscaSequencial(conjuntoB, tamB, conjuntoA[i]) != -1) {
                            if (!primeiro) System.out.print(", ");
                            System.out.print(conjuntoA[i]);
                            primeiro = false;
                        }
                    }
                    System.out.println(" }");
                    break;
                case 6:
                    System.out.print("A - B: { ");
                    primeiro = true;
                    for (int i = 0; i < tamA; i++) {
                        if (buscaSequencial(conjuntoB, tamB, conjuntoA[i]) == -1) {
                            if (!primeiro) System.out.print(", ");
                            System.out.print(conjuntoA[i]);
                            primeiro = false;
                        }
                    }
                    System.out.println(" }");
                    break;
                case 7:
                    System.out.print("B - A: { ");
                    primeiro = true;
                    for (int i = 0; i < tamB; i++) {
                        if (buscaSequencial(conjuntoA, tamA, conjuntoB[i]) == -1) {
                            if (!primeiro) System.out.print(", ");
                            System.out.print(conjuntoB[i]);
                            primeiro = false;
                        }
                    }
                    System.out.println(" }");
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}
