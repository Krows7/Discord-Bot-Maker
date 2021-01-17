package net.discordbot.core;

import static net.discordbot.core.DiscordBotPreloader.LANGUAGE_PACK_FILE;
import static net.coretools.core.io.file.FileTools.CORE_JAVA_PREFERENCE_FILE;

import java.util.HashMap;
import java.util.Map;

import net.coretools.core.io.file.FileTools;
import net.jlp.core.JLPFactory;
import net.jlp.core.JLPack;

/**
 * 
 * DiscordBotLoader class is used for loading language map and notificating preference file about it.
 * 
 * @since 1.0.0
 * 
 * @author Krows
 * 
 */
public class DiscordBotLoader {

/**
 * 
 * Decodes main language pack file, finds and returns needed language map by abbreviation.
 * 
 * @param abbr Abbreviation of needed map language.
 * 
 * @return Selected language map.
 * 
 */
	public static Map<String, String> loadLanguageMap(String abbr) {
		
		return loadLanguagePack().getByAbbreviation(abbr);
	}
	
/**
 * 
 * Decodes main language pack file and returns {@link JLPack} object with its data. 
 * If the file isn't exists then returns empty JLPack.
 * 
 * @return JLPack object from file or empty JLPack object if file isn't exists.
 * 
 */
	public static JLPack loadLanguagePack() {
		
		if(!LANGUAGE_PACK_FILE.exists()) return JLPack.create(new HashMap<>());
		
		return JLPFactory.decode(LANGUAGE_PACK_FILE);
	}
	
/**
 * 
 * Changes current language name in preference file.
 * 
 * @param name Language name.
 * 
 */
	public static void changePreferenceLanguage(String name) {
		
		FileTools.setText(CORE_JAVA_PREFERENCE_FILE, name);
	}
}