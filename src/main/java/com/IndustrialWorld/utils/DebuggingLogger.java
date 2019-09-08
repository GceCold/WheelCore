package com.IndustrialWorld.utils;

import com.IndustrialWorld.IndustrialWorld;

public class DebuggingLogger {
	private static boolean DEBUGGING = true;
	public static void debug(String msg) {
		if (DEBUGGING) {
			IndustrialWorld.instance.getLogger().info("DEBUG: " + msg);
		}
	}
}
