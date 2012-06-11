package interviewQuestions;

import java.util.*;

public class BidirectionalMap<K, V> implements Map<K, V> {
	
	private HashMap<K, V> KeyMap;
	private HashMap<V, List<K>> ValueMap;
	
	public BidirectionalMap()
	{
		KeyMap = new HashMap<K,V>();
		ValueMap = new HashMap<V, List<K>>();
	}

	@Override
	public int size() {
		return KeyMap.size();
	}

	@Override
	public boolean isEmpty() {
		return KeyMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return KeyMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return KeyMap.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return KeyMap.get(key);
	}
	
	public List<K> getKeysForValue(Object value)
	{
		return ValueMap.get(value);
	}

	@Override
	public V put(K key, V value) {
		List<K> keys;
		if(ValueMap.containsKey(value)) {
			keys = ValueMap.get(value);
		}
		else {
			keys = new ArrayList<K>();
		}
		keys.add(key);
		ValueMap.put(value, keys);
		
		return KeyMap.put(key, value);
	}

	@Override
	public V remove(Object key) {
		V value = KeyMap.get(key);
		List<K> keys = ValueMap.get(value);
		keys.remove(key);
		if(keys.isEmpty()) {
			ValueMap.remove(value);
		}
		return KeyMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		KeyMap.putAll(m);
	}

	@Override
	public void clear() {
		
	}

	@Override
	public Set<K> keySet() {
		return KeyMap.keySet();
	}

	@Override
	public Collection<V> values() {
		return KeyMap.values();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return KeyMap.entrySet();
	}

}
