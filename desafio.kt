import org.junit.Test
import org.junit.Assert

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario

data class ConteudoEducacional(var nome: String, var duracao: Int = 60)

class Formacao(var nome: String, var nivel: Nivel, primeirosConteudos: Set<ConteudoEducacional>) {
    private val _inscritos = mutableSetOf<Usuario>()
    private val _conteudos = primeirosConteudos.toMutableSet()
        
    val inscritos
        get() = _inscritos.toSet()
    
    fun matricular(usuario: Usuario) {
        _inscritos.add(usuario)
    }
    
    val conteudos
        get() = _conteudos.toSet()
        
    fun registarConteudo(conteudo: ConteudoEducacional) {
        _conteudos.add(conteudo)
    }
}

fun main() {
}

class TestesUsuario() {
    val usuario = Usuario()
        
    val conteudos = setOf(
        ConteudoEducacional("Aprendendo Kotlin na Prática em Sua Documentação Oficial")
    )
    
    @Test
    fun `chamadas para o método matricular devem resultar na inclusão de um usuário`() {
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
