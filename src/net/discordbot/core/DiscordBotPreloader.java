package net.discordbot.core;

import static net.coretools.core.io.file.FileTools.CORE_JAVA_PREFERENCE_FILE;
import static net.coretools.core.io.file.FileTools.JAVA_LANGUAGE_PACK_EXTENSION;
import static net.coretools.core.io.file.FileTools.JAVA_LANGUAGE_PATH_NAME;
import static net.coretools.core.io.file.FileTools.RESOURCE_PATH_NAME;
import static net.coretools.core.io.file.FileTools.TEXT_EXTENSION;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * DiscordBotPreloader class contains main data about files of this program.
 * 
 * @since 1.0.0
 * 
 * @author Krows
 * 
 */
public class DiscordBotPreloader {
	
/**
 * 
 * The file of main localization data of this program.
 * 
 */
	public static final File LANGUAGE_PACK_FILE = new File(JAVA_LANGUAGE_PATH_NAME + "bot" + JAVA_LANGUAGE_PACK_EXTENSION);
	
/**
 * 
 * The file of "black list" by ID for the bot.
 * 
 */
	public static final File BLACK_LIST_FILE = new File(RESOURCE_PATH_NAME + "bl" + TEXT_EXTENSION);
	
/**
 * 
 * This method preloads language {@link Map} by language name written into preference file.
 * 
 * @return initialized language map.
 * 
 */
	public static Map<String, String> preloadLanguageMap() {
		
		if(!CORE_JAVA_PREFERENCE_FILE.exists()) return new HashMap<>();
		
		try(Scanner prefScanner = new Scanner(CORE_JAVA_PREFERENCE_FILE)) {
			
			return DiscordBotLoader.loadLanguageMap(prefScanner.next());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
/**
 * 
 * Preloads "black {@link List}" object by ID written into "black list" file.
 * 
 * @return Black list of the bot.
 * 
 */
	public static List<String> preloadBlackList() {
		
		List<String> result = new ArrayList<>();
		
		if(!BLACK_LIST_FILE.exists()) return result;
		
		try(Scanner scanner = new Scanner(BLACK_LIST_FILE)) {
		
			while(scanner.hasNextLine()) result.add(scanner.nextLine());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return result;
	}
}