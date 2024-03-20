package usuarios;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		daoUsers dao = new daoUsers();

		ArrayList<User> users = new ArrayList<User>();

		Scanner reader = new Scanner(System.in);

		boolean loop = true;

		while (loop == true) {
			System.out.println("---Sistema de usuários---");
			System.out.println("Qual operação deseja realizar?");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Editar");
			System.out.println("3 - Deletar");
			System.out.println("4 - Consultar");
			System.out.println("5 - Consultar por Id");

			String opcao = reader.nextLine();

			if (opcao.equals("1")) {
				dao.post(users);
			} else if (opcao.equals("2")) {
				dao.put(users);
			} else if (opcao.equals("3")) {
				dao.delete(users);
			} else if (opcao.equals("4")) {
				dao.get(users);
			} else if (opcao.equals("5")) {
				dao.getId(users);
			}
		}
	}
}