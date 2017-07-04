import datagen.FichaUtente;

/**
 * 
 * Inteface que controla consultas, sejam normais ou urgentes
 *
 */
public interface ConsultaInterface {

	public void terminarConsulta();
	public FichaUtente getPaciente();
	public AgenteDeSaudeInterface getMedico();
}
