package com.sfeir.githubTrello.json.trello;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sfeir.githubTrello.domain.trello.Card;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CardMixin extends Card {

	@Override
	@JsonProperty("id")
	public abstract String getId();

	@Override
	@JsonProperty("idBoard")
	public abstract String getBoardId();

	@Override
	@JsonProperty("idList")
	public abstract String getListId();

	@Override
	@JsonProperty("name")
	public abstract String getName();

	@Override
	@JsonProperty("desc")
	public abstract String getDescription();

	@Override
	@JsonProperty("url")
	public abstract String getUrl();

}