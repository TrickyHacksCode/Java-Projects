import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import datagen.FichaUtente;

public class UnidadeDeSaude implements UnidadeDeSaudeInterface {

	/**
	 * Esta classe é a mais importante após a Main.
	 * a 'UnidadeDeSaude' é o cerebro da aplicação porque é nela que maior parte da informação reside.
	 * Vai chamar métodos a outras classes para depois na Main, ao introduzir-se os comandos,
	 * as tarefas serem efectuadas. 
	 */
	
	
	/**
	 * Os Metoddos
	 */
	
	private static final int URGENCIA_PERIGO_DE_VIDA = 2;
	private static final int SEM_URGENCIA = 1;
	private List<FichaUtente> lista_espera;
	private List<AgenteDeSaudeInterface> agentes_disponiveis;
	private List<AgenteDeSaudeInterface> agentes_ocupados;
	private List<ConsultaInterface> consultas;
	
	
	public UnidadeDeSaude ()
	{
		lista_espera = new ArrayList<FichaUtente>();
		agentes_disponiveis = new ArrayList<AgenteDeSaudeInterface>();
		agentes_ocupados = new ArrayList<AgenteDeSaudeInterface>();
		consultas = new ArrayList<ConsultaInterface>();
		
	}
	
	public void adicionar_utente(FichaUtente fu){	// verificar que dados sao necessarios para os utentes
		lista_espera.add(fu);
	}
	
	public void adicionar_agente(AgenteDeSaude a){
		
		agentes_disponiveis.add(a);
	}
	
	/**
	 * 	Devolve o elemento actual da lista de espera, verifica a especialidade da consulta pretendida pelo utente
		Verifica se ha um medico dessa especialidade disponivel, se houver, criar uma nova consulta com o medico
		encontrado e colocar essa pessoa na mesma (e consequentemente na lista de atendimento) e retornar mensagem
	 	de sucesso. Caso nao haja um medico deve retornar mensagem de erro.
	 */
	public String NovaConsulta() {
	
		FichaUtente u = ProximoUtente(); 
		System.out.println("Nome: "+ u.nome() + " | " + "Urgencia: "+ u.urgencia());
																
		if( u.urgencia() == SEM_URGENCIA){									    //se nao é um caso urgente
			
			AgenteDeSaudeInterface a = agente_disponivel(u.especialidade());    //Vai buscar `'AgenteDeSaudeInterface' um objecto agente que está disponivel e tem a especialidade pretendida pelo utente
		
			if( a != null ){  								  				    //Enquanto houver agente
				
				lista_espera.remove(u);										//remove a pessoa da lista de espera
				consultas.add( new ConsultaNormal(a, u));						//cria consulta com o medico
				agentes_ocupados.add(a);										//adiciona o agente na lista de agentes ocupados
				agentes_disponiveis.remove(a);									//remove o agente da lista dos agentes disponiveis
				
				return "Deu entrada:" + u.toString() + "\nAtribuído a " + a.nome() + "\t" + a.especialidade();
			}
			
			else{
				lista_espera.remove(u);						
				return "Não há agentes de saude disponíveis para a especialidade \n" +  buscarEspecialidadeUtente(u.especialidade()) + "\n Utente" + u.nome() + "colocado em lista de espera.";
			}
		}
		
			else{								
			if(agente_urgencia(u))
				lista_espera.remove(u);	
			
			return "";
		}
	} 
	
	private FichaUtente ProximoUtente(){						// retorna o proximo utente a ser atendido
		int i = -1;
		
		if( lista_espera.get(0).urgencia() == URGENCIA_PERIGO_DE_VIDA)	// se for urgente nao precisa de esperar
			return lista_espera.get(0);									// se nao for, vai procurar a primeira pessoa da lista com uma especialização cujo
																		// medico esteja disponivel
		
		for(  i= 0; i < lista_espera.size(); i++)		// percorrer toda a lista do inicio
			if( agente_disponivel( verificaEspecialidade(lista_espera.get(i)) ) != null)
				return lista_espera.get(i);
		
		return null;
	}
	
	private int verificaEspecialidade( FichaUtente u ){			// verifica as condicoes necessarias para a especialidade
		int especialidade = u.especialidade();
		
		if( (especialidade == 7 || especialidade == 1) && u.idade() > 12	)	// e um creep que quer pediatria com mais de 12 anos ou um gajo que nao sabe o que quer
			especialidade = 3;	// passar para clinica geral
		if( u.idade() <= 12 )
			especialidade = 7;	// passar para pediatria
		
		return especialidade;
	}
	
	/**
	 *  Criar uma variavel do tipo utente. Procurar em todos os agentes um que tenha como paciente um utente com o nome de utente dado.
	 * 	atribuir a essa variavel utente o paciente do medico correspondente. Desassociar o medico desse mesmo paciente, ou seja, agente disponivel novamente.
	 *  Fazer prints.
	 */

