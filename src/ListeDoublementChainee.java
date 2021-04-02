// ============================================================================
// AUTEURS              :   Juliette Beaulieu-Lépine, Loïc Gobet
// DATE DE CREATION     :   15 mars 2021
// DESCRIPTION          :   Classe s'occupant de l'initialisation de la liste doublement
//                          chainée.
//                          Contient aussi une méthode pour l'impressison du rapport.
// NOTES                :
// ============================================================================
// Dernière mise à jour :   24 mars 2021
//
//=============================================================================

import java.io.Serializable;

public class ListeDoublementChainee implements IListeDoublementChainee, Serializable {

    private Noeud tete;
    private Noeud queue;
    private String sens;
    private int taille; // Permet de trouver les listes vides

    public ListeDoublementChainee(String sens){
        this.sens = sens;
        this.taille = 0;
    }

    @Override
    public void ajouterListe(String listeEnString) {

        // Extraire les valeurs de la liste
        String elementsListeEnString;
        elementsListeEnString = listeEnString.substring( 1, listeEnString.length() - 1 );
        String[] elementsArray = elementsListeEnString.split(",");
        Item<?> item;

        for (int i = 0; i < elementsArray.length; i++){
            if (Main.isInteger(elementsArray[i])){
                Integer valeurInteger = new Integer(elementsArray[i]);
                item = new Item<Integer>(valeurInteger);
                ajouterNoeud(item);
            }
            else if (Main.isDouble(elementsArray[i])){
                Double valeurDouble = new Double(elementsArray[i]);
                item = new Item<Double>(valeurDouble);
                ajouterNoeud(item);
            }
            else {
                String valeurString = new String (elementsArray[i]);
                item = new Item<String>(valeurString);
                ajouterNoeud(item);
            }
        }
    }

    @Override
    public void ajouterNoeud(Item<?> item) {

        // Liste vide
        if (taille == 0) {
            tete = new Noeud(item, null, null);
            queue = tete;
            taille++;
            return;
        }

        // Création de la liste
        else {
            switch (sens) {
                case "asc": {
                    if (item.compareTo(tete.getItem()) < 0) {
                        Noeud nouveauNoeud = new Noeud(item, null, tete);
                        tete.setPrecedent(nouveauNoeud);
                        tete = nouveauNoeud;
                        taille++;
                        return;
                    }
                    else {
                        Noeud present = tete.getSuivant();
                        while (present != null) {
                            if (item.compareTo(present.getItem()) <= 0) {
                                Noeud nouveauNoeud = new Noeud(item, present.getPrecedent(), present);
                                present.getPrecedent().setSuivant(nouveauNoeud);
                                present.setPrecedent(nouveauNoeud);
                                taille++;
                                return;
                            }
                            present = present.getSuivant();
                        }
                    }
                }
                break;

                case "desc": {
                    if (item.compareTo(tete.getItem()) > 0) {
                        Noeud nouveauNoeud = new Noeud(item, null, tete);
                        tete.setPrecedent(nouveauNoeud);
                        tete = nouveauNoeud;
                        taille++;
                        return;
                    }
                    else {
                        Noeud present = tete.getSuivant();
                        while (present != null) {
                            if (item.compareTo(present.getItem()) >= 0) {
                                Noeud nouveauNoeud = new Noeud(item, present.getPrecedent(), present);
                                present.getPrecedent().setSuivant(nouveauNoeud);
                                present.setPrecedent(nouveauNoeud);
                                taille++;
                                return;
                            }
                            present = present.getSuivant();
                        }
                    }
                }
                break;
            }
        }

        Noeud nouveauNoeud = new Noeud(item, queue, null);
        queue.setSuivant(nouveauNoeud);
        queue = nouveauNoeud;
        taille++;
    }

    @Override
    public void imprimerListeDuDebut() {

        Noeud tempo = tete;
        System.out.print("Noeuds du debut vers la fin: ");
        while ( tempo != null){
            if (tempo.getSuivant()== null){
                System.out.print(tempo.item.getValeur());
            }
            else {
                System.out.print(tempo.item.getValeur() + "->");
            }
            tempo = tempo.getSuivant();
        }
    }

    @Override
    public void imprimerListeDeLaFin() {

        Noeud tempo = queue;
        System.out.print("\nNoeuds de la fin vers le debut: ");
        while ( tempo != null) {
            if (tempo.getPrecedent()== null){
                System.out.print(tempo.item.getValeur());
            }
            else {
                System.out.print(tempo.item.getValeur() + "->");
            }
            tempo = tempo.getPrecedent();
        }
    }
}
