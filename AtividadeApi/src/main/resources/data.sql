-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.26 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando dados para a tabela desafioapi.desafio: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `desafio` DISABLE KEYS */;
INSERT INTO `desafio` (`id`, `nome`) VALUES
	(1, 'Desafio Api'),
	(2, 'Desafio MVC'),
	(3, 'Desafio MVVM');
/*!40000 ALTER TABLE `desafio` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi.nota: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` (`id`, `qualidade`, `quantidade`, `submissao_id`) VALUES
	(1, 3, 3, 1),
	(2, 1, 3, 2),
	(3, 1, 1, 3),
	(5, 2, 2, 4);
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi.perfil: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` (`id`, `nome`) VALUES
	(1, 'Starter'),
	(2, 'Instrutor');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi.starter: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `starter` DISABLE KEYS */;
INSERT INTO `starter` (`id`, `email`, `bairro`, `cep`, `cidade`, `complemento`, `estado`, `logradouro`, `numero`, `linguagem`, `nome`, `quatro_letras`, `senha`, `telefone`, `perfil_id`) VALUES
	(1, 'diga@gft.com', 'Centro', '83.840-000', 'Quitandinha', 'Casa', 'Paraná', 'Av. Fernandes de Andrade', '1405', 'Java', 'Dirceu Garcia', 'diga', '$2a$10$ZuTTZ5Z8luQbyGAAANFMD.8jOmUNjBfwV/vepPYIFk4NXwFDIkOjW', '41999939918', 2),
	(2, 'dota@gft.com', 'Jardim Velho', '22.321-456', 'Quitandinha', 'Casa', 'Paraná', 'Rua Novo Mundo', '6703', 'Kotlin', 'Douglas Tarantino', 'dota', '$2a$10$rwN.tDiS6NnsOcpNkjirY.DAF.aue.jsUlhn0kA5dVi33B/sWZW2G', '4164885868', 2),
	(3, 'amdi@gft.com', 'Vista Alegre', '22.321-458', 'Quitandinha', 'Ap 702', 'Paraná', 'Rua Da Oliveira', '623', 'Swift', 'Amanda Dias', 'amdi', '$2a$10$TLgnTKSTSfdCI2mm.jbKU.N7pDb3XiB0cY71h1E1O2SElPTMTY.yi', '4196848329', 1),
	(4, 'furo@gft.com', 'Centro', '83.840-000', 'Quitandinha', 'Casa', 'Paraná', 'Rua Da Puma', '132', 'Android', 'Fulvio Rodrigo', 'furo', '$2a$10$3GP9rsPusL2SaOzlMsJWk.3mcv2z0BTK7L0B77y4q8ZKmx8MK/S/m', '41993757823', 1);
/*!40000 ALTER TABLE `starter` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi.submissao: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `submissao` DISABLE KEYS */;
INSERT INTO `submissao` (`id`, `repositorio`, `desafio_id`, `starter_id`) VALUES
	(1, 'Repositorio Aqui', 1, 3),
	(2, 'Repositorio Aqui', 2, 3),
	(3, 'Repositorio Aqui', 3, 3),
	(4, 'Repositorio Aqui', 2, 4),
	(5, 'Repositorio Aqui', 1, 4),
	(6, 'Repositorio Aqui', 3, 4),
	(7, 'Repositorio Aqui', 3, 4);
/*!40000 ALTER TABLE `submissao` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

SET FOREIGN_KEY_CHECKS=1;

