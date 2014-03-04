package com.IutJavaBdd.beans;

import java.math.BigDecimal;

public class Article {
	private int idArticle;
	private String nomArticle;
	private BigDecimal prixArticle;
	private int disponibiliteArticle;
	private String categorieArticle;
	
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public BigDecimal getPrixArticle() {
		return prixArticle;
	}
	public void setPrixArticle(BigDecimal prixArticle) {
		this.prixArticle = prixArticle;
	}
	public int getDisponibiliteArticle() {
		return disponibiliteArticle;
	}
	public void setDisponibiliteArticle(int disponibiliteArticle) {
		this.disponibiliteArticle = disponibiliteArticle;
	}
	public String getCategorieArticle() {
		return categorieArticle;
	}
	public void setCategorieArticle(String categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

}
