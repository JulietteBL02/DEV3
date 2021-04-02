// ============================================================================
// AUTEURS              :   Juliette Beaulieu-Lépine, Loïc Gobet
// DATE DE CREATION     :   29 mars 2021
// DESCRIPTION          :   Serveur qui lit un socket pour prendre
// 							les listes sérialisées, les traite et
// 							les exporte dans un fichier output.txt
// NOTES                :
// ============================================================================
// Dernière mise à jour :   1 avril 2021
//
//=============================================================================

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LeServeur {

	public static void main(String[] args) {

		try {
			System.out.println("Demarrage du serveur sur port 1337");
			ServerSocket server = new ServerSocket(1337, 1);
			Socket client = server.accept();
			System.out.println(
					"Un client ayant l'addresse " + client.getInetAddress() +
							" a connecté sur port " + client.getLocalPort()
			);

			/*
			À FAIRE:
			Créer un objet ObjectInputStream objectReader à partir du clientStream
			*/

			InputStream clientStream = client.getInputStream();
			ObjectInputStream objectReader = new ObjectInputStream(clientStream);

			// Réinitialise le fichier output vide s'il est déjà créé
			FileWriter fw = new FileWriter("output.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fw);
			String s = "";
			bufferedWriter.append(s);
			bufferedWriter.close();

			try {
				while (true) {
					try {
						String info = (String) objectReader.readObject();
						ListeDoublementChainee ldc = (ListeDoublementChainee) objectReader.readObject();

						faireDesChoses(ldc, info);

						} catch (ClassNotFoundException e) {
							System.err.println("L'objet lu n'etait pas une instance de la classe attendue.");
						}
					}
			} catch (EOFException eof) {
				/*
				Rien à faire ici. Le EOFException signifie juste que l' objectReader
				n'arrive pas à recevoir plus d'objets par clientStream.
				Cela nous permet de sortir du boucle while(true) quand il n'y a plus
				d'objets à deserialiser.
				*/
			}

			System.out.println("Plus des choses à lire. Au revoir.");

			objectReader.close();
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//À FAIRE: adaptez la signature selon votre implementation du IListeDoublementChainee
	private static void faireDesChoses(ListeDoublementChainee d, String info) {
    /*
    À FAIRE:
    (Devoir 2) exporter la liste de l'avant vers l'arrière et inversement
	au fond du fichier output.txt comme en devoir 2
	*/
		try{
		PrintStream out = new PrintStream(new FileOutputStream("output.txt",true),true);
		System.setOut(out);
		System.out.print(info);
		d.imprimerListeDuDebut();
		d.imprimerListeDeLaFin();
		System.out.println("\n----------");
		out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
