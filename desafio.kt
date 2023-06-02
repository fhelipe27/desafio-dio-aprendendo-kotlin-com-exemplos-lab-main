enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome: String, val email: String)

data class ConteudoEducacional(var nome: String, var duracao: Int = 60, val nivelDificuldade: Nivel)

data class Formacao(val nome: String, var conteudos: ArrayList<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        // TODO: Simular a matrícula do usuário utilizando a lista de inscritos.
        inscritos.add(usuario)
    }

    fun removerInscrito(usuario: Usuario) {
        inscritos.remove(usuario)
    }
}

fun main() {
    val usuarios = arrayListOf<Usuario>()
    var usuario: Usuario
    var nomeUsuario: String
    var emailUsuario: String
    var quantidadeUsuarios: Int
    var count = 1
    var formacao: Formacao
    val conteudos = arrayListOf<ConteudoEducacional>()
    var conteudo: ConteudoEducacional
    var nomeConteudo: String
    var nivelConteudo: Nivel = Nivel.BASICO
    var duracaoConteudo: Int
    var opcaoSelecionada = 0

    println("Informe quantos usuários pretende cadastrar:")
    print("> ")
    quantidadeUsuarios = readLine()!!.toInt()

    // Cadastro de usuários
    while (count <= quantidadeUsuarios) {
        println("Digite o nome do $count° usuário:")
        print("> ")
        nomeUsuario = readLine().toString()

        println("Digite o e-mail do $count° usuário:")
        print("> ")
        emailUsuario = readLine().toString()

        usuario = Usuario(nomeUsuario, emailUsuario)
        usuarios.add(usuario)

        count++
    }

    if (quantidadeUsuarios == 0) {
        println("Você não cadastrou nenhum usuário.")
    } else {
        println("$quantidadeUsuarios usuários cadastrados com sucesso!")
        println("....")
        println("....")
    }

    count = 1

    println("Informe quantos conteúdos pretende cadastrar:")
    print("> ")
    val quantidadeConteudos = readLine()!!.toInt()

    // Cadastro de conteúdos
    while (count <= quantidadeConteudos) {
        println("Digite o nome do conteúdo número $count:")
        print("> ")
        nomeConteudo = readLine().toString()

        println("Digite a duração do conteúdo número $count:")
        print("> ")
        duracaoConteudo = readLine()!!.toInt()

        while (opcaoSelecionada < 1 || opcaoSelecionada > 3) {
            println("Digite o número correspondente ao nível do conteúdo número $count:")
            println(
                """
                1 - BÁSICO
                2 - INTERMEDIÁRIO
                3 - DIFÍCIL   
                """.trimIndent()
            )
            print("> ")
            opcaoSelecionada = readLine()!!.toInt()

            nivelConteudo = when (opcaoSelecionada) {
                1 -> Nivel.BASICO
                2 -> Nivel.INTERMEDIARIO
                3 -> Nivel.DIFICIL
                else -> {
                    println("Digite uma opção válida.")
                    nivelConteudo
                }
            }
        }

        opcaoSelecionada = 0

        conteudo = ConteudoEducacional(nomeConteudo, duracaoConteudo, nivelConteudo)
        conteudos.add(conteudo)

        count++
    }

    while (opcaoSelecionada != -1) {
        println("....")
        println("Escolha uma das opções abaixo e digite o número correspondente:")
        println("....")

        // Exibir conteúdos cadastrados
        for (i in conteudos.indices) {
            println()
            println("$i - Conteúdo: ${conteudos[i].nome}, duração: ${conteudos[i].duracao} horas")
        }

        println("-1 - Sair")
        println("....")
        print("> ")
        opcaoSelecionada = readLine()!!.toInt()

        if (opcaoSelecionada == -1) {
            break
        }

        formacao = Formacao(conteudos[opcaoSelecionada].nome, conteudos)

        println("....")
        println("Qual usuário deseja cadastrar no conteúdo ${conteudos[opcaoSelecionada].nome}?")
        println("....")

        // Exibir usuários cadastrados
        println("....")
        for (i in usuarios.indices) {
            println("$i - ${usuarios[i].nome}, e-mail: ${usuarios[i].email}")
        }

        println("-1 - Sair")
        println("....")
        print("> ")
        opcaoSelecionada = readLine()!!.toInt()

        if (opcaoSelecionada == -1) {
            break
        }

        formacao.matricular(usuarios[opcaoSelecionada])

        println("....")
        println("Usuário matriculado com sucesso!")
        println("....")
        println("Deseja matricular outro usuário?")
        println("....")
        println(
            """
            1 - Matricular novo usuário
            -1 - Sair
            """.trimIndent()
        )

        opcaoSelecionada = readLine()!!.toInt()

        if (opcaoSelecionada == -1) {
            break
        }
    }

    println("Matrículas criadas com sucesso!")
}