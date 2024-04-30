Feature: Realizar empréstimo

	Scenario: Exemplar disponível
		Given um "exemplar" "está" disponível
     When um sócio solicita o empréstimo do exemplar
     Then o sistema realiza o empréstimo
      And o sistema notifica a realização do empréstimo

	Scenario: Exemplar indisponível
		Given um "exemplar" "não está" disponível
     When um sócio solicita o empréstimo do exemplar
     Then o sistema informa que o "exemplar" não está disponível

	Scenario: Livro disponível
		Given um "livro" "está" disponível
     When um sócio solicita o empréstimo do livro
     Then o sistema realiza o empréstimo
      And o sistema notifica a realização do empréstimo

	Scenario: Livro indisponível
		Given um "livro" "não está" disponível
     When um sócio solicita o empréstimo do livro
     Then o sistema informa que o "livro" não está disponível