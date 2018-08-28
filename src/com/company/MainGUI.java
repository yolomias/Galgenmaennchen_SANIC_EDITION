package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
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
    private JCheckBox multiplayer;
    private JButton aeButton;
    private JButton oeButton;
    private JButton ueButton;
    private JButton szButton;
    public int laenge;
    public char[] loesung;
    private char[] ueberpruefer;
    public String ausgabe = "";
    private boolean[] istRichtig;
    public int versuche = 0;
    public boolean[] buchstaben;
    private String verwendeteBuchstaben = "";
    public  int zaehler = 0;
    public int sieg;
    private boolean start = false;
    public String wort;
    private boolean[] buttonPressed;

    private void buttonFarbe() {
        aButton.setBackground(Color.WHITE); // Setze die Farbe aller Knöpfe auf weiß, damit diese nicht die Farbe aus dem vorherigen Spiel beibehalten
        bButton.setBackground(Color.WHITE);
        cButton.setBackground(Color.WHITE);
        dButton.setBackground(Color.WHITE);
        eButton.setBackground(Color.WHITE);
        fButton.setBackground(Color.WHITE);
        gButton.setBackground(Color.WHITE);
        hButton.setBackground(Color.WHITE);
        iButton.setBackground(Color.WHITE);
        jButton.setBackground(Color.WHITE);
        kButton.setBackground(Color.WHITE);
        lButton.setBackground(Color.WHITE);
        mButton.setBackground(Color.WHITE);
        nButton.setBackground(Color.WHITE);
        oButton.setBackground(Color.WHITE);
        pButton.setBackground(Color.WHITE);
        qButton.setBackground(Color.WHITE);
        rButton.setBackground(Color.WHITE);
        sButton.setBackground(Color.WHITE);
        tButton.setBackground(Color.WHITE);
        uButton.setBackground(Color.WHITE);
        vButton.setBackground(Color.WHITE);
        wButton.setBackground(Color.WHITE);
        xButton.setBackground(Color.WHITE);
        yButton.setBackground(Color.WHITE);
        zButton.setBackground(Color.WHITE);
        aeButton.setBackground(Color.white);
        oeButton.setBackground(Color.white);
        ueButton.setBackground(Color.white);
        szButton.setBackground(Color.white);
    }

    public  static void main(String[] args) { //Startet das Fenster und macht es sichtbar
        JFrame frame = new JFrame("Galgenmännchen ver. 3 Graphical Editions");
        frame.setContentPane(new MainGUI().megaTOLLGUI);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void buttonClicked (char buchstabe, int zahl)
    {
        if(start && !buttonPressed[zahl]) { //Die Funktion darf nur ausgeführt werden wenn start wahr ist und der Knopf noch nicht gedrückt wurde, da sonst ein Fehler im Programm auftritt
            buttonPressed[zahl] = true; //Setze den Buchstaben auf wahr, weil er gedrückt wurde
            int loser = 0;
            if (versuche > 0) { //Wird nur ausgeführt, wenn noch versuche übrig sind
                ausgabe = ""; //Cleare die Ausgabe
                buchstaben[zahl] = true; //Setze den Buchstaben A auf wahr
                for (int i = 0; i < laenge; i++) {
                    if (loesung[i] == buchstabe && !istRichtig[i]) { //Frage ab ob der Erste Buchstabe des Wortes ein A enthält und ob die Variable falsch ist
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
                    buttonRed(buchstabe);
                    versuche = versuche - 1; //Ziehe ein Versuch ab
                    zaehler = zaehler + 1; // erhöhe um 1
                    String bildAusgabe;
                    if(zaehler < 10) { // wenn kleiner ist als 10
                        bildAusgabe = "/img/" + "0" + zaehler + ".gif"; // Setze die Variable für die Bildausgabe zusammen
                    } else bildAusgabe = "/img/" + zaehler + ".gif"; // Wenn größer als 9 lasse die 0 weg
                    bild.setIcon(new ImageIcon(Class.class.getResource(bildAusgabe))); //Gebe das Bild aus
                    if (versuche <= 0) { // wenn Versuche kleiner oder gleich 0 sind
                        JOptionPane.showMessageDialog(null, "Du hast keine Versuche mehr übrig, du wurdest erhängt."); //Gebe eine Meldung aus, das man verloren hat
                    }
                    versucheVerbleibend.setText(String.valueOf(versuche)); //Gebe die neue Anzahl der Versuche aus
                } else buttonGreen(buchstabe);

                boolean istEnhalten = verwendeteBuchstaben.contains("" + buchstabe);
                if (buchstaben[zahl] && !istEnhalten) { //Kontrolliere ob die erste Stelle des Buchstaben wahr ist und ob A nicht enthalten ist
                    verwendeteBuchstaben = verwendeteBuchstaben + buchstabe + " "; //Füge ein A der Variablen hinzu
                    usedWords.setText(verwendeteBuchstaben); // Gebe die bereits verwendeten Buchstaben aus
                }
                wortDisplay.setText(ausgabe); // Gebe das Wort aus
                if (laenge == sieg) { // Wenn die Anzahl der Buchstaben gleich der Zahl der Siege ist
                    versuche = 0; //Setze die Versuche auf null, damit das Spiel nicht weiter gespielt werden kann
                    bild.setIcon(new ImageIcon(Class.class.getResource("/img/17.gif"))); // Gebe das Siegbild aus
                    JOptionPane.showMessageDialog(null, "Du hast Wort erraten, das ist krass!!"); //Gebe eine Meldung aus, dass der Spieler gewonnen hats
                }
            }
        }
    }

    private void buttonRed (char buchstabe)
    {
        if      (buchstabe == 'A') aButton.setBackground(Color.red);
        else if (buchstabe == 'B') bButton.setBackground(Color.red);
        else if (buchstabe == 'C') cButton.setBackground(Color.red);
        else if (buchstabe == 'D') dButton.setBackground(Color.red);
        else if (buchstabe == 'E') eButton.setBackground(Color.red);
        else if (buchstabe == 'F') fButton.setBackground(Color.red);
        else if (buchstabe == 'G') gButton.setBackground(Color.red);
        else if (buchstabe == 'H') hButton.setBackground(Color.red);
        else if (buchstabe == 'I') iButton.setBackground(Color.red);
        else if (buchstabe == 'J') jButton.setBackground(Color.red);
        else if (buchstabe == 'K') kButton.setBackground(Color.red);
        else if (buchstabe == 'L') lButton.setBackground(Color.red);
        else if (buchstabe == 'M') mButton.setBackground(Color.red);
        else if (buchstabe == 'N') nButton.setBackground(Color.red);
        else if (buchstabe == 'O') oButton.setBackground(Color.red);
        else if (buchstabe == 'P') pButton.setBackground(Color.red);
        else if (buchstabe == 'Q') qButton.setBackground(Color.red);
        else if (buchstabe == 'R') rButton.setBackground(Color.red);
        else if (buchstabe == 'S') sButton.setBackground(Color.red);
        else if (buchstabe == 'T') tButton.setBackground(Color.red);
        else if (buchstabe == 'U') uButton.setBackground(Color.red);
        else if (buchstabe == 'V') vButton.setBackground(Color.red);
        else if (buchstabe == 'W') wButton.setBackground(Color.red);
        else if (buchstabe == 'X') xButton.setBackground(Color.red);
        else if (buchstabe == 'Y') yButton.setBackground(Color.red);
        else if (buchstabe == 'Z') zButton.setBackground(Color.red);
        else if (buchstabe == 'Ä') aeButton.setBackground(Color.red);
        else if (buchstabe == 'Ö') oeButton.setBackground(Color.red);
        else if (buchstabe == 'Ü') ueButton.setBackground(Color.red);
        else if (buchstabe == 'ẞ') szButton.setBackground(Color.red);
    }

    private void buttonGreen (char buchstabe)
    {
        if      (buchstabe == 'A') aButton.setBackground(Color.green);
        else if (buchstabe == 'B') bButton.setBackground(Color.green);
        else if (buchstabe == 'C') cButton.setBackground(Color.green);
        else if (buchstabe == 'D') dButton.setBackground(Color.green);
        else if (buchstabe == 'E') eButton.setBackground(Color.green);
        else if (buchstabe == 'F') fButton.setBackground(Color.green);
        else if (buchstabe == 'G') gButton.setBackground(Color.green);
        else if (buchstabe == 'H') hButton.setBackground(Color.green);
        else if (buchstabe == 'I') iButton.setBackground(Color.green);
        else if (buchstabe == 'J') jButton.setBackground(Color.green);
        else if (buchstabe == 'K') kButton.setBackground(Color.green);
        else if (buchstabe == 'L') lButton.setBackground(Color.green);
        else if (buchstabe == 'M') mButton.setBackground(Color.green);
        else if (buchstabe == 'N') nButton.setBackground(Color.green);
        else if (buchstabe == 'O') oButton.setBackground(Color.green);
        else if (buchstabe == 'P') pButton.setBackground(Color.green);
        else if (buchstabe == 'Q') qButton.setBackground(Color.green);
        else if (buchstabe == 'R') rButton.setBackground(Color.green);
        else if (buchstabe == 'S') sButton.setBackground(Color.green);
        else if (buchstabe == 'T') tButton.setBackground(Color.green);
        else if (buchstabe == 'U') uButton.setBackground(Color.green);
        else if (buchstabe == 'V') vButton.setBackground(Color.green);
        else if (buchstabe == 'W') wButton.setBackground(Color.green);
        else if (buchstabe == 'X') xButton.setBackground(Color.green);
        else if (buchstabe == 'Y') yButton.setBackground(Color.green);
        else if (buchstabe == 'Z') zButton.setBackground(Color.green);
        else if (buchstabe == 'Ä') aeButton.setBackground(Color.green);
        else if (buchstabe == 'Ö') oeButton.setBackground(Color.green);
        else if (buchstabe == 'Ü') ueButton.setBackground(Color.green);
        else if (buchstabe == 'ẞ') szButton.setBackground(Color.green);
    }

    private MainGUI() {
        bild.setIcon(new ImageIcon(Class.class.getResource("/img/00.gif"))); //Setzt das Bild
        buttonFarbe();
        //Startet das Spiel mittels Startknopf
        spielStartenButton.addActionListener(e -> {
            //________________________________________________
            if (multiplayer.isSelected() ) { //Wenn Zweispielermodus angekreuzt wurde
                 wort = JOptionPane.showInputDialog(null, "Geben Sie ein Wort ein, welches der andere Spieler " + //Abfrage des eingetippten Wortes
                         "erraten soll.", "Wortabfrage", JOptionPane.PLAIN_MESSAGE);
                 if (wort == null || wort.equals(""))
                 {
                     wort = "Nürnberg";
                 }
            }
            else {
              InputStream inputStream = MainGUI.class.getResourceAsStream("/res/wortliste.txt");
               String[] listOfLines = new String[178592];

                Scanner scan = new Scanner(inputStream);
                int i = 0;

               while (scan.hasNextLine())
               {
                   listOfLines[i] = scan.nextLine();
                   i++;
               }
               scan.close();
                 //________________________________ Die externe Wortliste wird eingelesen
                int wortlaenge = 178591;
                Random rand = new Random();
                int random = rand.nextInt(wortlaenge); //Generiere eine zufällige Zahl
                wort = listOfLines[random]; //Wähle ein Wort aus dem Array
            }
            buttonFarbe();
            wort = wort.toUpperCase(); //Vergrößere alle Buchstaben des Wortes
            if (multiplayer.isSelected()) wort = wort.replace("SS", "ẞ");
            laenge = wort.length(); //Zähle die Anzahl der Buchstaben in dem Wort und speichere es in der Variablen
            loesung = wort.toCharArray(); // Speichere die einzelnen Buchstaben des Strings in ein Char Array
            ueberpruefer = wort.toCharArray(); // macht das gleiche nochmal
            istRichtig = new boolean[laenge]; // Erstelle ein Boolean Array Limit der Länge des Wortes
            Arrays.fill(istRichtig, Boolean.FALSE); //Setze jeden Wert des Arrays auf Falsch
            buchstaben = new boolean[30]; // Erstelle ein neues Array für jeden Buchstaben des Alphabets
            Arrays.fill(buchstaben, Boolean.FALSE); // Setze jeden Wert auf Falsch
            buttonPressed = new boolean[30];
            Arrays.fill(buttonPressed, Boolean.FALSE);
            verwendeteBuchstaben = ""; // initialisiere die Variable
            String strich = ""; // initialisiere die Variable
            for (int i = 0; i <= laenge; i++) {
                strich = strich + "_ "; //Erstelle für jeden Buchstaben einen Strich
            }
            zaehler = 1; //Setze die Variable auf 1
            versuche = 15; // Lege die Anzahl der Versuche fest
            bild.setIcon(new ImageIcon(Class.class.getResource("/img/01.gif"))); //Lege das Startbild fest
            wortDisplay.setText(strich); // Gebe den Strich in der GUI aus
            versucheVerbleibend.setText(String.valueOf(versuche)); //Gebe die Anzahl der Versuche in der GUI aus
            usedWords.setText(verwendeteBuchstaben); // Gebe die bereits Verwendeten Buchstaben in der GUI aus
            sieg = 0; //Setze die Siegbedingung auf null
            start = true; // setze den Start auf wahr
        });

        aButton.addActionListener(e -> buttonClicked('A', 0));

        bButton.addActionListener(e -> buttonClicked('B', 1));

        cButton.addActionListener(e -> buttonClicked('C', 2));

        dButton.addActionListener(e -> buttonClicked('D', 3));

        eButton.addActionListener(e -> buttonClicked('E', 4));

        fButton.addActionListener(e -> buttonClicked('F', 5));

        gButton.addActionListener(e -> buttonClicked('G', 6));

        hButton.addActionListener(e -> buttonClicked('H', 7));

        iButton.addActionListener(e -> buttonClicked('I', 8));

        jButton.addActionListener(e -> buttonClicked('J', 9));

        kButton.addActionListener(e -> buttonClicked('K', 10));

        lButton.addActionListener(e -> buttonClicked('L', 11));

        mButton.addActionListener(e -> buttonClicked('M', 12));

        nButton.addActionListener(e -> buttonClicked('N', 13));

        oButton.addActionListener(e -> buttonClicked('O', 14));

        pButton.addActionListener(e -> buttonClicked('P', 15));

        qButton.addActionListener(e -> buttonClicked('Q', 16));

        rButton.addActionListener(e -> buttonClicked('R', 17));

        sButton.addActionListener(e -> buttonClicked('S', 18));

        tButton.addActionListener(e -> buttonClicked('T', 19));

        uButton.addActionListener(e -> buttonClicked('U', 20));

        vButton.addActionListener(e -> buttonClicked('V', 21));

        wButton.addActionListener(e -> buttonClicked('W', 22));

        xButton.addActionListener(e -> buttonClicked('X', 23));

        yButton.addActionListener(e -> buttonClicked('Y', 24));

        zButton.addActionListener(e -> buttonClicked('Z', 25));

        aeButton.addActionListener(e -> buttonClicked('Ä', 26));

        oeButton.addActionListener(e -> buttonClicked('Ö', 27));

        ueButton.addActionListener(e -> buttonClicked('Ü', 28));

        szButton.addActionListener(e -> buttonClicked('ẞ', 29));
    }
}
