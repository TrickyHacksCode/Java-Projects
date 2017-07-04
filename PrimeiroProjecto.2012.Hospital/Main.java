import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import datagen.FichaUtente;
import datagen.GeradorDeFichas;


/**
 * Trabalho realizado por:
 *  Alexandre Mogárrio nº36830
 *  Miguel Morais nº37135
 */


/**
 * A função Main é a função mais importante no projecto.
 * é nela que vai acontecer a interacção do utillizador com a Unidade de Saude.
 * O utilizador vai poder nalguns comandos introduzir valores de entrada como o nome do utente e a 
 * especialidade de um agente.
 * Os metodos são todos 'private static void' na main.
 */

public class Main {

	public static final String S = "S"; // Sair
	public static final String NC = "NC"; // Nova Consulta
	public static final String TC = "TC"; // Terminar Consulta
	public static final String OC = "OC"; // Numero de agentes disponiveis, ocupados e tamanho da fila de espera
	public static final String LD = "LD"; // Listar agents de saude disponiveis
	public static final String LO = "LO"; // Listar agentes de saude com paciente atribuido
	public static final String LE = "LE"; // Listar agentes com determinada especialidade
	public static final String LA = "LA"; //Listar todos os os agentes de saude
	public static final String LF = "LF"; // Listar os utentes em fila de espera
	public static final String A = "A"; // Ajuda
	public static final String TERMINAR = "Sessao terminada. Ate a proxima."; //String que aparece no de encerro da sessão
	public static final String PROMPT = "> ";

	public static void main(String[] args) {

		UnidadeDeSaudeInterface us = new UnidadeDeSaude();  //Criar um novo objecto
		importar_utentes(us);	// importa utentes para o sistema
		importar_agentes(us);   // importa agentes para o sistema
		
		Scanner in = new Scanner(System.in);

		System.out.print(PROMPT);
		String input = in.nextLine(); 					// ler primeiro comando que vai ser inserido na linha abaixo na consola

		while (!input.equalsIgnoreCase(S)) { 			// enquanto o comando actual for diferente de SAIR

			if (input.equalsIgnoreCase(A)) {
				ajuda(); 								//input = executa a opção que lista as opções que podem ser introduzidas na consola
			}					
			
			else if (input.equalsIgnoreCase(NC))		 // input = novaconsulta
				nova_consulta( us); 

			else if (input.equalsIgnoreCase(TC)) 			// input = terminar consulta
				terminar_consulta(in, us);

			else if (input.equalsIgnoreCase(OC)) 			//input = quantidade de agentes disponiveis, ocupados e lista de espera
				oc(us);

			else if (input.equalsIgnoreCase(LD))
				list_agentes_disponiveis(us);

			else if (input.equalsIgnoreCase(LO))
				list_agentes_ocupados(us);

			else if (input.equalsIgnoreCase(LE))
				list_agentes_especializados(in, us); 		//agentes com determinada especialidade

			else if (input.equalsIgnoreCase(LA)) 
				list_todos_agentes( us);


			else if (input.equalsIgnoreCase(LF))
				list_fila_espera(us);
			
			
			System.out.print(PROMPT);  				// ">"
			input = in.nextLine(); 					//Faz com que o comando que o programa vai receber na linha abaixo seja lido
		}

		System.out.println(TERMINAR); 			//Despedida
		in.close(); 					 //Fecha o "scanner"
	}
	
	
	
	/**
	 * Metodo adicionar à maquina os diversos utentes apos a leitura do seu nome e afins
	 */
	private static void importar_utentes(UnidadeDeSaudeInterface us) {

		GeradorDeFichas gdf = new GeradorDeFichas();  		//Criado novo gerador de fichas do package
		for (int i = 0; i < 150; i++) {
			FichaUtente fu = gdf.next();     
				if( fu != null) 
				us.adicionar_utente(fu); 
		}
	}
	
	
/**
 * Metodo imprime na consola as opçoes que o utilizador pode
 * introduzir para executar as operações na unidade de saude
 */
	private static void ajuda() {

		System.out.println("S - Sair do programa");
		System.out.println("NC - Processar Nova Consulta");
		System.out.println("TC - Dar uma consulta por Terminada");
		System.out.println("OC - Números de agentes disponíveis, ocupados e fila de espera");
		System.out.println("LO - Listar agentes de saude com paciente atribuído");
		System.out.println("LD - Listar agentes de saude disponiveis");
		System.out.println("LE - Listar agentes de saúde com determinada especialidade");
		System.out.println("LA - Listar todos os agentes de saude");
		System.out.println("LF - Listar utentes em fila de espera");
	}
	
	
	//Cria nova consulta
	private static void nova_consulta(UnidadeDeSaudeInterface us) { 
		
		System.out.println(us.NovaConsulta());
		
	}
	
	//termina consulta
	private static void terminar_consulta(Scanner in, UnidadeDeSaudeInterface us) { 
		
		System.out.print("Nome do utente: " );
		String nome = in.nextLine();
		System.out.println( us.TerminarConsulta(nome) );
	}
	

	private static void oc(UnidadeDeSaudeInterface us) { 
		
		int disponiveis = us.agentes_disponiveis();				//nº agentes disponiveis
		int ocupados = us.agentes_ocupados(); 			   	    //nº agentes ocupados
		int espera = us.em_espera();						    //nº untentes na fila de espera
		
		System.out.println("Agentes disponiveis: "+ disponiveis +"\nAgentes ocupados: "+ ocupados +"\nUtentes em fila de espera: "+ espera);
		
	}
	
