Feature: Devolver

	Scenario: Exemplar emprestado
		Given um exemplar "foi" emprestado
     When o exemplar for devolvido
     Then o empréstimo é concluído com sucesso
      And o sistema notifica a devolução do exemplar

	Scenario: Exemplar não emprestado
		Given um exemplar "não foi" emprestado
     When o exemplar for devolvido
     Then o sistema informa que o exemplar não está emprestado