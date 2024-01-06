import org.junit.Test
import org.junit.Assert

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario

data class ConteudoEducacional(var nome: String, var duracao: Int = 60)

data class Formacao(var nome: String, var nivel: Nivel, val conteudos: List<ConteudoEducacional>) {
    private val _inscritos = mutableSetOf<Usuario>()
        
    val inscritos
        get() = _inscritos.toSet()
    
    fun matricular(usuario: Usuario) {
        _inscritos.add(usuario)
    }
}

fun main() {
//     TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
//     TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}

class TestesUsuario() {
    val usuario = Usuario()
        
    val conteudos = listOf(
        ConteudoEducacional("Aprendendo Kotlin na Prática em Sua Documentação Oficial")
    )
    
    @Test
    fun `chamadas para o método matricular devem resultar na inclusão de um Usuário`() {
        val formacao = Formacao("Desenvolvimento Backend com Kotlin", Nivel.BASICO, conteudos).also {
            it.matricular(usuario)
        }
        
        Assert.assertEquals(formacao.inscritos, setOf(usuario))
    }
    
    @Test
    fun `items da lista de usuários devem ser únicos`() {
        val formacao = Formacao("Desenvolvimento Backend com Kotlin", Nivel.BASICO, conteudos).also {
            it.matricular(usuario)
            it.matricular(usuario)
        }
        
        Assert.assertEquals(formacao.inscritos, setOf(usuario))
    }
}
