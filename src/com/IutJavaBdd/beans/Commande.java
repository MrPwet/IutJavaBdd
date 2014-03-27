package com.IutJavaBdd.beans;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class Commande {
	int idCommande;
	String username;
	BigDecimal prixTotal;
	DateTime dateCommande;
	
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BigDecimal getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(BigDecimal prixTotal) {
		this.prixTotal = prixTotal;
	}
	public DateTime getDate() {
		return dateCommande;
	}
	public void setDate(DateTime date) {
		this.dateCommande = date;
	}

}
