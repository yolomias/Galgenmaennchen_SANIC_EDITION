package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;

public class MainGUI {          //Deklaration der Buttons und aller Variablen
    private JButton qButton;
    private JButton wButton;
    private JButton aButton;
    private JButton yButton;
    private JButton eButton;
    private JButton sButton;
    private JButton xButton;
    private JButton dButton;
    private JButton cButton;
    private JButton rButton;
    private JButton fButton;
    private JButton vButton;
    private JButton tButton;
    private JButton gButton;
    private JButton bButton;
    private JButton zButton;
    private JButton hButton;
    private JButton nButton;
    private JButton uButton;
    private JButton jButton;
    private JButton mButton;
    private JButton iButton;
    private JButton kButton;
    private JButton oButton;
    private JButton lButton;
    private JPanel megaTOLLGUI;
    private JButton spielStartenButton;
    private JLabel wortDisplay;
    private JButton pButton;
    private JLabel versucheVerbleibend;
    private JLabel usedWords;
    private JLabel bild;
    public int laenge;
    public char[] loesung;
    public char[] ueberpruefer;
    public String ausgabe = "";
    public boolean[] istRichtig;
    public int versuche = 0;
    public int loser = 0;
    public boolean[] buchstaben;
    String verwendeteBuchstaben = "";
    boolean istEnhalten = false;
    public String bildAusgabe = "00.gif";
    public  int zaehler = 0;
    public int sieg;
    public boolean start = false;

