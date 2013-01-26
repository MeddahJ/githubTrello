package com.sfeir.githubTrello.domain.trello;

import java.util.Collection;

import static java.util.Collections.*;

public class List {

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBoardId() {
		return boardId;
	}

	public Collection<Card> getCards() {
		return cards;
	}

	public List withNewCards(Collection<Card> newCards) {
		return listBuilder().id(id).name(name).boardId(boardId).cards(newCards).build();
	}

	public static List nullList() {
		return new List();
	}

	public static Builder listBuilder() {
		return new List.Builder();
	}

	public static class Builder {

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder boardId(String boardId) {
			this.boardId = boardId;
			return this;
		}

		public Builder cards(Collection<Card> cards) {
			this.cards = cards;
			return this;
		}

		public List build() {
			List list = new List();
			list.id = id;
			list.name = name;
			list.boardId = boardId;
			list.cards = cards;
			return list;
		}

		private String id;
		private String name;
		private String boardId;
		private Collection<Card> cards = emptyList();
	}

	private String id;
	private String name;
	private String boardId;
	private Collection<Card> cards;
}
