package com.sfeir.githubTrello.domain.trello;

import com.google.common.base.Objects;

import static com.google.common.base.Objects.*;

public class Card {

	public String getId() {
		return this.id;
	}

	public String getBoardId() {
		return this.boardId;
	}

	public String getListId() {
		return this.listId;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public Card inNewList(List newList) {
		return cardBuilder().id(id).boardId(boardId).name(name).description(description).listId(newList.getId()).url(url).build();
	}

	@Override
	public String toString() {
		return toStringHelper(this)
				.add("id", id)
				.add("boardId", boardId)
				.add("listId", listId)
				.add("name", name)
				.add("description", description)
				.add("url", url).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, boardId, listId, name, description, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Card) {
			Card other = (Card) obj;
			return equal(id, other.id)
					&& equal(boardId, other.boardId)
					&& equal(listId, other.listId)
					&& equal(name, other.name)
					&& equal(description, other.description)
					&& equal(url, other.url);
		}
		return false;
	}


	public static Builder cardBuilder() {
		return new Card.Builder();
	}

	public static class Builder {

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder boardId(String boardId) {
			this.boardId = boardId;
			return this;
		}

		public Builder listId(String listId) {
			this.listId = listId;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Card build() {
			Card card = new Card();
			card.id = id;
			card.boardId = boardId;
			card.listId = listId;
			card.name = name;
			card.description = description;
			card.url = url;
			return card;
		}

		private String id;
		private String boardId;
		private String listId;
		private String name;
		private String description;
		private String url;
	}


	private String id;
	private String name;
	private String description;
	private String boardId;
	private String listId;
	private String url;
}
