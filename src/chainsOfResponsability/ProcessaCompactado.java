package chainsOfResponsability;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ProcessaCompactado extends Processador {

	public ProcessaCompactado(Processador proximo) {
		super(proximo);
	}
	
	public ProcessaCompactado () {
		
	}
	
	@Override
	protected byte[] processaConteudo(byte[] bytes)  throws IOException  {
		//compacta
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		ZipOutputStream out = new ZipOutputStream(byteout);
		out.putNextEntry(new ZipEntry("internal"));
		out.write(bytes);
		out.closeEntry();
		out.close();
		return byteout.toByteArray();
	}
}
