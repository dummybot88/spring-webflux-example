    package com.dummybot.got.webflux;
    import com.dummybot.got.model.Clan;
    import com.dummybot.got.model.Root;
    import com.dummybot.got.repository.GotClanRepository;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import reactor.core.publisher.Flux;
    import reactor.core.publisher.Mono;

    /**
     *
     * This class showcases the Reactive or Non Blocking Annotation Based Programming Model
     *
     *
     */

    @Slf4j
    @RestController
    @RequestMapping("/annotation-based")
    public class GameOfThronesRestController {

        @Autowired
        private GameOfThronesClient gameOfThronesClient;

        @Autowired
        private GotClanRepository gotClanRepository;

        @GetMapping("/root")
        public Mono<Root> getGameOfThronesRoot(){
            log.info("Starting Non-blocking Annotation Based Controller");
            Mono<Root> gotRoot = gameOfThronesClient.fetchGameOfThronesClan();
            //gotRoot.flatMap(root -> Mono.just(root.getClans())).flatMap(clans -> clans.stream().count())
            gotRoot.subscribe(root -> log.info(root.getClans().toString()));
            log.info("Ending Non-blocking Annotation Based Controller");
            return gotRoot;
        }

        @GetMapping("/all-clans")
        public Flux<Clan> getAllClans(){
            return gotClanRepository.findAllClans();
        }

        @GetMapping("/{name}/clan")
        public Mono<Clan> getClanByFamilyName(@PathVariable String name){
            return gotClanRepository.findClanByFamilyName(name);
        }

    }
