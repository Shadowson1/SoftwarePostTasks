/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl2;

import java.util.Scanner; 



public class Task1Post {
    public static void main(String[] args){
        Scanner inputSentence = new Scanner(System.in);
        System.out.println("Enter your Sentence");
        String originSentence = inputSentence.nextLine();
        System.out.println("Your Original Sentence: " + originSentence);
        System.out.println("Now in Uppercase: " + originSentence.toUpperCase());
        int wordCount = originSentence.split(" ").length;
        System.out.println("Number of words: " + wordCount);
        String reversedSentence = new StringBuilder(originSentence).reverse().toString();
        System.out.println("Reversed Sentence: " + reversedSentence);
        if (originSentence.toLowerCase().contains("java")) {
            System.out.println("The sentence contains 'Java'!!");
                }
        else {
            System.out.println("The sentence does not contain 'Java', sad.");
                }
        System.out.println("Now with the sentence's spaces replaced with underscores: " + originSentence.replace(" ", "_"));
    }
}
