import org.junit.Test
import org.junit.Assert

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(var nome: String)

data class ConteudoEducacional(var nome: String, var duracao: Int = 60)

class Formacao(var nome: String, var nivel: Nivel, primeirosConteudos: Set<ConteudoEducacional> = emptySet()) {
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

class TestesFormacao() {    
    @Test
    fun `chamadas para o método matricular devem resultar na inclusão de um usuário`() {
        val usuario = Usuario("João")
        
        val formacao = Formacao("Desenvolvimento Backend com Kotlin", Nivel.BASICO).also {
            it.matricular(usuario)
        }
        
        Assert.assertTrue(formacao.inscritos.contains(usuario))
    }
    
    @Test
    fun `chamadas para o método registarConteudo devem resultar na inclusão de um conteúdo`() {
        val conteudo = ConteudoEducacional("Aprendendo Kotlin na Prática em Sua Documentação Oficial")
        
        val formacao = Formacao("Desenvolvimento Backend com Kotlin", Nivel.BASICO).also {
            it.registarConteudo(conteudo)
        }
        
        Assert.assertTrue(formacao.conteudos.contains(conteudo))
    }
    
    @Test
    fun `items da lista de usuários devem ser únicos`() {
        val usuario = Usuario("João")
        
        val formacao = Formacao("Desenvolvimento Backend com Kotlin", Nivel.BASICO).also {
            it.matricular(usuario)
            it.matricular(usuario)
        }
        
        Assert.assertEquals(formacao.inscritos.filter({ it === usuario }).size, 1)
    }
}
