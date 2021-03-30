import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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

			InputStream clientStream = client.getInputStream();
			ObjectInputStream objectReader = new ObjectInputStream(clientStream);
			
			/*
			À FAIRE: 
			Créer un objet ObjectInputStream objectReader à partir du clientStream
			*/

			try {
				while (true) {
					try {
						/*
						À FAIRE:
						Deserialiser un objet uneListe du type IListeDoublementChainee en appellant
							objectReader.readObject()
						*/

						ListeDoublementChainee ldc = (ListeDoublementChainee) objectReader.readObject();

						faireDesChoses(ldc);

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
	private static void faireDesChoses(IListeDoublementChainee d) {
    /*
    À FAIRE:
    (Devoir 2) exporter la liste de l'avant vers l'arrière et inversement
	au fond du fichier output.txt comme en devoir 2
	*/
		d.imprimerListeDuDebut();
		d.imprimerListeDeLaFin();

		System.out.println("\n----------");



	}

}
