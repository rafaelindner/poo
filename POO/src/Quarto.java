public abstract class Quarto {
    private String numero;
    private double precoDiaria;

    public Quarto(String numero, double precoDiaria) {
        this.numero = numero;
        this.precoDiaria = precoDiaria;
    }

    public String getNumero() {
        return numero;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public abstract double calcularPreco(int dias);
}
