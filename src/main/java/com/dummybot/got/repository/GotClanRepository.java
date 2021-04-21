package com.dummybot.got.repository;

import com.dummybot.got.model.Clan;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GotClanRepository {

    static Map<String, Clan> gotClanData;

    static{
        gotClanData = new HashMap<>();
        gotClanData.put("Stark", new Clan("Stark", Arrays.asList("Arya Stark",
                "Benjen Stark",
                "Bran Stark",
                "Catelyn Stark",
                "Eddard Stark",
                "Ghost",
                "Grey Wind",
                "Jon Snow",
                "Lady",
                "Nymeria",
                "Rickon Stark",
                "Robb Stark",
                "Sansa Stark",
                "Shaggydog",
                "Summer")));
        gotClanData.put("Targaryen",new Clan("Targaryen",Arrays.asList("Daenerys Targaryen",
                "Drogon",
                "Rhaegal",
                "Viserion",
                "Viserys Targaryen")));
        gotClanData.put("Lannister",new Clan("Lannister",Arrays.asList("Cersei Lannister",
                "Jaime Lannister",
                "Kevan Lannister",
                "Lancel Lannister",
                "Tyrion Lannister",
                "Tywin Lannister")));
        gotClanData.put("WhiteWalkers",new Clan("WhiteWalkers", Arrays.asList("Mag the Mighty Wight",
                "The Night King",
                "White Walker",
                "Wight Wildling Girl",
                "Wun Wun Wight")));
    }

    public Mono<Clan> findClanByFamilyName(String familyName){
        return Mono.justOrEmpty(gotClanData.get(familyName));
    }

    public Flux<Clan> findAllClans(){
        return Flux.fromIterable(gotClanData.values());
    }

}
