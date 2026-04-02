# Refatoração com Princípios SOLID

## Explicação das principais mudanças

O projeto foi refatorado com o objetivo de melhorar a organização do código, reduzir o acoplamento entre as classes e facilitar a manutenção e evolução do sistema.
A refatoração foi baseada nos princípios **SOLID** e na separação de responsabilidades em camadas.


O sistema foi dividido nos seguintes pacotes:

```
domain       -> Entidades do sistema
repository   -> Acesso a dados
service      -> Regras de negócio
view         -> Entrada e saída no console
controller   -> Controle do fluxo da aplicação
scoring      -> Estratégias de cálculo de pontuação
seed         -> Carga inicial de dados
config       -> Configuração e injeção de dependências
```

### Principais mudanças realizadas

* Separação do sistema em camadas
* Criação de entidades no pacote domain
* Criação da camada repository para acesso a dados
* Criação da camada service para regras de negócio
* Criação da camada controller para controlar o fluxo da aplicação
* Criação da camada view para entrada e saída no console
* Implementação de Strategy para cálculo de pontuação
* Criação do ApplicationContext para injeção de dependências
* Redução de acoplamento entre classes
* Aplicação dos princípios SOLID em toda a arquitetura

Essas mudanças tornaram o sistema mais organizado, mais fácil de manter e preparado para futuras expansões.

---

# Onde cada princípio SOLID foi aplicado

## S — Single Responsibility Principle (SRP)

Uma classe deve ter apenas uma responsabilidade.

Esse princípio foi aplicado separando o sistema em várias camadas:

* **domain**: apenas entidades do sistema
* **repository**: apenas acesso a dados
* **service**: apenas regras de negócio
* **view**: apenas entrada e saída
* **controller**: apenas controle do fluxo da aplicação
* **seed**: apenas carga inicial de dados
* **config**: apenas configuração da aplicação

Assim, cada classe possui apenas uma responsabilidade e uma única razão para mudar.

---

## O — Open/Closed Principle (OCP)

Classes devem estar abertas para extensão e fechadas para modificação.

Esse princípio foi aplicado principalmente em:

* **ScoringStrategy** (estratégia de pontuação)
* **DataSeeder** (carga inicial de dados)
* Interfaces de **Repository**, **Service** e **View**

Agora é possível:

* Criar novas estratégias de pontuação
* Criar novos tipos de repositório (ex: banco de dados)
* Criar novas interfaces de visualização
  Sem modificar o código existente, apenas criando novas implementações.

---

## L — Liskov Substitution Principle (LSP)

Objetos de classes filhas devem poder substituir objetos da classe pai sem quebrar o sistema.

Esse princípio foi aplicado nas implementações de interfaces, como:

* InMemoryParticipanteRepository
* InMemoryProvaRepository
* InMemoryQuestaoRepository
* InMemoryTentativaRepository
* SimpleAcertosScoringStrategy

Todas essas classes podem substituir suas interfaces sem alterar o funcionamento do sistema.

---

## I — Interface Segregation Principle (ISP)

Uma classe não deve ser forçada a implementar interfaces que não utiliza.

Esse princípio foi aplicado separando as interfaces da camada de visualização:

* InputView
* OutputView
* ChessBoardRenderer

Assim, cada classe implementa apenas a interface que realmente precisa, evitando interfaces grandes e desnecessárias.

---

## D — Dependency Inversion Principle (DIP)

As classes devem depender de abstrações e não de implementações concretas.

Esse princípio foi aplicado em toda a camada de serviços e controladores:

* Services dependem de interfaces de Repository
* Controllers dependem de interfaces de Service
* O ApplicationContext faz a injeção de dependências

Isso permite trocar implementações sem alterar a lógica principal do sistema.

---

# Conclusão

A refatoração utilizando os princípios SOLID deixou o sistema:

* Mais organizado
* Com menor acoplamento
* Mais fácil de manter
* Mais fácil de testar
* Preparado para futuras expansões
* Seguindo boas práticas de orientação a objetos e arquitetura de software

O sistema agora está estruturado em camadas e utilizando princípios SOLID para melhorar a qualidade do código.
