package br.com.testes.testederecycleview;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import br.com.testes.testederecycleview.adapters.TabsAdapter;
import br.com.testes.testederecycleview.domain.Car;
import br.com.testes.testederecycleview.extras.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private List<Car> listCars;

    private FloatingActionMenu fab;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TRANSITIONS
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                TransitionInflater inflater = TransitionInflater.from(this);
                Transition transition = inflater.inflateTransition(R.transition.transitions);

                getWindow().setSharedElementExitTransition(transition);
            }

        super.onCreate(savedInstanceState);

        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            listCars = savedInstanceState.getParcelableArrayList("listCars");
        }
        else{
            listCars = getSetCarList(16);

        }

        // TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("REDE Money");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);

        //TABS
        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorFAB));
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {}

        });
        mSlidingTabLayout.setViewPager(mViewPager);
        fab = (FloatingActionMenu) findViewById(R.id.menu1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) item.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView( item );
        }

        searchView.setSearchableInfo( searchManager.getSearchableInfo( getComponentName() ) );
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // CAR
    public List<Car> getSetCarList(int qtd){
        return(getSetCarList(qtd, 0));
    }

    public List<Car> getSetCarList(int qtd, int category){
        String[] models = new String[]{
                //DICAS - TÍTULOS
                    //DICA 1
                        "1- Dicas para invesitir",
                    //DICA 2
                        "2- Dicas de Empreendedores",
                    //DICA 3
                        "3- 10 dicas para o sucesso do Empreendedor",
                    //DICA 4
                        "4- 10 atitudes simples",
                    //DICA 5
                        "5- 10 dicas de empreendedorismo para iniciantes",

                //BIOGRAFIAS - TÍTULOS DE PESSOAS                                               //ADICIONADO
                    "Abílio Diniz", "Antônio Alberto Saraiva", "Fabio Seixas", "Guy Kawasaki", "Elon Musk", "Luiza Helena Trajano",
                    "Marco Gomes", "Marie Forleo", "Mark Zuckerberg", "Romero Rodrigues", "Stephen Kanitz"};

        String[] brands = new String[]{
                //DICAS - SUBTÍTULOS
                "TOP 10", "TOP 10", "TOP 10", "TOP 10", "TOP 10",

                //BIOGRAFIAS - SUBTÍTULOS
                "Grupo Pão de Açúcar", "Fundador do Habib's", "Co-criador da Camiseteria", "Fundador da Alltop", "Criador do Tesla Roadster, SpaceX",
                "Fundadora do Magazine Luiza", "Criador da boo-box", "Guru do empreendimento", "Fundador do Facebook", "CEO do Buscapé",
                "Consultor de empresas"};

        int[] categories = new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

        int[] photos = new int[]{
                //FOTOS PARA SEÇÃO DICAS
                R.drawable.foto_dicas, R.drawable.foto_dicas_2, R.drawable.foto_dicas_3, R.drawable.foto_dicas_4, R.drawable.foto_dicas_5,

                //FOTOS PARA SEÇÃO BIOGRAFIAS
                R.drawable.abilio_diniz, R.drawable.antonio_alberto_saraiva, R.drawable.fabio_seixas, R.drawable.guy_kawasaki,
                R.drawable.elon_musk, R.drawable.luiza_helena_trajano, R.drawable.marco_gomes, R.drawable.marie_forleo,
                R.drawable.mark_zuckerberg, R.drawable.romero_rodrigues, R.drawable.stephen_kanitz};

        //TEXTS
        String[] descriptions = new String[] {
                //DICA 1 - DESCRIÇÃO
                "Para fazer o seu dinheiro render e começar a trabalhar para você, investir é a estratégia ideal. E a hora certa para começar a fazer isso é agora mesmo. É claro que o cenário econômico brasileiro atual – com desemprego crescente, recessão e perda do poder de consumo – pode assustar muita gente. Mas sabendo entender esses fatores e até mesmo aproveitando-se deles, é possível criar boas oportunidades de investimento e até garantir uma renda extra. Quer saber como fazer isso? Descubra como funcionam algumas modalidades de investimento e analise qual a mais indicada para o seu caso.\n" +
                        "\n" +
                        "1 – O que quer dizer aplicação em renda fixa?\n" +
                        "\n" +
                        "O termo renda fixa refere-se a qualquer tipo de investimento que possua uma remuneração paga em intervalos e condições pré-estabelecidas.\n" +
                        "\n" +
                        "Os títulos de renda fixa podem ser classificados como pré-fixados (cujo rendimento está atrelado a um valor previamente conhecido) ou pós-fixados (que, em essência tem renda variável, mas estão associados a indicadores do mercado que sofrem menores oscilações), sendo emitidos por entidades públicas ou privadas.\n" +
                        "\n" +
                        "A fim de aumentar a atratividade dos títulos, é comum que as instituições emissoras garantam sua liquidez, permitindo que o valor investido seja resgatado antes da data de vencimento sob condições diferenciadas, com uma rentabilidade menor, por exemplo.\n" +
                        "\n" +
                        "Por não estarem tão sujeitas às oscilações do mercado e devido a suas garantias de rentabilidade, essas aplicações podem ser consideradas menos arriscadas e, por isso, mais adequadas a quem tem um perfil de investimento mais conservador. Além disso, diante do cenário econômico previsto para 2016, especialistas afirmam que a renda fixa continuará sendo uma das melhores opções de investimentos financeiros disponíveis.\n" +
                        "\n" +
                        "Alguns dos exemplos mais comuns de aplicações em renda fixa são: a poupança, as letras de crédito, os CDBs e o Tesouro Direto. Quer saber qual a melhor opção para você? Continue lendo.\n" +
                        "\n" +
                        "2 – O que são letras de crédito?\n" +
                        "\n" +
                        "As LCIs (Letras de Crédito Imobiliário) e LCAs (Letras de Crédito do Agronegócio) são títulos de renda fixa, em que os recursos captados são destinados a um dos mencionados setores (imobiliário ou agronegócio).\n" +
                        "\n" +
                        "Por serem isentos da cobrança de Imposto de Renda (para pessoas físicas) e por serem protegidos pelo FGC (Fundo Garantidor de Crédito) em até R$ 250 mil, esses tipos de investimentos tem se mostrado bastante atrativos. Além disso, têm baixo risco e são alguns dos mais rentáveis investimentos de renda fixa.\n" +
                        "\n" +
                        "Em geral, as LCIs e LCAs são recomendadas para médios e grandes investidores, pois costumam exigir um valor mínimo inicial de investimento de, pelo menos, R$ 20 mil, a variar pela instituição financeira. Também possuem baixa liquidez, sendo indicadas para aplicações de maior prazo, sem previsões de resgate de emergência.\n" +
                        "\n" +
                        "3 – O que são CDBs, RDBs e RDCs?\n" +
                        "\n" +
                        "O CDB (Certificado de Depósito Bancário) é um título de renda fixa, semelhante às letras de crédito, mas neste caso os recursos são emprestados ao próprio banco e, em troca, você recebe o pagamento de juros.\n" +
                        "\n" +
                        "Além dos CDBs, os bancos também emitem os RDBs (Recibos de Depósitos Bancários), que têm as mesmas características do primeiro, mas não admitem negociações antes de seu vencimento (excepcionalmente, poderá ser rescindido contrato com concordância da instituição).\n" +
                        "\n" +
                        "Em ambos os casos, a taxa paga pode ser pré-fixada ou pós-fixada, com prazos que costumam variar entre 30 e 720 dias. Os riscos são baixos e o investimento é garantido pelo FGC em até 250 mil. A liquidez costuma ser diária, mas quanto maior o prazo de aplicação, menor a incidência de Imposto de Renda e IOF (Imposto sobre Operações Financeiras).\n" +
                        "\n" +
                        "Outra opção interessante com essas mesmas características são os RDCs (Recibos de Depósitos Cooperativos). Exclusivo para associados de cooperativas financeiras, esse tipo de investimento ainda conta com uma vantagem que só uma cooperativa pode proporcionar: pode servir de base para a distribuição de sobras. Ou seja, quanto mais você aplica no RDC, maior pode ser a sua participação nos resultados da cooperativa. Saiba mais aqui  ou nesse link.\n" +
                        "\n" +
                        "4 – O que é e como funciona o Tesouro Direto?\n" +
                        "\n" +
                        "O Tesouro Direto é um programa que dá acesso ao investimento em títulos públicos, possibilitando sua compra por pessoas físicas pela Internet (anteriormente isso só era possível adquirindo cotas de fundos de investimento).\n" +
                        "\n" +
                        "Além de ser uma forma alternativa e democrática de aplicação dos seus recursos, sem a necessidade de intermediação de agente financeiro nas negociações, essa opção de investimento oferece boa rentabilidade (podendo ser pré-fixado, atrelado a taxa SELIC, ao IGP-M ou ao IPCA), tem baixas taxas administrativas, possibilidades de diversificação de investimentos e liquidez garantida pelo Tesouro Nacional.\n" +
                        "\n" +
                        "É bastante recomendado para pequenos investidores, tendo em vista que, com R$ 100,00 já é possível começar a aplicar no Tesouro e sair em grande vantagem frente à poupança. E o programa ainda permite um bom gerenciamento da aplicação diretamente pelo investidor, que pode consultar extratos e saldos a qualquer tempo no site. Desde 2009, o Tesouro Direto também conta com um simulador  para auxiliar o usuário na escolha da melhor aplicação de acordo com seus objetivos.\n" +
                        "\n" +
                        "5 – Como funcionam os fundos de investimento?\n" +
                        "\n" +
                        "Formado por um grupo de cotistas, o fundo de investimento é uma opção que conta com um gestor, responsável por escolher em qual título aplicar o montante do grupo ou como distribuir essas aplicações entre vários títulos. Ou seja, é possível investir tendo uma orientação especializada em troca, é claro, de uma taxa administrativa.\n" +
                        "\n" +
                        "Há quatro categorias de fundos de renda fixa mais populares (pré-fixados, crédito, multi-índices e alavancados), além de fundos referenciados DI (alternativa mais conservadora, indicada para cenários de altas nos juros).\n" +
                        "\n" +
                        "6 – Por que investir em Previdência Privada?\n" +
                        "\n" +
                        "Previdência Privada é um tipo de investimento de longo prazo (aliás, quanto maior for o prazo de aplicação, mais interessante se torna esse tipo de investimento) que todo brasileiro deveria fazer.\n" +
                        "\n" +
                        "Entre os planos mais comuns, o PBGL – Plano Gerador de Benefício Livre – é dedutível em até 12% da base tributável do IR, enquanto o VGBL – Vida Gerador de Benefício Livre – não é dedutível do Imposto de Renda. Existem ainda os planos de previdência fechada, também chamados fundos de pensão, exclusivos de algumas classes e associações específicas. Diferente dos planos de previdência aberta que visam lucro, os planos de previdência fechada podem oferecer taxas mais competitivas, além de terem o mesmo benefício tributário dos planos PGBL.\n" +
                        "\n" +
                        "Em todo caso, em uma Previdência Privada, quanto mais cedo você começar a investir, menos precisa pagar por mês para acumular o que deseja. E esse é só um dos motivos para você começar já a sua. Outras boas razões para fazer esse tipo de investimento são:\n" +
                        "– para não depender apenas da Previdência Social (garantindo mais segurança);\n" +
                        "– para manter na aposentadoria o padrão de vida que tinha quando trabalhava;\n" +
                        "– para realizar seus sonhos;\n" +
                        "– para cuidar de você e da sua família. Neste caso, é bom saber também que alguns planos de previdência privada contam com coberturas por morte ou invalidez. Assim, além de garantir sua renda futura, você e sua família ficam mais tranquilos para aproveitar a vida. Saiba mais aqui: www.sicoobsc.com.br/para-voce-previdencia.\n" +
                        "\n" +
                        "Para complementar, o especialista Rafael Pavan, sócio da RP Capital, ainda frisa: “Por um problema conjuntural da nossa economia, o momento em 2016 é bom para investidores com visão previdenciária”.\n" +
                        "\n" +
                        "7 – Consórcios são bons negócios?\n" +
                        "\n" +
                        "O consórcio é uma modalidade de compra baseada na união de pessoas que formam um fundo para a aquisição de bens ou serviços. Com o montante das parcelas do grupo, ao menos uma pessoa será contemplada mensalmente (ou em outra periodicidade informada no contrato) através de sorteio.\n" +
                        "\n" +
                        "Como forma de investimento, um consórcio pode ser uma opção interessante para quem quer investir em um bem específico (em um imóvel, por exemplo) e não tem pressa no recebimento. Além disso, diferente de outras opções de financiamento, em um consórcio não é cobrada taxa de juros, apenas uma taxa administrativa, tornando o investimento mais atraente.\n" +
                        "\n" +
                        "Saiba mais sobre consórcios.\n" +
                        "\n" +
                        "8 – Quando a poupança pode valer a pena?\n" +
                        "\n" +
                        "A poupança é um das modalidades mais seguras e conservadoras entre os investimentos de renda fixa. Apesar de seu rendimento ser, via de regra, baixo se comparado aos demais, esse tipo de aplicação não deve ser desprezada.\n" +
                        "\n" +
                        "Se você ainda não tem o hábito de investir seu dinheiro, por exemplo, manter uma poupança pode ser a forma mais indicada de começar. Afinal, antes de pensar em entrar no mercado financeiro, é preciso começar a poupar dinheiro, fazer uma boa reserva financeira de emergências e, só então, passar a fazer seu dinheiro trabalhar para você, diversificando suas opções de investimentos. Se esse é o seu caso, comece criando o hábito de poupar no mínimo 10% do que você ganha todo mês. Não espere sobrar. Refaça suas contas e comece a cuidar melhor do seu orçamento, pensando em seu futuro.\n" +
                        "\n" +
                        "9 – Empresas também precisam investir?\n" +
                        "\n" +
                        "Investir é uma forma de fazer seu dinheiro render mais. E isso também vale para o capital corporativo. Neste caso, uma boa gestão financeira é fundamental para indicar as porcentagens ideais de reserva e de investimento e as melhores modalidades de aplicação. De qualquer forma, poupança e consórcios são algumas das opções mais básicas oferecidas para empresas, estando disponíveis no mercado também investimentos corporativos de maior rentabilidade, como letras de crédito, CDBs, RDCs, entre outros.\n" +
                        "\n" +
                        "10 – Por que investir em uma cooperativa pode ser mais rentável que em um banco comum?\n" +
                        "\n" +
                        "Cooperativas de crédito (também chamadas de cooperativas financeiras) são instituições que oferecem os mesmos produtos e serviços de um banco comum, mas não visam lucro, já que seu objetivo é administrar melhor e com mais vantagens o dinheiro de seus associados (não há acionistas ou clientes).\n" +
                        "\n" +
                        "Por isso, cooperativas chegam a ter taxas até 20% menores que os bancos comuns e podem oferecer retornos maiores para os mais variados tipos de investimentos. Além disso, em uma cooperativa, resultados positivos podem dar origem a sobras, redistribuídas aos cooperados conforme a participação de cada um. Logo, quanto maiores seus investimentos por meio de uma cooperativa, maiores também as sobras que você pode receber. E os investimentos nessas instituições ainda contam com fundo garantidor próprio, o FGCoop, que proporciona segurança em até R$ 250 mil por CPF/CNPJ. ",

                    //DICA 2 - DESCRIÇÃO
                "1. Boas ideias são comuns a muitas pessoas. A diferença está naqueles que conseguem fazer as idéias transformarem-se em realidade, isto é, implementar as idéias. A maioria das pessoas fica apenas na \"boa idéia\" e não passa para a ação. O empreendedor passa do pensamento à ação e faz as coisas acontecerem;\n" +
                        "\n" +
                        "2. Todo empreendedor tem uma verdadeira paixão por aquilo que faz. Paixão faz a diferença. Entusiasmo e Paixão são as principais características de um empreendedor!\n" +
                        "\n" +
                        "3. O empreendedor é aquele que consegue escolher entre várias alternativas e não fica pensando no que deixou para trás. Sabe ter foco e fica focado no que quer;\n" +
                        "\n" +
                        "4. O empreendedor tem profundo conhecimento daquilo que quer e daquilo que faz e se esforça continuadamente para aumentar esse conhecimento sob todas as formas possíveis;\n" +
                        "\n" +
                        "5. O empreendedor tem uma tenacidade  incrível. Ele não desiste!\n" +
                        "\n" +
                        "6. O empreendedor acredita na sua própria capacidade. Tem alto grau de auto-confiança;\n" +
                        "\n" +
                        "7. O empreendedor não tem fracassos. Ele vê os \"fracassos\" como oportunidades de aprendizagem e segue em frente;\n" +
                        "\n" +
                        "8. O empreendedor faz uso de sua imaginação. Ele imagina-se sempre vencedor;\n" +
                        "\n" +
                        "9. O empreendedor tem sempre uma visão de vários cenários pela frente. Tem, na cabeça,  várias alternativas para vencer;\n" +
                        "\n" +
                        "10. O empreendedor nunca se acha uma \"vítima\". Ele não fica parado, reclamando das coisas e dos acontecimentos. Ele age para modificar a realidade!",

                    //DICA 3 - DESCRIÇÃO
                        "7.1K COMPARTILHE  TWEET  COMPARTILHE  EMAIL  COMENTÁRIOS\n" +
                                "Seja qual for a sua definição sobre sucesso, existem hábitos comuns entre empreendedores que possuem negócios lucrativos e duradouros. James Stephenson, especialista americano em marketing e gestão, soltou uma lista bem interessante no site da Entrepreneur para ajudar os donos de empresa a prosperar. Confira:\n" +
                                "\n" +
                                "1. Leve a sério o que você faz: No mundo dos negócios, não dá para ser bem-sucedido se você não acredita no que vende ou nos serviços que presta aos consumidores. A motivação do dono deve ser grande o bastante para contagiar as pessoas. Seja na hora de negociar com fornecedores ou conquistar clientes, é fundamental ter seriedade, garra e saber transmiti-las aos outros.\n" +
                                "2. Planejamento: Planejar é importante não só na hora de montar a empresa, como também no dia a dia do negócio. Crie o hábito de escrever relatórios semanais com os dados mais marcantes do período. Isso ajuda a traçar metas, corrigir erros e verificar o que já foi alcançado.\n" +
                                "3. Fiquei de olho no fluxo de caixa: Ele é o coração de qualquer empreendimento e o sucesso da sua empresa vai depender se você sabe mantê-lo saudável. O empreendedor nunca pode esquecer de controlar as finanças e garantir que as despesas não ultrapassem os lucros. Embora a premissa seja básica, é bastante comum negócios irem à falência por falta de organização com dinheiro.\n" +
                                "4. Foque no consumidor: Você pode ter boas ideias, um marketing agressivo e a equipe afinada, mas não se esqueça de manter o foco nos consumidores. Produtos e serviços devem estar alinhados com a expectativa deles. A busca por qualidade, aperfeiçoamento e preço justo jamais podem ser deixados em segundo plano. Lembre-se que, no final das contas, são os clientes que vão decidir se a sua empresa vai para a glória ou para o buraco.\n" +
                                "5. Construa uma imagem positiva e confiável: Ter uma imagem positiva é uma estratégia interessante para aumentar as vendas. As pessoas costumam associar marcas a quem está por trás delas. Quem tem credibilidade, tem o poder de lançar tendências. Para isso, não transmita insegurança, procure manter uma postura assertiva com consumidores, parceiros e funcionários e cumpra suas promessas. Se você prometeu entregar um produto até quarta-feira, por exemplo, não há desculpas para atraso.\n" +
                                "6. Aposte em tecnologia: Não é preciso ser obcecado por gadgets, mas tire vantagem dos avanços tecnológicos. Hoje é possível reduzir custos e aumentar a eficiência com serviços de cloud computing, software livres e ferramentas online. Só fique atento e escolha as tecnologias que melhor te ajudam e não aquelas que apenas impressionam.\n" +
                                "7. Invista no time de funcionários: Investir em funcionários é de extrema importância. A maioria das pessoas gosta de aprender algo novo e elas podem aplicar esse conhecimento na sua empresa. Aposte em treinamentos, cursos e em uma gestão onde a equipe tem liberdade criativa. Colaboradores desmotivados ficam ociosos e facilitam o desperdício de tempo e matéria-prima.\n" +
                                "8. Crie uma vantagem competitiva: Defina desde o início quais são os seus pontos fortes em relação à concorrência e invista neles. Para isso, faça a seguinte pergunta: “Por que as pessoas escolheriam meus produtos ou serviços ao invés de optar pelos concorrentes”? Em outras palavras, descubra que aspectos positivos vão o separar de outros competidores. O melhor serviço? O atendimento? A melhor seleção? A garantia de prazos maiores? Preços mais baixos? E por ai vai…\n" +
                                "9. Aprenda a negociar: Desenvolva a habilidade da negociação. Você vai precisar dela praticamente todos os dias para conseguir preços mais atraentes, fazer parcerias, buscar empréstimos e até para contratar funcionários. Procure firmar acordos onde os dois lados saem satisfeitos. A chance de serem mais duradouros aumenta.\n" +
                                "10. Limite suas atividades: É verdade que muitos empreendedores de sucesso são multifuncionais. Porém, é preciso tomar cuidado para não se sobrecarregar com tarefas simples que podem ser executadas por outros, ao invés de focar em questões importantes. Saber delegar é essencial para quem quer obter resultados em larga escala, além de estimular o surgimento de novas ideias.",

                    //DICA 4 - DESCRIÇÃO
                        "1 – Preze pelos rituais\n" +
                                "\n" +
                                "Para o especialista Fernando Góes, da Ockam, uma atitude que ajuda no sucesso dos empreendedores é dar valor a rituais diários. Como assim? “São coisas simples como estar na empresa desde cedo, dando exemplo aos seus liderados. A nova geração quer mais liberdade, mas isso não significa abrir mão da disciplina”, exemplifica.\n" +
                                "\n" +
                                "2 – Converse mais\n" +
                                "\n" +
                                "Para o especialista, é importante que o empreendedor não fique “encastelado em seu computador”. “Ele precisa sair da sua cadeira e conversar com a sua equipe sobre o negócio e sobre a vida. Também precisa conversar com o cliente, deve ter pelo menos uma visita por dia”, aconselha. Essa troca de ideias ajuda o empresário a enxergar melhor o mercado e o seu negócio.\n" +
                                "\n" +
                                "3 – Não viva só para trabalhar\n" +
                                "\n" +
                                "Fazer as coisas que gosta, como praticar um esporte, ir ao cinema ou ter um trabalho voluntário ajuda o empreendedor a tomar decisões na empresa, segundo o especialista Góes. “Sem isso, as coisas começam a se desequilibrar, ele fica estressado, a conta começa a vir na saúde e a qualidade de decisões dele fica comprometida”, afirma.\n" +
                                "\n" +
                                "Outro ponto importante, segundo o especialista Arnaldo Auad, da consultoria Direção e Sentido, é dar atenção à vida afetiva. “Manter seus relacionamentos afetivos em dia e prezar pelo equilíbrio entre a vida pessoal e a profissional tem reflexos em qualquer coisa que o empreendedor faça”, afirma.\n" +
                                "\n" +
                                "4 – Aprenda sobre seu negócio\n" +
                                "\n" +
                                "Para Auad, é importante que o empreendedor busque sempre aprender algo novo sobre seu negócio. “Se você lida com uma empresa do setor de serviços, pelo menos uma vez ao dia leia algo sobre esse assunto”, exemplifica. Outras opções são cursos de curta duração ou mesmo programas disponíveis na internet.\n" +
                                "\n" +
                                "5 – Procure ajuda\n" +
                                "\n" +
                                "Em muitos momentos, a vida do empreendedor pode ser muito solitária, com decisões importantes que afetam a sua vida e a de seus funcionários. Sendo assim, Auad recomenda que os empresários busquem ter sempre alguém com quem possam trocar experiências. “Recomendo que ele tenha um coach, pessoas que possam ter algo a acrescentar sobre aquilo que ele está vivendo”, afirma.\n" +
                                "\n" +
                                "6 – Cuide da sua saúde\n" +
                                "\n" +
                                "Dizer que é necessário cuidar da saúde parece óbvio, porém, muitos empreendedores deixam isso de lado. “A rotina do empreendedor pode ter muitas aflições, ansiedade, e essa parte emocional pode influenciar no físico, o que não podemos permitir”, afirma Auad.\n" +
                                "\n" +
                                "7 – Seja persistente (sem ser teimoso)\n" +
                                "\n" +
                                "Segundo Joacir Martinelli, da consultoria Duomo, é comum os empreendedores ouvirem que devem ser persistentes para ter sucesso. No entanto, não é recomendado confundir persistência com teimosia. “Vejo que algumas pessoas confundem uma coisa com a outra. É preciso ser persistente, mas não ter a teimosia de insistir num produto que não está dando certo, por exemplo. O empreendedor deve ser flexível e enxergar o que o mercado está pedindo”, afirma.\n" +
                                "\n" +
                                "8 – Ouça o feedback dos seus clientes\n" +
                                "\n" +
                                "Um hábito que poucos empreendedores têm, segundo Martinelli, é o de ouvir verdadeiramente seus clientes. “A grande maioria dos empreendedores não quer ter o feedback do cliente, quer apenas que ele diga que está tudo ótimo”, afirma. Para o consultor, um retorno sincero, mesmo que não seja tão positivo, é um “presente” para o empreendedor melhorar e ter mais sucesso. Portanto, ouça o seu cliente.\n" +
                                "\n" +
                                "9 – Planeje\n" +
                                "\n" +
                                "Ter iniciativa é uma das principais características que diferenciam os empreendedores. Mas agir sem planejar pode trazer muita dor de cabeça. Portanto, use uma parte do seu tempo para pensar antes de agir. “O empreendedor precisa balancear a iniciativa com o planejamento, caso contrário ele pode sofrer com o desperdício de recursos”, aconselha Martinelli.\n" +
                                "\n" +
                                "10 – Pense o seu produto\n" +
                                "\n" +
                                "Acredite se quiser: sua vontade de colocar a mão na massa pode atrapalhar o seu sucesso. “Alguns empresários ficam na operação do produto e tornam-se empregados do próprio negócio”, explica Martinelli. Com isso, o empreendedor não consegue pensar sobre como melhorar, e a empresa não decola. “Esse talvez seja o grande diferencial de empreendedores de sucesso”, afirma.\n" +
                                "\n",

                    //DICA 5 - DESCRIÇÃO
                        "O sonho de ser chefe de si mesmo é cada vez mais comum entre os brasileiros. Mas abrir uma empresa e, mais do que isso, mantê-la é extremamente desafiador. Assim, o primeiro passo básico para iniciar qualquer negócio é entender sobre empreendedorismo.\n" +
                                "\n" +
                                "Pesquisas apontam que, aproximadamente, 25% das novas organizações fecham as portas antes mesmo de completar dois anos. A falta de experiência é um dos motivos dessa estatística e, para evitar que isso aconteça, separamos algumas dicas para você obter o sucesso em seu negócio.\n" +
                                "\n" +
                                "1.      Você está apto para atuar nessa área?\n" +
                                "\n" +
                                "Antes de pensar em abrir uma empresa, tenha certeza que você tem aptidão para a área na qual deseja atuar. Ser experiente ou ter uma mínima noção do ramo é essencial para garantir uma boa administração do empreendimento e saber como lidar com os problemas que podem surgir.\n" +
                                "\n" +
                                "liderança eficiente\n" +
                                "\n" +
                                "2.      Entenda de empreendedorismo\n" +
                                "\n" +
                                "Aprofundar-se é fundamental. Faça cursos de gestão, procure órgãos especializados e tente entender ao máximo sobre todas as esferas de sua organização. Como exercício inicial, faça um plano bem elaborado, procurando identificar as necessidades de seu público-alvo e oferecer os melhores produtos e serviços.\n" +
                                "\n" +
                                "mba in one day\n" +
                                "\n" +
                                "3.      Elabore um planejamento financeiro\n" +
                                "\n" +
                                "Praticar o empreendedorismo não é tarefa fácil. Para que seu negócio dê certo, prepare-se para trabalhar até 15 horas por dia e programe-se financeiramente, pois são muitos os impostos pagos pelas pequenas empresas no Brasil. Além disso, no início, você não terá salário fixo, 13º ou férias.\n" +
                                "\n" +
                                "plano financeiro\n" +
                                "\n" +
                                "4.      Invista em liderança\n" +
                                "\n" +
                                "Liderar nem sempre é algo fácil, porque não existe liderança inata, mas sim hábitos a serem aprendidos. Situações e pessoas diferentes exigem abordagens específicas.\n" +
                                "\n" +
                                "Liderança exige customização contínua, por isso invista em seu desenvolvimento. Leia livros, faça cursos, assista palestras e seminários.\n" +
                                "\n" +
                                "liderança positiva\n" +
                                "\n" +
                                "5.      Tenha uma estratégia\n" +
                                "\n" +
                                "Elabore um plano de ação diferente, único e especial. Toda estratégia exige escolhas – sobretudo, de longo prazo. Pegue uma oportunidade e insista nela enquanto for possível.\n" +
                                "\n" +
                                "Muitos empreendedores têm dificuldade em ignorar novos fluxos de receita e não conseguem manter o foco em sua estratégia. Querer ser tudo para todo mundo, acaba te descaracterizando e te tornando desinteressante.\n" +
                                "\n" +
                                "estratégia\n" +
                                "\n" +
                                "6.      Não tenha medo de ousar\n" +
                                "\n" +
                                "Estabeleça metas ambiciosas e inspiradoras. Elas devem estar diretamente relacionadas à essência da empresa e ser capazes de mobilizar as pessoas. Por exemplo: a meta da Ford era “democratizar o automóvel”, a da Microsoft era ter “um computador em cada mesa de trabalho e em cada casa”.\n" +
                                "\n" +
                                "não tenha medo\n" +
                                "\n" +
                                " \n" +
                                "\n" +
                                "7.      Cuide de seus clientes\n" +
                                "\n" +
                                "Seja qual for o ramo da sua empresa, respeite seu consumidor, pois ele é o bem maior que você deve conquistar. Além de estimá-lo, desde o primeiro contato, mantenha um bom relacionamento e satisfaça todas as expectativas criadas por seu produto ou serviço. Invista em áreas como Marketing, Comunicação e Atendimento para criar uma relação sólida e duradoura.\n" +
                                "\n" +
                                "cuide de seus clientes\n" +
                                "\n" +
                                "8.      Execute seus planos\n" +
                                "\n" +
                                "Um dos maiores problemas das empresas é a incapacidade de garantir que seja feito aquilo que foi planejado. A maioria dos esforços de mudança em empresas fracassa. Assim, executar com sucesso a estratégia de uma organização tornou-se um desafio importante.\n" +
                                "\n" +
                                "Este problema causou a queda de vários gestores. De acordo com uma análise da renomada empresa de negócios Fortune, intitulada Why CEOs fail, mais de 70% das demissões forçadas de diretores-executivos estão relacionadas ao fracasso em fazer aquilo que se comprometem a cumprir.\n" +
                                "\n" +
                                "execute os seus planos\n" +
                                "\n" +
                                "9.      Encoraje o empreendedorismo\n" +
                                "\n" +
                                "Se quer ter uma organização empreendedora, vá além do ambiente estimulante. Líderes eficientes devem ter paixão por talentos e saber encontrar pessoas extraordinárias, que se assemelhem com o seu público-alvo, acreditem em suas metas, e sejam o espelho da sua empresa para a sociedade.\n" +
                                "\n" +
                                "Atraia as pessoas certas, pague-as bem, ofereça oportunidades de crescimento e apoie-as em sua carreira.\n" +
                                "\n" +
                                "Liderança positiva e motivação\n" +
                                "\n" +
                                "10.  Aposte em crescer\n" +
                                "\n" +
                                "A última dica, mas não menos importante, é desejar ascender continuamente. Esse crescimento deve ser ordenado, planejado e bem executado. A ambição de expandir suas realizações é fundamental para manter novos empreendedores confiantes e empenhados.\n" +
                                "\n",

                //PESSOAS PARA SE INPIRAR - DESCRIÇÃO
                    //1- ABÍLIO DINIZ
                        "Admirado no meio empreendedor pela sua capacidade de enfrentar problemas com humildade e tolerância, Abilio Diniz tem em sua trajetória profissional a passagem pelo Conselho do Grupo Pão de Açúcar, no qual atuou durante anos, e a atual presidência do Conselho de Administração da BRF. Além do seu site, Abilio frequentemente fala sobre como empreender e crescer profissionalmente em seu Twitter e em sua página no Facebook.",

                    //2- Antônio Alberto Saraiva - Dsc
                        "A história de Antônio Alberto Saraiva é típica de um empreendedor conhecido por força do destino. Isso porque teve que assumir os negócios do pai após ele ser assassinado na própria padaria.\n" +
                                "Apesar de dividir seu tempo com a empresa que assumirá e os estudos de medicina, Alberto conseguiu em determinado momento forçado pelo espirito empreendedor, conquistar uma personalidade que o fizeram enxergar um investimento que traria muito lucro a um custo popular: fast-food especializado em comida árabe.\n" +
                                "O Habib’s, fundado por ele em meados dos anos de 1988, unia preços bastante acessíveis com um cardápio diferenciado que incluía esfihas, quibes e beirutes unidos as tradicionais comidas de fast-food como batatas fritas, pasteis e pizzas. Genuinamente brasileiro, o Habib’s hoje conta com mais de 400 estabelecimentos espalhados pelo Brasil.\n" +
                                "Antônio se caracterizou pela visão avançada de oportunidades de negócio. Enxergou o espaço para espalhar a culinária pouco conhecida pelo povo brasileiro até então, com um custo mínimo de produção, possibilitando cobrar preços acessíveis aos mais variados bolsos das famílias brasileiras. Hoje é presidente, além do Habib’s, de redes que incluem a Ragazzo, Arabian Bread, Ice Lips, Promilat, Vox Line e Centrais de Produção em território nacional.\n" +
                                "Leia mais em Endeavor @ https://endeavor.org.br/conheca-5-empreendedores-de-sucesso/",

                    //3- FABIO SEIXAS
                        "Empreendedor, analista de sistemas e cocriador do Camiseteria, site de venda de camisetas com estampas diferencias, Fabio usa seu blog e sua conta no Twitter para dar dicas e conselhos para que está interessado em investir em design e no universo das startups.",

                    //4- GUY KAWASAKI
                        "Fundador da Alltop, é uma das personalidades de maior influência e sinônimo de inovação no Vale do Silício, EUA. Guy Kawasaki sempre oferece informações preciosas e dicas fundamentais sobre tecnologia, inovação e empreendedorismo para aqueles que acompanham seu trabalho em seu Twitter e blog.",

                    //5- ELON MUSK
                        "Em 1982, com apenas 11 anos, criou seu próprio video game, que mais tarde foi vendido para uma empresa sul-africana por 500 dólares. Formou-se em Economia pela Universidade da Pensilvânia e um ano depois obteve um diploma de Física. Mudou-se para a Califórnia e fundou a empresa Zip2, uma companhia que desenvolvia conteúdo para portais de notícias.\n" +
                                "\n" +
                                "Em 1999, a empresa foi comprada pela Compaq por 307 milhões de dólares e 34 milhões em ações. Aos 28 anos se tornou milionário. Mais tarde conseguiu a venda do PayPal para o eBay por 1,5 bilhão de dólares.\n" +
                                "\n" +
                                "Em janeiro de 2011, uma de suas empresas, a SpaceX, tornou-se a primeira empresa no mundo a vender um voo comercial à Lua. A missão, marcada para 2013, foi contratada pela empresa Astrobotic Technology, tendo como objectivo colocar um pequeno jipe na superfície lunar, o que não aconteceu. Em 2012, encerrou o projeto do Tesla Roadster, o primeiro modelo da sua autoria, um carro totalmente elétrico que custava cerca de 92 mil dólares. Atualmente, a Tesla já lançou mais três modelos: S, X e o modelo 3, este último com a responsabilidade de trazer os carros elétricos para as massas, partindo de um custo inicial de 35 mil dólares.",

                    //6- LUIZA HELENA TRAJANO
                        "Fundadora do Magazine Luiza e uma das mais importantes representantes do comércio brasileiro, Luiza Helena Trajano solidificou com sucesso o nome de sua empresa. Sempre humilde e decidida, ela afirma que começamos a solucionar nossos problemas quando resolvemos parar de reclamar. Em seu Twitter, você encontra muito mais sobre ela e seus planos.",

                    //7- MARCO GOMES
                        "Criador da boo-box, empresa especializada em propaganda em blogs, sites e redes sociais, e da Mova Mais, programa de incentivos para quem faz exercícios físicos, Marco Gomes foi considerado pela World Technology Network como o melhor profissional de tecnologias de marketing do mundo em 2013. Sua atuação como jovem empreendedor e palestrante tem feito com que ele seja cada vez mais reconhecido por seu trabalho. Compartilha suas ideias e trajetória com seus seguidores do seu blog e Twitter.",

                    //8- MARIE FORLEO
                        "Mixando energia e impetuosidade, Marie Forleo é uma guru do empreendimento, destacando-se pelas suas habilidades com negócios. Entre as grandes personalidades que reconhecem seu talento, está Oprah Winfrey, que a aponta como um exemplo de liderança para as próximas gerações. Seja através da sua fan page< ou do seu site, Marie oferece diversas dicas e conselhos sobre como empreender com segurança e altivez.",

                    //9- MARK ZUCKERBERG
                        "O idealizador e fundador da rede social mais importante da atualidade, o Facebook, tem o seu sucesso profissional atribuído a um fator essencial: dedicação. Persistente, ele fez de seu trabalho sua vida e transformou sua empresa em um dos melhores lugares para se trabalhar e construir uma carreira promissora. Em seu perfil, Mark fala sobre seu trabalho e sobre o que acontece na rede, permitindo que o usuário acompanhe todas as novidades que surgem.",

                    //10- ROMERO RODRIGUES
                        "Apesar de jovem, Romero se destacou no mercado como fundador e CEO da empresa Buscapé. Mesmo encontrando diversos obstáculos, ele nunca deixou de acreditar no modelo da sua empresa até que ela se tornasse uma das maiores do segmento no país. Em seu site e Twitter, ele orienta novos empreendedores a lidar com dificuldades e contratempos inerentes ao mundo dos negócios.",

                    //11- STEPHEN KANITZ
                        "Consultor de empresas e conferencista brasileiro, Stephen Kanitz é um grande entendedor de empreendimentos e grandes negócios. Foi um dos responsáveis pela disseminação do conceito de responsabilidade social no mundo empresarial. Além de realizar seminários em empresas do Brasil e do exterior, ele mantém um blog em que aborda os mais diversos temas, além de sua conta no Twitter, em que fala, com frequência, sobre assuntos ligados a negócio e investimentos.\n" +
                                "\n" +
                                "Um empreendedor de sucesso é quem, antes de tudo, possui iniciativa, e que, assim como os que citei há pouco, busca trazer soluções inovadoras e criativas para um mercado cada vez mais saturado e competitivo. Minha dica de hoje é que você comece a acompanhar de perto o trabalho de cada um desses visionários que apresentei. Ao fazer isso, você poderá se inspirar e aprender lições importantes para levar sua empresa pelo caminho do sucesso. Se você tem outros exemplos de grandes empreendedores de sucesso para compartilhar comigo, me conte quem eles são nos comentários. Vou adorar conversar mais com você!"};

        List<Car> listAux = new ArrayList<>();

         for(int i = 0; i < qtd; i++){
            Car c = new Car( models[i % models.length], brands[ i % brands.length], photos[i % photos.length], descriptions[i % descriptions.length]);
            c.setCategory( categories[ i % brands.length ] );

            if(category != 0 && c.getCategory() != category){
                continue;
            }

            listAux.add(c);
        }
        return(listAux);
    }

    public List<Car> getCarsByCategory(int category){
        List<Car> listAux = new ArrayList<>();
        for(int i = 0; i < listCars.size() ; i++){
            if(category != 0 && listCars.get(i).getCategory() != category){
                continue;
            }

            listAux.add(listCars.get(i));
        }
        return(listAux);
    }

    public List<Car> getListCars(){
        return(listCars);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("listCars", (ArrayList<Car>) listCars);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if(fab.isOpened()) {
            fab.close(true);
        }
        else {
            super.onBackPressed();
        }
    }
}
