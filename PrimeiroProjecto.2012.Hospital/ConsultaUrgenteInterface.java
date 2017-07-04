
/**
 * 
 * Interface criada apenas para acrescentar métodos adicionais aos de uma consulta normal
 *
 */

public interface ConsultaUrgenteInterface extends ConsultaInterface{
	
	
	/**
	 * Método para ir buscar um agente não especializado para atribuir a um utente urgente
	 * Não são necessários métodos para atribuir um agente especializado e buscar pacientes
	 * porque já estão declarados na 'ConsultaInterface'
	 */
	public AgenteDeSaudeInterface getEnfermeiro();   
	
}
