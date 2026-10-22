package com.ada.spedev.guillaume.katas.kata10_reductions;

import java.util.ArrayList;
import java.util.List;

public class Panier {

	private final List<Article> articles = new ArrayList<>();
	private Reduction reduction = new AucuneReduction();

	public void ajouter(Article article) {
		this.articles.add(article);
	}

	// On peut changer de stratégie à tout moment, sans toucher au code de Panier
	public void setReduction(Reduction reduction) {
		this.reduction = reduction;
	}

	public double sousTotal() {
		double total = 0;
		for (Article article : this.articles) {
			total += article.prixTotal();
		}
		return total;
	}

	public double total() {
		return this.reduction.appliquer(sousTotal());
	}
}
