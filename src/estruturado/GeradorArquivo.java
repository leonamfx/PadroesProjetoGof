package estruturado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class GeradorArquivo {
	
	public final void gerarArquivo(String nome, Map<String,Object> propriedades, String tipo) throws IOException {
		byte[] bytes = null;
		if (tipo.equals("PROPRIEDADES_CRIPTOGRAFADO")) {
			ProcessaPropriedadesCriptografado propCript = new ProcessaPropriedadesCriptografado();
			bytes = propCript.processaPropriedadesCriptografado(propriedades);
			
		} else if (tipo.equals("XML_COMPACTADO")) {
			ProcessaXmlCompactado xml = new ProcessaXmlCompactado();
			bytes = xml.processaXmlCompactado(propriedades);
		} else {
			System.out.println("Desconheço essa opção");
			return;
		}
		FileOutputStream fileout = new FileOutputStream(nome);
		fileout.write(bytes);
		fileout.close();
	}
	
	

	



}
