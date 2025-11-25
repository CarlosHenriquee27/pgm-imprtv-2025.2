public class Produto {
    private String nome;
    private int quantidade;
    private double preco;
    private String categoria;
    private int qtdMinima;

    public Produto(String nome, int quantidade, double preco, String categoria, int qtdMinima) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
        this.qtdMinima = qtdMinima;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getValorTotal() {
        return preco * quantidade;
    }

    public void setPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    @Override
    public String toString() {
        return nome + " - " + quantidade + " un - R$" + preco + " - Categoria: " + categoria;
    }
}

