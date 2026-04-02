package com.filehandlingsystem.fileHandling;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Logger;

@SpringBootApplication
public class FileHandlingApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FileHandlingApplication.class, args);

	}

}