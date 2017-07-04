import datagen.FichaUtente;


public class AgenteDeSaude implements AgenteDeSaudeInterface{    

	private FichaUtente utente;	//utente a ter uma consulta com um medico
	private String Nome;  
	private int Especialidade;

	public AgenteDeSaude(String nome, int especialidade) {

		Nome = nome;
		Especialidade = especialidade;
		utente = null;
	}
	
	
	public String nome() {
		return Nome;
	}
	
	public String toString() {
		return "Nome do Agente: [" + nome() + "]. Especialidade: [" + GetEsNameFromID(especialidade()) + "]";
	}

	private String GetEsNameFromID(int especialidade2) {
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
	


	public boolean temEspecialidade() {
		return this.Especialidade != 1;
	}

	
	public int especialidade() {
		return Especialidade;
	}

	
	public boolean equals(AgenteDeSaude other) {
		if( other.nome().equals(this.nome()) && other.especialidade()==this.especialidade() )	// sao iguais se tiverem o mesmo nome e especialidade
			return true;
		else 
			return false;
	}

	
	public void afectar(FichaUtente paciente) {
		utente = paciente;
	}

	
	public void desafectar() {
		utente = null;
	}

	
	public FichaUtente pacienteAtribuido() {
		return utente;
	}


	public boolean ocupado() {
		return utente != null;
	}

	// referente a urgencia do utente actual 1 ou 2
	public int urgenciaDaOcupacao() {
		return utente.urgencia();
	}

}
