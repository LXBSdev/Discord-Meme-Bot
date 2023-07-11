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

    private String getActivities(List activitiesList) {
        String activitie = "";
        if (!activitiesList.isEmpty()) {
            Activity tempActiv = (Activity) activitiesList.get(0);
            for (int i = 1; i < activitiesList.size(); i++) {
                tempActiv = (Activity) activitiesList.get(i);
                activitie = activitie + ", " + tempActiv;
            }
        } else {
            activitie = "No activitie";
        }
        return activitie;
    }

    private String getRolesAsString(List rolesList) {
        String roles = "";
        if (!rolesList.isEmpty()) {
            Role tempRole = (Role) rolesList.get(0);
            roles = tempRole.getAsMention();
            for (int i = 1; i < rolesList.size(); i++) {
                tempRole = (Role) rolesList.get(i);
                roles = roles + ", " + tempRole.getAsMention();
            }
        } else {
            roles = "No roles";
        }
        return roles;
    }

    @Override
    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("help")) {
            EmbedBuilder emb = new EmbedBuilder();

            emb.setTitle("LXBS Commands")
                    .setDescription("List of all available commands")
                    .setColor(0x828282)
                    .setThumbnail(
                            "https://cdn.discordapp.com/attachments/837779743486378075/1125779362919219252/lxbs-logo-meme-alex-jones.png")
                    .addField("</respect:1125414321204236334>", "Pay respect", true)
                    .addField("</rickroll:1125414321204236333>", "Rick-roll someone", true)
                    .addField("", "", false)
                    .addField("More Infos: ", "[lxbs.online](https://lxbs.online)", false);

            event.replyEmbeds(emb.build())
                    .addActionRow(Button.link("http://lxbs.online", "lxbs.online"))
                    .setEphemeral(true)
                    .queue();
        }

        if (command.equals("rickroll")) {
            Member member = event.getOption("user").getAsMember();
            event.reply(member.getAsMention()
                    + "\n https://tenor.com/view/rickroll-roll-rick-never-gonna-give-you-up-never-gonna-gif-22954713")
                    .queue();
        }

        if (command.equals("respect")) {
            Member member = event.getOption("user").getAsMember();
            event.reply(member.getAsMention()
                    + "\n https://tenor.com/view/keyboard-hyperx-rgb-hyperx-family-hyperx-gaming-gif-17743649").queue();
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
