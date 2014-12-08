package com.cpacademy.core.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


public class StringUtil {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	/**
	 * This will replace the substring in the passed original string with the new string.
	 * 
	 * @param origString
	 *            - the original string to which we need to apply the changes.
	 * @param oldSubString
	 *            - the old string which needs to replace.
	 * @param newSubString
	 *            - the new string which needs to apply as the replacement.
	 * 
	 * @return - the new replaced string.
	 */
	public static String replaceSubstring(String origString, String oldSubString, String newSubString) {

		String result = "";

		if (origString == null || origString.length() == 0)
			return "";

		// If any of the replacement strings are null, then no substitution is possible. So, fall-through to the return original string below.
		if (oldSubString != null && newSubString != null) {
			if (oldSubString.length() == 0 || origString.indexOf(oldSubString) < 0) {
				result = origString;
			} else {
				int index = 0, lastIdx = 0;
				StringBuffer buffer = new StringBuffer(origString.length());

				// Build result until no more matching patterns found.
				while ((index = origString.indexOf(oldSubString, lastIdx)) > -1) {

					// Replace next matching substring with new substring
					buffer.append(origString.substring(lastIdx, index)).append(newSubString);

					// Set index for next pattern match.
					lastIdx = index + oldSubString.length();
				}

				// Append remaining portion of original to result
				//
				buffer.append(origString.substring(lastIdx));
				result = buffer.toString();
			}
		}

		return result;
	}

	/**
	 * This function returns a collection of "tokens" parsed from the input string. If no delimiter is found, the entire string is returned in the first collection element. If null is passed, and
	 * empty (but not null) collection is returned.
	 * 
	 * @param stringToParse
	 *            - the String to parse or tokenize.
	 * @param delimiter
	 *            - the String to use as the delimiter
	 * @return Collection of tokens - includes 'empty' tokens which is different from StringTokenizer.
	 */
	public static List<String> listTokenize(String stringToParse, String delimiter) {

		List<String> list = new ArrayList<String>();
		if (stringToParse != null && stringToParse.length() > 0) {

			// If delimiter null, empty or not found in string to parse, then return original string to parse as only item in list.
			//
			int index = 0, last = 0;
			if (delimiter == null || delimiter.length() == 0 || (index = stringToParse.indexOf(delimiter)) < 0) {
				list.add(stringToParse);
			} else {

				// Grab each substring between (but excluding) the delimiters, until no delimiters are left.
				//
				while (index > -1) {
					list.add(stringToParse.substring(last, index));
					last = index + delimiter.length();
					index = stringToParse.indexOf(delimiter, last);
				}

				// If initial string ended in the delimiter, than add one more blank token since last empty string component needs to be accounted for.
				// Otherwise, grab the remainder of the initial string.
				//
				if (stringToParse.endsWith(delimiter)) {
					list.add("");
				} else {
					list.add(stringToParse.substring(last));
				}

			}
		}
		return list;
	}

	/**
	 * This function returns text to the left of the first occurrence of the passed delimiter. If the first character(s) of the passed string match the delimiter, then an empty (not null) string is
	 * returned. If no delimiter is found, the entire string is returned.
	 * 
	 * <p>
	 * Note: This method treats the delimiter as a single "matching" sequence of characters, which is NOT the way the StringTokenizer uses the delimiter.
	 * 
	 * <p>
	 * EACH CALL TO THIS METHOD MODIFIES THE ORIGINAL StringBuffer ARGUMENT BY REMOVING THE PARSED TOKEN AND DELIMITER. THE CALLER KNOWS HE IS DONE PARSING WHEN THIS METHOD RETURNS AN EMPTY BUFFER.
	 * FOR EXAMPLE:<br>
	 * 
	 * while(buffer.length() > 0) { String nextToken = ParseUtility.nextToken(buffer, delimiter); ... // your logic goes here. }
	 * 
	 * @param bufferToParse
	 *            The StringBuffer to parse or tokenize.
	 * @param delimiter
	 *            The String to use as the delimiter.
	 * @return String The next token within the passed StringBuffer. Can be an 'empty' token, which is different from StringTokenizer.
	 */
	public static String nextToken(StringBuffer bufferToParse, String delimiter) {
		return StringUtil._nextToken(bufferToParse, delimiter, 0, false);
	}

