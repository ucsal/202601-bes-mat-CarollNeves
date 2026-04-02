package br.com.ucsal.olimpiadas.config;

import br.com.ucsal.olimpiadas.controller.MenuController;
import br.com.ucsal.olimpiadas.controller.ParticipanteController;
import br.com.ucsal.olimpiadas.controller.ProvaController;
import br.com.ucsal.olimpiadas.controller.QuestaoController;
import br.com.ucsal.olimpiadas.controller.TentativaController;
import br.com.ucsal.olimpiadas.repository.InMemoryParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.InMemoryProvaRepository;
import br.com.ucsal.olimpiadas.repository.InMemoryQuestaoRepository;
import br.com.ucsal.olimpiadas.repository.InMemoryTentativaRepository;
import br.com.ucsal.olimpiadas.scoring.SimpleAcertosScoringStrategy;
import br.com.ucsal.olimpiadas.seed.DataSeeder;
import br.com.ucsal.olimpiadas.seed.DefaultDataSeeder;
import br.com.ucsal.olimpiadas.service.ParticipanteServiceImpl;
import br.com.ucsal.olimpiadas.service.ProvaServiceImpl;
import br.com.ucsal.olimpiadas.service.QuestaoServiceImpl;
import br.com.ucsal.olimpiadas.service.TentativaServiceImpl;
import br.com.ucsal.olimpiadas.view.ChessBoardRenderer;
import br.com.ucsal.olimpiadas.view.ConsoleChessBoardRenderer;
import br.com.ucsal.olimpiadas.view.ConsoleInputView;
import br.com.ucsal.olimpiadas.view.ConsoleOutputView;
import br.com.ucsal.olimpiadas.view.InputView;
import br.com.ucsal.olimpiadas.view.OutputView;

public class ApplicationContext {
    // Repositories
    private final InMemoryParticipanteRepository participanteRepository =
            new InMemoryParticipanteRepository();
    private final InMemoryProvaRepository provaRepository =
            new InMemoryProvaRepository();
    private final InMemoryQuestaoRepository questaoRepository =
            new InMemoryQuestaoRepository();
    private final InMemoryTentativaRepository tentativaRepository =
            new InMemoryTentativaRepository();

    // Scoring strategy
    private final SimpleAcertosScoringStrategy scoringStrategy =
            new SimpleAcertosScoringStrategy();

    // Services
    private final ParticipanteServiceImpl participanteService =
            new ParticipanteServiceImpl(participanteRepository);
    private final ProvaServiceImpl provaService =
            new ProvaServiceImpl(provaRepository);
    private final QuestaoServiceImpl questaoService =
            new QuestaoServiceImpl(questaoRepository);
    private final TentativaServiceImpl tentativaService =
            new TentativaServiceImpl(tentativaRepository, scoringStrategy);

    // Views
    private final InputView inputView = new ConsoleInputView();
    private final OutputView outputView = new ConsoleOutputView();
    private final ChessBoardRenderer chessBoardRenderer =
            new ConsoleChessBoardRenderer(outputView);

    // Controllers
    private final ParticipanteController participanteController =
            new ParticipanteController(participanteService, inputView, outputView);
    private final ProvaController provaController =
            new ProvaController(provaService, inputView, outputView);
    private final QuestaoController questaoController =
            new QuestaoController(questaoService, provaController, inputView, outputView);
    private final TentativaController tentativaController =
            new TentativaController(tentativaService, questaoService,
                    participanteController, provaController,
                    inputView, outputView, chessBoardRenderer);
    private final MenuController menuController =
            new MenuController(participanteController, provaController,
                    questaoController, tentativaController,
                    inputView, outputView);

    // Seeder
    private final DataSeeder dataSeeder =
            new DefaultDataSeeder(provaService, questaoService);
     // Inicializa os dados de exemplo e inicia o loop de menu da aplicação.
    public void run() {
        dataSeeder.seed();
        menuController.run();
    }
}
