import datagen.FichaUtente;
/**
 * 
 * Classe criada apenas para trabalhar com utentes urgentes,
 * agentes especializados (médicos) e agentes não especializados (enfermeiros)
 * Extende a classe abstracta ConsultaAbs e é implementada pela interface ConsultaUrgenteInterface
 */

public class ConsultaUrgente extends ConsultaAbs implements ConsultaUrgenteInterface {

	private AgenteDeSaudeInterface enf;
	private AgenteDeSaudeInterface med;

	
	public ConsultaUrgente( AgenteDeSaudeInterface med, AgenteDeSaudeInterface enf, FichaUtente u){
		super(u);    //utente 'u' do construtor da classe abstracta
		this.enf = enf;
		this.med = med;

		
		med.afectar(u);    //associar ao utente urgente um agente especializado
		enf.afectar(u);	   //associar ao utente urgente um agente não especializado
	}
	
	public void terminarConsulta(){
		
		med.desafectar();    //desassociar ao utente urgente um agente especializado
		enf.desafectar();	 //desassociar ao utente urgente um agente não especializado
	}

	public AgenteDeSaudeInterface getMedico() {
		
		return this.med;			//buscar um agente especializado
	}

	public AgenteDeSaudeInterface getEnfermeiro() {
	
		return this.enf; 			//buscar um agente não especializado
	}
}
