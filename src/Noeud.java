// ============================================================================
// AUTEURS              :   Juliette Beaulieu-Lépine, Loïc Gobet
// DATE DE CREATION     :   15 mars 2021
// DESCRIPTION          :   Classe qui contient un Item et les pointeurs précédents
//                          et suivants de cet item vers les noeuds concernés.
// NOTES                :
// ============================================================================
// Dernière mise à jour :   24 mars 2021
//
//=============================================================================

import java.io.Serializable;

public class Noeud implements Serializable {

    public Item item;
    public Noeud precedent;
    public Noeud suivant;

    public Noeud(Item item, Noeud precedent, Noeud suivant){
        this.item = item;
        this.precedent =precedent;
        this.suivant = suivant;
    }

    public Item getItem() {
        return item;
    }

    public Noeud getPrecedent() {
        return precedent;
    }

    public void setPrecedent(Noeud precedent){
        this.precedent = precedent;
    }

    public Noeud getSuivant() {
        return suivant;
    }

    public void setSuivant(Noeud suivant){
        this.suivant = suivant;
    }
}
