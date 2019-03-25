package Bridge;

import java.util.Map;

public class GeradorArquivoXml extends GeradorArquivo {
	
	public GeradorArquivoXml(Processador processador) {
		super(processador);
	}
	
	@Override
	protected String gerarConteudo(Map<String,Object> propriedades) {
		//gera xml
		StringBuilder propFileBuilder = new StringBuilder();
		propFileBuilder.append("<properties>");
		for (String prop: propriedades.keySet()) {
			propFileBuilder.append("<"+prop+">"+propriedades.get(prop)+"</"+prop+">");
		}
		propFileBuilder.append("</propriedades>");
		return propFileBuilder.toString();
		
	}
	

	
}
