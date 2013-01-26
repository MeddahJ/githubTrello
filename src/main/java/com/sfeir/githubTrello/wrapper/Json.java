package com.sfeir.githubTrello.wrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import static com.google.common.collect.Maps.*;

import static java.util.Collections.*;

public final class Json {

	public <T> T toObject(String json, Class<T> type) {
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

	public <T> Collection<T> toObjects(String json, Class<T> type) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(Collection.class, type));
		}
		catch (IOException e) {
			logger.info(e, e);
			return emptyList();
		}
	}

	public String toString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		}
		catch (IOException e) {
			logger.error(e, e);
			return "";
		}
	}

	public String extract(String json, String... attributes) {
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

	public static Builder jsonBuilder() {
		return new Builder();
	}

	public static class Builder {
		public Builder withMixin(Class<?> target, Class<?> mixin) {
			mixins.put(target, mixin);
			return this;
		}

		public Json build() {
			Json converter = new Json();
			for (Entry<Class<?>, Class<?>> association : mixins.entrySet()) {
				converter.mix(association.getKey(), association.getValue());
			}
			return converter;
		}

		private Map<Class<?>, Class<?>> mixins = newHashMap();
	}

	private void mix(Class<?> targetClass, Class<?> mixinClass) {
		mapper.getSerializationConfig().addMixInAnnotations(targetClass, mixinClass);
		mapper.getDeserializationConfig().addMixInAnnotations(targetClass, mixinClass);
	}

	private Json() {}

	private final ObjectMapper mapper = new ObjectMapper();
	private static final Log logger = LogFactory.getLog(Json.class);

}
