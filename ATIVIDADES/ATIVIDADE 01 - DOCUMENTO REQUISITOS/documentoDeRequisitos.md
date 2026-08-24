# Documento de Especificação de Requisitos

**Projeto:** Gestão de Chamadas

**Data de Criação:** 21/08/2026

**Autor:** Fabricio

**Cargo:** Técnico de Suporte de Ti

---

## Conteúdo  
1 Introdução

2 Descrição geral

3 Requisitos específicos

4 Restrições e Limitações

5 Glossário

6 Referências

7 Aprovação

---

## 1 Introdução  
O Sistema de Gestão de Chamados de TI tem como finalidade registrar, organizar e acompanhar as solicitações de suporte técnico no ambiente da clínica médica. O objetivo é substituir solicitações informais (como mensagens de texto ou pedidos verbais na recepção) por um fluxo organizado de atendimento.  
Público-alvo: Funcionários alocados em setores da clínica (ex: Recepção, Laboratório, Densitometria, Consultórios), o Técnico de TI (atendente único) e o Administrador do sistema.

### 1.1 Objetivo  
Este documento tem como objetivo apresentar de forma detalhada os requisitos funcionais e não funcionais do módulo de Chamados TI. Ele servirá como guia para o desenvolvimento do software contínuo em POO, descrevendo as funcionalidades, características e necessidades que serão abordadas na implementação.

### 1.2 Escopo do produto  
O escopo deste documento abrange as funcionalidades e características do sistema de chamados, desde a abertura de chamados pelo solicitante, roteamento para uma fila centralizada, gestão do ciclo de vida da solicitação (ABERTO -> EM_ANDAMENTO -> RESOLVIDO -> FECHADO) pelo técnico com registro obrigatório de parecer, e a possibilidade de reabertura em até 48 horas. Contempla também a gestão de cadastros de setores e usuários pelo perfil administrador.

### 1.3 Definições, Acrônimos e Abreviações  
| № | Sigla | Definição |  
| :--- | :--- | :--- |  
| 01 | RF | Requisito funcional |  
| 02 | RNF | Requisito não funcional |  
| 03 | TI | Tecnologia da Informação |  
| 04 | POO | Programação Orientada a Objetos |  
| 05 | DAO | Data Access Object (Objeto de Acesso a Dados) |

## 2 Descrição geral

### 2.1 Modo de Operação  
O sistema opera como uma plataforma de centralização de demandas técnicas da clínica. Ele interliga os colaboradores dos diversos setores operacionais (como a sala da máquina de densitometria e recepção) diretamente ao técnico responsável, proporcionando um fluxo de solicitação, rastreabilidade e resolução ágil.

### 2.2 Funções do produto  
| № | Função | Descrição |  
| :--- | :--- | :--- |  
| 01 | Gerenciar Cadastros | O administrador pode cadastrar, editar e desativar usuários e setores. |  
| 02 | Abrir Chamado | O solicitante pode registrar um chamado, selecionando categoria e relatando o problema. |  
| 03 | Atender Chamado | O técnico assume demandas da fila central e atualiza o status de atendimento. |  
| 04 | Registrar Solução | O técnico é obrigado a documentar a solução do chamado para poder finalizá-lo. |  
| 05 | Reabrir Chamado | O solicitante pode contestar a solução em até 48 horas. |

### 2.3 Usuários e sistemas externos  
| № | Usuário | Descrição |  
| :--- | :--- | :--- |  
| 01 | Administrador | Responsável pelo cadastro e manutenção das tabelas base (setores, categorias, usuários). |  
| 02 | Solicitante (Funcionário) | Colaborador da clínica que registra a falha técnica e acompanha o andamento. |  
| 03 | Técnico de TI | Profissional único responsável por analisar e resolver os chamados da fila central. |

### 2.4 Restrições  
| № | Tipo | Descrição |  
| :--- | :--- | :--- |  
| 01 | Processo | O ciclo de vida do chamado obedece a uma máquina de estados estrita. |  
| 02 | Processo | A reabertura de solicitações expira estritamente após 48 horas da resolução. |  
| 03 | Regra de Negócio | O parecer técnico de resolução deve conter no mínimo 10 caracteres. |  
| 04 | Segurança | O acesso é restrito por login individual. O solicitante só visualiza suas solicitações. |

### 2.5 Requisitos Adiados  
| № | Referência | Detalhes |  
| :--- | :--- | :--- |  
| 01 | Relatórios de Ativos | Geração de relatórios cruzando chamados com hardwares específicos. |  
| 02 | SLA de Atendimento | Implementação de tempo máximo limite para primeira resposta do técnico. |

## 3 Requisitos específicos

