package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Mike
 * This class is a collection of algorithms working with Strings.
 * 
 * 1. reverse(char[] s): reverses a string
 * 2. reverseWords(char[] s): reverse words order in string without using additional memory
 * 3. firstUniqueChar(String s): finds the first unique char in the string
 * 4. areUniqueChars(String s): checks if there are duplicated symbols in the string
 * 5. longestSequence(String s): finds the longest increasing sequence in the string
 * 6. getMostFrequentWords(File file): finds list of most frequently used words in a file
 * 7. segmentString(String input, Set<String> dict): given an input string and a dictionary 
 * of words, segment the input string into a space-separated sequence of dictionary words
 * 8. atoi(char[] arr): convert string to int
 * 9. itoa(int i): convert int to string
 * 10. permutation(String str): finds all string permutations
 * 11. permuteUnique(String src): finds all unique permutations
 * 12. groupAnagrams(String[] source): finds and groups anagrams using given list of string
 *
 */

public class StringUtils {

	// reverse string without using additional memory
	static char[] reverse(char[] s) {
		int len = s.length;
		for (int i = 0; i < len / 2; i++) {
			s[i] = (char) (s[i] ^ s[len - i - 1]);
			s[len - i - 1] = (char) (s[i] ^ s[len - i - 1]);
			s[i] = (char) (s[i] ^ s[len - i - 1]);
		}
		return s;
	}
	
	// reverse words order in string without using additional memory
	static char[] reverseWords(char[] s) {
		int len = s.length;
		reverse(s, 0, len);
		int j = 0;
		for (int i = 0; i < len; i = j + 1) {
			for (j = i; j < s.length && s[j] != ' '; j++) {
			}
			reverse(s, i, j);
		}
		return s;
	}
	
	private static void reverse(char[] s, int start, int end) {
		for (int i = start, j = end - 1; i < j; i++, j--) {
	        char swap = s[i];
	        s[i] = s[j];
	        s[j] = swap;
	    }
	}

	// get the first unique char in the string
	static char firstUniqueChar(String s) {
		int[] arr = new int[26];
		for (int i = 0; i < s.length(); i++)
			arr[s.charAt(i) - 'a']++;
		for (int i = 0; i < s.length(); i++)
			if (arr[s.charAt(i) - 'a'] == 1)
				return s.charAt(i);
		return '0';
	}
	
