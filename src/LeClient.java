import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

// Si necessaire:
import java.util.ArrayList;

public class LeClient {

	public static void main(String[] args) {

		try {
			System.out.println("Demarrage du client sur port 1337.");
			Socket monSocket = new Socket("127.0.0.1", 1337);

			/*
			À FAIRE: 
			
			Créer un objet ObjectOutputStream writer à partir du monSocket
			
			(Devoir 2) Lire listes du fichier d'entrée
			(Devoir 2) Créer les objets necessaires
			
			Itérer tous les listes et les sérializer en appellant
				writer.writeObject(...)
			*/			

			writer.close();
			socket.close();

		} catch (ConnectException x) {
			System.out.println("Connexion impossible sur port 1337: pas de serveur.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
