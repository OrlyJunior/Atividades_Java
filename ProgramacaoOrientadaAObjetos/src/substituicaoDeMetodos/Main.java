package substituicaoDeMetodos;

public class Main {
	public static void main(String[] args) {
		veiculo veiculo = new veiculo();
		carro carro = new carro();
		moto moto = new moto();
		onibus onibus = new onibus();
		
		System.out.println(veiculo.numeroDeRodas());
		System.out.println(carro.numeroDeRodas());
		System.out.println(moto.numeroDeRodas());
		System.out.println(onibus.numeroDeRodas());
	}
}