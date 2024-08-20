public class QuartoSimples extends Quarto {
    public QuartoSimples(String numero, double precoDiaria) {
        super(numero, precoDiaria);
    }

    @Override
    public double calcularPreco(int dias) {
        return getPrecoDiaria() * dias;
    }
}
