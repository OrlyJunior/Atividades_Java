package usuarios;

import java.util.ArrayList;
import java.util.Scanner;

public class daoUsers implements inter<User> {
	Scanner leitor = new Scanner(System.in);

	public boolean post(ArrayList<User> lista) {
		boolean continuar = true;

		int id = 0;

		if (lista.isEmpty() == false) {
			id = lista.getLast().getId();
		}

		while (continuar == true) {
			continuar = false;

			id++;

			User user = new User();

			System.out.println("Insira seu nome:");
			user.setNome(leitor.nextLine());

			System.out.println("Insira sua senha:");
			user.setSenha(leitor.nextLine());;

			System.out.println("Este usuário está ativo? S - Sim  N - Não");
			
			String ativado = leitor.nextLine();

			if (ativado.toLowerCase().equals("s")) {
				user.setAtivo(true);
			}else {
				user.setAtivo(false);
			}

			user.setId(id);

			lista.add(user);

			for (User i : lista) {
				System.out.println(i.toString());
			}

			System.out.println("Deseja continuar?");

			String cont = leitor.nextLine();

			if (cont.toLowerCase().equals("s")) {
				continuar = true;
			}
		}

		return true;
	}

	@Override
	public void delete(ArrayList<User> lista) {
		boolean continuar = true;

		while (continuar == true) {
			continuar = false;
			System.out.println("Insira o Id do usuário que deseja deletar:");

			int id = leitor.nextInt();

			try {
				lista.remove(id - 1);
			} catch (java.util.NoSuchElementException e) {
				System.out.println("Este usuário não existe!");
			} catch (java.lang.IndexOutOfBoundsException x) {
				System.out.println("Este usuário não existe!");
			}

			System.out.println("Deseja continuar?");

			String cont = leitor.nextLine();

			if (cont.toLowerCase().equals("s")) {
				continuar = true;
			}
		}
	}
	
	public boolean put(ArrayList<User> lista) {
		boolean continuar = true;

		while (continuar == true) {
			continuar = false;
			System.out.println("Insira o Id do usuário que deseja alterar:");

			int id = leitor.nextInt();

			leitor.nextLine();

			User user = new User();

			System.out.println("Insira seu novo nome:");
			user.setNome(leitor.nextLine());

			System.out.println("Insira sua nova senha:");
			user.setSenha(leitor.nextLine());

			System.out.println("Este usuário está ativo? S - Sim  N - Não");
			String ativado = leitor.nextLine();

			if (ativado.toLowerCase().equals("s")) {
				user.setAtivo(true);
			}else {
				user.setAtivo(false);
			}

			try {
				user.setId(lista.get(id - 1).getId());
				
				lista.set(id - 1, user);
			} catch (java.util.NoSuchElementException e) {
				System.out.println("Este usuário não existe!");
			} catch (java.lang.IndexOutOfBoundsException x) {
				System.out.println("Este usuário não existe!");
			}
			
			System.out.println("Deseja continuar?");

			String cont = leitor.nextLine();

			if (cont.toLowerCase().equals("s")) {
				continuar = true;
			}
		}

		return true;
	}

	public ArrayList<User> get(ArrayList<User> lista) {
		ArrayList<User> users = new ArrayList<User>();

		for (User i : lista) {
			users.add(i);
		}

		for (User i : users) {
			System.out.println(i.toString());
		}

		System.out.println("Pressione enter para retornar ao menu");

		leitor.nextLine();

		return users;
	}

	public User getId(ArrayList<User> lista) {
		User user = new User();

		System.out.println("Insira o Id do usuário que deseja ver:");

		int id = leitor.nextInt();

		leitor.nextLine();

		try {
			user = lista.get(id - 1);

			System.out.println(user.toString());
		} catch (java.util.NoSuchElementException e) {
			System.out.println("Este usuário não existe!");
		} catch (java.lang.IndexOutOfBoundsException x) {
			System.out.println("Este usuário não existe!");
		}

		System.out.println("Pressione enter para retornar ao menu");

		leitor.nextLine();

		return user;
	}
}