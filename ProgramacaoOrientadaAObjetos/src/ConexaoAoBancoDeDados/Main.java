package ConexaoAoBancoDeDados;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Conexao con = new Conexao();

		ConexaoProd conProd = new ConexaoProd();

		Scanner inserir = new Scanner(System.in);

		boolean continuar = true;

		while (continuar == true) {
			System.out.println("Você deseja realizar funções para produtos ou categorias?");

			System.out.println("1 - Categorias / 2 - Produtos");

			int escolher = inserir.nextInt();

			if (escolher == 1) {
				System.out.println("Insira qual operação deseja realizar");
				System.out.println("1 - Consultar / 2 - Consultar por ID / 3 - Inserir / 4 - Deletar / 5 - Editar");

				int operacao = inserir.nextInt();

				if (operacao == 1) {
					con.get();
				} else if (operacao == 2) {
					con.getId();
				} else if (operacao == 3) {
					con.post();
				} else if (operacao == 4) {
					con.delete();
				} else if (operacao == 5) {
					con.put();
				}
			} else {
				System.out.println("Insira qual operação deseja realizar");
				System.out.println("1 - Consultar / 2 - Consultar por ID / 3 - Inserir / 4 - Deletar / 5 - Editar");

				int operacao = inserir.nextInt();

				if (operacao == 1) {
					conProd.get();
				}
			}

			System.out.println("Deseja continuar? 1 - Sim / 2 - Não");

			int cont = inserir.nextInt();

			if (cont != 1) {
				continuar = false;
			}
		}
	}
}