package com.seleniumGrid.node.Node2;

import java.io.File;

import org.openqa.grid.selenium.GridLauncherV3;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Node2Runner {
	public static void main(String[] args) throws Exception {
		File configPath = new File("src/com/seleniumGrid/node/Node2/DefaultNode.json");
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.operadriver().setup();
		WebDriverManager.edgedriver().setup();
		GridLauncherV3.main(new String[] { "-role", "node",
				"-hub","http://localhost:4444/grid/register","-nodeConfig",configPath.toPath().toString()});
	}
}
