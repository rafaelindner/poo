import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Quarto> quartos;
    private List<Reserva> reservas;
    private List<Hospede> hospedes;

    public Hotel() {
        quartos = new ArrayList<>();
        reservas = new ArrayList<>();
        hospedes = new ArrayList<>();
    }

    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public Hospede buscarHospedePorCpf(String cpf) {
        for (Hospede hospede : hospedes) {
            if (hospede.getCpf().equals(cpf)) {
                return hospede;
            }
        }
        return null;
    }

    public Quarto buscarQuartoPorNumero(String numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero().equals(numero)) {
                return quarto;
            }
        }
        return null;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void salvarReservas(String arquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.getHospede().getNome() + "," +
                             reserva.getHospede().getCpf() + "," +
                             reserva.getQuarto().getNumero() + "," +
                             reserva.getDataCheckIn().format(DateTimeFormatter.ISO_LOCAL_DATE) + "," +
                             reserva.getDataCheckOut().format(DateTimeFormatter.ISO_LOCAL_DATE));
                writer.newLine();
            }
        }
    }

    public void carregarReservas(String arquivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                String cpf = partes[1];
                String numeroQuarto = partes[2];
                LocalDate dataCheckIn = LocalDate.parse(partes[3], DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate dataCheckOut = LocalDate.parse(partes[4], DateTimeFormatter.ISO_LOCAL_DATE);

                Hospede hospede = new Hospede(nome, cpf);
                Quarto quarto = buscarQuartoPorNumero(numeroQuarto);
                if (quarto != null) {
                    Reserva reserva = new Reserva(hospede, quarto, dataCheckIn, dataCheckOut);
                    adicionarReserva(reserva);
                }
            }
        }
    }
}
