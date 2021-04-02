import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

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

			// Initialise le fichier output vide s'il est déjà créé
			FileWriter fw = new FileWriter("output.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fw);
			String s = "";
			bufferedWriter.append(s);
			bufferedWriter.close();

			/*
			À FAIRE: 
			Créer un objet ObjectInputStream objectReader à partir du clientStream
			*/

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
	private static void faireDesChoses(IListeDoublementChainee d, String info) {
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
		System.out.println("----------");
		out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

//		try {
//			FileWriter fw = new FileWriter("lettres.txt", true);
//			BufferedWriter bufferedWriter = new BufferedWriter(fw);
//			String s = "DEF\n";
//			bufferedWriter.append(s);
//			bufferedWriter.close();
////		System.out.println("\n----------");
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}
