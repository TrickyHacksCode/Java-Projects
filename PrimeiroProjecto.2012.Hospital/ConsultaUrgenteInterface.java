
/**
 * 
 * Interface criada apenas para acrescentar m�todos adicionais aos de uma consulta normal
 *
 */

public interface ConsultaUrgenteInterface extends ConsultaInterface{
	
	
	/**
	 * M�todo para ir buscar um agente n�o especializado para atribuir a um utente urgente
	 * N�o s�o necess�rios m�todos para atribuir um agente especializado e buscar pacientes
	 * porque j� est�o declarados na 'ConsultaInterface'
	 */
	public AgenteDeSaudeInterface getEnfermeiro();   
	
}
