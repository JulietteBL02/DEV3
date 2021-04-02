// ============================================================================
// AUTEURS              :   Juliette Beaulieu-Lépine, Loïc Gobet
// DATE DE CREATION     :   29 mars 2021
// DESCRIPTION          :   Client qui lit un fichier input.txt,
// 							crée les objets représentants les listes,
// 							les sérialise et les envoie via un socket
// 							au serveur
// NOTES                :
// ============================================================================
// Dernière mise à jour :   1 avril 2021
//
//=============================================================================

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
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
						String[] colonne = line.split(" ");
						String sens = colonne[0];
						String listeEnString = colonne[1];

						ListeDoublementChainee ldc = new ListeDoublementChainee(sens);

						ldc.ajouterListe(listeEnString);

						// Pour conserver les informations fournies par le input
						// (sens et liste originale) et les envoyer au serveur
						String info = "----------" + "\nSens du tri: " + sens + "\n" + "Liste originale: " + listeEnString + "\n" ;

						writer.writeObject(info);
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