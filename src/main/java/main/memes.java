package main;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class memes extends ListenerAdapter {

    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        if (event.isFromGuild()) {
            if (!event.getAuthor().isBot()) {
                if (event.getMessage().getContentStripped().toUpperCase().contains("CHESS")) {
                    event.getMessage().reply(
                            "https://cdn.discordapp.com/attachments/1059574035811414097/1062425543628308500/chessmaster.png")
                            .queue();
                }

                if (event.getMessage().getContentStripped().toUpperCase().contains("RICK")) {
                    event.getMessage().reply(
                            "https://tenor.com/view/rickroll-roll-rick-never-gonna-give-you-up-never-gonna-gif-22954713")
                            .queue();
                }

                if (event.getMessage().getContentStripped().toUpperCase().contains("MATHE")) {
                    event.getMessage().reply(
                            "https://cdn.discordapp.com/attachments/1059574035811414097/1062426087004581979/mathelehrer.jpg")
                            .queue();
                }

                if (event.getMessage().getContentStripped().toUpperCase().contains("NOICH")) {
                    event.getMessage().reply(
                            "https://cdn.discordapp.com/attachments/837779743486378075/1067835776215306270/IMG_3710.png")
                            .queue();
                }

                if (event.getMessage().getContentStripped().toUpperCase().contains("PIGSTEP")) {
                    event.getMessage().reply("https://www.youtube.com/watch?v=PTGGqwTYdc0").queue();
                }

                if (event.getMessage().getContentStripped().toUpperCase().contains("STAL")) {
                    event.getMessage().reply("https://www.youtube.com/watch?v=NhqxP91_CdI").queue();
                }

                if (event.getMessage().getContentStripped().toUpperCase().contains("FUNKE")) {
                    event.getMessage().reply("https://youtu.be/jP_ztxJhfA0").queue();
                }

                if (event.getMessage().getContentStripped().toUpperCase().contains("WATER")) {
                    event.getMessage().reply("https://media.tenor.com/OsnAgZjC-kQAAAAd/underwater-hello-im-under-water.gif").queue();
                    if (event.getMember().getVoiceState() != null) {
                        AudioChannel channel = event.getMember().getVoiceState().getChannel();
                        AudioManager manager = event.getGuild().getAudioManager();

                        manager.openAudioConnection(channel);
                        manager.setSelfDeafened(true);
                    }
                }
            }
        }
    }
}