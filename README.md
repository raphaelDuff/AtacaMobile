CENTRO UNIVERSITÁRIO DA FEI



LUCAS NEVADO PEREIRA DE TOLEDO

RAFAEL BARRIONUEVO

 RAISSA PEDROSA BOCALINI

 RAPHAEL OLIVEIRA PRATES

RODRIGO HARDMEIER ROCHA DE AMORIM LIMA



AUTOMATIZAÇÃO DE COMPRAS



São Bernardo do Campo

2017



LUCAS NEVADO PEREIRA DE TOLEDO

RAFAEL BARRIONUEVO

 RAISSA PEDROSA BOCALINI

 RAPHAEL OLIVEIRA PRATES

RODRIGO HARDMEIER ROCHA DE AMORIM LIMA



AUTOMATIZAÇÃO DE COMPRAS

Trabalho de Conclusão de Curso, apresentado ao Centro Universitário da FEI, como parte dos requisitos necessários para obtenção do título de Bacharel em Engenharia Elétrica, orientado pelo Prof. Victor Sonnenberg





















São Bernardo do Campo

2017





















LUCAS NEVADO PEREIRA DE TOLEDO

RAFAEL BARRIONUEVO

 RAISSA PEDROSA BOCALINI

 RAPHAEL OLIVEIRA PRATES

RODRIGO HARDMEIER ROCHA DE AMORIM LIMA



AUTOMATIZAÇÃO DE COMPRAS

Trabalho de Conclusão de Curso - Centro Universitário da FEI



Comissão julgadora

\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

Orientador e Presidente

\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

Examinador (1)

\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

Examinador (2)





























A Deus, aos nossos familiares, aos amigos, aos professores, ao professor orientador Victor Sonnenberg e a FEI.

Primeiramente a Deus, por ter tornado isso tudo possível, por ter dado a oportunidade de trabalharmos juntos e nos dar força para enfrentar as dificuldades que surgiram. Familiares e amigos, que nos incentivaram a dar o melhor, mesmo estando longe deles para nos dedicar a esse projeto. Aos professores que nos deram um caráter exigente em relação ao nosso desempenho, conhecimento e bom senso.

AGRADECIMENTOS





&quot;Todos os seus sonhos podem se tornar realidade se você tem coragem para persegui-los. &quot;

                                        &quot;Walt Disney&quot;

Fazer compras e um supermercado varejista ou/e atacadista pode ser uma tarefa estressante. Pensando nisso, vimos a necessidade de facilitar a compra das pessoas, visando o maior conforto e agilidade de maneira que haja uma forma de evitar filas e, consequentemente, perdendo menos tempo dentro desses estabelecimentos. Com um sistema inteligente e uso de um aplicativo de celular, as pessoas podem selecionar e acompanhar suas compras e fazer o pagamento usando seu próprio smartphone. Desse modo, os consumidores ganham tempo evitando filas. No nosso projeto utilizamos a tecnologia NFC (Near Field Communications) onde o consumidor vai aproximar o seu celular na TAG NFC onde está o produto e o aplicativo vai reconhecer imediatamente o produto. O projeto também busca a redução de custos ao supermercado, trazendo uma solução que o custo de implementação será somente com o software e o banco de dados, pois o equipamento utilizado é o próprio celular do consumidor.

RESUMO

Palavras-chave : Supermercado, Aplicativo, Compras, NFC, Android, Android Studio.



ABSTRACT

Shopping at a supermarket retailer and/or wholesaler can be a stressful task. Come to think of it, we saw the need to facilitate the purchase of the people, to comfort and agility so that there is a way to avoid queues and, consequently, losing less time within those institutions. With an intelligent system and use of a mobile phone application people can select and track their purchases and make payment using your own smartphone. That way, consumers win while avoiding queues. In our project we use the NFC technology (Near Field Communications) where the consumer will close your phone in NFC TAG where is the product and the application will recognize immediately the product. The project also seeks to reduce costs to the market, bringing a solution in which the cost of implementation will be only with the software and the database, because the equipment used is the cell itself.

