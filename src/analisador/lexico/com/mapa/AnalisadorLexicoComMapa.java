/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador.lexico.com.mapa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ProjetoSiteDB
 */



public class AnalisadorLexicoComMapa {

    static void appendCSV(String currentLine, String column, String value, String token) throws IOException {
        writer.append(currentLine);
        writer.append("\t");
        writer.append(column);
        writer.append("\t");
        writer.append(value);
        writer.append("\t");
        writer.append(token);
        writer.append("\n");
    }
    /**
     * @param args the command line arguments
     */
    
    static String pathIn = "code.txt";
    static String pathOut = "result.txt";
    static Scanner scan = new Scanner(System.in);
    static FileWriter writer;

    public static void main(String[] args) {
        // TODO code application logic here
        Map<String,String> hm = new HashMap<>();
        //Commands 
        hm.put("beginnen",   "tk_begin");
        hm.put("ende",         "tk_end");
        hm.put("schreiben",  "tk_print");
        hm.put("var",          "tk_var");
        hm.put("scannen",     "tk_scan");
        hm.put("wenn",          "tk_if");
        hm.put("wennnicht",  "tk_elif");
        hm.put("sonst",       "tk_else");
        hm.put("von",           "tk_do");
        hm.put("durch",      "tk_while");
        hm.put("zum",          "tk_for");
        //Logic Expressions
        hm.put("und",          "tk_and");
        hm.put("oder",          "tk_or");
        hm.put("<",        "tk_smaller");
        hm.put(">",         "tk_bigger");
        hm.put("<=", "tk_smaller_equal");
        hm.put(">=",  "tk_bigger_equal");
        hm.put("==",          "tk_equal");
        hm.put("<>",     "tk_different");
        //Algebric Expressions
        hm.put("(",   "tk_open_bracket");
        hm.put(")",  "tk_close_bracket");
        hm.put("+",            "tk_sum");
        hm.put("-",    "tk_subtraction");
        hm.put("*", "tk_multiplication");
        hm.put("/",       "tk_division");
        //Others
        hm.put(";",      "tk_semicolon");
        hm.put(",",          "tk_comma");
        hm.put(":",          "tk_colon");
        hm.put("|",      "tk_separator");
        hm.put("->",           "tk_get");
        hm.put("=",           "tk_take");
        hm.put("{",      "tk_open_keys");
        hm.put("}",     "tk_close_keys");
        
        Map<String,String> wa = new HashMap<>();
        wa.put("0",   "00");
        wa.put("1",   "01");
        wa.put("2",   "02");
        wa.put("3",   "03");
        wa.put("4",   "04");
        wa.put("5",   "05");
        wa.put("6",   "06");
        wa.put("7",   "07");
        wa.put("8",   "08");
        wa.put("9",   "09");
        wa.put("a",   "10");
        wa.put("b",   "11");
        wa.put("c",   "12");
        wa.put("d",   "13");
        wa.put("f",   "14");
        wa.put("g",   "15");
        wa.put("h",   "16");
        wa.put("i",   "17");
        wa.put("j",   "18");
        wa.put("k",   "19");
        wa.put("l",   "20");
        wa.put("m",   "21");
        wa.put("n",   "22");
        wa.put("o",   "23");
        wa.put("p",   "24");
        wa.put("q",   "25");
        wa.put("r",   "26");
        wa.put("s",   "27");
        wa.put("t",   "28");
        wa.put("u",   "29");
        wa.put("v",   "30");
        wa.put("w",   "31");
        wa.put("x",   "32");
        wa.put("y",   "33");
        wa.put("z",   "34");
        wa.put("A",   "35");
        wa.put("B",   "36");
        wa.put("C",   "37");
        wa.put("D",   "38");
        wa.put("E",   "39");
        wa.put("F",   "40");
        wa.put("G",   "41");
        wa.put("H",   "42");
        wa.put("I",   "43");
        wa.put("J",   "44");
        wa.put("K",   "45");
        wa.put("L",   "46");
        wa.put("M",   "47");
        wa.put("N",   "48");
        wa.put("O",   "49");
        wa.put("P",   "50");
        wa.put("Q",   "51");
        wa.put("R",   "52");
        wa.put("S",   "53");
        wa.put("T",   "54");
        wa.put("U",   "55");
        wa.put("V",   "56");
        wa.put("W",   "57");
        wa.put("X",   "58");
        wa.put("Y",   "59");
        wa.put("Z",   "60");
        
        StringBuilder buffer;
        FileReader fileIn = null;
        try {
            fileIn = new FileReader(pathIn);
        } catch (FileNotFoundException ex) {
            System.out.println("Ocorreu um erro: " + ex);
        }
        BufferedReader scanFile = new BufferedReader(fileIn);
        
        String currentLineValue[];
        boolean isId;
        boolean isString;
        boolean isToken;
        boolean error;
        int columnReset = 0;
        int bufferCont = 0;
        int currentLine = 1;
        int column, currentColumn;
        
        try {
                writer = new FileWriter("output.csv");
                writer.append("Line");
                writer.append("\t");
                writer.append("Column");
                writer.append("\t");
                writer.append("Value");
                writer.append("\t");
                writer.append("Token");
                writer.append("\n");
                        
            while (scanFile.ready()) {
                System.out.println("l" + currentLine);
                column = 0;
                currentColumn = 0;

                buffer = new StringBuilder();
                currentLineValue = scanFile.readLine().split("");
                try {
                    do {
                        isId = false;
                        isToken = false;
                        error = false;
                        if (currentLineValue[currentColumn].equals(" ") || 
                            currentLineValue[currentColumn].equals("\t")) {
                            currentColumn++;
                        } else {
                            if (columnReset == 0) {
                                column = currentColumn;
                                columnReset = 1;
                            }
                            //Atual caractere analisado é inserido no buffer
                            buffer.append(currentLineValue[currentColumn]);
                            
                            //Identifica se é uma String
                            if (buffer.toString().equals("\"")) {
                                appendCSV(Integer.toString(currentLine), Integer.toString(column), "''", "tk_quotation_marks");
                                System.out.println("tk_quotation_marks, c" + column);
                                currentColumn++;
                                column++;
                                isString = true;
                                int j = currentColumn;
                                buffer = new StringBuilder();
                                while (!currentLineValue[j].equals("\"")) {
                                    if (j + 1 == currentLineValue.length) {
                                        System.out.println("Erro Léxico na linha " + currentLine + ", coluna "+ column + ": \" não encontrado.");
                                        appendCSV(Integer.toString(currentLine), Integer.toString(column), "Erro: \" não encontrado", "NULL");
                                        isString = false;
                                        break;
                                    } else {
                                        buffer.append(currentLineValue[j]);
                                    }
                                    j++;
                                }
                                if (isString) {
                                    System.out.println("tk_Stringid, c" + column);
                                    appendCSV(Integer.toString(currentLine), Integer.toString(column), buffer.toString(), "tk_Stringid");
                                    column = column + j;
                                    System.out.println("tk_quotation_marks, c" + column);
                                    appendCSV(Integer.toString(currentLine), Integer.toString(column), "''", "tk_quotation_marks");
                                    buffer = new StringBuilder();
                                    columnReset = 0;
                                    bufferCont = 0;
                                }
                                currentColumn = currentColumn + j;
                            }
                            
                            for (String key : hm.keySet()) {
                                //Verifica se o buffer é equivalente à algum token
                                if (key.equals(buffer.toString())) {
                                    System.out.println(hm.get(key) + ", c" + column);
                                    appendCSV(Integer.toString(currentLine), Integer.toString(column), buffer.toString(), hm.get(key));
                                    buffer = new StringBuilder();
                                    columnReset = 0;
                                    bufferCont = 0;
                                    isToken = true;
                                }
                            }
                            
                            //Identifica um ID
                            if (currentLineValue.length > currentColumn + 1 && !isToken) {
                                String bufferArray[] = buffer.toString().split("");
                                if ((" ".equals(currentLineValue[currentColumn + 1])) || 
                                    ("+".equals(currentLineValue[currentColumn + 1])) || 
                                    ("-".equals(currentLineValue[currentColumn + 1])) || 
                                    ("*".equals(currentLineValue[currentColumn + 1])) || 
                                    ("/".equals(currentLineValue[currentColumn + 1])) || 
                                    (">".equals(currentLineValue[currentColumn + 1])) || 
                                   (">=".equals(currentLineValue[currentColumn + 1])) || 
                                    ("<".equals(currentLineValue[currentColumn + 1])) || 
                                   ("<=".equals(currentLineValue[currentColumn + 1])) || 
                                    ("(".equals(currentLineValue[currentColumn + 1])) || 
                                    (")".equals(currentLineValue[currentColumn + 1])) || 
                                    ("|".equals(currentLineValue[currentColumn + 1])) || 
                                    ("=".equals(currentLineValue[currentColumn + 1])) || 
                                    (",".equals(currentLineValue[currentColumn + 1])) || 
                                    (";".equals(currentLineValue[currentColumn + 1]))) {
                                    for (int i = 0; i < buffer.toString().length(); i++) {
                                        for (String key : wa.keySet()) {
                                            //Verifica se o buffer é equivalente à algum caractere válido
                                            if (key.equals(bufferArray[i])) {
                                                isId = true;
                                            } 
                                        }
                                        if (!isId) {
                                            System.out.println("Erro Léxico na linha " + currentLine + ", coluna " + column + ": ID inválido.");
                                            appendCSV(Integer.toString(currentLine), Integer.toString(column), "Erro: ID inválido", "NULL");
                                            buffer = new StringBuilder();
                                            columnReset = 0;
                                            bufferCont = 0;
                                            isId = true;
                                            break;
                                        }
                                        if (i == 0 && (bufferArray[0].equals("0") || 
                                                       bufferArray[0].equals("1") || 
                                                       bufferArray[0].equals("2") || 
                                                       bufferArray[0].equals("3") || 
                                                       bufferArray[0].equals("4") || 
                                                       bufferArray[0].equals("5") || 
                                                       bufferArray[0].equals("6") || 
                                                       bufferArray[0].equals("7") || 
                                                       bufferArray[0].equals("8") || 
                                                       bufferArray[0].equals("9"))) {
                                            error = true;
                                        }
                                        if (bufferArray[i].equals("0") || bufferArray[i].equals("1") || bufferArray[i].equals("2") || bufferArray[i].equals("3") || bufferArray[i].equals("4") || bufferArray[i].equals("5") || bufferArray[i].equals("6") || bufferArray[i].equals("7") || bufferArray[i].equals("8") || bufferArray[i].equals("9")) {
                                            bufferCont++;
                                        }
                                        isId = false;
                                    }
                                    //intid identificado
                                    if (bufferCont == buffer.toString().length() && !isId) {
                                        System.out.println("tk_intid, c" + column);
                                        appendCSV(Integer.toString(currentLine), Integer.toString(column), buffer.toString(), "tk_intid");
                                        buffer = new StringBuilder();
                                        columnReset = 0;
                                        bufferCont = 0;
                                    }
                                    else if (bufferCont != buffer.toString().length() && error && !isId){
                                        System.out.println("Erro Léxico na linha " + currentLine + ", coluna "+ column + ": ID não pôde ser identificado.");
                                        appendCSV(Integer.toString(currentLine), Integer.toString(column), "Erro: ID não identificado.", "NULL");
                                        buffer = new StringBuilder();
                                        columnReset = 0;
                                        bufferCont = 0;
                                    }
                                    else if (bufferCont != buffer.toString().length() && !error && !isId) {
                                        //id identificado
                                        System.out.println("tk_id, c" + column);
                                        appendCSV(Integer.toString(currentLine), Integer.toString(column), buffer.toString(), "tk_id");
                                        buffer = new StringBuilder();
                                        columnReset = 0;
                                        bufferCont = 0;
                                    } 
                                }
                            }
                        currentColumn++;
                        }
                    } while (currentColumn < currentLineValue.length);
                } catch (ArrayIndexOutOfBoundsException ex) {
                } finally {
                    writer.append("\n");
                    currentLine++;
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println("Correu um erro: " + ex);
        }
    }
}
