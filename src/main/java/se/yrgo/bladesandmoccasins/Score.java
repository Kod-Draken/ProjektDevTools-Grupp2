package se.yrgo.bladesandmoccasins;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Score {
    private String name;
    private int value;

    public Score(String name, int value){
        this.name = name;
        this.value = value;
        //try {
            //changeScoorBoard();
        //} catch (IOException e) {
          // System.out.println(e.getMessage());
          // e.printStackTrace();
        //}
    }

    private void changeScoorBoard()throws IOException{
        try(BufferedReader buff = Files.newBufferedReader(Path.of("src/main/java/se.yrgo.bladesandmoccasins/Score.txt"))){
            String line;
            while ((line=buff.readLine())!= null) {
                String[] splitLine = line.split(" ");



                if(name.equals(splitLine[0]) && (value > Integer.parseInt(splitLine[1]))){
                    try (BufferedWriter writ = Files.newBufferedWriter(Path.of("Score.txt"))){
                        writ.append(line);
                    }
                    catch (IOException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString(){
        try(BufferedReader buff = Files.newBufferedReader(Path.of("se.yrgo.bladesandmoccasins/Score.txt"))){
            String line;
            for(int i = 1; i<=10; i++){
                line = buff.readLine();
                System.out.println(line);
            }
            return " ";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "";
    }
}
