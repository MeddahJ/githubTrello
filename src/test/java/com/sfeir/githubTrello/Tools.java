package com.sfeir.githubTrello;

import com.sfeir.githubTrello.domain.trello.Card;
import com.sfeir.githubTrello.domain.trello.List;

import static com.sfeir.githubTrello.domain.trello.Card.*;
import static com.sfeir.githubTrello.domain.trello.List.*;
import static java.util.Arrays.*;

public class Tools {

	public static List list(String listId, String name, Card... cards) {
		return listBuilder().id(listId).name(name).cards(asList(cards)).build();
	}

	public static Card card(String cardId) {
		return cardBuilder().id(cardId).build();
	}
}