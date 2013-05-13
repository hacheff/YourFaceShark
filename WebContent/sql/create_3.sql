-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- G�n�r� le : Mer 20 Mars 2013 � 09:49
-- Version du serveur: 5.5.20
-- Version de PHP: 5.3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de donn�es: `yourfaceshark`
--

-- --------------------------------------------------------

--
-- Structure de la table `amis`
--

CREATE TABLE IF NOT EXISTS `amis` (
  `idUser` int(11) NOT NULL,
  `idShark` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

CREATE TABLE IF NOT EXISTS `commentaires` (
  `idCommentaire` int(11) NOT NULL AUTO_INCREMENT,
  `idPost` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `commentaire` varchar(2000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idCommentaire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `like`
--

CREATE TABLE IF NOT EXISTS `Like` (
  `idLike` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPost` int(10) unsigned NOT NULL DEFAULT '0',
  `idLikeur` int(10) unsigned NOT NULL DEFAULT '0',
  `choix` int(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`idLike`),
  KEY `idPost` (`idPost`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `photos`
--

CREATE TABLE IF NOT EXISTS `photos` (
  `idPhoto` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `donnees` longblob NOT NULL,
  PRIMARY KEY (`idPhoto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `idPost` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPosteur` int(10) unsigned NOT NULL DEFAULT '0',
  `idCible` int(10) unsigned NOT NULL DEFAULT '0',
  `texte` varchar(1500) NOT NULL DEFAULT '',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` varchar(250) NOT NULL DEFAULT '',
  PRIMARY KEY (`idPost`),
  KEY `idCible` (`idCible`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `post`
--

INSERT INTO `post` (`idPost`, `idPosteur`, `idCible`, `texte`, `date`, `url`) VALUES
(1, 1, 1, '1er post trololo', '2013-02-20 09:51:56', ''),
(2, 4, 3, 'Le snow, c''est la base ! ', '2013-02-20 09:51:56', 'http://membres.multimania.fr/ecoutelecoulidusnow/snowboard-22.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL DEFAULT '',
  `prenom` varchar(30) NOT NULL DEFAULT '',
  `dateNaissance` date NOT NULL,
  `sexe` char(1) NOT NULL DEFAULT '',
  `mail` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `profile` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  KEY `nom` (`nom`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`idUser`, `nom`, `prenom`, `dateNaissance`, `sexe`, `mail`, `password`, `profile`) VALUES
(1, 'Mumpert', 'Cl�mence', '1990-05-21', 'f', 'c.mumpert@gmail.com', 'clemence', 0),
(3, 'Fontaine', 'Hugo', '1991-04-17', 'm', 'hugo.fontaine@live.fr', 'hugo', 0),
(4, 'Mouliac', 'Bastien', '1992-03-30', 'm', 'bmouliac@gmail.com', 'bastien', 0),
(5, 'Chassot', 'Florian', '1991-09-29', 'm', 'florian.chassot@gmail.com', 'florian', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
