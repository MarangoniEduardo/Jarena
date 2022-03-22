package br.uffs.cc.jarena;

public class EdnaldoPereira extends Agente {
	private int OPOSTO_DIREITA = ESQUERDA;
	private int OPOSTO_ESQUERDA = DIREITA;
	private int OPOSTO_BAIXO = CIMA;
	private int OPOSTO_CIMA = BAIXO;

	private static int numeroDeRodadas = 0;

	public EdnaldoPereira(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		setDirecao(geraDirecaoAleatoria());
	}

	public void pensa() {
		lidaComFinalDoMapa(getDirecao());

		decideMovimento();

		// Se o agente conseguie se dividir (tem energia) e se o total de energia
		// do agente é maior que 400, nos dividimos. O agente filho terá a metade
		// da nossa energia atual.
		if (podeDividir() && getEnergia() >= 400) {
			divide();
		}
	}

	private void lidaComFinalDoMapa(int direcao) {
		if (!podeMoverPara(direcao)) {
			if (direcao == ESQUERDA) {
				setDirecao(OPOSTO_ESQUERDA);
			} else if (direcao == DIREITA) {
				setDirecao(OPOSTO_DIREITA);
			} else if (direcao == CIMA) {
				setDirecao(OPOSTO_CIMA);
			} else if (direcao == BAIXO) {
				setDirecao(OPOSTO_BAIXO);
			}
		}
	}

	private void decideMovimento() {
		numeroDeRodadas++;

		if (numeroDeRodadas < 2000) {
			para();
		} else {
			if (numeroDeRodadas % 2 == 0) {
				setDirecao(DIREITA);
			} else {
				setDirecao(BAIXO);
			}
		}
	}

	public void recebeuEnergia() {
		// Invocado sempre que o agente recebe energia.

	}

	public void tomouDano(int energiaRestanteInimigo) {
		// Invocado quando o agente está na mesma posição que um agente inimigo
		// e eles estão batalhando (ambos tomam dano).

	}

	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.

	}

	public void recebeuMensagem(String msg) {
		// Invocado sempre que um agente aliado próximo envia uma mensagem.
		enviaMensagem("Você vale tudo");
	}

	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "Fernando".
		return "Ednaldo Pereira";
	}
}
