package com.IutJavaBdd.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.IutJavaBdd.beans.Article;

public class FlorantPanier {
	private Article article;
	private int quantite;
	private BigDecimal prixTotal;
	private BigDecimal prixTotalTTC;
	
	public FlorantPanier(Article article, int quantite, BigDecimal prixTotal, BigDecimal prixTotalTTC) {
		this.article = article;
		this.quantite = quantite;
		this.prixTotal = prixTotal;
		this.prixTotalTTC = prixTotalTTC;
	}
	public FlorantPanier(Article article, int quantite) {
		this.article = article;
		this.quantite = quantite;
		calculeTotalHTC();
		calculeTotalTTC();
	}
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public BigDecimal getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(BigDecimal prixTotal) {
		this.prixTotal = prixTotal;
	}
	public BigDecimal getPrixTotalTTC() {
		return prixTotalTTC;
	}
	public void setPrixTotalTTC(BigDecimal prixTotalTTC) {
		this.prixTotalTTC = prixTotalTTC;
	}

	private void calculeTotalHTC() {
		this.prixTotal = article.getPrixArticle().multiply(BigDecimal.valueOf(quantite));
	}
	
	private void calculeTotalTTC() {
		prixTotalTTC = prixTotal.multiply(new BigDecimal(1.2));
		prixTotalTTC = prixTotalTTC.setScale(2, RoundingMode.CEILING);
	}
}




