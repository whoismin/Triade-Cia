# 🔐 SecureLogin CIA

Aplicação desenvolvida em Java com interface gráfica utilizando Swing, criada para demonstrar na prática os conceitos da Tríade CIA da Segurança da Informação:

* **Confidencialidade**
* **Integridade**
* **Disponibilidade**

O sistema simula um login seguro aplicando mecanismos reais de proteção de dados, verificação de integridade e controle de acesso.

---

# 📚 Objetivo do Projeto

O objetivo desta aplicação é demonstrar como conceitos fundamentais de Segurança da Informação podem ser aplicados diretamente no desenvolvimento de software.

O projeto foi desenvolvido como atividade prática da disciplina de Segurança e Defesa Cibernética.

---

# 🛡️ Tríade CIA Implementada

| Pilar             | Mecanismo utilizado    | Objetivo                      |
| ----------------- | ---------------------- | ----------------------------- |
| Confidencialidade | SHA-256                | Proteção da senha do usuário  |
| Integridade       | Verificação de Hash    | Detectar alterações nos dados |
| Disponibilidade   | Controle de tentativas | Evitar força bruta            |

---

# ⚙️ Funcionalidades

* Interface gráfica em Java Swing
* Sistema de login
* Proteção de senha utilizando SHA-256
* Verificação de integridade dos dados
* Bloqueio após múltiplas tentativas incorretas
* Mensagens de autenticação
* Demonstração prática da Tríade CIA

---

# 🖥️ Tecnologias Utilizadas

* Java
* Java Swing
* NetBeans
* SHA-256 (`MessageDigest`)

---

# 🔒 Confidencialidade

A senha do usuário não é armazenada em texto puro.

O sistema utiliza o algoritmo SHA-256 para gerar um hash criptográfico da senha antes da autenticação.

## Exemplo

```java
senhaHashArmazenada = gerarHashSHA256("123456");
```

---

# ✅ Integridade

A aplicação gera um hash contendo os dados do usuário e verifica se as informações foram alteradas.

## Exemplo

```java
String hashAtual = gerarHashSHA256(dadosAtuais);

if (hashAtual.equals(hashIntegridadeOriginal))
```

---

# 🚦 Disponibilidade

O sistema possui controle de tentativas de login.

Após 3 tentativas incorretas, o acesso é bloqueado temporariamente.

## Exemplo

```java
if (tentativasFalhas >= LIMITE_TENTATIVAS) {
    sistemaBloqueado = true;
}
```

---

# ▶️ Como Executar

## 1. Clone o repositório

```bash
git clone https://github.com/seuusuario/SecureLoginCIA.git
```

---

## 2. Abra no NetBeans

* File → Open Project
* Selecione a pasta do projeto

---

## 3. Execute a aplicação

Execute a classe:

```text
SecureLoginCIA.java
```

---

# 🔑 Dados para Teste

## Login válido

```text
Usuário: yasmin
Senha: 123456
```

---

# 🧪 Demonstração

## Login correto

* Autenticação realizada
* Verificação de integridade executada
* Mensagem da Tríade CIA exibida

## Login incorreto

* Sistema reduz tentativas restantes

## Excesso de tentativas

* Aplicação bloqueia novas tentativas
* Proteção contra força bruta ativada

---

# 📖 Conceitos Aplicados

* Segurança da Informação
* Tríade CIA
* Hash criptográfico
* Controle de autenticação
* Integridade de dados
* Proteção contra força bruta
* Interface gráfica em Java

---

# 👩‍💻 Desenvolvido por

**Yasmin Oliveira, Rebeca Matewanga, Leticia Borges, Mariana Ocireu, João Machado, Giovanna Vivencio**
Projeto acadêmico desenvolvido para fins educacionais na disciplina de Segurança e Defesa Cibernética.
