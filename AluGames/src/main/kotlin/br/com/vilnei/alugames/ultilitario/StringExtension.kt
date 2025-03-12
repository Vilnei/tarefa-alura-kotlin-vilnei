import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

//isso aqui é uma extension function onde eu estou adicionando uma nova funcionalidade a função String
// entao e como se eu tivesse adionando a função de tranforma em anos para a Class String do Java/Kotlin

//transformar a data de nascimento em uma idade de valor inteirp
fun String.transformarEmIdade(): Int{

    //essa primeira variavel e para instabelecer qual formato vai ser recibido no caso dia/mes/ano
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    //vamos apenas criar a variavel idade pra modificala logo abaixo
    var idade = 0

    //aqui vamos usar a função parse para pegar essa data e colocar dentro do formato acima descrito
    val dataNascimento = LocalDate.parse(this, formatter)

    // aqui pegamos a data atual
    val hoje = LocalDate.now()

    //e aqui sim vamos tranforma essa data em uma idade inteira com o between fazendo comparação e o years trasnformando
    idade = Period.between(dataNascimento, hoje).years

    return idade

}