Keywords: Supermarket, App, Shopping, NFC

**LISTA DE ILUSTRAÇÕES**

Figura 1 - Tag NFC Passivo        14

Figura 2 - Aplicação usando NFC ativo        14

Figura 3 - Qual a preferência dos compras dos clientes americanos        17

Figura 4 - Método Main        19

Figura 5 - Estrutura de um MBaaS        21

Figura 6 - Banco de dados dos produtos no Firebase        23

[Figura 7 - Tela inicial do aplicativo AtacaMobile](../../C:%5CUsers%5CLucas%5CDownloads%5CMONOGRAFIA_AUTOMATIZACAO_DE_COMPRAS_V4.docx#_Toc500101527)        25

[Figura 8 - Foto do logotipo do aplicativo AtacaMobile](../../C:%5CUsers%5CLucas%5CDownloads%5CMONOGRAFIA_AUTOMATIZACAO_DE_COMPRAS_V4.docx#_Toc500101528)        25

[Figura 9 - Botão &quot;Carrinho de Compras&quot;](../../C:%5CUsers%5CLucas%5CDownloads%5CMONOGRAFIA_AUTOMATIZACAO_DE_COMPRAS_V4.docx#_Toc500101529)        25

[Figura 10 - Botão de leitura do NFC](../../C:%5CUsers%5CLucas%5CDownloads%5CMONOGRAFIA_AUTOMATIZACAO_DE_COMPRAS_V4.docx#_Toc500101530)        26

[Figura 11 - Tela de leitura da TAG NFC](../../C:%5CUsers%5CLucas%5CDownloads%5CMONOGRAFIA_AUTOMATIZACAO_DE_COMPRAS_V4.docx#_Toc500101531)        26

Figura 12 - Tela da leitura de um produto        30

Figura 13 - Tela do Menu Principal        31

Figura 14 - Busca pela localização do produto desejado        31

Figura 15 - Indicação dos produtos pesquisados pelo cliente        32

[Figura 16 - Localização do produto representada por um mapa do mercado](../../C:%5CUsers%5CLucas%5CDownloads%5CMONOGRAFIA_AUTOMATIZACAO_DE_COMPRAS_V4.docx#_Toc500101536)        32

[Figura 17 - Diagrama de leitura dos produtos](../../C:%5CUsers%5CLucas%5CDownloads%5CMONOGRAFIA_AUTOMATIZACAO_DE_COMPRAS_V4.docx#_Toc500101537)        33

Figura 18 - Diagrama de procura dos produtos        33



# 7.Sumário



Sumário        11

1.        INTRODUÇÃO        12

2.        OBJETIVO        13

3.        TECNOLOGIA        13

3.1        NFC (Near Field Communications)        13

3.1.1.        Modos de Comunicação        14

3.1.2.        Modos de operação        15

3.1.3.        NFC no projeto        15

3.2.        Tags NFC        16

3.3.        IoT no supermercado/atacado        16

3.4        Android Studio        18

3.5        Linguagem Java        19

3.6        Banco de dados        21

3.6.1.        MBaaS: Firebase        21

3.6.2.        Firebase Realtime Database        22

3.6.3.        Firebase Authentication        23

4.        O PROJETO        24

4.1.        Fluxograma        33

5.        PROBLEMAS DURANTE O PROJETO        34

6.        CONCLUSÕES        35

7.        POSSÍVEIS MELHORIAS        36

8.        APÊNDICE        37

9.        REFERÊNCIAS        37

1. 1.INTRODUÇÃO

Hoje em dia nos supermercados, os consumidores fazem suas compras sem saber quanto irão gastar ao passar pelo caixa, pegam filas enormes para comprarem poucos produtos, perdem muito tempo na procura de determinados produtos, e pela correria do dia - a – dia, muitas vezes acabam esquecendo a carteira em casa ou no carro. Pensando nesses transtornos, decidimos realizar um projeto visando o benefício de ambas as partes: a melhoria no bem-estar do consumidor, evitando qualquer tipo de desconforto ou frustrações, e a melhoria devido a automatização do processo de compra para o supermercado e os benefícios referentes a economia e agilidade que isto traz (menor número de funcionários e fidelidade do cliente).

Este projeto busca implementar um sistema inteligente em um supermercado de maneira a auxiliar e facilitar as compras dos consumidores, visando baixo custo para o supermercado. O sistema permitirá que o cliente verifique o preço e validade do produto usando o seu próprio smartphone, adicionar o(s) produto(s) em sua lista de compras virtual, como também identificar a localização do(s) produto(s) desejado(s), tudo isso através de um aplicativo (App). O projeto engloba também o sistema utilizado pelo supermercado para a atualização dos preços, validade dos produtos e lançamentos de promoções relâmpagos.

As prateleiras do supermercado são dotados com tag NFC, em que cada tag representa um produto. Dessa maneira, o cliente que contém celular com esta tecnologia pode acessar o aplicativo &quot;ATACAMOBILE&quot; e fazer a leitura dos tags. O celular inicia a comunicação com o tag, capta o seu sinal que é enviado para o aplicativo. Esse sinal vai transmitir uma palavra-chave contextual, responsável por buscar as informações referentes ao produto no banco de dados do estoque. Então essa informação será exibida na tela, mostrando sua validade e seu preço - podendo o cliente selecionar a quantidade desejada do produto e adicioná-lo ou não ao seu carrinho de compras virtual.

Este aplicativo permite a implementação de um sistema para efetuar o pagamento, mas vale ressaltar que tal implementação não compõe o escopo deste projeto. Dentre os métodos disponíveis para efetuar o pagamento estão: PayPal, PagSeguro, Mercado Pago e o Android Pay, opção mais recente lançada ao público pela Google que também faz o uso de NFC e contribuirá para a popularização desta tecnologia.

1. 2.OBJETIVO

O objetivo do projeto consiste em implementar um sistema inteligente de supermercado de maneira a auxiliar e facilitar as compras dos consumidores. Além de otimizar o processo de compra. O projeto também busca uma redução de custo ao supermercado, visto que o equipamento utilizado é o smartphone do próprio consumidor, portanto, o supermercado terá de investir apenas no software e em um banco de dados.

1. 3.TECNOLOGIA

1.
  1. 3.1NFC (Near Field Communications)

O NFC (Near Field Communications) ou &quot;comunicações em campo próximos&quot; é um conjunto de tecnologias de comunicação sem-fio (Contactless) para transações simples, troca de dados e conexões sem-fio entre aparelhos próximos (entre 4 e 20 cm). Essa tecnologia funciona a partir de campos de indução magnética e opera em uma faixa de rádio frequência de 13,56MHz, com velocidade de transmissão de dados variando entre 106, 212 e 424 kbps (kilobits por segundo).

O desenvolvimento dessa tecnologia se iniciou em 2002 pela holandesa Philips e a japonesa Sony. Em 2003 a tecnologia recebeu reconhecimento pela norma ISSO/IEC18092. Porém foi somente em 2004 em que o NFC começou a ter mais relevância devido a criação do NFC FORUM, composto por diversas empresas que tinham o interesse no desenvolvimento dessa tecnologia. Entre elas estão Google, PayPal, LG, American Express, Nokia, Samsung, Intel, NEC, Visa, Huawei e Qualcomm.

O princípio de funcionamento é simples, em que um dos dispositivos é dito como sendo o _Initiator_, que é o responsável por iniciar a comunicação e controlar a troca de informações. O outro dispositivo é o _Target_, que deverá responder às solicitações do dispositivo _Initiator_.

1. **1.**
2. **2.**
3. **3.**
  1. 3.1.

1.
  1.
    1. **1.1.1.**** Modos de Comunicação**

Existem dois modos de transmissão:

- Passivo

Nesse modo apenas um dos dispositivos (_Initiator_) que gera o sinal de rádio frequência na conexão. O dispositivo passivo é apenas alimentado pelo primeiro por meio das ondas eletromagnéticas emitidos pelo _Initiator_, ou seja, neste modo de comunicação o dispositivo Target não possui fonte de energia elétrica direta. Transmissão passiva é usada em aplicações em que tags NFC substituem o código de barras em embalagens de produtos, como é o caso deste projeto.



Figura 1 - Tag NFC Passivo

- Ativo

Nesse modo ambos os dispositivos são responsáveis por gerar o sinal de rádio frequência. Para isso, os dois dispositivos necessitam de uma fonte de alimentação de energia elétrica.

Figura 2 - Aplicação usando NFC ativo

1.
  1.
    1. **1.1.2.**** Modos de operação**

- Leitura e gravação

Baseado no modo de comunicação passivo, este modo de operação é usado em situações em que se deseja fazer uma leitura e/ou alteração em um dispositivo passivo (_Target_). Como exemplo, temos a leitura de um tag NFC presente na embalagem de um produto por meio de um smartphone visando visualizar as informações do mesmo.

- Peer-to-Peer

Baseado no modo de comunicação ativo, neste modo a comunicação é bidirecional entre os dois dispositivos. Portanto ambos podem receber e enviar dados.

- Emulação de Cartão

Neste modo de operação o dispositivo NFC emula um cartão inteligente ou tag NFC.

1.
  1.
    1. **1.1.3.**** NFC no projeto**

A tecnologia NFC foi empregada nesse projeto devido sua simplicidade, disponibilidade e baixo custo. No projeto, os tags NFC, que serão fixados nas prateleiras do supermercado, substituirão o tradicional código de barras, proporcionando aos clientes a oportunidade de acompanharem o valor do seu carrinho de compras em tempo real e verificar preço e validade dos produtos do supermercado. Além disso, essa tecnologia tem um viés econômico interessante aos donos do supermercado uma vez que não será necessário alteração e impressão de novas etiquetas de papel que informam o valor dos produtos nas prateleiras, pois isso será feito via software no banco de dados do sistema do estabelecimento.

1.
  1. 1.2.Tags NFC

As tags NFC são formadas por um pequeno chip de rádio acompanhados de uma antena e algum tipo de memória para armazenamento de dados. Existem quatro categorias padronizadas de tags NFC:

- Tipo 1: normalmente armazena entre 96 bytes e 2 KB de dados e tem velocidade de 106 Kb/s (kilobits por segundo);
- Tipo 2: armazena entre 48 bytes e 2 KB de dados e tem velocidade de 106 Kb/s. É compatível com o tipo 1;
- Tipo 3: baseada em uma tecnologia da Sony chamada FeliCa, esse tipo normalmente armazena 2 KB (mas pode chegar a 1 MB) e tem velocidade de 212 Kb/s. A compatibilidade com outros padrões existe, mas não é garantida;
- Tipo 4: armazena até 32 KB e tem velocidade entre 106 Kb/s e 424 Kb/s. É compatível com os tipos 1 e 2.

1.
  1. 1.3.IoT no supermercado/atacado

Mesmo com a ascensão das compras online, muitas pessoas ainda preferem fazer suas compras em supermercados (vide Figura 3). Apesar dessa preferência, ir ao supermercado pode ser uma experiência bem estressante. Após o trabalho, por exemplo, milhares de pessoas tem o costume de passar no supermercado para comprar itens de uso diário e/ou até mesmo para fugir do trânsito das ruas. Em contrapartida, esse momento que deveria ser de descontração, pode se tornar frustrante uma vez que as filas do supermercado podem estar grandes e demoradas ou caso a pessoa não consiga encontrar o que deseja. As pressões relacionadas ao advento das compras online em que não existem filas de pagamento e a compra é feita em qualquer lugar que o cliente estiver tem impulsionado muitos supermercados a investir em tecnologias que conectem seus clientes a dispositivos inteligentes proporcionando uma experiência personalizada e ajudando-os a economizarem tempo e dinheiro.



Figura 3 - Qual a preferência de compras dos clientes americanos

Além do óbvio benefício de fazer compras em um supermercado que é disponibilidade imediata para compras necessárias sem o pagamento de taxas de envio, existem poderosas experiências sensoriais — cheirar e sentir o pão que acabou de sair do forno e ver as cores vibrantes e textura de morangos maduros — e isso, virtualmente é impossível de replicar. Também é difícil de combinar o poder de interação humana e a emoção da descoberta não planejada que lojas físicas podem fornecer. Tão importante quanto, para muitos consumidores, compras de supermercado podem ser uma divertida atividade que gera sentimentos positivos. Porém, como mencionado anteriormente, para manter competitividade em relação a compras virtuais, é necessário que os supermercados apliquem tecnologias que diferenciem a experiência dos seus clientes, de modo que facilite suas compras.

Supermercados que empregam tecnologia de IoT podem trazer facilidade, conveniência e personalização de lojas virtuais. Instituir estratégias digitais para a experiência no supermercado já é uma necessidade, essas opções podem diminuir o tempo de compras, aumentar a colaboração dos clientes e consequentemente sua satisfação.

Atualmente, os compradores fazem todo o trabalho de colocar as compras para chegar à sua decisão de compra final. Em um ambiente de varejo competitivo, varejistas e fabricantes podem adicionar valor e diferenciação, fornecendo ferramentas digitais para ajudar os consumidores a tomar o controle de sua experiência de compras enquanto também aumentar as vendas, e dar maior empoderamento aos clientes.

Além disso, essas tecnologias permitirão que os supermercados tracem perfis de compras de cada cliente e faça sugestões de compras e até promoções dedicadas, ou seja, tornando a experiência ainda mais inteligente.



1.
  1. 3.4Android Studio

Android Studio é um  [ambiente de desenvolvimento integrado](https://pt.wikipedia.org/wiki/Ambiente_de_desenvolvimento_integrado) (IDE) para desenvolvimento na plataforma Android baseado no InteliJ Community Version.

Foi desenvolvido as telas para o aplicativo o código para a contagem, armazenamento dos produtos e leitura no NFC.

A princípio foi feita uma pesquisa para quais softwares usaríamos para o desenvolvimento, tínhamos a opção do Eclipse, Android Studio e o MIT App Inventor. Com o mesmo objetivo do Eclipse + ADT (Android Developer Tools), eles provem um ambiente de desenvolvimento, debug e profile multiplataforma para Android.

Seguindo o fluxo da comunidade desenvolvedora, que praticamente deixou o Eclipse em desuso e, após os testes com o MIT App Inventor os quais demonstraram uma facilidade de uso inicial mas apresenta uma funcionalidade limitada, foi escolhido pelo uso do Android Studio para o desenvolvimento.

A desvantagem do Android Studio é o grande consumo de memória e alto uso do processador. Com relação as vantagens: tem vários recursos que eram usados no Eclipse, permite que o código de outras linguagens sejam validadas na sua IDE,  possibilidade de visualizar o layout em várias telas de tamanhos diferentes simultaneamente e fazer o layout da tela via ícones ao estilo _drag and drop_.

O Android Studio tem um emulador para simular o celular, ferramentas utilitárias como o LogCat para acompanhar a execução do App no celular, uma central de download para manter o Android SDK atualizado e outras ferramentas disponíveis do Google e uma API completa para a linguagem JAVA, com todas as classes para desenvolver as aplicações. É possível plugar um celular real na porta USB do computador e executar os aplicativos diretamente no celular, o que facilitou para nós os testes em aparelhos reais e tornou o desenvolvimento mais produtivo.

1.
  1. 3.5Linguagem Java

É uma  [linguagem de programação](https://pt.wikipedia.org/wiki/Linguagem_de_programa%C3%A7%C3%A3o)  [interpretada](https://pt.wikipedia.org/wiki/Linguagem_de_programa%C3%A7%C3%A3o_interpretada)  [orientada a objetos](https://pt.wikipedia.org/wiki/Orienta%C3%A7%C3%A3o_a_objetos). Aonde tivemos que aprender o conceito de classe (É um conjunto de objetos com características comuns. Uma classe é como um modelo para a criação de objetos, que tem as mesmas características da classe à qual pertence), objeto (elemento de uma classe), atributo, método, mensagem, herança, associação, encapsulamento, abstração, polimorfismo, interface e pacotes. Por orientação dos professores fizemos a analogia ao conceito de objetos seria equivalente a um componente eletrônico, e montaríamos a programação, na mesma ideia de componentes eletrônicos e circuito eletrônico, objetos seriam os componentes, e o circuito eletrônico a programação, só seria necessário saber o que cada código fazia, e orientação dos professores o que nos ajudou muito. Como estamos usando NFC do celular, tivemos que aprender a chamar a classe NFC na linguagem Java, aonde teríamos que fazer ler as Tags. Existem bibliotecas com classes feitas especialmente para NFC e é necessário declara-las na programação quando for usar o NFC.

Exemplos de código:

O método main é onde o programa inicia. Pode estar presente em qualquer classe. Os parâmetros de linha de comando são enviados para o array de Strings chamado args.



Figura 4 - Método Main

**Classes –** A classe em uso seria &quot;carrinho&quot;, os objetos seriam &quot;produto &quot; e &quot;quantidade&quot;.

public class CarrinhoItem {

     private Produto prod;

     private int quantidade;

     public CarrinhoItem(){

         prod = new Produto();

     }

     public Produto getProd() {

         return prod;

     }

     public void setProd(Produto prod) {

         this.prod = prod;

     }

     public int getQuantidade(){

         return this.quantidade;

     }

    public void setQuantidade(int quantidade){

        this.quantidade = quantidade;

    }

NFC- Declaração de bibliotecas do NFC e acionamento do NFC através de um botão

            import android.nfc.NfcAdapter

            Button bt = (Button) findViewById(R.id.btn\_read);

            if (Build.VERSION.SDK\_INT &gt;= Build.VERSION\_CODES.M) {

             if (checkSelfPermission(Manifest.permission.NFC) !=

                     PackageManager.PERMISSION\_GRANTED) {

                      }

                      }



1.
  1. 3.6Banco de dados

1.
  1. 1.4.
  2. 1.5.
  3. 1.6.
    1. **1.6.1.** MBaaS: Firebase

As aplicações desenvolvidas para smartphones necessitam de serviços de banco de dados para reunir as informações dos clientes, gerenciar o sistema de login e acompanhar o fluxo de acesso à sua aplicação. Esta gama de serviços é conhecida como _Mobile Backend as a Service (MBaaS)._

Figura 5 - Estrutura de um MBaaS

_BaaS_ é essencialmente um middleware que os desenvolvedores usam para conectar uma variedade de tipos de serviços e armazenamento que são executados em servidores back-end em uma infraestrutura de Cloud.

Tais serviços são complexos para construir e necessitam de um background técnico profundo em segurança de informações, organização de banco de dados e computação em nuvem. Para que tal dificuldade não seja um problema para o processo de desenvolvimento de novos aplicativos, o que travaria a indústria de smartphones, surgiram plataformas de MBaaS que oferecem esse recurso de uma forma amigável para que os desenvolvedores utilizem os serviços repetidamente e sem complicações.

A plataforma escolhida para o projeto, Firebase, foi comprada pela Google em 2014 e hoje é uma escolha popular entre os desenvolvedores, sendo utilizada por marcas como: Trivago, Shazam, Duolingo e The New York Times.

As principais razões para a escolha dessa plataforma são: estabilidade, grande conteúdo para pesquisa na internet sobre o funcionamento desta MBaaS e o uso sem custos para um projeto da dimensão proposta pelo projeto.

Dentre a gama de funcionalidades do Firebase, o projeto AtacaMobile utiliza o Firebase Realtime Database e o Firebase Authentication.

1.
  1.
    1. **1.6.2.** Firebase Realtime Database

&quot;O Firebase Realtime Database é um banco de dados hospedado na nuvem. Os dados são armazenados como JSON e sincronizados em tempo real com todos os clientes conectados.&quot;  (FIREBASE...,2017)

&quot;O Realtime Database é um banco de dados NoSQL e, por isso, tem otimizações e funcionalidades diferentes de um banco de dados relacional. A API do Realtime Database foi desenvolvida para autorizar apenas operações que possam ser executadas com rapidez. Isso possibilita uma ótima experiência em tempo real que atende a milhões de usuários sem comprometer a capacidade de resposta.&quot; (FIREBASE...,2017)

Para alcançar tal fluidez, o banco de dados é estruturado em um formato de árvore JSON, ou seja, cada dado se torna um nó possuindo uma chave associada.

Segue um exemplo de um código do banco de dados de um aplicativo de bate-papo para mostrar como funciona o armazenamento de informações no estilo JSON:

{

        &quot;usuários&quot;: {

               &quot;raphaprates&quot;: {

                        &quot;nome&quot;: &quot;Raphael Prates&quot;,

                        &quot;contatos: { &quot;nevado&quot;: true },

              },

             &quot;nevado&quot;: { ...},

             &quot;cuervo&quot;: { ...}

        }

}

 Pode ser observado que nessa estrutura, existe um nó chamado usuários de onde a árvore se ramifica para os usuários presentes e cada informação de usuário (nome, contato). No AtacaMobile, as informações dos produtos do estoque do supermercado são armazenadas no banco de dados &quot;produtos&quot; da seguinte maneira:

Figura 6 - Banco de dados dos produtos no Firebase

Nota-se que cada item possui uma chave exclusiva, por exemplo: KxkRY2KXTFqmnzNoOoa. Essa chave é gerada automaticamente a partir da data/hora possuindo várias casas decimais de cada postagem, portanto nenhum conflito de escrita ocorrerá se diversos usuários adicionarem um valor ao mesmo tempo.

 Cada produto apresenta um código próprio, usado para facilitar a identificação e é esse valor que é gravado nas tags de NFC. Através desse código, o produto é encontrado no banco de dados e outras informações podem ser obtidas através dele: validade do produto, descrição, se ele está na promoção, sua foto, nome e valor.

 Os itens do produto podem ser editados no site do Firebase e suas informações são atualizadas instantaneamente para os dispositivos móveis dos usuários do aplicativo.

1.
  1.
    1. **1.6.3.** Firebase Authentication

A segurança dos dados de usuários e as informações na internet são pontos cruciais para a execução de um projeto robusto. Para alcançar tal nível de segurança, foi acrescentado ao App o Firebase Authentication, um sistema de login desenvolvido pela mesma equipe que criou o Login do Google, Smart Lock e o Chrome Password Manager.

Para se cadastrar no aplicativo, é possível escolher entre um cadastro usando seu próprio endereço de email, ou a conta do Google que já está logada no seu dispositivo. A interface que gerencia essas aplicações é chamada de FirebaseUI.

Para mais informações sobre essa UI, sua biblioteca _open source_ está disponível em: https://github.com/firebase/FirebaseUI-Android/tree/master/auth.

1. **2.** O PROJETO

O projeto consiste em ser o mais simples possível, pois almeja atingir todos os tipos de públicos. Inicialmente a ideia era utilizar um cesto de compras, porém com o decorrer do desenvolvimento, surgiram alternativas melhores que traziam economia de custos por utilizar somente o smartphone do próprio cliente que possuem a tecnologia NFC e tags NFC.

O sistema visa proporcionar uma completa interação do consumidor com sua compra, garantindo que o mesmo saiba todas as informações referentes ao produto, quantos e quais já foram adquiridos, seu valor, validade do produto, a localização do mesmo através do aplicativo (App) do seu próprio smartphone e finalizar a compra no próprio celular, evitando dessa maneira as filas dos caixas. Além do objetivo principal, o aplicativo auxiliará o cliente a encontrar o produto desejado de forma fácil e rápida.

A proposta é sempre visar o baixo custo e desenvolver melhorias para combater o furto entre supermercados e contribuir para uma compra segura e de confiança.

A seguir, há um passo a passo do funcionamento do aplicativo. Junto com a imagem de cada tela desse aplicativo, acompanha uma explicação sobre as partes fundamentais do código usadas na implementação do projeto (banco de dados e NFC).
