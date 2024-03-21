package substituicaoDeMetodos;

public class Main {
	public static void main(String[] args) {
		Veiculo veiculo = new Veiculo();
		Carro carro = new Carro();
		Moto moto = new Moto();
		Onibus onibus = new Onibus();
		
		System.out.println(veiculo.numeroDeRodas());
		System.out.println(carro.numeroDeRodas());
		System.out.println(moto.numeroDeRodas());
		System.out.println(onibus.numeroDeRodas());
	}
}