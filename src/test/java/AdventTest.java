import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AdventTest {

    private BufferedReader readFile(String fileName) {
        File file = new File("src\\test\\resources\\" + fileName);
        BufferedReader bufferedReader;
        try {
            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());


            try {
                return new BufferedReader(new FileReader(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void day3Test() {
        BufferedReader bf = readFile("input3b.txt");
        try {
            assert bf != null;
            Assert.assertEquals(336, (int) Advent3.parse(bf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void day4Test() {
        BufferedReader bf = readFile("input4b.txt");
        try {
            assert bf != null;
            Assert.assertEquals(4, Advent4.parse(bf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void day6Test() {
        BufferedReader bf = readFile("input6b.txt");
        try {
            assert bf != null;
            Assert.assertEquals(6, Advent6.parse(bf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Only part one is done.
     */
    @Test
    public void day7Test() {
        BufferedReader bf = readFile("input7b.txt");
        try {
            assert bf != null;
            Assert.assertEquals(4, Advent7.parse(bf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void day8Test() {
        BufferedReader bf = readFile("input8b.txt");
        try {
            assert bf != null;
            Assert.assertEquals(8, Advent8.parse(bf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void day9Test() {
        BufferedReader bf = readFile("input9b.txt");
        try {
            assert bf != null;
            Assert.assertEquals(62, Advent9.parse(bf, 5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void day10Test() {
        BufferedReader bf = readFile("input10b.txt");
        try {
            assert bf != null;
            Assert.assertEquals(19208, Advent10.parse(bf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
