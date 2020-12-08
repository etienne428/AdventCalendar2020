import java.io.*;
import java.util.*;

public class Advent4 {

    static int count = 0;

    private static void add(String input) {
        if (input.length() < 20) {
//            System.out.println("Input too short ! " + input);
            return;
        }
        String[] passportData = input.split(" ");

        // Must contain at least 7 fields
        if (passportData.length < 7) {
//            System.out.println("Not enough data ! " + passportData.length + "   " + input);
            return;
        }

        HashMap<Code, String> fieldOfP = new HashMap<>();
        for (String pD: passportData) {
            if (pD.length() < 3) {
//                System.out.println("pD too short ! " + pD);
                break;
            }
            String[] in = pD.split(":", 2);
            try {
                fieldOfP.put(Code.valueOf(in[0]), in[1]);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(Arrays.deepToString(passportData) + e.toString() + "\npD = " + pD);
            }
        }

        for (Code code : Code.values()) {
            if (!checkCode(fieldOfP, code)) {
//                System.out.println(input + "\n is not correct because of " + code.name());
                return;
            }
        }
//        System.out.println(input + " is correct!");
        count++;
    }

    private static boolean checkCode(Map<Code, String> fieldOfP, Code code) {
        String tmp;
        tmp = fieldOfP.get(code);
        if (tmp == null) {
            if (code.compareTo(Code.cid) != 0)
                System.out.println(code + fieldOfP.toString());
            return code.compareTo(Code.cid) == 0;
        }
        int val;
        switch (code) {
            case byr:
                val = Integer.parseInt(tmp);
                return (val >= 1920 && val <= 2002);
            case iyr:
                val = Integer.parseInt(tmp);
                return (val >= 2010 && val <= 2020);
            case eyr:
                val = Integer.parseInt(tmp);
                return (val >= 2020 && val <= 2030);
            case hgt:
                if (tmp.contains("cm")) {
                    tmp = tmp.replaceAll("cm", "");
                    val = Integer.parseInt(tmp);
                    return (val >= 150 && val <= 193);
                } else if (tmp.contains("in")) {
                    tmp = tmp.replaceAll("in", "");
                    val = Integer.parseInt(tmp);
                    return (val >= 59 && val <= 76);
                }
                return false;
            case hcl:
                if (tmp.charAt(0) == '#') {
                    tmp = tmp.substring(1);
                    if (tmp.length() == 6) {
                        for (int i = 0; i < 6; i++) {
                            int ii = tmp.charAt(i);
                            if (ii < 48 || (ii > 57 && ii < 97) || ii > 103) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
                return false;
            case ecl:
                String[] colours = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                for (String c: colours) {
                    if (tmp.equals(c)) {
                        return true;
                    }
                }
                return false;
            case pid:
                if (tmp.length() != 9) {
                    return false;
                }
                try {
                    Integer.parseInt(tmp);
                } catch (Exception e) {
                    return false;
                }
                return true;
            case cid:
                return true;
            default:
                return false;
        }
    }

    public static void parse(BufferedReader bufferedReader) throws IOException {

        String line = "";
        String nextLine = bufferedReader.readLine();
        int nOfLine = 0;
        while (nextLine != null) {
            if (nextLine.equals("")) {
//                System.out.println("\n");
                add(line);
                nOfLine++;
                line = "";
            } else {
                line = line.concat(nextLine.concat(" "));
            }
            nextLine = bufferedReader.readLine();
        }
        if (!line.equals("")) {
//            System.out.println("\n");
            add(line);
            nOfLine++;
        }
        System.out.println(nOfLine);
    }

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\input4.txt");
        try {
            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());


            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                parse(bufferedReader);
                System.out.println("Solution is " + count);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum Code {
        byr,
        iyr,
        eyr,
        hgt,
        hcl,
        ecl,
        pid,
        cid;
    }
}

