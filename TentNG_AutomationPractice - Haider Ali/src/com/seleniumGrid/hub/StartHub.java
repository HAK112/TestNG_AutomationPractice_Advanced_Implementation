package com.seleniumGrid.hub;

import java.io.File;

import org.openqa.grid.selenium.GridLauncherV3;

public class StartHub {
	public static void main(String[] args) throws Exception {
		String configPath = "src/com/seleniumGrid/hub/DefaultHub.json";
        GridLauncherV3.main(new String[] { "-role", "hub", "-hubConfig",new File(configPath).toPath().toString()});
    }
}
