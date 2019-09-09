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

*Implementar condição na GA

*Implementar min e max para todos os parametros

*Aptidao Jaccard*--FEITO

https://github.com/mrquincle/gibbs-lda

https://lettier.com/projects/lda-topic-modeling/

model-final.theta

3. Como usar o GibbsLDA ++
3.1 Linha de comando e parâmetros de entrada
Após compilar o GibbsLDA ++, temos o arquivo executável "lda" no diretório "GibbsLDA ++ / src". Usamos isso para estimativa de parâmetros e inferência para novos dados.

3.1.1 Estimativa de parâmetros a partir do zero
$ lda -est [-alpha <double>] [-beta <double>] [-ntopics <int>] \
  [-niters <int>] [-savestep <int>] [-twords <int>] -dfile <string>

in which (parameters in [] are optional):

-est:
    ESTimate the LDA model from scratch

-alpha <double>:
    The value of alpha, hyper-parameter of LDA. The default value
    of alpha is 50 / K (K is the the number of topics). See [Griffiths04]
    for a detailed discussion of choosing alpha and beta values.

-beta <double>:
    The value of beta, also the hyper-parameter of LDA. Its default value
    is 0.1

-ntopics <int>:
    The number of topics. Its default value is 100. This depends on the
    input dataset. See [Griffiths04] and [Blei03] for a more careful
    discussion of selecting the number of topics.

-niters <int>:
    The number of Gibbs sampling iterations. The default value is 2000.

-savestep <int>:
    The step (counted by the number of Gibbs sampling iterations) at which
    the LDA model is saved to hard disk. The default value is 200.

-twords <int>:
    The number of most likely words for each topic. The default value is zero.
    If you set this parameter a value larger than zero, e.g., 20, GibbsLDA++
    will print out the list of top 20 most likely words per each topic each
    time it save the model to hard disk according to the parameter "savestep"
    above.

-dfile <string>:
    The input training data file. See Section 3.2 for a description of
    input data format.

3.3 Saídas
3.3.1 Resultados da estimativa de amostragem de Gibbs de GibbsLDA ++
As saídas da estimativa de amostragem de Gibbs do GibbsLDA ++ incluem os seguintes arquivos:

<model_name>.others
<model_name>.phi
<model_name>.theta
<model_name>.tassign
<model_name>.twords
no qual:

+ <model_name>:
   is the name of a LDA model corresponding to the time step it was saved
   on the hard disk. For example, the name of the model was saved at the Gibbs
   sampling iteration 400th will be "model-00400". Similarly, the model was
   saved at the 1200th iteration is "model-01200". The model name of the last
   Gibbs sampling iteration is "model-final".

+ <model_name>.others:
   This file contains some parameters of LDA model, such as:
      alpha=?
      beta=?
      ntopics=? # i.e., number of topics)
      ndocs=? # i.e., number of documents)
      nwords=? # i.e., the vocabulary size)
      liter=? # i.e., the Gibbs sampling iteration at which the model was saved)

+ <model_name>.phi:
   This file contains the word-topic distributions,
   i.e., p(word_w | topic_t). Each line is a topic, each column is a word in
   the vocabulary

+ <model_name>.theta:
   This file contains the topic-document distributions,
   i.e., p(topic_t | document_m). Each line is a document and each column is
   a topic.

+ <model_name>.tassign:
   This file contains the topic assignments for words in training data. Each
   line is a document that consists of a list of <word_ij>:<topic of word_ij>

+ <model_file>.twords:
   This file contains <twords> most likely words of each topic. <twords> is
   specified in the command line (see Sections 3.1.1 and 3.1.2).
O GibbsLDA ++ também salva um arquivo chamado "wordmap.txt" que contém os mapas entre as palavras e os IDs das palavras (número inteiro). Isso ocorre porque o GibbsLDA ++ trabalha diretamente com IDs inteiros de palavras / termos internos, em vez de cadeias de texto.