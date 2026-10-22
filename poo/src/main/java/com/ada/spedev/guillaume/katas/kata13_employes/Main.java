package com.ada.spedev.guillaume.katas.kata13_employes;

import java.util.ArrayList;
import java.util.List;

/*
 * KATA 13 - La paie des employés
 * Notions : classe abstraite, méthode abstraite, abstract + implements ensemble,
 *           "patron de méthode" (une méthode concrète qui appelle une méthode abstraite)
 *
 * Une classe abstraite, c'est une interface... avec du code dedans.
 *     interface        : que des signatures (+ des default qui n'ont pas accès aux attributs)
 *     classe abstraite : des signatures (abstract) + des attributs + des méthodes concrètes
 *     classe concrète  : tout est écrit
 *
 * Consigne :
 *   1) Interface Payable :  double montantMensuel();  String libelle();
 *   2) Classe ABSTRAITE Employe implements Payable :
 *        - nom, matricule attribué automatiquement (static, comme au kata 04)
 *        - méthodes abstraites  salaireBrut()  et  poste()
 *        - méthode concrète  salaireNet()  = brut * 0.78   (écrite UNE fois pour tous)
 *        - montantMensuel() retourne salaireBrut(), fichePaie() retourne une ligne formatée
 *   3) Trois sous-classes :
 *        Salarie(nom, salaireAnnuel)              -> brut = annuel / 12
 *        Commercial(nom, fixe, tauxCommission)    -> brut = fixe + ventes * taux / 100
 *        Stagiaire(nom)                           -> 650 €, et net = brut (pas de cotisations)
 *   4) Afficher les fiches de paie de tous les employés.
 *
 * Étape 2 : l'entreprise paie aussi des factures (électricité, loyer...).
 *           Créer Facture implements Payable (SANS hériter d'Employe).
 *           Calculer le total des sorties d'argent du mois avec une List<Payable>.
 *
 * Étape 3 : essayer  new Employe("Bob");  -> que dit le compilateur ?
 *           Essayer de créer une sous-classe d'Employe sans écrire poste() -> idem ?
 *
 * Réflexion : Facture et Employe partagent l'interface Payable mais pas de code.
 *             Salarie et Commercial partagent l'interface ET du code (salaireNet, fichePaie).
 *             -> L'héritage n'est utile QUE quand il y a du code à partager.
 */
public class Main {

	public static double totalMensuel(List<Payable> aPayer) {
		double total = 0;
		for (Payable p : aPayer) {
			total += p.montantMensuel();
		}
		return total;
	}

	public static void main(String[] args) {
		List<Employe> employes = new ArrayList<>();
		employes.add(new Salarie("Ada", 42000));

		Commercial alan = new Commercial("Alan", 1800, 5);
		alan.enregistrerVente(12000);
		alan.enregistrerVente(8000);
		employes.add(alan);
		employes.add(new Stagiaire("Grace"));

		System.out.println("===== Fiches de paie =====");
		for (Employe e : employes) {
			System.out.println(e.fichePaie());
		}

		System.out.println("===== Étape 2 : tout ce qu'il faut payer =====");
		List<Payable> aPayer = new ArrayList<>(employes);
		aPayer.add(new Facture("EDF", 340.50));
		aPayer.add(new Facture("Loyer bureaux", 2500));

		for (Payable p : aPayer) {
			System.out.printf("%-35s %10.2f €%n", p.libelle(), p.montantMensuel());
		}
		System.out.printf("%-35s %10.2f €%n", "TOTAL", totalMensuel(aPayer));

		// Employe bob = new Employe("Bob");   // ne compile pas : Employe is abstract; cannot be instantiated
	}
}
