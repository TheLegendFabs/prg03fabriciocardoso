# Atividade 19 - DAO

PersistenceEntity centraliza o ID com @MappedSuperclass; Usuario e Perfil herdam diretamente, e Tecnico e Solicitante herdam por Usuario.

GenericIDao declara save, update, delete, findById e findAll. GenericDao recebe Class<Entity> e centraliza as transações, rollback, encerramento do EntityManager e tradução de falhas para DaoException.

Usuario, Perfil, Tecnico e Solicitante possuem interface e DAO específicos, com findByNome parametrizado em JPQL. As telas usam UsuarioIDao e os métodos do contrato; as mensagens de domínio continuam sendo exibidas com JOptionPane, sem EntityManager ou transações nas telas.

Os testes de integração anteriores utilizam o novo contrato de UsuarioDao.

GenericDaoTest usa assertThrows para falhas em save, update e delete; os testes anteriores validam CRUD, rollback, mapeamentos e migrations. As credenciais permanecem no persistence.xml local ignorado pelo Git.