	/**
	 * See nextToken method for complete details. This method does that exact same thing EXCEPT that any token/delimiter before the "after" index/argument is ignored. That is, parsing tokens via the
	 * passed in delimiter starts after the index/position specified in the 3rd parameter.
	 * 
	 * @param bufferToParse
	 *            The StringBuffer to parse or tokenize.
	 * @param delimiter
	 *            The String to use as the delimiter.
	 * @param afterIndex
	 *            The index after which the delimiter should be looked for.
	 * @return String The next token within the passed StringBuffer. Can be an 'empty' token, which is different from StringTokenizer.
	 */
	public static String nextToken(StringBuffer bufferToParse, String delimiter, int after) {
		return StringUtil._nextToken(bufferToParse, delimiter, after, false);
	}

	/**
	 * This function returns text to the left of the first occurrence of the passed delimiter. If the first character(s) of the passed string match the delimiter, then an empty (not null) string is
	 * returned. If no delimiter is found, the entire string is returned.
	 * 
	 * <p>
	 * Note: This method treats the delimiter as a single "matching" sequence of characters, which is NOT the way the StringTokenizer uses the delimiter.
	 * 
	 * <p>
	 * The first call to this method modifies the original StringBuffer argument by removing the parsed token. However, the delimiter is NOT REMOVED. This method should NEVER BE CALLED FROM WITHIN A
	 * LOOP because on the second and subsequent calls, the same "remaining" string will be returned.<br>
	 * 
	 * @param bufferToParse
	 *            The StringBuffer to parse or tokenize.
	 * @param delimiter
	 *            The String to use as the delimiter.
	 * @return String The next token within the passed StringBuffer. Can be an 'empty' token, which is different from StringTokenizer.
	 */
	public static String getTokenExcludingDelimiter(StringBuffer bufferToParse, String delimiter) {
		return StringUtil._nextToken(bufferToParse, delimiter, 0, true);
	}

	/**
	 * Private method that contains all the actual next token logic.
	 */
	public static String _nextToken(StringBuffer bufferToParse, String delimiter, int after, boolean excludeDelimiter) {

		// Default result is blank if null buffer passed.
		//
		String result = "";
		if (bufferToParse != null) {

			// Convert buffer to string for indexOf operations.
			// A null or empty delimiter will exception out or can cause
			// an infinite loop (if caller repeats nextToken calls),
			// so consider these delimiters as not found (no match).
			//
			String temp = bufferToParse.toString();
			int index = (delimiter == null || delimiter.length() == 0 ? -1 : temp.indexOf(delimiter, after));

			// If the delimiter is found, return that portion (before the match) and
			// remove that portion (and optionally the delimiter itself) from the buffer.
			//
			if (index >= 0) {
				result = bufferToParse.substring(0, index);
				int end = (excludeDelimiter ? index : index + delimiter.length());
				bufferToParse.delete(0, end);
			} else {
				// If delimiter null, empty or not found, return the entire string.
				//
				result = temp;
				bufferToParse.delete(0, bufferToParse.length());
			}
		}

		return result;
	}

	/**
	 * Checks to see if a string is null or if a string is empty
	 * 
	 * @param s
	 * @return true if a string is null or if a trimmed string is empty
	 */
	public static boolean isNullOrEmpty(String s) {
		if (s == null)
			return true;
		if ("".equals(s.trim()))
			return true;
		return false;
	}

	/**
	 * This function returns a HashMap of name/value pairs. Pairs within the source string must be separated by the source delimitor. Each name/value pair should be separated by "=".
	 * 
	 * <p>
	 * For example, "AA=20|BB=30|CC=40" would return a HashMap with keys "AA", "BB" and "CC" with values of 20, 30 and 40 respectively.
	 * 
	 * @param stringToParse
	 *            The String to parse into a hash
	 * @param delimiter
	 *            The String to use as the delimiter between pairs.
	 * @return hashMap of name/value pairs.
	 */
	public static HashMap<String, String> hash(String stringToHash, String delimiter) {
		return StringUtil.hash(stringToHash, delimiter, "=");
	}

