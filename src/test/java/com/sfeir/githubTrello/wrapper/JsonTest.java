package com.sfeir.githubTrello.wrapper;

import org.junit.Test;

import com.google.common.base.Objects;

import static com.google.common.base.Objects.*;
import static com.sfeir.githubTrello.wrapper.Json.*;
import static com.sfeir.githubTrello.wrapper.JsonTest.Dummy.*;
import static java.util.Arrays.*;
import static org.fest.assertions.Assertions.*;


public class JsonTest {

	@Test
	public void should_deserialize_serialized_object_correctly() {
		assertThat(fromJsonToObjects(fromObjectToJson(asList(dummy("01"), dummy("02"))), Dummy.class))
					.containsOnly(dummy("01"), dummy("02"));
	}

	@Test
	public void should_deserialize_into_empty_collection() {
		assertThat(fromJsonToObjects("[]", Dummy.class)).isEmpty();
	}

	@Test
	public void should_deserialize_into_empty_card() {
		assertThat(fromJsonToObject("", Dummy.class)).isEqualTo(dummy(null));
	}


	static class Dummy {

		public static Dummy dummy(String id) {
			Dummy dummy = new Dummy();
			dummy.id = id;
			return dummy;
		}
		
		public String getId() {
			return id;
		}

		@Override
		public boolean equals(Object other) {//@formatter:off
			return (other == this) ? true
				 : (other == null) ? false
				 : (other instanceof Dummy) ? equal(((Dummy) other).id, id) 
				 : false;
		}//@formatter:on

		@Override
		public int hashCode() {
			return Objects.hashCode(id);
		}

		private String id;
	}

}

