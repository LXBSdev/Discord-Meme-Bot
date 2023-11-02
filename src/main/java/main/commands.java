package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.managers.AudioManager;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Nonnull;

public class commands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("rickroll")) {
            Member member = event.getOption("user").getAsMember();
            EmbedBuilder emb = new EmbedBuilder();
            emb.setTitle("You just got rickrolled!", "https://youtube.com/watch?=dQw4w9WgXcQ")
                .setAuthor("Rick Astley", "https://youtu.be/dQw4w9WgXcQ")
                .setDescription(member.getAsMention() + " well, not as you thought it was going..." + <meta>)
            event.replyEmbeds(emb.build()).queue();
        }

        if (command.equals("respect")) {
            Member member = event.getOption("user").getAsMember();
            EmbedBuilder emb = new EmbedBuilder();
            emb.setDescription(member.getAsMention())
                .setImage("https://tenor.com/view/keyboard-hyperx-rgb-hyperx-family-hyperx-gaming-gif-17743649");
            event.replyEmbeds(emb.build()).queue();
        }

        if (command.equals("leave")) {
            AudioManager manager = event.getGuild().getAudioManager();
            EmbedBuilder emb = new EmbedBuilder();
            if (manager.isConnected()) {
                if (event.getMember().getVoiceState().getChannel() == manager.getConnectedChannel()) {
                    emb.setDescription("I already realise when I'm unwanted :/");
                    manager.closeAudioConnection();
                    event.replyEmbeds(emb.build()).queue();
                }
            }
        }
        
        if (command.equals("join")) {
            AudioManager manager = event.getGuild().getAudioManager();
            AudioChannel channel = event.getMember().getVoiceState().getChannel();
            EmbedBuilder emb = new EmbedBuilder();
            if (!manager.isConnected()) {
                emb.setDescription("Hello there!");
                manager.openAudioConnection(channel);
                manager.setSelfDeafened(true);
                event.replyEmbeds(emb.build()).queue();
            } else {
                emb.setDescription("Sorry, I'm already rickrolling someone else");
                event.replyEmbeds(emb.build()).queue();
            }
        }
    }
}