### 3.1 Requisitos Funcionais  
| Código | Nome | Descrição |  
| :--- | :--- | :--- |  
| RF01 | Abertura de Chamado | O solicitante deve registrar o chamado informando título, descrição, setor de origem e categoria (Hardware, Software, Rede, Impressora, Densitometria). |  
| RF02 | Fila Centralizada | Todo novo chamado registrado deve ser direcionado automaticamente para a Fila Central da TI. |  
| RF03 | Gestão de Ciclo de Vida | O sistema deve controlar as etapas do chamado: ABERTO -> EM_ANDAMENTO -> RESOLVIDO -> FECHADO. |  
| RF04 | Registro de Solução | O técnico deve obrigatoriamente informar a descrição do parecer técnico (min. 10 caracteres) para alterar o status para RESOLVIDO. |  
| RF05 | Contestação | O solicitante pode contestar a solução em até 48 horas após a resolução. |  
| RF06 | Consulta e Filtros | Permitir a busca e filtragem de chamados por setor, status, categoria, prioridade e data. |  
| RF07 | Gestão Base | Permitir o cadastro, alteração e desativação de setores e contas de usuários. |

### 3.2 Requisitos Não-Funcionais

#### 3.2.1 Desempenho  
| Código | Requisito / Aplicação | Descrição |  
| :--- | :--- | :--- |  
| RNF01 | Resposta da Fila | A atualização e ordenação dinâmica da fila central devem ocorrer em tempo real. |

#### 3.2.2 Usabilidade  
| Código | Requisito / Aplicação | Descrição |  
| :--- | :--- | :--- |  
| RNF02 | Tratamento de Erros | O sistema deve tratar erros de regra de negócio (ex: tentar resolver sem parecer) lançando mensagens em português. |

#### 3.2.3 Segurança  
| Código | Requisito / Aplicação | Descrição |  
| :--- | :--- | :--- |  
| RNF03 | Autenticação | Acesso restrito por login individual, isolando a visão do Solicitante da visão do Técnico. |

#### 3.2.4 Expansibilidade  
| Código | Requisito / Aplicação | Descrição |  
| :--- | :--- | :--- |  
| RNF04 | Arquitetura Modular | Código estruturado em camadas desacopladas (Domínio/Model, Service, DAO e Controller). |

### 3.3 Requisitos de Interface

#### 3.3.1 Interfaces de Usuário  
| № | Nome | Descrição |  
| :--- | :--- | :--- |  
| 01 | Tela de Login | Interface para autenticação no sistema. |  
| 02 | Abertura de Chamado | Interface para o solicitante preencher os dados do problema. |  
| 03 | Fila Central | Interface para o Técnico visualizar e assumir os chamados. |  
| 04 | Tela de Resolução | Interface onde o técnico digita o parecer e finaliza o chamado. |

#### 3.3.2 Interfaces de Hardware

#### 3.3.3 Interfaces de Software

#### 3.3.4 Interfaces de Comunicação

### 3.4 Casos de Uso (UC)

#### UC01: Abrir Solicitação de Chamado
* **Ator:** Solicitante (Funcionário do Setor)
* **Pré-condição:** Usuário autenticado e vinculado a um setor.
* **Fluxo Principal:**
  1. O solicitante acessa o menu de abertura de chamados.
  2. Informa título, descrição detalhada e seleciona a categoria do problema.
  3. O sistema identifica o setor de origem.
  4. O sistema insere o chamado na Fila Central da TI e gera o número de protocolo.
* **Pós-condição:** Chamado registrado com status ABERTO.

#### UC02: Atender e Resolver Chamado
* **Ator:** Técnico de TI (Atendente Único)
* **Pré-condição:** Existir chamado com status ABERTO ou EM_ANDAMENTO na Fila Central.
* **Fluxo Principal:**
  1. O técnico acessa a Fila Central e seleciona a solicitação.
  2. Altera o status para EM_ANDAMENTO para sinalizar que a manutenção foi iniciada.
  3. Após a execução do reparo, digita a descrição técnica da solução realizada e o tempo investido.
  4. Confirma a resolução. O sistema valida os campos e atualiza o status para RESOLVIDO.
* **Fluxo Alternativo:** Se a descrição da solução for omitida, o sistema bloqueia a ação e exige o preenchimento.
* **Pós-condição:** Chamado finalizado com parecer registrado e status RESOLVIDO.

#### UC03: Reabrir Chamado Contestado
* **Ator:** Solicitante
* **Pré-condição:** Chamado no status RESOLVIDO com menos de 48 horas de encerramento.
* **Fluxo Principal:**
  1. O solicitante informa que a falha voltou a ocorrer e justifica a reabertura.
  2. O sistema valida a janela de 48 horas.
  3. O status do chamado é alterado para EM_ANDAMENTO e a prioridade é aumentada em um nível.
* **Fluxo Alternativo:** Se transcorridas mais de 48 horas, o sistema orienta o usuário a abrir um novo chamado.

## 4 Restrições e Limitações

## 5 Glossário  
| Termo | Definição |  
| :--- | :--- |  
| Parecer Técnico | Justificativa ou descrição técnica (mínimo de 10 caracteres) da ação realizada para consertar a falha. |  
| Fila Central | Repositório único onde todos os chamados abertos da clínica são listados e ordenados por prioridade. |  
| Máquina de Estados | Regra lógica que impede que um chamado pule etapas (ex: ir de ABERTO direto para FECHADO). |

## 6 Referências

## 7 Aprovação  
Este documento foi revisado e aprovado por:

Nome:

Cargo:  
