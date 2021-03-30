import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

// Si necessaire:
import java.util.ArrayList;
import java.util.Scanner;

public class LeClient {

	public static void main(String[] args) {

		try {
			System.out.println("Demarrage du client sur port 1337.");
			Socket monSocket = new Socket("127.0.0.1", 1337);

			ObjectOutputStream writer = new ObjectOutputStream(
					monSocket.getOutputStream()
			);

			/*
			À FAIRE: 
			
			Créer un objet ObjectOutputStream writer à partir du monSocket
			
			(Devoir 2) Lire listes du fichier d'entrée
			(Devoir 2) Créer les objets necessaires
			
			Itérer tous les listes et les sérializer en appellant
				writer.(...)
			*/


			try {
				File myObj = new File("./assets/input.txt");
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String line = myReader.nextLine();
					if (!line.isEmpty()) {
						System.out.println("----------");
						String[] colonne = line.split(" ");
						String sens = colonne[0];
						String listeEnString = colonne[1];
						System.out.println("Sens du tri: " + sens);
						System.out.println("Liste originale: " + listeEnString);

						ListeDoublementChainee ldc = new ListeDoublementChainee(sens);

						ldc.ajouterListe(listeEnString);

						writer.writeObject(ldc);
					}
				}
				myReader.close();

			} catch (FileNotFoundException e) {
				System.out.println("Une erreur est survenue.");
				e.printStackTrace();
			}

			writer.close();
			monSocket.close();

		} catch (ConnectException x) {
			System.out.println("Connexion impossible sur port 1337: pas de serveur.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}