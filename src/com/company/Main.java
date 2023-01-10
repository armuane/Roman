package com.company;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;


public class Main {

        public static void main(String[] args) {
            scannerInput();
        }

        public static String calcValue(String input){

            String[] mass = input.split(" ");
            String value = "";

            if(mass.length <= 1){
                System.out.println("Строка не является математической операцией");
            }else if(mass.length > 3){
                System.out.println("формат математической операции не удовлетворяет заданию - " +
                        "два операнда и один оператор (+, -, /, *)");
            }else{

                String value1 = mass[0];
                String value2 = mass[2];
                String operation = mass[1];

                boolean bol1 = check(value1);
                boolean bol2 = check(value2);

                if(bol1 == bol2){
                    if(bol1 == true && bol2 == true){

                        if(Integer.parseInt(value1) > 10 || Integer.parseInt(value2) > 10){
                            return "Введите числа меньше, либо равные 10";
                        }else{
                            value = opearations(value1,operation,value2);
                            return value;
                        }
                    }else if(bol1 == false && bol2 == false){

                        String romanNumber1 =  romanNumerals(value1);
                        String romanNumber2 = romanNumerals(value2);

                        if(Integer.parseInt(romanNumber1) > 10 || Integer.parseInt(romanNumber2) > 10){
                            System.out.println("Введите числа меньше, либо равные X");
                        }else{
                            value = opearations(romanNumber1, operation, romanNumber2);
                            if(Integer.parseInt(value) <= 0){
                                return "В римских цифрах нет отрицательных чисел";
                            }else{
                                return arabNumerals(Integer.parseInt(value));
                            }
                        }
                    }
                }else {
                    return "Типы не равны";
                }
            }
            return value;
        }

        public static void scannerInput(){

            Scanner sc = new Scanner(System.in);
            String scInput = sc.nextLine();
            System.out.println( calcValue(scInput));

        }

        public static boolean check(String val1){

            int intValue1;

            try {
                intValue1 = Integer.parseInt(val1);
                return true;
            } catch (NumberFormatException e) {

            }
            return false;
        }

        public static String opearations(String value1, String operation, String value2){

            if(operation.equals("+")){
                return Integer.toString(Integer.parseInt(value1) + Integer.parseInt(value2));
            }else if(operation.equals("-")){
                return Integer.toString(Integer.parseInt(value1) - Integer.parseInt(value2));
            }else if(operation.equals("*")){
                return Integer.toString(Integer.parseInt(value1) * Integer.parseInt(value2));
            }else if(operation.equals("/")){
                return Integer.toString(Integer.parseInt(value1) / Integer.parseInt(value2));
            }else{
                return "Такой операции нет";
            }

        }

        public static String romanNumerals(String value){
            Map<String,Integer> map = new HashMap<String,Integer>();
            map.put("I", 1);
            map.put("V", 5);
            map.put("X", 10);


            int last = value.length() - 1;
            String[] arr = value.split("");
            int arab;
            int arabValue = map.get(arr[last]);
            for(int i = last - 1; i >= 0; i--){
                arab = map.get(arr[i]);

                if(arab < map.get(arr[i + 1])){
                    arabValue -= arab;
                }else{
                    arabValue += arab;
                }
            }
            return Integer.toString(arabValue);
        }

        public static String arabNumerals(int value){
            int arabVal = value;
            String romanValue = "";

            while(arabVal >= 100){
                romanValue += "C";
                arabVal -= 100;
            }
            while(arabVal >= 90){
                romanValue += "XC";
                arabVal -= 90;
            }
            while(arabVal >= 50){
                romanValue += "L";
                arabVal -= 50;
            }
            while(arabVal >= 40){
                romanValue += "XL";
                arabVal -= 40;
            }
            while(arabVal >= 10){
                romanValue += "X";
                arabVal -= 10;
            }
            while(arabVal >= 9){
                romanValue += "IX";
                arabVal -= 9;
            }
            while(arabVal >= 5){
                romanValue += "V";
                arabVal -= 5;
            }
            while (arabVal >= 4){
                romanValue += "IV";
                arabVal -= 4;
            }
            while (arabVal >= 1){
                romanValue += "I";
                arabVal -= 1;
            }
            return romanValue;
        }
    }
