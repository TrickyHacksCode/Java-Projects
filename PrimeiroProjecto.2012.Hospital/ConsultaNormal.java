import datagen.FichaUtente;


public class ConsultaNormal extends ConsultaAbs implements ConsultaInterface {

	private AgenteDeSaudeInterface med;

	
	public ConsultaNormal(AgenteDeSaudeInterface med, FichaUtente u) {
		super(u); //esta-se a usar o construtor da classe abstracta.
		this.med = med;

		
		med.afectar(u); //atribuir agente ao utente
	}
	
	public void terminarConsulta() {    //desassociar o medico do utente 
		med.desafectar();
		
	}

	public AgenteDeSaudeInterface getMedico() {
	
		return this.med;
	}

}
