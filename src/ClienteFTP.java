import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ClienteFTP {
	
	public static void main ( String[] args) {
		
		FTPClient cliente = new FTPClient();
		
		String servFTP = "localhost";
		
		System.out.println("Me estoy conectando a mi IP: "+servFTP);
		
		String usuario ="ivan";
		
		String clave = "ivan";
		
		
		
		
	}
	
	public static void descargaArchivos(FTPClient cliente, String servFTP, String usuario, String clave) {
		
		System.out.println("Concentandose a: "+servFTP);
		
		try {
			cliente.connect(servFTP);
			
			boolean login = cliente.login(usuario, clave);
			
			String direc = "/ingles";
			
			if(login) {
				cliente.changeWorkingDirectory(direc);
				cliente.setFileType(FTP.BINARY_FILE_TYPE);
				
				// Stream de entrada con el fichero a descargar
				FileOutputStream fOut = new FileOutputStream("C:\\Users\\AlumnoT\\Desktop\\IvanDAMT\\ingles\\descarga\\descargame.txt");
				
				BufferedOutputStream out = new BufferedOutputStream(fOut);
				
				if(cliente.retrieveFile("descargame.txt", out)) {
					
					System.out.println("Recuperado correctamente..");
				}else {
					System.out.println("No se ha podido descargar..");
				}
				out.close();
				cliente.logout();
				cliente.disconnect();
				
				
				
				
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void descargaListaArchivos(FTPClient cliente , String servFTP, String usuario,String clave) {
		
		
		
		try {
			cliente.connect(servFTP);
			
			boolean login = cliente.login(usuario, clave);
			
			
			if(login) {
				System.out.println("Login correcto");
			}else {
				System.out.println("Login incorrecto");
				cliente.disconnect();
				System.exit(1);
			}
			
			System.out.println("Directiorio actual: "+cliente.printWorkingDirectory());
			
			FTPFile[] files = cliente.listFiles();
			
			System.out.println("Cantidad de ficheros en el directorio: "+files.length);
			
			// array para visualizar el tipo de fichero
			
			String tipo[] = {"Fichero","Directorio","Enlace simb."};
			
			for(int i=0; i<files.length;i++) {
				System.out.println("\t"+files[i].getName()+" => "+tipo[files[i].getType()]);
			}
			
			boolean logout = cliente.logout();
			
			if(logout) {
				System.out.println("Logueando del servidor FTP..");
			}else {
				System.out.println("Error al hacer logout..");
			}
			cliente.disconnect();
			System.out.println("Desconectado..");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
