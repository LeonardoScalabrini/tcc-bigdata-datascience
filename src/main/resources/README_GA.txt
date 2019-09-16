**DEV**

**GA**

*Eletismo dois individuos*--FEITO
Copia os dois, ou poucos melhores individuos e o restante da população é gerado normalmente

*K, N, A, B individuo*

*Seleção roda de roleta*--FEITO
https://www.obitko.com/tutorials/genetic-algorithms/portuguese/selection.php
[Soma] Calcule a soma dos valores de adequação de todos os cromossomas da população - soma S.
[Seleção] Gere um número aleatório no intervalo (0,S) - r.
[Repetição] Percorra toda a população e some a adequação de 0 - soma s. Quando a soma s for maior que r, pare e retorne o cromossoma atual.

*Crossover aritimetico*--FEITO
Outro operador para representação real é o crossover aritmético definido como uma combinação linear de dois vetores (cromossomos): sejam x1 e x2 dois indivíduos selecionados para crossover, então os dois filhos resultantes serão
`x1 = ax1 + (1 − a)x2
`x2 = (1 − a)x1 + ax2
, onde a é um número aleatório pertencente ao intervalo [0,1]

*Mutação uniforme*--FEITO
um outro operador importante para problemas em que os indivíduos empregam
codificação em ponto flutuante é a mutação uniforme (MICHALEWICZ ,1996). Este
operador seleciona aleatoriamente um componente k ∈ {1, 2, …, n} do
cromossomo x = [x1 … xk … xn] e gera um indivíduo [ ] k n ′ = x x′ x x 1 , onde k x′
é um número aleatório (com distribuição de probabilidade uniforme) amostrado no
intervalo [LB, UB] e LB e UB são, respectivamente, os limites inferior e superior
da variável xk.

GA, we used the following settings: a crossover probability
of 0.6, a mutation probability of 0.01, a population of 100
individuals, and an elitism of 2 individuals. As a stopping
criterion for the GA, we terminated the evolution if the best
results achieved did not improve for 10 generations; otherwise
we stopped after 100 generations.

[k, n, α, β].

LDA params
k = k is the number of topics
n = number of iterations
As for α and β, we varied them between 0 and 1 by increments of 0.1.

GA params
500 is the number of iterations
100 is the size of population

*Verificar Random java --FEITO

*Verificar Exception --FEITO

*Implementar condição na GA --FEITO

*Implementar min e max para todos os parametros --FEITO

*Aptidao Jaccard*--FEITO