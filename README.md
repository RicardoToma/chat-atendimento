## Exercício: adicionar as sequintes validações utilizando Exceptions:

- O nome do usuário deve conter ao menos 3 caracteres.
- O telefone do usuário deve conter ao menos 10 caracteres.
- O CPF deve conter 11 digitos.
- O e-mail deve conter @

Para resolver esse exercício:
- crie uma nova exceção chamada DadosUsuarioInvalidosException
- crie um método valida() que irá receber o usuário e fazer ifs para verificar os campos.
- para cada problema de validação do usuário, dispare a exceção com throw new.
- altere o main do programa ou menu apropriado para tratar a exceção lançada (try/catch).
