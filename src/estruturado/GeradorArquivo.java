package estruturado;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GeradorArquivo {
	
	public final void gerarArquivo(String nome, Map<String,Object> propriedades, String tipo) throws IOException {
		byte[] bytes = null;
		if (tipo.equals("PROPRIEDADES_CRIPTOGRAFADO")) {
			//gera properties
			StringBuilder propFileBuilder = new StringBuilder();
			for (String prop: propriedades.keySet()) {
				propFileBuilder.append(prop+"m"+propriedades.get(prop)+"\n");
			}
			bytes = propFileBuilder.toString().getBytes();
			//criptografa
			byte[] newBytes = new byte[bytes.length];
			for (int i = 0; i<bytes.length;i++) {
				newBytes[i] = (byte)((bytes[i]+10) % Byte.MAX_VALUE);
			}
			bytes = newBytes;
		} else if (tipo.equals("XML_COMPACTADO")) {
			//gera xml
			StringBuilder propFileBuilder = new StringBuilder();
			propFileBuilder.append("<properties>");
			for (String prop: propriedades.keySet()) {
				propFileBuilder.append("<"+prop+">"+propriedades.get(prop)+"</"+prop+">");
			}
			propFileBuilder.append("</propriedades>");
			bytes = propFileBuilder.toString().getBytes();
			
			//compacta
			ByteArrayOutputStream byteout = new ByteArrayOutputStream();
			ZipOutputStream out = new ZipOutputStream(byteout);
			out.putNextEntry(new ZipEntry("internal"));
			out.write(bytes);
			out.closeEntry();
			out.close();
			bytes = byteout.toByteArray();
		} else {
			System.out.println("Desconheço essa opção");
			return;
		}
		FileOutputStream fileout = new FileOutputStream(nome);
		fileout.write(bytes);
		fileout.close();
	}

}
