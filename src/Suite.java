public class Suite extends Quarto {
    private double desconto;

    public Suite(String numero, double precoDiaria, double desconto) {
        super(numero, precoDiaria);
        this.desconto = desconto;
    }

    @Override
    public double calcularPreco(int dias) {
        double precoTotal = getPrecoDiaria() * dias;
        if (dias > 7) {
            precoTotal *= (1 - desconto);
        }
        return precoTotal;
    }
}
