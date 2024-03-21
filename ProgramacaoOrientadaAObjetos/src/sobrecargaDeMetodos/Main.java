package sobrecargaDeMetodos;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		
		Calculadora calculadora = new Calculadora();
		
		System.out.println("Insira o primeiro número:");
		int n1 = leitor.nextInt();
		
		System.out.println("Insira o segundo número:");
		int n2 = leitor.nextInt();
		leitor.nextLine();
		
		System.out.println("Deseja fazer uma operação com um número a mais? S - Sim  N - Não");
		String opcao = leitor.nextLine();
		
		if(opcao.toLowerCase().equals("s")) {
			System.out.println("Insira o terceiro número:");
			int n3 = leitor.nextInt();
			
			System.out.println(calculadora.soma(n1, n2, n3));
		} else {
			System.out.println(calculadora.soma(n1, n2));
		}
	}
}