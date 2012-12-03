package com.sfeir.githubTrello.wrapper;

import java.io.IOException;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.sfeir.githubTrello.domain.github.Branch;
import com.sfeir.githubTrello.domain.github.Branch.Commit;
import com.sfeir.githubTrello.domain.github.PullRequest;
import com.sfeir.githubTrello.domain.github.PullRequest.Head;
import com.sfeir.githubTrello.domain.trello.Card;
import com.sfeir.githubTrello.domain.trello.List;
import com.sfeir.githubTrello.json.github.BranchMixin;
import com.sfeir.githubTrello.json.github.CommitMixin;
import com.sfeir.githubTrello.json.github.HeadMixin;
import com.sfeir.githubTrello.json.github.PullRequestMixin;
import com.sfeir.githubTrello.json.trello.CardMixin;
import com.sfeir.githubTrello.json.trello.ListMixin;

import static java.util.Collections.*;

public final class Json {
	public static <T> T fromJsonToObject(String json, Class<T> type) {
		try {
			return mapper.readValue(json, type);
		}
		catch (IOException ioe) {
			try {
				return type.newInstance();
			}
			catch (InstantiationException | IllegalAccessException roe) {
				logger.error(ioe, ioe);
				logger.error(roe, roe);
				return null;
			}
		}
	}

	public static <T> Collection<T> fromJsonToObjects(String json, Class<T> type) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(Collection.class, type));
		}
		catch (IOException e) {
			logger.info(e, e);
			return emptyList();
		}
	}

	public static String fromObjectToJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		}
		catch (IOException e) {
			logger.error(e, e);
			return "";
		}
	}

	public static String extractValue(String json, String... attributes) {
		try {
			JsonNode node = mapper.readTree(json);
			for (String attribute : attributes)
				node = node.get(attribute);
			return node.getTextValue();
		} catch (IOException e) {
			logger.error(e, e);
			return "";
		}
	}

	private Json() {}

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Log logger = LogFactory.getLog(Json.class);

	static {
		mix(Branch.class, BranchMixin.class);
		mix(Commit.class, CommitMixin.class);
		mix(Head.class, HeadMixin.class);
		mix(PullRequest.class, PullRequestMixin.class);
		mix(Card.class, CardMixin.class);
		mix(List.class, ListMixin.class);
	}

	private static void mix(Class<?> targetClass, Class<?> mixinClass) {
		mapper.getSerializationConfig().addMixInAnnotations(targetClass, mixinClass);
		mapper.getDeserializationConfig().addMixInAnnotations(targetClass, mixinClass);
	}
}
