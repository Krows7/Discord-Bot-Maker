package net.discordbot.core;

import net.coretools.core.Launcher;
import net.dv8tion.jda.api.JDA;

/**
 * 
 * DiscordBotLauncher class is default {@link Launcher} for Java Discord Bot API and is used for launching and working with Discord bot.
 * 
 * @since 1.0.0
 * 
 * @author Krows
 * 
 */
public class DiscordBotLauncher extends Launcher {

/**
 * 
 * The Discord bot token.
 * 
 */
	protected String token;

/**
 * 
 * The Discord bot contains {@link JDA} for working with bot.
 * 
 */
	protected DiscordBot bot;
	
/**
 * 
 * Constructor saves token. The string token should be located at first position in arguments.
 * 
 * @param args Arguments for launcher.
 * 
 */
	public DiscordBotLauncher(String... args) {
		
		super(args);
		
		this.token = args[0];
	}
	
	@Override
	public void launch() {

		if(bot == null) bot = DiscordBot.createEmpty(token);
		
		bot.start();
	}
}