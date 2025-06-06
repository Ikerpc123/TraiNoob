package com.ikerpc123.trainoob;

import org.springframework.boot.CommandLineRunner;

public class Principal implements CommandLineRunner{
	
	@Override
	public void run(String... args) throws Exception{
		
		try {
            String url = "http://localhost:8080/index";
            
            ProcessBuilder pb = new ProcessBuilder();
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                pb.command("cmd", "/c", "start", url);
            }
            else {
                throw new UnsupportedOperationException("Sistema operativo no soportado para abrir navegadores.");
            }
            pb.start();
        } catch (Exception e) {
            System.out.println("\n\t\t\t\033[1;31mError al intentar abrir el navegador:\033[0m");
            e.printStackTrace();
        }
		
	}
}