	private static void list_agentes_disponiveis(UnidadeDeSaudeInterface us) {	
			
		Iterator<AgenteDeSaudeInterface> it = us.ListarAgentesDisponiveis();  //Criado iterador 'it' para lista agentes disponiveis na unidade de saude
		AgenteDeSaudeInterface a = null;									  //Agente 'a'
			
		while( it.hasNext()){							//Enquanto tiver um elementro seguinte
			a = it.next();								
			if( !a.temEspecialidade())					//se agente 'a' for enfermeiro 
				System.out.println(a.nome() + "; " + "agente não especializado");
			else
				System.out.println(a.nome() + "; " + buscarEspecialidadeAgente(a.especialidade()));
		}
	}

	private static void list_agentes_ocupados(UnidadeDeSaudeInterface us) {// falta corrigir print

		Iterator<AgenteDeSaudeInterface> it = us.ListarAgentesOcupados();
		AgenteDeSaudeInterface a = null;
		
		while( it.hasNext()){
			a = it.next();
			System.out.println(a.nome() + "; " + buscarEspecialidadeAgente(a.especialidade()) + " a atender " + a.pacienteAtribuido().nome());
		}	
	}

	
	/**
	 *   Metodo lista agentes com uma determinada especialidade.
	 * 	 O utilizador introduz como valor de entrada uma determinada especialidade
	 *   os agentes que a tiverem sao listados na consola
	 */
	private static void list_agentes_especializados(Scanner in, UnidadeDeSaudeInterface us) {
		
		Iterator<AgenteDeSaudeInterface> it = us.ListarAgentesDisponiveis();   //Vai iterar primeiro a partir da lista de agentes disponiveis
		AgenteDeSaudeInterface a = null;
		String input = in.nextLine();
		
		while( it.hasNext()){
			a = it.next();
			if( buscarEspecialidadeAgente(a.especialidade()).equalsIgnoreCase(input)) 		//
				System.out.println(a.nome() + "; " + input.toUpperCase());       // 'Nome do agente; Especialidade'
		}
		
		it = us.ListarAgentesOcupados();          								 // A seguir vai-se buscar a lista dos agentes ocupados 
		
		while( it.hasNext()){
			a = it.next();
			if( buscarEspecialidadeAgente(a.especialidade()).equalsIgnoreCase(input))
				System.out.println(a.nome() + "; " + input.toUpperCase());  
		}

	}
	
	private static void list_todos_agentes(UnidadeDeSaudeInterface us) { 				
		Iterator<AgenteDeSaudeInterface> it = us.ListarAgentesDisponiveis();	//listar disponiveis primeiro
		AgenteDeSaudeInterface a = null;
		
		while( it.hasNext()){
			a = it.next();
			System.out.println(a.nome() + " " + buscarEspecialidadeAgente(a.especialidade()) + " disponível." ); 	// 'Nome Especialidade disponível'
		}
		
		it = us.ListarAgentesOcupados();		//listar agentes ocupados
		a = null;
		
		while( it.hasNext()){
			a = it.next();		//metodo para converter a especialidade para string 
			System.out.println(a.nome() +" "+ buscarEspecialidadeAgente(a.especialidade()) + " a atender "+ a.pacienteAtribuido().nome());
		}
		
	}

	
	//Metodo para listar a fila de espera
	private static void list_fila_espera(UnidadeDeSaudeInterface us) {
		
		Iterator<FichaUtente> it = us.ListarFilaDeEspera();	//listar disponiveis primeiro
		FichaUtente u = null;
		
		while( it.hasNext()){
			u = it.next();
			System.out.println(u.nome() +"|"+ u.idade() +"|"+ u.nrUtente() +"|"+ buscarEspecialidadeAgente(u.especialidade()));
		}
	}


	private static void importar_agentes(UnidadeDeSaudeInterface us ) {

		// Ler Ficheiro
		String fileName = "C:\\Users\\Mike\\workspace\\Hospital Lervas\\data\\uds.txt";
		AgenteDeSaude agent;   //Cria objecto agente de saude

		try {
			// Criado o objecto que vai ler o ficheiro
			FileReader inputFile = new FileReader(fileName);

			// Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			// Variável 'linha'
			String line;

			// Lê o ficheiro linha a linha e imprime na consola
			while ((line = bufferReader.readLine()) != null) {    //enquanto nao houver mais para ler
				String agenteNome = buscarNomeAgenteFicheiro(line);
				String agenteEsp = buscarEspecialidadeAgenteFicheiro(line);

				// Converter String para inteiro
				int agenteEspInt = Integer.parseInt(agenteEsp);

				agent = new AgenteDeSaude(agenteNome, agenteEspInt);
				us.adicionar_agente(agent);
			}
			// Fecha o leitor
			bufferReader.close();
		} catch (Exception e) {
			System.out.println("Erro a ler o ficheiro." + e.getMessage());   //Se algo correr mal a ler o fiche, imprime o seguinte
		}

	}

	private static String buscarNomeAgenteFicheiro(String line) {
		String output = line.substring(0, line.indexOf(';'));
		return output;
	}

	private static String buscarEspecialidadeAgenteFicheiro(String line) {
		String output = line.substring(line.lastIndexOf(";") + 1);
		return output.trim();
	}

	
	private static String buscarEspecialidadeAgente (int Esp)    //Converter a especialidade de int para String
	{
		
		switch (Esp)
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
}