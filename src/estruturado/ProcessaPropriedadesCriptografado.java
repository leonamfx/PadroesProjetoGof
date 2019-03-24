package estruturado;

import java.util.Map;

public class ProcessaPropriedadesCriptografado {

	byte[] processaPropriedadesCriptografado (Map<String,Object> propriedades) {
		String conteudo = geraConteudoPropriedades(propriedades);
		return processaCriptografado(conteudo.getBytes());
	}
	
	private String geraConteudoPropriedades (Map<String,Object> propriedades) {
		// gera properties
		StringBuilder propFileBuilder = new StringBuilder();
		for (String prop : propriedades.keySet()) {
			propFileBuilder.append(prop + "m" + propriedades.get(prop) + "\n");
		}
		return propFileBuilder.toString();

	}
	
	private byte[] processaCriptografado(byte[] bytes[]) {
		// criptografa
		byte[] newBytes = new byte[bytes.length];
		for (int i = 0; i<bytes.length;i++) {
			newBytes[i] = (byte)((bytes[i]+10) % Byte.MAX_VALUE);
		}
		return newBytes;
	}
	
}
