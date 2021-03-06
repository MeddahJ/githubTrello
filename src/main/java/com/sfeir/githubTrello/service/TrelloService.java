package com.sfeir.githubTrello.service;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sfeir.githubTrello.domain.trello.Board;
import com.sfeir.githubTrello.domain.trello.Card;
import com.sfeir.githubTrello.domain.trello.List;
import com.sfeir.githubTrello.json.trello.CardMixin;
import com.sfeir.githubTrello.json.trello.ListMixin;
import com.sfeir.githubTrello.wrapper.Json;
import com.sfeir.githubTrello.wrapper.RestClient;

import static com.sfeir.githubTrello.domain.trello.List.*;
import static com.sfeir.githubTrello.wrapper.Json.*;
import static java.lang.String.*;

public class TrelloService {

	public TrelloService(String token) {
		this.restClient = new RestClient(API_URL, format("&key=%s&token=%s", API_KEY, token));
	}

	public final Card getCard(String cardId) {
		return json.toObject(
				restClient.url("/cards/%s", cardId).get(),
				Card.class);
	}

	public final List getList(Board board, String listName) {
		for (List list : getListsFromBoard(board)) {
			if (listName.equals(list.getName())) {
				return list.withNewCards(getCardsFromList(list));
			}
		}
		logger.warn("Trello list '" + listName + "' not found");
		return nullList();
	}

	//		public final Card updateCardDescription(Card card, String newDescription) {
	//		}

	private Collection<List> getListsFromBoard(Board board) {
		return json.toObjects(
				restClient.url("/boards/%s/lists", board.getId()).get(),
				List.class);
	}

	private Collection<Card> getCardsFromList(List list) {
		return json.toObjects(restClient.url("/lists/%s/cards", list.getId()).get(), Card.class);
	}

	protected RestClient restClient;

	private static final String API_URL = "https://api.trello.com/1";
	private static final String API_KEY = "d0e4aa36488c2e5957da7c3a61a76ff2";
	private static final Log logger = LogFactory.getLog(TrelloService.class);
	
	private static Json json = jsonBuilder()
		.withMixin(List.class, ListMixin.class)
		.withMixin(Card.class, CardMixin.class)
		.build();

}