CREATE TABLE `tb_turma_aluno` (
  `id_turma` bigint NOT NULL,
  `id_pessoa` bigint NOT NULL,
  PRIMARY KEY (`id_turma`,`id_pessoa`),
  KEY `FK5jwccl7kaf1k5w9f8xr0f6m0s` (`id_pessoa`),
  CONSTRAINT `FK5jwccl7kaf1k5w9f8xr0f6m0s` FOREIGN KEY (`id_pessoa`) REFERENCES `tb_alunos` (`id_pessoa`),
  CONSTRAINT `FK7191qufs39lgqtyjvsuo0dwn4` FOREIGN KEY (`id_turma`) REFERENCES `tb_turmas` (`id_turma`)
)