	/**
	 * This function returns a HashMap of name/value pairs. Pairs within the source string must be separated by the source delimiter. Each name/value pair should be separated by "=".
	 * 
	 * <p>
	 * For example, "AA<e>20|BB<e>30|CC<e>40" would return a HashMap with keys "AA", "BB" and "CC" with values of 20, 30 and 40 respectively where "<e>" is typically "=", but can be be anything based
	 * on equator argument.
	 * 
	 * @param stringToParse
	 *            The String to parse into a hash
	 * @param delimiter
	 *            The String to use as the delimiter between pairs.
	 * @param equator
	 *            The String that represents the equator between name and value data.
	 * @return hashMap of name/value pairs.
	 */
	public static HashMap<String, String> hash(String stringToHash, String delimiter, String equator) {
		HashMap<String, String> map = new HashMap<String, String>();

		// Tokenize the string to hash. Initialize list size and delim length once.
		//
		List<String> list = StringUtil.listTokenize(stringToHash, delimiter);
		int size = list.size();
		int len = (equator == null ? 0 : equator.length());

		// For each tokenize string component,
		// get name/value pair and add to hash.
		//
		for (int i = 0; i < size; i++) {
			String nextPair = list.get(i);
			if (nextPair.length() > 0) {
				int nextIndex = nextPair.indexOf(equator);
				String nextName = (nextIndex < 0 ? nextPair : nextPair.substring(0, nextIndex));
				String nextValue = (nextIndex < 0 ? "" : nextPair.substring(nextIndex + len));
				map.put(nextName, nextValue);
			}
		}
		return map;
	}

	/**
	 * @see lastToken(StringBuffer, String)
	 */
	public static String lastToken(String stringToParse, String delimiter) {
		String result = "";
		if (stringToParse != null) {
			result = lastToken(new StringBuffer(stringToParse), delimiter);
		}
		return result;
	}

	/**
	 * This function returns text to the right of the "right most" occurrence of the passed delimiter (and removes this text, including the delimiter, from the passed StringBuffer). If no delimiter is
	 * found, the entire string is returned and the buffer is emptied.
	 * 
	 * <p>
	 * Note: This method treats the delimiter as a single "matching" sequence of characters, which is NOT the way the StringTokenizer uses the delimiter.
	 * 
	 * @param bufferToParse
	 *            The StringBuffer to parse or tokenize.
	 * @param delimiter
	 *            The String to use as the delimiter.
	 * @return String The last token within the passed StringBuffer. Can be an 'empty' token, which is different from StringTokenizer.
	 */
	public static String lastToken(StringBuffer bufferToParse, String delimiter) {

		// Default result is blank if null buffer passed.
		//
		String result = "";
		if (bufferToParse != null) {

			// Convert buffer to string for indexOf operations.
			// A null or empty delimiter will exception out or can cause an infinite loop (if caller repeats nextToken calls),
			// so consider these delimiters as not found (no match).
			//
			String temp = bufferToParse.toString();
			int index = (delimiter == null || delimiter.length() == 0 ? -1 : temp.lastIndexOf(delimiter));

			// If the delimiter is found, return that portion (before the match) and
			// remove that portion (and optionally the delimiter itself) from the buffer.
			//
			if (index >= 0) {
				result = bufferToParse.substring(index + delimiter.length());
				bufferToParse.delete(index, bufferToParse.length());
			} else {
				// If delimiter null, empty or not found, return the entire string.
				//
				result = temp;
				bufferToParse.delete(0, bufferToParse.length());
			}
		}

		return result;
	}

