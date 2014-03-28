package com.IutJavaBdd.tools;

import java.sql.Connection;
import java.sql.Statement;



public class TableCreator {
	public static void dropAllTheTable() {
		Connection conn = null;
		Statement stm = null;
		String sql[] = new String[5];
		sql[0] = "drop table if exists Concerne;";
		sql[1] = "drop table if exists Contient;";
		sql[2] = "drop table if exists Commande;";
		sql[3] = "drop table if exists User;";
		sql[4] = "drop table if exists Article;";
		try {
			conn = Singleton.DS.getConnection();
			stm = conn.createStatement();
			for(int i = 0 ; i < 5 ; i++) {
				stm.executeUpdate(sql[i]);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {stm.close();} catch (Exception ignore) {}
			try {conn.close();} catch (Exception ignore) {}
		}
	}
	
	public static void createTable() {
		Connection conn = null;
		Statement stm = null;
		String sql[] = new String[5];
		sql[0] = "" 
				+ "create table Article ("
				+ "	idArticle int not null auto_increment,"
				+ "	nomArticle varchar(50) not null,"
				+ "	prixArticle decimal(12,2) not null,"
				+ "	disponibiliteArticle int not null,"
				+ "	categorieArticle varchar(50) not null,"
				+ "	primary key(idArticle)); ";
		sql[1] = ""
				+ "create table User ("
				+ "	username varchar(50) not null,"
				+ "	password varchar(50) not null,"
				+ "	primary key(username)); ";
		sql[2] = ""
				+ "create table Commande ("
				+ "	idCommande int not null auto_increment,"
				+ "	username varchar(50),"
				+ "	prixTotal decimal(12,2) not null,"
				+ "	dateCommande date not null,"
				+ "	primary key(idCommande),"
				+ "	foreign key(username) references User(username));";
		sql[3] = ""
				+ "create table Contient ("
				+ "	username varchar(50) not null,"
				+ "	idArticle int not null,"
				+ "	qte int not null,"
				+ "	primary key(username, idArticle),"
				+ "	foreign key(username) references User(username),"
				+ "	foreign key(idArticle) references Article(idArticle));";
		sql[4] = ""
				+ "create table Concerne ("
				+ "	idCommande int not null,"
				+ "	idArticle int not null,"
				+ "	qte int not null,"
				+ "	prixTotal decimal(12,2) not null,"
				+ "	prixTotalTTC decimal(12,2) not null,"
				+ "	primary key(idCommande, idArticle),"
				+ "	foreign key(idCommande) references Commande(idCommande),"
				+ "	foreign key(idArticle) references Article(idArticle));";
		
		try {
			conn = Singleton.DS.getConnection();
			stm = conn.createStatement();
			for(int i = 0 ; i < 5 ; i++) {
				stm.executeUpdate(sql[i]);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {stm.close();} catch (Exception ignore) {}
			try {conn.close();} catch (Exception ignore) {}
		}
	}
	
	public static void populateTable() {
		Connection conn = null;
		Statement stm = null;
		String sql[] = new String[8];
		sql[0] = "insert into Article values(null, 'Poire', 1.20, 50, 'fruits');";
		sql[1] = "insert into Article values(null, 'Pomme', 0.80, 25, 'fruits');";
		sql[2] = "insert into Article values(null, 'Banane', 1.00, 32, 'fruits');";
		sql[3] = "insert into Article values(null, 'Carotte', 1.10, 75, 'légumes');";
		sql[4] = "insert into Article values(null, 'Coca-cola', 0.60, 120, 'boissons');";
		sql[5] = "insert into User values('defaut', 'defaut');";
		sql[6] = "insert into User values('user1', 'user1');";
		sql[7] = "insert into User values('user2', 'user2');";
		
		try {
			conn = Singleton.DS.getConnection();
			stm = conn.createStatement();
			for(int i = 0 ; i < 8 ; i++) {
				stm.executeUpdate(sql[i]);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {stm.close();} catch (Exception ignore) {}
			try {conn.close();} catch (Exception ignore) {}
		}
	}
}