	// get the first unique char in the string
	static char firstUniqueChar2(String s) {
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			Integer value = map.get(s.charAt(i));
			map.put(s.charAt(i), value == null ? 1 : value + 1);
		}
		for (Map.Entry<Character, Integer> entry: map.entrySet()) {
			if (entry.getValue() == 1)
				return entry.getKey();
		}
		return '0';
	}
	
	// check if there are duplicated symbols in the string
	static boolean areUniqueChars(String s) {
		boolean[] charSet = new boolean[256];
		for (int i = 0; i < s.length(); i++) {
			int j = s.charAt(i);
			if (charSet[j])
				return false;
			charSet[j] = true;
		}
		return true;
	}
	
	static boolean areUniqueChars2(String s) {
		int checker = 0;
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0)
				return false;
			checker |= 1 << val;
		}
		return true;
	}

	// O(n) - time, O(1) - space
	static String removeDupChars(char[] s) {
		boolean[] hit = new boolean[256];
		int tail = 1;
		hit[s[0]] = true;
		for (int i = 1; i < s.length; i++) {
			if (!hit[s[i]]) {
				s[tail] = s[i];
				tail++;
				hit[s[i]] = true;
			}
		}
		for (int i = tail; i < s.length; i++)
			s[i] = 0;
		return new String(s);
	}
	
	// get the longest sequence in the string
	static String longestSequence(String s) {
		char prev = s.charAt(0);
		int diff = 1, start = 0;
		String sequence = "";
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) - prev == diff) {
				diff++;
				if (i == s.length() - 1)
					sequence = getSubstring(s, sequence, diff, start, i + 1);
			} else {
				if (diff > 0) {
					sequence = getSubstring(s, sequence, diff, start, i);
					diff = 1;
				}
				prev = s.charAt(i);
				start = i;
			}
		}
		return sequence;
	}
	
	private static String getSubstring(String s, String sequence, int diff, int start, int end) {
		int length = sequence.length();
		if (length == 0 || (length > 0 && diff > length))
			sequence = s.substring(start, end);
		return sequence;
	}
	
	// find most frequently list of used words in a file
	static List<String> getMostFrequentWords(File file) {
		// read file
		Map<String, Integer> wordsMap = readFile(file);
		// build result map with the most frequent words
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		int mapResultValue = 0;
		for (Map.Entry<String, Integer> entry: wordsMap.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (resultMap.isEmpty()) {
				resultMap.put(key, value);
				mapResultValue = value;
			} else if (value >= mapResultValue) {
				if (value > mapResultValue)
					resultMap.clear();
				resultMap.put(key, value);
				mapResultValue = value;
			}
		}
		// build a words list from the result map 
		List<String> resultList = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry: resultMap.entrySet())
			resultList.add(entry.getKey());
		return resultList;
	}
	
	private static Map<String, Integer> readFile(File file) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String word;
			while ((word = br.readLine()) != null) {
			   if (map.containsKey(word))
				   map.put(word, map.get(word) + 1);
			   else
				   map.put(word, 1);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { br.close(); } catch (IOException e) {}
		}
		return map;
	}
	
	// given an input string and a dictionary of words, segment the input string into a space-separated sequence of dictionary words
	// O(n^2)
	static Map<String, String> memoized = new HashMap<String, String>();

	static String segmentString(String input, Set<String> dict) {
		if (dict.contains(input))
			return input;
		if (memoized.containsKey(input))
			return memoized.get(input);
		int len = input.length();
		for (int i = 1; i < len; i++) {
			String prefix = input.substring(0, i);
			if (dict.contains(prefix)) {
				String suffix = input.substring(i, len);
				String segSuffix = segmentString(suffix, dict);
				if (segSuffix != null) {
					memoized.put(input, prefix + " " + segSuffix);
					return prefix + " " + segSuffix;
				}
			}
		}
		memoized.put(input, null);
		return null;
	}
	  
	static int atoi(char[] arr) {
		int result = 0;
		boolean isSigned = false, isNumberStarted = false;
		if (arr == null || arr.length == 0)
			return 0;
		// trim
		String s = new String(arr);
		char[] c = s.trim().toCharArray();
		// parse
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '-' || c[i] == '+') {
				if (isNumberStarted)
					break;
				else {
					isSigned = (c[i] == '-');
					isNumberStarted = true;
				}
			} else if (c[i] >= '0' && c[i] <= '9') {
				isNumberStarted = true;
				int digit = c[i] - '0';
				result = result * 10 + digit;
			} else {
				if (isNumberStarted)
					break;
				else {
					result = 0;
					break;
				}
			}
		}
		if (isSigned)
			result = -result;
		return result;
	}
	
	static char[] itoa(int value) {
		String s = "";
		boolean isSigned = (value < 0);
		if (isSigned)
			value = -value;
		while (value != 0) {
			int digit = value % 10;
			value /= 10;
			s += digit;
		}
		if (isSigned)
			s += "-";
		return reverse(s.toCharArray());
	}
	
    // permute
	// O(n*n!)
    static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0)
        	System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++) {
            	//System.out.println("debug: "+i+" "+prefix + str.charAt(i) + " " + str.substring(0, i) + str.substring(i+1, n));
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }
    
	// permute uniquely
	static ArrayList<ArrayList<String>> permuteUnique(String src) {
		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
		returnList.add(new ArrayList<String>());
		for (int i = 0; i < src.length(); i++) {
			Set<ArrayList<String>> currentSet = new HashSet<ArrayList<String>>();
			for (List<String> l: returnList) {
				for (int j = 0; j < l.size() + 1; j++) {
					l.add(j, src.charAt(i)+"");
					ArrayList<String> T = new ArrayList<String>(l);
					l.remove(j);
					currentSet.add(T);
				}
			}
			returnList = new ArrayList<ArrayList<String>>(currentSet);
		}
		return returnList;
	}

	static List<String> groupAnagrams(String[] source) {
		List<String> resultList = new ArrayList<String>();
		Map<String, String> wordMap = new HashMap<String, String>();
		for (String word: source) {
			char[] charArray = word.toCharArray();
			Arrays.sort(charArray);
			String sortedWord = new String(charArray);
			if (wordMap.containsKey(sortedWord))
				wordMap.put(sortedWord, wordMap.get(sortedWord) + ", " + word);
			else
				wordMap.put(sortedWord, word);
		}
		for (Map.Entry<String, String> entry: wordMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		return resultList;
	}
	
	// converts map with nested maps to json format:
	// { key1 : value1, key2 : { key21 : value21, ... }, ... }
	static String mapToJSON(final Map<String, Object> map) {
		StringBuilder builder = new StringBuilder();
		mapToJSON(map, builder);
		return builder.toString();
	}
	
	private static void mapToJSON(final Map<String, Object> map, StringBuilder builder) {
		builder.append("{");
		boolean isEmpty = true;
		for (Map.Entry<String, Object> entry: map.entrySet()) {
			if (!isEmpty)
				builder.append(", ");
			String key = entry.getKey();
			Object value = entry.getValue();
			builder.append(key + " : ");
			if (value instanceof Map)
				mapToJSON((Map) value, builder);
			else
				builder.append(value);
			isEmpty = false;
		}
		builder.append("}");
	}
	
	static boolean isPalindrome(String s) {
		boolean res = true;
		int n = s.length();
		for (int i = 0, j = n - 1; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) {
				res = false;
				break;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		//char[] s = {'a', 'b', 'c', 'd', ' ', 'e', 'f', 'g', 'h', ' ', 'i', 'j', 'k'};
		//System.out.println(String.valueOf(reverseWords(s)));
		//System.out.println(longestSequence("abc qwe rstuvwxyz defghijklmnop hrrr jklmnopq rst qrstuvwxyzzxcvb"));
		//System.out.println(atoi(new char[] {' ', '-', '0', '1', '2', '6', '0', '6', '-', '7'}));
		//System.out.println(itoa(-12345));
		//System.out.println(areUniqueChars("!@#$%^&DUdfgjh!"));
		//System.out.println(firstUniqueChar2("aabbfddeec"));
		//ArrayList<ArrayList<String>> res = permuteUnique("aabb");
		//List<String> res = getMostFrequentWords(new File("words.txt"));
		//for (String a: res) {
		//	System.out.println(a);
		//}
		groupAnagrams(new String[] {"qwe", "abc", "cab", "def", "acb", "fed", "012", "weq"});
		//Set<String> set = new HashSet<String>();
		//set.add("hi");set.add("hello");set.add("i");set.add("am");set.add("mike");set.add("hit");set.add("the");
		//System.out.println(segmentString("hithelloiammike", set));
	}

}
