# Aprenda Kotlin Com Exemplos: Desafio de Projeto (Lab)

##  Descrição do problema:

A [DIO](https://web.dio.me) possui `Formacoes` incríveis que têm como objetivo oferecer um conjunto de `ConteudosEducacionais` voltados para uma stack tecnológica específica, preparando profissionais de TI para o mercado de trabalho. `Formacoes` possuem algumas características importantes, como `nome`, `nivel` e seus respectivos `conteudosEducacionais`. Além disso, tais experiências educacionais têm um comportamento relevante ao nosso domínio, definido pela capacidade de `matricular` um ou mais `Alunos`.

Crie uma solução em Koltin abstraindo esse domínio.

## Descrição da solução:



### Modelagem de dados
O processo de modelagem de dados resultou na criação de 4 classes, sendo que foi dada uma atenção especial ao encapsulamento de dados, o que levou à opções de design como:

* Backing fields
```kotlin
class Formacao(var nome: String, var nivel: Nivel, primeirosConteudos: Set<ConteudoEducacional> = emptySet()) {
    ...
    
    private val _inscritos = mutableSetOf<Usuario>()
        
    val inscritos
	    get() = _inscritos.toSet()
    
	fun matricular(usuario: Usuario) {
	    _inscritos.add(usuario)
	}
	
	...
}
```
* O não uso de Data Class para classes que tenham como parâmetros de seu construtor uma coleção à qual elementos poderão ser adicionados, visto que isso levaria à exposição de métodos que poderiam alterá-las na interface da classe de que fazem parte

```diff
- data class Formacao(var nome: String,  var nivel: Nivel, val primeirosConteudos: MutableSet<ConteudoEducacional> =  emptyMutableSet())
+ class Formacao(var nome: String,  var nivel: Nivel, primeirosConteudos: Set<ConteudoEducacional> = emptySet())
```

### Validação

Para validação da solução, foi utilizada a biblioteca de testes JUnit:
```kotlin
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
```
