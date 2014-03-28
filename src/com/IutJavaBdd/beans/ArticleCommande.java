package com.IutJavaBdd.beans;

import java.math.BigDecimal;

public class ArticleCommande {
	int idCommande;
	int idArticle;
	int qte;
	BigDecimal prixTotal;
	BigDecimal prixTotalTTC;
	
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
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
	
	

}
