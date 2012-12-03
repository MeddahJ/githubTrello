package com.sfeir.githubTrello.json.trello;

import java.util.Collection;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sfeir.githubTrello.domain.trello.Card;
import com.sfeir.githubTrello.domain.trello.List;

public abstract class ListMixin extends List {

	@Override
	@JsonProperty("id")
	public abstract String getId();

	@Override
	@JsonProperty("name")
	public abstract String getName();

	@Override
	@JsonProperty("idBoard")
	public abstract String getBoardId();

	@Override
	@JsonProperty("cards")
	public abstract Collection<Card> getCards();

}