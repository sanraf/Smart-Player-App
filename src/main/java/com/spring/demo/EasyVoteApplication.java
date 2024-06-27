package com.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class EasyVoteApplication {

	public static void main(String[] args) {

		SpringApplication.run(EasyVoteApplication.class, args);
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.println("choose your lucky number");
//		int getValue = scanner.nextInt();
//		List<Integer> num1 = Arrays.asList(1,3,5,7,9,11);
//		List<Integer> num2 = Arrays.asList(2,4,6,8,10);
//
//		randNum(num1,getValue );

//		randNum(num2);
//		while (k !=num1.size()){
//			int collected = num1.stream().limit(num1.size() - k).reduce(Integer::sum).get();
//			System.out.println(collected);
//			System.out.println("santo "+num1.stream().limit(num1.size()-k).toList());
//			System.out.println("reverse "+num1.reversed().stream().limit(num1.size()-k).toList().reversed());
//			k++;
//
//		}




	}


//	public static void randNum(List<Integer> integers,int getValue){
//		Random random = new Random();
//		int nextInt = random.nextInt(integers.size());
//		Integer x = integers.get(nextInt);
//		System.out.println(x);
//		System.out.println(x == getValue ? "You have won" : "You lost! "+nextInt);



//	}

}
