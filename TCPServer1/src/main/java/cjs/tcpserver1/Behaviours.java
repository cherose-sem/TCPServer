/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjs.tcpserver1;

import java.util.HashMap;

/**
 *
 * @author cherr
 */
public class Behaviours {

    String msgCmd = "";
    String result = "";
    HashMap<String, String> list;

    public Behaviours(String msg) {
        this.msgCmd = msg;
        list = new HashMap();
        list.put("hund", "dog");
        list.put("hus", "house");
        list.put("familie", "family");
        list.put("bog", "book");
        list.put("ord", "word");
    }

    public void doCommands() {

        try {
            String[] s = msgCmd.split("#");
            String cmd = s[0];
            String word = s[1];
            cmd = cmd.toUpperCase();

            switch (cmd) {
                case "UPPER":
                    result = word.toUpperCase();
                    break;

                case "LOWER":
                    result = word.toLowerCase();
                    break;

                case "REVERSE":
                    result = new StringBuilder(word).reverse().toString();
                    break;

                case "TRANSLATE":
                    if (list.containsKey(word)) {
                        result = list.get(word);
                    } else {
                        result = "Sorry! Word is not in the list yet.";
                    }
                    break;

                default:
                    break;
            }
        } catch (Exception ex) {
            result = "stop";
        }
    }

    public String getResult() {
        return result;
    }

}
