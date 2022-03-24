package br.uffs.cc.jarena;

public class EdnaldoPereira extends Agente {
	private int direcao;
	private long horaProximoEstado;

	public EdnaldoPereira(Integer x, Integer y, Integer energia) {
		super(x, y, energia);

		direcao = DIREITA;
		horaProximoEstado = 0;
	}

	public void pensa() {
		this.movimenta();
	}

	private void movimenta() {
		long tempoAgora = System.currentTimeMillis();
		long tempoRestanteMudarEstado = horaProximoEstado - tempoAgora;

		decideEstrategia(tempoRestanteMudarEstado);

		if (podeMoverPara(direcao))
			setDirecao(direcao);

		if (tempoRestanteMudarEstado <= 0) {
			int tempoAndando = 2000;

			horaProximoEstado = tempoAgora + tempoAndando;
		}
	}

	private void decideEstrategia(long tempoRestanteMudarEstado) {
		direcao = NENHUMA_DIRECAO;

		if (getX() <= Constants.LARGURA_MAPA - 100) {
			if (tempoRestanteMudarEstado < 1200) {
				direcao = DIREITA;
			} else {
				direcao = BAIXO;
			}
		} else if (getY() > Constants.ALTURA_MAPA / 2) {
			if (tempoRestanteMudarEstado < 1200) {
				direcao = DIREITA;
			} else {
				direcao = CIMA;
			}
		} 

		if (getX() >= 200 && tempoRestanteMudarEstado < 400) {
			direcao = geraDirecaoAleatoria();
		}

		if(getX() >= Constants.LARGURA_MAPA - 50) {
			direcao = ESQUERDA;
		} else if(getX() <= 50) {
			direcao = DIREITA;
		}

		if(getY() >= Constants.ALTURA_MAPA - 50) {
			direcao = CIMA;
		} else if (getY() <= 50) {
			direcao = BAIXO;
		}
	}

	public void recebeuEnergia() {
		if (podeDividir() && getEnergia() > 1000) {
			divide();
		}
	}

	public void tomouDano(int energiaRestanteInimigo) {
		if (getEnergia() > energiaRestanteInimigo)
			para();
	}

	public void ganhouCombate() {
		para();
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
