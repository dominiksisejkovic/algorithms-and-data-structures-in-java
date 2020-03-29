package dsisejkovic.algortihms.numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsisejkovic on 4.8.2015..
 */
public class BaseTransformer {

    public static String getBinaryLessZeroToOne(double value) {
        if (value <= 0 || value >= 1) {
            throw new IllegalArgumentException("Value must be between 0 and 1.");
        }

        StringBuilder builder = new StringBuilder();
        builder.append('.');
        while (value > 0) {
            if (builder.length() >= 32) {
                throw new IllegalArgumentException();
            }
            value *= 2;
            if (value >= 1) {
                builder.append(1);
                value -= 1;
            } else {
                builder.append(0);
            }
        }

        return builder.toString();
    }

    public static String getBinaryFromInteger(int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }

        StringBuilder b = new StringBuilder();

        while (value != 0) {
            b.append(value % 2);
            value /= 2;
        }

        return b.reverse().toString();
    }

    public static String getOctalFromInteger(int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }

        StringBuilder b = new StringBuilder();
        while (value != 0) {
            b.append(value % 8);
            value /= 8;
        }

        return b.reverse().toString();
    }

    public static String getHexadecimalFromInteger(int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }

        StringBuilder b = new StringBuilder();

        while (value != 0) {
            int tmp = value % 16;

            if (tmp > 9) {
                switch (tmp) {
                    case 10:
                        b.append("A");
                        break;
                    case 11:
                        b.append("B");
                        break;
                    case 12:
                        b.append("C");
                        break;
                    case 13:
                        b.append("D");
                        break;
                    case 14:
                        b.append("E");
                        break;
                    case 15:
                        b.append("F");
                        break;
                    default:
                        break;
                }
            } else {
                b.append(tmp);
            }
            value /= 16;
        }

        return b.reverse().toString();
    }

    public static String binaryToInt(String binary) {
        int value = 0;
        int len = binary.length();

        for (int i = 0; i < len; i++) {
            if (binary.charAt(i) == '1') {
                value += Math.pow(2, (len - i - 1));
            }
        }

        return String.valueOf(value);
    }


    public static String decimalToBinaryString(String decimalStr) {
        int intPart = Integer.parseInt(decimalStr.substring(0, decimalStr.indexOf('.')));
        double decPart = Double.parseDouble(decimalStr.substring(decimalStr.indexOf('.'), decimalStr.length()));

        //convert int part
        StringBuilder b = new StringBuilder();
        while (intPart != 0) {
            b.append(intPart % 2); // b.append(intPart & 1) bitwise
            intPart >>= 1;
        }
        
        String intPartStr = b.reverse().toString();
        b.setLength(0);
        
        // convert decimal part
        while(decPart > 0) {
            if (b.length() > 32) {
                return "Error";
            }
            
            decPart *=2;
            if (decPart>=1) {
                b.append(1);
                decPart -=1;
            } else {
                b.append(0);
            }
        }
        
        return  intPartStr + "." + b.toString();
    }

    private static List<List<Integer>> getSubsets(List<Integer> set) {
        List<List<Integer>> subsetCollection = new ArrayList<>();

        if (set.size() == 0) {
            subsetCollection.add(new ArrayList<>());
        } else {
            List<Integer> reducedSet = new ArrayList<>();

            reducedSet.addAll(set);

            int first = reducedSet.remove(0);
            List<List<Integer>> subsets = getSubsets(reducedSet);
            subsetCollection.addAll(subsets);

            subsets = getSubsets(reducedSet);

            for (List<Integer> subset : subsets) {
                subset.add(0, first);
            }

            subsetCollection.addAll(subsets);
        }

        return subsetCollection;
    }

    public static void main(String[] args) {
        System.out.println(BaseTransformer.getBinaryLessZeroToOne(0.5));
        System.out.println(BaseTransformer.getBinaryFromInteger(55));
        System.out.println(BaseTransformer.getHexadecimalFromInteger(6597457));
        System.out.println(BaseTransformer.getOctalFromInteger(5548));
        System.out.println(BaseTransformer.binaryToInt("10101011111"));
        System.out.println(BaseTransformer.decimalToBinaryString("350.25"));
    }
}