    public  static void main(String[] args) { //Startet das Fenster und macht es sichtbar
        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new MainGUI().megaTOLLGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MainGUI() {
        bild.setIcon(new ImageIcon("00.gif")); //Setzt das Bild
        spielStartenButton.addActionListener(new ActionListener() { //Startet das Spiel mittels Startknopf
            @Override
            public void actionPerformed(ActionEvent e) { //________________________________________________
                BufferedReader bufReader = null;
                try {
                    bufReader = new BufferedReader(new FileReader("wortliste.txt"));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                ArrayList<String> listOfLines = new ArrayList<>();
                String line = null;
                try {
                    line = bufReader.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                while (line != null) {
                    listOfLines.add(line);
                    try {
                        line = bufReader.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                try {
                    bufReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } //________________________________ Die externe Wortliste wird eingelesen
                int wortlaenge = listOfLines.size(); //Misst die Länge des Arrays und speichert den Wert
                wortlaenge = wortlaenge - 1; //Ziehe von der Länge 1 ab, da Arrays immer mit 0 anfangen zu zählen
                Random rand = new Random();
                int random = rand.nextInt(wortlaenge); //Generiere eine zufällige Zahl
                String wort = listOfLines.get(random); //Wähle ein Wort aus dem Array
                wort = wort.toUpperCase(); //Vergrößere alle Buchstaben des Wortes
                laenge = wort.length(); //Zähle die Anzahl der Buchstaben in dem Wort und speichere es in der Variablen
                loesung = wort.toCharArray(); // Speichere die einzelnen Buchstaben des Strings in ein Char Array
                ueberpruefer = wort.toCharArray(); // macht das gleiche nochmal
                istRichtig = new boolean[laenge]; // Erstelle ein Boolean Array mit der Länge des Wortes
                Arrays.fill(istRichtig, Boolean.FALSE); //Setze jeden Wert des Arrays auf Falsch
                buchstaben = new boolean[26]; // Erstelle ein neues Array für jeden Buchstaben des Alphabets
                Arrays.fill(buchstaben, Boolean.FALSE); // Setze jeden Wert auf Falsch
                verwendeteBuchstaben = ""; // initialisiere die Variable
                String strich = ""; // initialisiere die Variable
                for (int i = 0; i <= laenge; i++) {
                    strich = strich + "_ "; //Erstelle für jeden Buchstaben einen Strich
                }
                zaehler = 1; //Setze die Variable auf 1
                versuche = 15; // Lege die Anzahl der Versuche fest
                bild.setIcon(new ImageIcon("01.gif")); //Lege das Startbild fest
                wortDisplay.setText(strich); // Gebe den Strich in der GUI aus
                versucheVerbleibend.setText(String.valueOf(versuche)); //Gebe die Anzahl der Versuche in der GUI aus
                usedWords.setText(verwendeteBuchstaben); // Gebe die bereits Verwendeten Buchstaben in der GUI aus
                sieg = 0; //Setze die Siegbedingung auf null
                start = true; // setze den Start auf wahr
            }
        });

        aButton.addActionListener(new ActionListener() { //Starte die Funktion, wenn der A knopf gedrückt wird ( ist bei den nächsten Buchstaben genau gleich,
            @Override                                    //deshalb werden diese nicht mehr erklärt.)
            public void actionPerformed(ActionEvent e) {
                if(start) { //Die Funktion darf nur ausgeführt werden wenn start wahr ist, da sonst ein Fehler im Programm auftritt
                loser = 0; //Setze die Variable auf 0
                if (versuche > 0) { //Wird nur ausgeführt, wenn noch versuche übrig sind
                    ausgabe = ""; //Cleare die Ausgabe
                    buchstaben[0] = true; //Setze den Buchstaben A auf wahr
                    for (int i = 0; i < laenge; i++) {
                        if (loesung[i] == 'A' && !istRichtig[i]) { //Frage ab ob der Erste Buchstabe des Wortes ein A enthält und ob die Variable falsch ist
                            istRichtig[i] = true; //Setze sie nun auf der ersten Stelle auf wahr
                            sieg = sieg + 1; //Erhöhe die Siegbedingung um 1
                            loser = loser + 1000; //Erhöhe die Variable um einen hohen Wert, dass er nie unter 0 gehen wird
                        }
                        if (istRichtig[i]) {
                            ausgabe = ausgabe + ueberpruefer[i] + " "; // wenn Wahr Gebe den richtigen Buchstaben aus
                        } else {
                            ausgabe = ausgabe + "_ "; // wenn Falsch, dann gebe einen Strich aus
                            loser = loser - 5; // wenn Falsch soll
                        }
                    }
                    if (!istRichtig[laenge - 1]) { // Wenn die letzte Stelle des Wortes nicht wahr ist, soll es durch einen Strich ersetzt werden
                        ausgabe = ausgabe + "_";
                    }
                    if (loser <= 0) { //Wenn die Variable kleiner oder gleich 0 ist
                        versuche = versuche - 1; //Ziehe ein Versuch ab
                        zaehler = zaehler + 1; // erhöhe um 1
                        if(zaehler < 10) { // wenn kleiner ist als 10
                            bildAusgabe = "0" + zaehler + ".gif"; // Setze die Variable für die Bildausgabe zusammen
                        } else bildAusgabe = zaehler + ".gif"; // Wenn größer als 9 lasse die 0 weg
                        bild.setIcon(new ImageIcon(bildAusgabe)); //Gebe das Bild aus
                        if (versuche <= 0) { // wenn Versuche kleiner oder gleich 0 sind
                           // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt."); //Gebe eine Meldung aus, das man verloren hat
                        }
                        versucheVerbleibend.setText(String.valueOf(versuche)); //Gebe die neue Anzahl der Versuche aus
                    }

                    istEnhalten = verwendeteBuchstaben.contains("A"); //Schreibe wahr in die Variable, wenn im String ein A enthalten ist
                    if (buchstaben[0] && !istEnhalten) { //Kontrolliere ob die erste Stelle des Buchstaben wahr ist und ob A nicht enthalten ist
                        verwendeteBuchstaben = verwendeteBuchstaben + "A "; //Füge ein A der Variablen hinzu
                        usedWords.setText(verwendeteBuchstaben); // Gebe die bereits verwendeten Buchstaben aus
                    }
                    wortDisplay.setText(ausgabe); // Gebe das Wort aus
                    if (laenge == sieg) { // Wenn die Anzahl der Buchstaben gleich der Zahl der Siege ist
                        versuche = 0; //Setze die Versuche auf null, damit das Spiel nicht weiter gespielt werden kann
                        bild.setIcon(new ImageIcon("17.gif")); // Gebe das Siegbild aus
                        JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!"); //Gebe eine Meldung aus, dass der Spieler gewonnen hats
                    }
                    }
                }
            }
        });

        bButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[1] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'B' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("B");
                        if (buchstaben[1] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "B ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[2] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'C' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("C");
                        if (buchstaben[2] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "C ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        dButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[3] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'D' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("D");
                        if (buchstaben[3] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "D ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[4] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'E' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("E");
                        if (buchstaben[4] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "E ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        fButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[5] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'F' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("F");
                        if (buchstaben[5] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "F ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        gButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[6] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'G' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("G");
                        if (buchstaben[6] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "G ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        hButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[7] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'H' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("H");
                        if (buchstaben[7] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "H ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        iButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[8] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'I' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                               // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("I");
                        if (buchstaben[8] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "I ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[9] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'J' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                               // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("J");
                        if (buchstaben[9] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "J ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        kButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[10] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'K' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("K");
                        if (buchstaben[10] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "K ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        lButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[11] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'L' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("L");
                        if (buchstaben[11] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "L ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        mButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[12] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'M' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("M");
                        if (buchstaben[12] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "M ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        nButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[13] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'N' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                               // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("N");
                        if (buchstaben[13] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "N ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        oButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[14] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'O' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("O");
                        if (buchstaben[14] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "O ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[15] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'P' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                               // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("P");
                        if (buchstaben[15] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "P ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        qButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[16] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'Q' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                               // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("Q");
                        if (buchstaben[16] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "Q ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[17] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'R' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("R");
                        if (buchstaben[17] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "R ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[18] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'S' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("S");
                        if (buchstaben[18] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "S ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        tButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[19] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'T' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("T");
                        if (buchstaben[19] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "T ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        uButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[20] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'U' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                               // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("U");
                        if (buchstaben[20] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "U ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        vButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[21] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'V' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                               // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("V");
                        if (buchstaben[21] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "V ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        wButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[22] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'W' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                               // JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("W");
                        if (buchstaben[22] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "W ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[23] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'X' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("X");
                        if (buchstaben[23] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "X ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        yButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[24] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'Y' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("Y");
                        if (buchstaben[24] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "Y ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });

        zButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(start) {
                    loser = 0;
                    if (versuche > 0) {
                        ausgabe = "";
                        buchstaben[25] = true;
                        for (int i = 0; i < laenge; i++) {
                            if (loesung[i] == 'Z' && !istRichtig[i]) {
                                istRichtig[i] = true;
                                sieg = sieg + 1;
                                loser = loser + 1000;
                            }
                            if (istRichtig[i]) {
                                ausgabe = ausgabe + ueberpruefer[i] + " ";
                            } else {
                                ausgabe = ausgabe + "_ ";
                                loser = loser - 5;
                            }
                        }
                        if (!istRichtig[laenge - 1]) {
                            ausgabe = ausgabe + "_";
                        }
                        if (loser <= 0) {
                            versuche = versuche - 1;
                            zaehler = zaehler + 1;
                            if(zaehler < 10) {
                                bildAusgabe = "0" + zaehler + ".gif";
                            } else bildAusgabe = zaehler + ".gif";
                            bild.setIcon(new ImageIcon(bildAusgabe));
                            if (versuche <= 0) {
                                //JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt.");
                            }
                            versucheVerbleibend.setText(String.valueOf(versuche));
                        }

                        istEnhalten = verwendeteBuchstaben.contains("Z");
                        if (buchstaben[25] && !istEnhalten) {
                            verwendeteBuchstaben = verwendeteBuchstaben + "Z ";
                            usedWords.setText(verwendeteBuchstaben);
                        }
                        wortDisplay.setText(ausgabe);
                        if (laenge == sieg) {
                            versuche = 0;
                            bild.setIcon(new ImageIcon("17.gif"));
                            JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!");
                        }
                    }
                }
            }
        });
    }
}
