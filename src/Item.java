// ============================================================================
// AUTEURS              :   Juliette Beaulieu-Lépine, Loïc Gobet
// DATE DE CREATION     :   15 mars 2021
// DESCRIPTION          :   Classe générique qui peut contenir des éléments de
//                          différents types.
//                          Méthode pour comparer des String, Double et Integer
//                          ensemble.
// NOTES                :
// ============================================================================
// Dernière mise à jour :   24 mars 2021
//
//=============================================================================

import java.io.Serializable;

public class Item<T extends Comparable<T>> implements Serializable, Comparable<Item<T>>{

    public T valeur;

    public Item(T valeur) {
         this.valeur = valeur;
    }

    public T getValeur(){
        return this.valeur;
    }

    @Override
    public int compareTo(Item<T> item) {

         // Séparer la valeur au point de la partie décimale (si possible)
         String str1 = this.getValeur().toString();
         String[] splitstr1 = str1.split("[.]");
         String intValue1 = splitstr1[0];
         String decimalValue1 = "0";
         if (splitstr1.length > 1){
             decimalValue1 = splitstr1[1];
         }

         String str2 = item.getValeur().toString();
         String[] splitstr2 = str2.split("[.]");
         String intValue2 = splitstr2[0];
         String decimalValue2 = "0";
         if (splitstr2.length > 1){
            decimalValue2 = splitstr2[1];
         }

         if (Main.isInteger(intValue1) && Main.isInteger(intValue2)){
             int l1 = intValue1.length();
             int l2 = intValue2.length();
             int diff = l2-l1;

             if (diff > 0) {
                 for (int i = 0; i < 1 * diff; i++) {
                     intValue1 = "0" + intValue1;
                 }
                 str1 = intValue1 + "[.]" + decimalValue1;
             }
             else if(diff < 0){
                 for (int i = 0; i < -1 * diff; i++) {
                     intValue2 = "0" + intValue2;
                 }
                 str2 = intValue2 + "[.]" + decimalValue2;
             }
         }
         return (str1.compareTo(str2));
    }
}