	public String TerminarConsulta(String Name) {
 
		ConsultaInterface c = getConsulta(Name);
		FichaUtente u;
		
		if( c != null ){	// se ha uma consulta com o utente
			
			if( c instanceof ConsultaUrgente){
				agentes_ocupados.remove( c.getMedico());
				agentes_ocupados.remove( ((ConsultaUrgente) c).getEnfermeiro()); // cast para ter acesso aos metodos da interface
				agentes_disponiveis.add(c.getMedico());
				agentes_disponiveis.add( ((ConsultaUrgente) c).getEnfermeiro()); //cast para ter acesso aos metodos da interface
			}
			else{
				agentes_ocupados.remove(c.getMedico());
				agentes_disponiveis.add( c.getMedico());
			}

			u = c.getPaciente();
			c.terminarConsulta();
				
			return "Atendimento de " + u.nome() + " concluído. \n";
		}
		
		else return "Utente inexistente.";	
	}
	

	public int em_espera(){
		return lista_espera.size(); 
	}
	
	public int agentes_ocupados(){
		return agentes_ocupados.size();
	}
	
	public int agentes_disponiveis(){
		return agentes_disponiveis.size();
	}
	
	private String buscarEspecialidadeUtente(int especialidade2) {
		switch (especialidade2)
		{
			case 1:
				return "SEM_ESPECIALIDADE";
			case 2:
				return "CARDIOLOGIA";
			case 3:
				return "CLINICA_GERAL";
			case 4:
				return "NEUROLOGIA";
			case 5:
				return "ORTOPEDIA";
			case 6:
				return "OFTALMOLOGIA";
			case 7:
				return "PEDIATRIA";
			case 8:
				return "PNEUMONOLOGIA";
			case 9:
				return "URULOGIA";
			default:
				return "";
		}
	}
	
	public Iterator<FichaUtente> ListarFilaDeEspera() {

		return lista_espera.iterator();		// retornar um iterador implementado pelo java da lista de espera
	}
	
	private ConsultaInterface getConsulta( String name ){		// retorna a consulta que tem o paciente name, se nao encontrar retorna null
		
		for( int i = 0; i < consultas.size(); i++ ) 
			if( consultas.get(i).getPaciente().nome().equals(name))
				return consultas.get(i);
		
		return null;
	}
	
	
	// retorna um agente da especialidade indicada
	private AgenteDeSaudeInterface agente_disponivel( int especialidade ){
		
		for( int i = 0; i < agentes_disponiveis.size(); i++){
			AgenteDeSaudeInterface a = agentes_disponiveis.get(i);
			if( a.temEspecialidade()&& a.especialidade() == especialidade )
				return a;
		}
		return null;	// default é mesmo obrigatorio retornar qualquer coisa
	}
	
	/**
	 * Procurar um medico e um enfermeiro que estejam disponiveis ou que estejam ocupados mas nao com utentes urgentes
	 * e associa-los ao nosso utente urgente. Quando terminar a consulta, devemos procurar o enfermeiro e 
	 * desassocia-lo tambem.
	 */
	private boolean agente_urgencia( FichaUtente fu){  //Metodo para um utente 'fu' 
		AgenteDeSaudeInterface med = null;
		AgenteDeSaudeInterface enf = null;
		AgenteDeSaudeInterface temp = null;	// agente temporário com ou sem especialidade
		
		for( int i = 0; i < agentes_disponiveis.size(); i++ ){	// procura nos medicos disponiveis
			temp = agentes_disponiveis.get(i);
			if( temp.temEspecialidade() ){
				med = temp;
			}
		}	
		
		if( med == null ){
			for( int i = 0; i < agentes_ocupados.size(); i++ ){	// procura no medico ha menos tempo a atender
				temp = agentes_ocupados.get(i);
				if( temp.temEspecialidade() && temp.urgenciaDaOcupacao() != URGENCIA_PERIGO_DE_VIDA )
					med = temp;
			}
		}
		
		for( int i = 0; i < agentes_disponiveis.size(); i++ ){	// procura nos enfermeiros disponiveis
			temp = agentes_disponiveis.get(i);
			if( !temp.temEspecialidade() ){
				enf = temp;
			}
		}
		
		if( med != null && enf != null){
			consultas.add(new ConsultaUrgente(med, enf, fu));
			agentes_disponiveis.remove(med);
			agentes_disponiveis.remove(enf);
			agentes_ocupados.add(med);
			agentes_ocupados.add(enf);
			return true;
		}
		
		return false;
	}

	
	
	public Iterator<AgenteDeSaudeInterface> ListarAgentesOcupados() {
		return agentes_ocupados.iterator();
	}
	
	
	
	public Iterator<AgenteDeSaudeInterface> ListarAgentesDisponiveis() {
		return agentes_disponiveis.iterator();
	}
	

}

