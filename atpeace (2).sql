-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 01 mai 2023 à 21:37
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `atpeace`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nom` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `password`, `nom`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `audio`
--

CREATE TABLE `audio` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `fichier` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `rating` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `audio`
--

INSERT INTO `audio` (`id`, `titre`, `type`, `fichier`, `image`, `rating`) VALUES
(1, 'musique classique', 'musique', 'b39c7bb602416f4dd247d93e3acc7958.mp3', '684986e4191b710a38b1c9ee03956b31.jpg', -1),
(2, 'musique classique', 'musique', '26d9156d073da5b46f0f3aa781a2da90.mp3', 'd019e2e687d5aabfed2ed09e19e47d94.jpg', -12);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) NOT NULL,
  `frais_livraison` double NOT NULL,
  `date_livraison` date NOT NULL,
  `etat` varchar(255) NOT NULL,
  `quantite_livre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

CREATE TABLE `commentaires` (
  `id` int(11) NOT NULL,
  `continue_commentaire` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20230308125011', '2023-03-08 13:50:50', 501),
('DoctrineMigrations\\Version20230308172602', '2023-03-08 18:26:06', 132),
('DoctrineMigrations\\Version20230308175254', '2023-03-08 18:52:58', 169),
('DoctrineMigrations\\Version20230308201011', '2023-03-08 21:10:37', 41),
('DoctrineMigrations\\Version20230309051328', '2023-03-09 06:15:23', 159);

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

CREATE TABLE `favoris` (
  `id` int(11) NOT NULL,
  `regime_id` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `nb_favori` int(11) NOT NULL,
  `nb_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `favoris`
--

INSERT INTO `favoris` (`id`, `regime_id`, `id_patient`, `nb_favori`, `nb_total`) VALUES
(5, 2, 10, 11, 12),
(7, 3, 10, 11, 12);

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE `medecin` (
  `id` int(11) NOT NULL,
  `email` varchar(180) NOT NULL,
  `roles` longtext NOT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) NOT NULL,
  `diplome` varchar(100) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `etat` varchar(20) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id`, `email`, `roles`, `password`, `diplome`, `adresse`, `telephone`, `etat`, `nom`, `prenom`) VALUES
(1, 'radhwenessefi@gmail.com', '[\"ROLE_MEDECIN\"]', '$2y$13$dM9.2ReBtkUc/RBMJvDa6uCL3wCWRb0T6enWFmU/ldKLcD0UNkcEK', 'BAC', 'Tunis', '78965412', 'Disponible', 'Radhwen', 'Essefi'),
(2, 'samisellami@gmail.com', '[\"ROLE_MEDECIN\"]', '$2y$13$J05OuVYczIusfk6eqTZD2Ow1GHj2U0p/q4s55Z/2ySo6fo05FLSUK', 'BAC', 'Tunis', '78451203', 'Non disponible', 'Sami', 'Sellami'),
(3, 'mohamed@gmail.com', '[\"ROLE_MEDECIN\"]', '$2y$13$qRo544DP.y57cZDu8Kf44.4XrbGVe7HX7yhBo9rwYlPp9V2lOaBXy', 'BAC', 'Tunis', '78451203', 'disponible', 'Mohamed', 'Aloui'),
(4, 'dorra@gmail.com', '[\"ROLE_MEDECIN\"]', '$2y$13$4ThLt/vMmjzWdYGIezXqNOoV3CB84Lh1xgDHcEqQ3aB6hKqiiUqQK', 'BaC', 'Mansoura', '78451236', ' disponible', 'Dorra', 'Effi'),
(7, 'u@gmail.com', '[ROLE_MEDECIN]', '$2a$13$A7C3qoJZjWzT.t6/BGlD..GeIDX9mco4WeLs0i3qgf7XWYb/Lmtfe', 'Licence', 'Bizette', '78945612', ' disponible', 'u', 'ulo');

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
--

CREATE TABLE `messenger_messages` (
  `id` bigint(20) NOT NULL,
  `body` longtext NOT NULL,
  `headers` longtext NOT NULL,
  `queue_name` varchar(190) NOT NULL,
  `created_at` datetime NOT NULL,
  `available_at` datetime NOT NULL,
  `delivered_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id` int(11) NOT NULL,
  `id_panier` int(11) NOT NULL,
  `prod_panier` varchar(255) NOT NULL,
  `prix_panier` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `email` varchar(180) NOT NULL,
  `roles` longtext NOT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `adresse` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `email`, `roles`, `password`, `telephone`, `nom`, `prenom`, `adresse`) VALUES
(2, 'khaledhafsia@gmail.com', '[\"ROLE_PATIENT\"]', '$2y$13$y79qGqWclAM9DswKvUjH2eRGzMmEbMj5ehEPCW0IbW0FPwuTqwXIS', '78451230', 'Hafsia', 'Khaked', 'Bizette'),
(3, 'nour@gmail', '[\"ROLE_PATIENT\"]', '$2y$13$.xotll8VD4OJ7QgON6Cah.FehmXZgTca99OUxgOIlL4vlVnDBsPG.', '78945612', 'Nour', 'Mora', 'Bizette'),
(4, 'salah@gmail.com', '[\"ROLE_PATIENT\"]', '$2y$13$CJCJukCGlVjAqOeNPLTNtOrGoUys4ao.EpT0rza4JgnMH40wcbgku', '78451230', 'Marabout', 'SALAH', 'Tunis'),
(10, 'black2608cat@gmail.com', '[ROLE_PATIENT]', '$2a$13$mzEO4x.Kfj4XTJDcUybENehyb7vg82a5wjyi7hr8nS.YosVoK2nlu', '78945612', 'black', 'cat', 'Sfax');

-- --------------------------------------------------------

--
-- Structure de la table `poste`
--

CREATE TABLE `poste` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `discription` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id_produit` int(11) NOT NULL,
  `quantite_produit` int(11) NOT NULL,
  `categorie_produit` varchar(255) NOT NULL,
  `prix_produit` double NOT NULL,
  `image_produit` varchar(255) NOT NULL,
  `nom_produit` varchar(30) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `quantite_produit`, `categorie_produit`, `prix_produit`, `image_produit`, `nom_produit`, `description`) VALUES
(1, 4, 'Outils de relaxation', 15, '1678306416_isagi.png', 'Bougies Parfumés', 'mm'),
(2, 9, 'Outils de relaxation', 10, '1678306447_isagi.png', 'Masque yeux', 'mm');

-- --------------------------------------------------------

--
-- Structure de la table `rate`
--

CREATE TABLE `rate` (
  `id` int(11) NOT NULL,
  `regime_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `num_totale` int(11) DEFAULT NULL,
  `nub_of_rate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `rate`
--

INSERT INTO `rate` (`id`, `regime_id`, `rating`, `num_totale`, `nub_of_rate`) VALUES
(1, 2, 4, 4, 1),
(2, 3, 4, 4, 1),
(3, 3, 4, 8, 2);

-- --------------------------------------------------------

--
-- Structure de la table `rate_patient`
--

CREATE TABLE `rate_patient` (
  `rate_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `rate_patient`
--

INSERT INTO `rate_patient` (`rate_id`, `patient_id`) VALUES
(3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `titre_reclamation` varchar(50) NOT NULL,
  `description_reclamation` varchar(100) NOT NULL,
  `type_reclamation` varchar(25) NOT NULL,
  `date` date NOT NULL,
  `etat_reclamation` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id`, `titre_reclamation`, `description_reclamation`, `type_reclamation`, `date`, `etat_reclamation`) VALUES
(1, 'Gourmand Radhwen', 'Il  mange trop', 'produit', '2023-03-16', 'En attente'),
(2, 'Bonjour', 'Moi', 'produit', '2023-07-13', 'En attente');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation_patient`
--

CREATE TABLE `reclamation_patient` (
  `reclamation_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reclamation_patient`
--

INSERT INTO `reclamation_patient` (`reclamation_id`, `patient_id`) VALUES
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `regime`
--

CREATE TABLE `regime` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `discription` varchar(255) NOT NULL,
  `liste_alement` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `level` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `regime`
--

INSERT INTO `regime` (`id`, `titre`, `discription`, `liste_alement`, `image`, `level`) VALUES
(2, 'eryetyuerteuryeuru', 'eruyeuiryieuryeiuyri', 'eureuruetruerteur', '1678295799_blue lock 7.png', 'underweight'),
(3, 'arararaarararrarar', 'ararararrararra', 'aarararrararararrar', '1678295835_besoin no fonctionel.png', 'overWight');

-- --------------------------------------------------------

--
-- Structure de la table `regime_favorite`
--

CREATE TABLE `regime_favorite` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `regime_favorite`
--

INSERT INTO `regime_favorite` (`id`) VALUES
(1),
(2);

-- --------------------------------------------------------

--
-- Structure de la table `regime_favorite_patient`
--

CREATE TABLE `regime_favorite_patient` (
  `regime_favorite_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `regime_favorite_regime`
--

CREATE TABLE `regime_favorite_regime` (
  `regime_favorite_id` int(11) NOT NULL,
  `regime_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `regime_favorite_regime`
--

INSERT INTO `regime_favorite_regime` (`regime_favorite_id`, `regime_id`) VALUES
(1, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `regime_sport`
--

CREATE TABLE `regime_sport` (
  `regime_id` int(11) NOT NULL,
  `sport_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `regime_sport`
--

INSERT INTO `regime_sport` (`regime_id`, `sport_id`) VALUES
(2, 3),
(3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `rende_vous`
--

CREATE TABLE `rende_vous` (
  `id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `medecin_id` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `etat_rdv` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `rende_vous`
--

INSERT INTO `rende_vous` (`id`, `patient_id`, `medecin_id`, `date`, `etat_rdv`) VALUES
(2, 2, 2, '2023-07-01', 'Annuler');

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id_reponse` int(11) NOT NULL,
  `date_reponse` date NOT NULL,
  `solution_reponse` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `id_user` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `user_role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`id_user`, `username`, `user_role`) VALUES
(10, 'black', '[ROLE_PATIENT]');

-- --------------------------------------------------------

--
-- Structure de la table `sport`
--

CREATE TABLE `sport` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `discription` varchar(255) NOT NULL,
  `niveaux` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `sport`
--

INSERT INTO `sport` (`id`, `titre`, `discription`, `niveaux`, `image`) VALUES
(1, 'aazzaazzaaz', 'aaettetetet', 'zeazeazeaz', '1678295359_blue lock 2.png'),
(3, 'araraaaaaaaararar', 'uyuyuyuuyuyuyuyuyuy', 'yiuyiyuyiuyiyuyiuy', '1678295559_blue lock 2.png');

-- --------------------------------------------------------

--
-- Structure de la table `video`
--

CREATE TABLE `video` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `rating` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `video`
--

INSERT INTO `video` (`id`, `titre`, `description`, `video`, `image`, `rating`) VALUES
(1, 'yoga', 'by emelie', '6408d77405196.mp4', 'ee91f6f8817d8d0066f73ddfc3181aa5.jpg', 6);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `audio`
--
ALTER TABLE `audio`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`);

--
-- Index pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_fav` (`regime_id`),
  ADD KEY `fk_fav2` (`id_patient`);

--
-- Index pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_1BDA53C6E7927C74` (`email`);

--
-- Index pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_1ADAD7EBE7927C74` (`email`);

--
-- Index pour la table `poste`
--
ALTER TABLE `poste`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id_produit`);

--
-- Index pour la table `rate`
--
ALTER TABLE `rate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_DFEC3F3935E7D534` (`regime_id`);

--
-- Index pour la table `rate_patient`
--
ALTER TABLE `rate_patient`
  ADD PRIMARY KEY (`rate_id`,`patient_id`),
  ADD KEY `IDX_C018980ABC999F9F` (`rate_id`),
  ADD KEY `IDX_C018980A6B899279` (`patient_id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reclamation_patient`
--
ALTER TABLE `reclamation_patient`
  ADD PRIMARY KEY (`reclamation_id`,`patient_id`),
  ADD KEY `IDX_CBF0097A2D6BA2D9` (`reclamation_id`),
  ADD KEY `IDX_CBF0097A6B899279` (`patient_id`);

--
-- Index pour la table `regime`
--
ALTER TABLE `regime`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `regime_favorite`
--
ALTER TABLE `regime_favorite`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `regime_favorite_patient`
--
ALTER TABLE `regime_favorite_patient`
  ADD PRIMARY KEY (`regime_favorite_id`,`patient_id`),
  ADD KEY `IDX_35738F2D7B9C06C9` (`regime_favorite_id`),
  ADD KEY `IDX_35738F2D6B899279` (`patient_id`);

--
-- Index pour la table `regime_favorite_regime`
--
ALTER TABLE `regime_favorite_regime`
  ADD PRIMARY KEY (`regime_favorite_id`,`regime_id`),
  ADD KEY `IDX_6CA20B407B9C06C9` (`regime_favorite_id`),
  ADD KEY `IDX_6CA20B4035E7D534` (`regime_id`);

--
-- Index pour la table `regime_sport`
--
ALTER TABLE `regime_sport`
  ADD PRIMARY KEY (`regime_id`,`sport_id`),
  ADD KEY `IDX_E2C3BF4D35E7D534` (`regime_id`),
  ADD KEY `IDX_E2C3BF4DAC78BCF8` (`sport_id`);

--
-- Index pour la table `rende_vous`
--
ALTER TABLE `rende_vous`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_C9BB3BDC6B899279` (`patient_id`),
  ADD KEY `IDX_C9BB3BDC4F31A84` (`medecin_id`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id_reponse`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id_user`);

--
-- Index pour la table `sport`
--
ALTER TABLE `sport`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `audio`
--
ALTER TABLE `audio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commentaires`
--
ALTER TABLE `commentaires`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `favoris`
--
ALTER TABLE `favoris`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `poste`
--
ALTER TABLE `poste`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id_produit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `rate`
--
ALTER TABLE `rate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `regime`
--
ALTER TABLE `regime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `regime_favorite`
--
ALTER TABLE `regime_favorite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `rende_vous`
--
ALTER TABLE `rende_vous`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id_reponse` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `sport`
--
ALTER TABLE `sport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `video`
--
ALTER TABLE `video`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD CONSTRAINT `fk_fav` FOREIGN KEY (`regime_id`) REFERENCES `regime` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_fav2` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `FK_DFEC3F3935E7D534` FOREIGN KEY (`regime_id`) REFERENCES `regime` (`id`);

--
-- Contraintes pour la table `rate_patient`
--
ALTER TABLE `rate_patient`
  ADD CONSTRAINT `FK_C018980A6B899279` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_C018980ABC999F9F` FOREIGN KEY (`rate_id`) REFERENCES `rate` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reclamation_patient`
--
ALTER TABLE `reclamation_patient`
  ADD CONSTRAINT `FK_CBF0097A2D6BA2D9` FOREIGN KEY (`reclamation_id`) REFERENCES `reclamation` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_CBF0097A6B899279` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `regime_favorite_patient`
--
ALTER TABLE `regime_favorite_patient`
  ADD CONSTRAINT `FK_35738F2D6B899279` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_35738F2D7B9C06C9` FOREIGN KEY (`regime_favorite_id`) REFERENCES `regime_favorite` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `regime_favorite_regime`
--
ALTER TABLE `regime_favorite_regime`
  ADD CONSTRAINT `FK_6CA20B4035E7D534` FOREIGN KEY (`regime_id`) REFERENCES `regime` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_6CA20B407B9C06C9` FOREIGN KEY (`regime_favorite_id`) REFERENCES `regime_favorite` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `regime_sport`
--
ALTER TABLE `regime_sport`
  ADD CONSTRAINT `FK_E2C3BF4D35E7D534` FOREIGN KEY (`regime_id`) REFERENCES `regime` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_E2C3BF4DAC78BCF8` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `rende_vous`
--
ALTER TABLE `rende_vous`
  ADD CONSTRAINT `FK_C9BB3BDC4F31A84` FOREIGN KEY (`medecin_id`) REFERENCES `medecin` (`id`),
  ADD CONSTRAINT `FK_C9BB3BDC6B899279` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
