import java.util.Iterator;

import datagen.FichaUtente;


public interface UnidadeDeSaudeInterface {
	
	public void adicionar_agente(AgenteDeSaude a);  //adiciona um agente
	public void adicionar_utente(FichaUtente fu);	// adiciona um utente
	public String NovaConsulta();  //Cria uma nova consulta. Atribui a um utente normal um agente especializado ou a um utente urgente dois agentes ( 1 especializado e um não especializado
	public int em_espera();	//retorna o tamanho da fila de espera
	public int agentes_ocupados();  //nº de agentes ocupados
	public int agentes_disponiveis();	//nº de agentes disponiveis
	public String TerminarConsulta(String Name);  //Metodo que termina uma consulta. Desassocia a um utente normal um agente especializado ou a utente urgente um agente nao especializado e um agente especializado
	public Iterator<FichaUtente> ListarFilaDeEspera();  //Iterador para listar fila de espera
	public Iterator<AgenteDeSaudeInterface> ListarAgentesOcupados();  //Iterador para listar os agentes especializados ocupados
	public Iterator<AgenteDeSaudeInterface> ListarAgentesDisponiveis();  //Iterador para listar os agentes especializados disponiveis
}