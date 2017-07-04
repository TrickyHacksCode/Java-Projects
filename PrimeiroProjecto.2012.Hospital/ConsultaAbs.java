import datagen.FichaUtente;

/**
 * Classe abstracta que é implementada pela interface ConsultaInterface
 */
public abstract class ConsultaAbs implements ConsultaInterface{

	
	private FichaUtente u;       //Utente 'u' da FichaUtente
	
	public ConsultaAbs(FichaUtente u){
		
		this.u = u;   
	
	}
	
	public abstract void terminarConsulta();   //Metodo para terminar uma consulta, seja ela normal ou urgente

	
	public FichaUtente getPaciente(){			//buscar utente
		return u;
	}


}
