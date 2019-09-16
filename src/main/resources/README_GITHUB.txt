Busca todas as issues já fechadas
https://api.github.com/repos/iluwatar/java-design-patterns/issues?state=closed&page=1&per_page=100

Busca todos os commits
*Precisa filtrar os commits por branch (master)--FEITO
https://api.github.com/repos/iluwatar/java-design-patterns/commits?sha=master

Busca todos os detablhes de um commit, como
message: commit message
parents: Required. The SHAs of the commits that were the parents of this commit. If omitted or empty, the commit will be written as a root commit. For a single parent, an array of one SHA should be provided; for a merge commit, an array of more than one should be provided.
files: Lista de arquivos alterados
files.filename: Nome do arquivo
files.patch: Diferenças do arquivo alterado
https://api.github.com/repos/iluwatar/java-design-patterns/commits/019abc9980b9855bf04086c44b68f3282119c6b3