	/**
	 * 
	 * Utility method to return a String array as a CSV String. Uses Spring Frameworks StringUtils.arrayToCommaDelimitedString() method.
	 * 
	 * @param arr
	 *            the arr
	 * @return the string
	 */
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return StringUtils.arrayToCommaDelimitedString(arr);
	}

    /**
     * This method returns TRUE if the passed string can be converted to a number.
     * Note: Hex numbers are not allowed and will return false. 
     * Note: Expedential numbers (e.g., 10e2) return true.
     *
     * @param String
     * @return boolean
     */
    public static boolean isNumeric(String value) {

        boolean isNumeric = false;

        try {
            if (value != null) {
                BigDecimal bd = new BigDecimal(value);
                isNumeric = true;
            }
        }
        catch(Throwable e) {
            // Do nothing as the caller must decide if a non-numeric value is an error
        }

        return isNumeric;
    }	
    
    /* 
     * This method formats the collection using delimit COMMA using toString(Collection, String, String)
     * and adds surrounding square brackets.   
     */
    public static String formatCollectionSurroundBySquareBrackets(Collection<?> col) {
    	return toString(col, ",", null, "[", "]");
    }
    
    /* 
     * This method formats the Object array using delimit COMMA using toString(Collection, String, String)
     * and adds surrounding square brackets.   
     */
    public static String formatCollectionSurroundBySquareBrackets(Object... args) {
    	List<Object> col = Arrays.asList(args);
    	return formatCollectionSurroundBySquareBrackets(col);
    }
        
    
    /**
     * This method adds a prefix string, suffix string to the formatted collection.
     * This method uses toString(collection, String, String) and prepend, append strings to that. 
     * 
     * @param col Collection of objects to be formatted.
     * @param elementDelim  This is the delimiter put between each element of the collection.
     * @param quote  If not null this will be prefixed, suffixed for each element of the collection.
     * @param colPrefixStr This is prepended to the returned string.  
     * @param colSuffixStr  This is appended to the returned string.
     * @return
     * 
     * E.g.: If a collection, with three string abc, mno, xyz are passed in with delim as comman, 
     * colPrefixStr as [, colSuffixStr as ], this method returns "[abc,mno,xyz]". 
     */
    public static String toString(Collection<?> col, String elementDelim, String quote, String colPrefixStr, String colSuffixStr) {
    	StringBuilder formattedStr = new StringBuilder();
    	if(colPrefixStr == null){
    		colPrefixStr = "";
    	}
    	if(colSuffixStr == null){
    		colSuffixStr = "";
    	}
    	
    	if(col != null && col.size() != 0){
    		String colStr = toString(col, elementDelim, quote);
    		formattedStr.append(colPrefixStr)
    			.append(colStr)
    			.append(colSuffixStr);
    	} else {
    		formattedStr.append(colPrefixStr).append(colSuffixStr);
    	}
    	
    	return formattedStr.toString();
    }    
    
	/**
	 * Converts a Collection to a String, placing a delimiter between each
	 * value.
	 * 
	 * @param col
	 *			the Collection to convert.
	 * @param delim
	 *			the delimiter to place between values.
	 * @param quote
	 *			if non-null, this string is inserted before and after the
	 *			value.
	 * @return the converted String.
	 */
	public static String toString(Collection<?> col, String delim, String quote) {
		StringBuffer values = new StringBuffer();
		for (Iterator<?> it = col.iterator(); it.hasNext();) {
			final Object val = it.next();
			final String str = (val == null) ? "" : val.toString();
			if (quote == null)
				values.append(str);
			else
				values.append(quote).append(str).append(quote);
			if (it.hasNext()) {
				values.append(delim);
			}
		}
		return values.toString();
	}    
	
	public static String[] split(String str, char separatorChar) {
         if (str == null || str.length() == 0) return null;

		 int  len  = str.length();
		 List list = new ArrayList();
		 int i = 0, start = 0;
		 boolean match = false;
		 boolean lastMatch = false;
		 while (i < len) {
			 if (str.charAt(i) == separatorChar) {
				 if (match) {
					 list.add(str.substring(start, i));
					 match = false;
					 lastMatch = true;
				 }
				 start = ++i;
				 continue;
			 }
		     lastMatch = false;
		     match = true;
		     i++;
		 }
		 
		 if (match) {
		     list.add(str.substring(start, i));
		 }
		 
		 return (String[]) list.toArray(new String[list.size()]);
	}	
	
	/**
	 * Converts any number of parameters into Object[].  
	 */
	public static Object[] toArray(Object... args){
		return args;
	}	
	
    public static List<Long> listLongTokenize(String stringToParse, String delimiter) {

        // Initialize default empty list, returned if null string to parse.
        //
        List<Long> list = new ArrayList<Long>();
        if (stringToParse != null && stringToParse.length() > 0) {

            // If delimiter null, empty or not found in string to parse, then
            // return original string to parse as only item in list. Warning:
            // An empty delimiter will cause infinite loop unless handled pre-emptively.
            //
            int index = 0, last = 0;
            if (delimiter == null || delimiter.length() == 0 || (index = stringToParse.indexOf(delimiter)) < 0) {
                list.add(new Long(stringToParse));
            }
            else {

                // Grab each substring between (but excluding) the delimiters,
                // until no delimiters are left.
                //
                while (index > -1) {
                    list.add(new Long(stringToParse.substring(last, index)));
                    last  = index + delimiter.length();
                    index = stringToParse.indexOf(delimiter, last);
                }

                // If initial string ended in the delimiter, than add one more blank token
                // since last empty string component needs to be accounted for.
                // Otherwise, grab the remainder of the initial string.
                //
                if (stringToParse.endsWith(delimiter)) {
                    list.add(new Long(""));
                }
                else {
                    list.add(new Long(stringToParse.substring(last)));
                }

            }	
        }

        return list;
    }
	
}

