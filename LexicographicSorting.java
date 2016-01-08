import java.util.*;
import java.util.ArrayList;

class LexicographicSorting {

    /**
     * Sort a string array in lexicographic order
     * @param s String array to sort
     * @param order Lexicographic order providing guideline for sorting
     * @return Sorted string array
     */
	public static String[] lexiSort(String[] s, String order) {
        // Convert all arrays to ArrayLists to simplify sorting efforts
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(s));
        ArrayList<String> result = new ArrayList<String>(list.size());

        // Retrieve sorted ArrayList
        result = sortList(list, order, 0, 0);

        return result.toArray(new String[result.size()]);
	}

    /**
     * Sort an ArrayList in lexicographic order
     * @param list ArrayList to sort
     * @param order Lexicographic order
     * @param indexOrder Index of "order" to focus on when sorting
     * @param indexString Index of each string to focus on when sorting
     * @return
     */
    public static ArrayList<String> sortList(ArrayList<String> list, String order, int indexOrder, int indexString) {
        // Check for only 1 string remaining in the list
        // Check if the strings in the list are all the same denoted by the indexOrder pointing to null
        if (list.size() == 1 || indexOrder > order.length()-1) {
            return list;
        }

        // Sorted Arraylist to be returned
        ArrayList<String> result = new ArrayList<String>(list.size());

        // Section of higher order to be sorted further
        ArrayList<String> section = new ArrayList<String>(list.size());

        // Iterate through all values in the list
        for (int i=0; i<list.size(); i++) {
            // Check for no value at indexString denoting high value and therefore being added to results
            if (list.get(i).length() <= indexString) {
                result.add(list.get(i));
                list.remove(i);
                i--;
            }
            // Check if the String value at indexString matches Order value at indexOrder denoting higer order to be sorted
            else if (list.get(i).charAt(indexString) == order.charAt(indexOrder)) {
                section.add(list.get(i));
                list.remove(i);
                i--;
            }
        }

        // Sort the list of higher order strings first and add to result list
        if (section.size() > 0) {
            result.addAll(sortList(section, order, indexOrder, indexString+1));
        }

        // Sort the list of lower order strings and add to result list
        if (list.size() > 0) {
            result.addAll(sortList(list, order, indexOrder+1, indexString));
        }

        return result;
    }

    // Carry out test cases and printout results
    public static void main(String[] args) {
        ArrayList<String[]> tests = new ArrayList<String[]>(5);
		String[] test1 = {"", "aaa", "aa", "ba", "aac", "aaa", "aaa", "aab", "abc", "bac", "cac"};
        tests.add(test1);
        String[] test2 = {"acb", "abc", "bca"};
        tests.add(test2);
        String[] test3 = {"acb", "abc", "bca"};
        tests.add(test3);
        String[] test4 = {"aaa","aa",""};
        tests.add(test4);
        String[] test5 = {""};
        tests.add(test5);

        ArrayList<String> orders = new ArrayList<String>(5);
		orders.add("abc");
        orders.add("abc");
        orders.add("cba");
        orders.add("a");
        orders.add("a");

        for (int j=0; j < tests.size(); j++) {
            String[] result = lexiSort(tests.get(j), orders.get(j));

            System.out.print("[");

            for (int i=0; i<tests.get(j).length; i++) {
                System.out.print("\"" + tests.get(j)[i] + "\" ");
            }

            System.out.println("], \"" + orders.get(j) + "\"");

            for (int i=0; i<result.length; i++) {
                System.out.print("\"" + result[i] + "\" ");
            }

            System.out.println();
        }
    }
}