package com.IutJavaBdd.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class Commande {
	int idCommande;
	String username;
	BigDecimal prixTotal;
	Date dateCommande;
	
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
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date date) {
		this.dateCommande = date;
	}

}
