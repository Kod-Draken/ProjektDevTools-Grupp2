package se.yrgo.bladesandmoccasins;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Score {
    private String name;
    private int value;

    public Score(String name, int value){
        this.name = name;
        this.value = value;
        try {
            changeScoorBoard();
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

    private void changeScoorBoard()throws IOException{
        try(BufferedReader buff = new BufferedReader(new FileReader("Score.txt"))){
            String line;
            while ((line=buff.readLine())!= null) {
                String[] splitLine = line.split(" ");
                if(name.equals(splitLine[0]) && (value > Integer.parseInt(splitLine[1]))){
                    try (BufferedWriter writ = new BufferedWriter(new FileWriter("Score.txt"))) {
                        writ.append(line);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }
    }


    public String toString(){

        try(BufferedReader buff = new BufferedReader(new FileReader("Score.txt"))){
            String line;
            for(int i = 1; i<=10; i++){
                line = buff.readLine();
                System.out.println(line);
            }
            return " ";
        }
        catch(Exception e){
            e.getMessage();
        }
        return "";
    }
}
