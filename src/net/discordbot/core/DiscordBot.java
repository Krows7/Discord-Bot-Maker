package net.discordbot.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.jlp.core.JLPack;

/**
 * 
 * The DiscordBot class is Java Discord bot for using on servers.
 * 
 * @author Krows
 * 
 * @since 1.0.0
 *
 */
public abstract class DiscordBot {

/**
 * 
 * This is the token from Discord bot is used to work with him.
 * 
 */
	protected String token;
	
/**
 * 
 * The {@link JDA} which is used in manipulation with current bot.
 * 
 */
	protected JDA api;

/**
 * 
 * The loaded bot {@link JLPack} with all attached languages.
 * 
 */
	protected JLPack languagePack = DiscordBotLoader.loadLanguagePack();
	
/**
 * 
 * The localized data map of current bot language.
 * 
 */
	protected Map<String, String> languageDataMap = DiscordBotPreloader.preloadLanguageMap();
	
/**
 * 
 * The "black list" by ID of the bot.
 * 
 */
	protected List<String> blackIDList = DiscordBotPreloader.preloadBlackList();

/**
 * 
 * Constructor creates Discord bot and saves bot token.
 * 
 * @param token Discord bot token.
 * 
 */
	public DiscordBot(String token) {
		
		this.token = token;
	}
	
/**
 * 
 * This method creates empty Discord bot. But still you need have Discord bot token for creating its.
 * 
 * @param token Bot token.
 * 
 * @return Discord bot with empty methods.
 * 
 */
	public static DiscordBot createEmpty(String token) {
		
		return new DiscordBot(token) {
			
			@Override
			protected void respondCommand(MessageReceivedEvent event) {
				
			}
			
			@Override
			protected boolean checkForCommand(String message) {
				
				return false;
			}
			
			@Override
			protected void addListeners() {
				
			}
		};
	}
	
/**
 * 
 * This method starts this bot and launch listeners for work.
 * 
 */
	public void start() {
		
		try {
			
			api = new JDABuilder(AccountType.BOT).setToken(token).build().awaitReady();
			
			addListeners();
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
/**
 * 
 * This method adds listeners which bot will handle.
 * 
 */
	protected abstract void addListeners();
	
/**
 * 
 * This method responds to message requests from listeners.
 * 
 * @param event Message events from listeners.
 * 
 */
	protected abstract void respondCommand(MessageReceivedEvent event);
	
/**
 * 
 * This method checks user in parameter and user from api. Returns true if users are same. 
 * 
 * @param user User for checking.
 * 
 * @return Similarity of users.
 * 
 */
	protected boolean checkForSelf(User user) {

		return user == api.getSelfUser();
	}
	
/**
 * 
 * Checks user ID in parameter and IDs from "black {@link List}". Returns true if this ID contains in list. 
 * 
 * @param id ID for checking.
 * 
 * @return True if the ID contains in bot's black list, otherwise returns false.
 * 
 */
	protected boolean checkForBanned(String id) {
		
		return blackIDList.contains(id);
	}
	
/**
 * 
 * This method check String to similarity with the command prefix. 
 * Similarity will successful if prefix is placing at start. 
 * Returns true if String starts with command prefix.
 * 
 * @param message String for message checking.
 *
 * @return Similarity message start with command prefix.
 * 
 */
	protected abstract boolean checkForCommand(String message);
	
/**
 * 
 * This method returns {@link JDA} object of this bot for working with him.
 * 
 * @return JDA API of this bot.
 * 
 */
	public JDA getAPI() {
		
		return api;
	}
	
	protected void sendTempMessage(CharSequence cs, TextChannel channel, int delay, TimeUnit unit) {
		
		Message msg = channel.sendMessage(cs).complete();
		msg.delete().queueAfter(delay, unit);
	}
}