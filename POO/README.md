# Reserva de hotéis

Este projeto é um sistema de reservas de hotel desenvolvido em Java. O sistema permite gerenciar hóspedes, quartos e reservas, oferecendo funcionalidades para adicionar hóspedes, fazer reservas, visualizar reservas e quartos disponíveis

## Funcionalidades

* Adicionar Hóspede: Permite adicionar novos hóspedes ao sistema.
* Fazer Reserva: Permite fazer reservas de quartos para hóspedes existentes, exibindo os quartos disponíveis.
* Ver Reservas: Exibe todas as reservas feitas, incluindo detalhes como nome do hóspede, número do quarto, datas de check-in e check-out, e o preço total.
* Ver Quartos Disponíveis: Exibe todos os quartos que estão disponíveis para reserva.

## Estrutura do Projeto
O projeto é composto pelas seguintes classes:

* Hospede: Representa um hóspede do hotel, com atributos como nome e CPF.
* Quarto: Classe abstrata que representa um quarto do hotel, com atributos como número e preço da diária. Inclui o método abstrato calcularPreco.
* QuartoSimples: Subclasse de Quarto que implementa o método calcularPreco para calcular o preço da estadia.
* Suite: Subclasse de Quarto que implementa o método calcularPreco com desconto para estadias longas.
* Reserva: Representa uma reserva de um quarto por um hóspede, com atributos como hóspede, quarto, datas de check-in e check-out. Inclui o método calcularPreco para calcular o preço total da reserva.
* Hotel: Gerencia a lista de quartos, reservas e hóspedes. Inclui métodos para adicionar quartos, reservas e hóspedes, além de buscar hóspedes por CPF e quartos por número.
* Main: Classe principal que contém o menu interativo para o usuário, permitindo acessar as funcionalidades do sistema.