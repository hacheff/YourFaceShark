-- phpMyAdmin SQL Dump
-- version 3.1.5
-- http://www.phpmyadmin.net
--
-- Serveur: brain.fuck.sql.free.fr
-- Généré le : Mer 20 Février 2013 à 09:24
-- Version du serveur: 5.0.83
-- Version de PHP: 5.3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `brain_fuck`
--

-- --------------------------------------------------------

--
-- Structure de la table `Like`
--

CREATE TABLE IF NOT EXISTS `Like` (
  `idLike` int(11) NOT NULL auto_increment,
  `idPost` int(11) NOT NULL,
  `like` int(11) NOT NULL default '0',
  `dislike` int(11) NOT NULL default '0',
  PRIMARY KEY  (`idLike`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

--
-- Contenu de la table `Like`
--


-- --------------------------------------------------------

--
-- Structure de la table `Post`
--

CREATE TABLE IF NOT EXISTS `Post` (
  `idPost` int(11) NOT NULL auto_increment,
  `idPosteur` int(11) NOT NULL,
  `idCiblePost` int(11) NOT NULL,
  `texte` varchar(1500) collate latin1_general_ci default NULL,
  `date` date NOT NULL,
  `url` varchar(150) collate latin1_general_ci default NULL,
  PRIMARY KEY  (`idPost`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

--
-- Contenu de la table `Post`
--


-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `idUser` int(11) NOT NULL auto_increment,
  `nom` varchar(45) collate latin1_general_ci NOT NULL,
  `prenom` varchar(45) collate latin1_general_ci NOT NULL,
  `dateNaissance` date NOT NULL,
  `sexe` varchar(1) collate latin1_general_ci NOT NULL,
  `mail` varchar(45) collate latin1_general_ci NOT NULL,
  `password` varchar(45) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`idUser`),
  UNIQUE KEY `idUser` (`idUser`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

--
-- Contenu de la table `User`
--

