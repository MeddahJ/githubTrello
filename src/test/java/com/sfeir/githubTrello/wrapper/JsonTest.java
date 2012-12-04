package com.sfeir.githubTrello.wrapper;

import org.junit.Test;

import com.google.common.base.Objects;

import static com.google.common.base.Objects.*;
import static com.sfeir.githubTrello.wrapper.Json.*;
import static com.sfeir.githubTrello.wrapper.JsonTest.Something.*;
import static java.util.Arrays.*;
import static org.fest.assertions.Assertions.*;


public class JsonTest {

	private Json json = jsonBuilder().build();

	@Test
	public void should_deserialize_serialized_object_correctly() {
		assertThat(json.toObjects(
						json.toString(asList(something("01"), something("02")))
						, Something.class)
					).containsOnly(something("01"), something("02"));
	}

	@Test
	public void should_deserialize_into_empty_collection() {
		assertThat(json.toObjects("[]", Something.class)).isEmpty();
	}

	@Test
	public void should_deserialize_into_empty_object() {
		assertThat(json.toObject("", Something.class)).isEqualTo(something(null));
	}


	static class Something{

		public static Something something(String id) {
			Something something = new Something();
			something.id = id;
			return something;
		}
		
		public String getId() {
			return id;
		}

		@Override
		public boolean equals(Object other) {//@formatter:off
			return (other == this) ? true
				 : (other == null) ? false
				 : (other instanceof Something) ? equal(((Something) other).id, id) 
				 : false;
		}//@formatter:on

		@Override
		public int hashCode() {
			return Objects.hashCode(id);
		}

		private String id;
	}
}

