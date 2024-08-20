import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        // add alguns quartos aqui:
        hotel.adicionarQuarto(new QuartoSimples("100", 100.0));
        hotel.adicionarQuarto(new QuartoSimples("101", 100.0));
        hotel.adicionarQuarto(new QuartoSimples("102", 100.0));
        hotel.adicionarQuarto(new Suite("201", 200.0, 0.1));

        boolean running = true;

        while (running) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Hospede");
            System.out.println("2. Fazer Reserva");
            System.out.println("3. Ver Reservas");
            System.out.println("4. Ver Quartos Disponiveis");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    adicionarHospede(scanner, hotel);
                    break;
                case 2:
                    fazerReserva(scanner, hotel);
                    break;
                case 3:
                    verReservas(hotel);
                    break;
                case 4:
                    verQuartosDisponiveis(hotel);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void adicionarHospede(Scanner scanner, Hotel hotel) {
        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do hóspede: ");
        String cpf = scanner.nextLine();
        Hospede hospede = new Hospede(nome, cpf);
        hotel.adicionarHospede(hospede);  // add o hospede ao hotel
        System.out.println("Hóspede " + nome + " adicionado com sucesso.");
    }

    private static void fazerReserva(Scanner scanner, Hotel hotel) {
        System.out.print("CPF do hóspede: ");
        String cpf = scanner.nextLine();
        Hospede hospede = hotel.buscarHospedePorCpf(cpf);  // busca o hospede na lista dos hospedes

        if (hospede == null) {
            System.out.println("Hospede não encontrado. Adicione o hospede primeiro.");
            return;
        }

        System.out.println("Quartos disponiveis:");
        for (Quarto quarto : hotel.getQuartos()) {
            boolean reservado = false;
            for (Reserva reserva : hotel.getReservas()) {
                if (reserva.getQuarto().getNumero().equals(quarto.getNumero())) {
                    reservado = true;
                    break;
                }
            }
            if (!reservado) {
                System.out.println("Quarto: " + quarto.getNumero() + " - Preço da diaria: " + quarto.getPrecoDiaria());
            }
        }

        System.out.print("numero do quarto: ");
        String numeroQuarto = scanner.nextLine();
        Quarto quarto = hotel.buscarQuartoPorNumero(numeroQuarto);  // Busca o quarto na lista de quartos

        if (quarto == null) {
            System.out.println("Quarto nao encontrado.");
            return;
        }

        System.out.print("Data de check-in (dd-MM-yyyy): ");
        String dataCheckInStr = scanner.nextLine();
        System.out.print("Data de check-out (dd-MM-yyyy): ");
        String dataCheckOutStr = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dataCheckIn = LocalDate.parse(dataCheckInStr, formatter);
            LocalDate dataCheckOut = LocalDate.parse(dataCheckOutStr, formatter);
            Reserva reserva = new Reserva(hospede, quarto, dataCheckIn, dataCheckOut);
            hotel.adicionarReserva(reserva);
            System.out.println("Reserva feita com sucesso. Preço: " + reserva.calcularPreco());
        } catch (Exception e) {
            System.out.println("formato de data invalido!!");
        }
    }

    private static void verReservas(Hotel hotel) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Reserva reserva : hotel.getReservas()) {
            System.out.println("Reserva: " + reserva.getHospede().getNome() + " - Quarto: " + reserva.getQuarto().getNumero() + " - Check-in: " + reserva.getDataCheckIn().format(formatter) + " - Check-out: " + reserva.getDataCheckOut().format(formatter) + " - Preço: " + reserva.calcularPreco());
        }
    }

    private static void verQuartosDisponiveis(Hotel hotel) {
        System.out.println("Quartos disponiveis:");
        for (Quarto quarto : hotel.getQuartos()) {
            boolean reservado = false;
            for (Reserva reserva : hotel.getReservas()) {
                if (reserva.getQuarto().getNumero().equals(quarto.getNumero())) {
                    reservado = true;
                    break;
                }
            }
            if (!reservado) {
                System.out.println("Quarto: " + quarto.getNumero() + " - Preço da diaria: " + quarto.getPrecoDiaria());
            }
        }
    }
}
