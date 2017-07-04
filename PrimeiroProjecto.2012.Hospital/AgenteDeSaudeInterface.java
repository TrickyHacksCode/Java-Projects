import datagen.FichaUtente;


public interface AgenteDeSaudeInterface {
	
	public static final int SEM_ESPECIALIDADE = 1; 
	public static final int CARDIOLOGIA = 2;
	public static final int CLINICA_GERAL = 3; 
	public static final int NEUROLOGIA = 4; 
	public static final int ORTOPEDIA = 5; 
	public static final int OFTALMOLOGIA = 6; 
	public static final int PEDIATRIA = 7; 
	public static final int PNEUMONOLOGIA = 8; 
	public static final int URULOGIA = 9; 
	
	public String nome();
	public boolean temEspecialidade(); //true excepto para enfermeiros
	public int especialidade(); //SEM_ESPECIALIDADE caso seja enfermeiro
	public boolean equals(AgenteDeSaude other);
	public void afectar(FichaUtente paciente); //associa um utente
	public void desafectar(); //termina associacao ao utente
	public FichaUtente pacienteAtribuido(); //utente em atendimento, ou null
	public boolean ocupado(); //indica se está a atender um utente
	public int urgenciaDaOcupacao(); //devolve urgência do atendimento
	public String toString();
}