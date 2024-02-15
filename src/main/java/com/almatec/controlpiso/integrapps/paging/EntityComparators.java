package com.almatec.controlpiso.integrapps.paging;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class EntityComparators<T> {

    static class Key {
    	String property;
    	Direction direction;
    	
        public Key(String property, Direction direction) {
			super();
			this.property = property;
			this.direction = direction;
		}
        
		public String getProperty() {
			return property;
		}
		public Direction getDirection() {
			return direction;
		}

		@Override
		public int hashCode() {
			return Objects.hash(direction, property);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			return direction == other.direction && Objects.equals(property, other.property);
		}
    }

    private Map<Key, Comparator<T>> map = new HashMap<>();

    private <U extends Comparable<U>> Comparator<T> comparing(String property, Direction direction, Function<T, U> keyExtractor) {
        Comparator<T> comparator = Comparator.comparing(keyExtractor::apply);
        return direction == Direction.desc ? comparator.reversed() : comparator;
    }

    public <U extends Comparable<U>> void addComparator(String property, Direction direction, Function<T, U> keyExtractor) {
        map.put(new Key(property, direction), comparing(property, direction, keyExtractor));
    }

    public  Comparator<T> getComparator(String property, Direction direction) {
        return map.get(new Key(property, direction));
    }

    private EntityComparators() {
        // Constructor privado
    }

    public static <T> EntityComparators<T> create() {
        return new EntityComparators<>();
    }
}
