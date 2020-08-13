package com.namelessmc.bot.listeners;

import com.namelessmc.bot.NamelessBot;
import com.namelessmc.bot.types.MessageColor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class JoinLeave extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (!event.getUser().isBot() && !event.getUser().isBot()) {
            User eventUser = event.getUser();
            String cleanUsername = eventUser.getName().replace("`", "\\`");
            Integer memberCount = 0;
            for (Member member : event.getGuild().getMembers()) {
                if (!member.getUser().isBot() && !member.getUser().isFake()) {
                    memberCount++;
                }
            }
            String footerText = "There are now " + memberCount + " members.";
            /*Integer membersToGo = 1000 - memberCount;
            if (membersToGo > 0) {
                if (membersToGo == 1) {
                    footerText = footerText + " (" + membersToGo + " more member until 1,000!)";
                } else {
                    footerText = footerText + " (" + membersToGo + " more members until 1,000.)";
                }
            } else if (membersToGo > 0) {
                membersToGo = membersToGo * -1;
                if (membersToGo == 1) {
                    footerText = footerText + " (We now have " + membersToGo + " more member than 1,000!)";
                } else {
                    footerText = footerText + " (We now have " + membersToGo + " more members than 1,000!)";
                }
            } else {
                footerText = footerText + " (We've hit 1000 members! :tada: )";
            }*/
            if (event.getGuild().getTextChannelsByName(NamelessBot.WELCOME_CHANNEL_NAME, true).size() > 0) {
                TextChannel welcomeChannel = event.getGuild().getTextChannelsByName(NamelessBot.WELCOME_CHANNEL_NAME, true).get(0);
                EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("User Join").setColor(MessageColor.SUCCESS.color)
                        .setDescription("Welcome ``" + cleanUsername + "#" + eventUser.getDiscriminator() + "`` to the NamelessMC Discord.")
                        .setFooter(footerText, null);
                welcomeChannel.sendMessage(embedBuilder.build()).queue();
            }
        }
    }


    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        if (!event.getUser().isBot() && !event.getUser().isBot()) {
            User eventUser = event.getUser();
            String cleanUsername = eventUser.getName().replace("`", "\\`");
            Integer memberCount = 0;
            for (Member member : event.getGuild().getMembers()) {
                if (!member.getUser().isBot() && !member.getUser().isFake()) {
                    memberCount++;
                }
            }
            String footerText = "There are now " + memberCount + " members.";
            /*Integer membersToGo = 1000 - memberCount;
            if (membersToGo > 0) {
                if (membersToGo == 1) {
                    footerText = footerText + " (" + membersToGo + " more member until 1,000!)";
                } else {
                    footerText = footerText + " (" + membersToGo + " more members until 1,000.)";
                }
            } else if (membersToGo > 0) {
                membersToGo = membersToGo * -1;
                if (membersToGo == 1) {
                    footerText = footerText + " (We now have " + membersToGo + " more member than 1,000!)";
                } else {
                    footerText = footerText + " (We now have " + membersToGo + " more members than 1,000!)";
                }
            } else {
                footerText = footerText + " (We've hit 1000 members! :tada: )";
            }*/
            if (event.getGuild().getTextChannelsByName(NamelessBot.WELCOME_CHANNEL_NAME, true).size() > 0) {
                TextChannel welcomeChannel = event.getGuild().getTextChannelsByName(NamelessBot.WELCOME_CHANNEL_NAME, true).get(0);
                EmbedBuilder embedBuilder = new EmbedBuilder().setTitle("User Leave").setColor(MessageColor.ERROR.color)
                        .setDescription("Goodbye ``" + cleanUsername + "#" + eventUser.getDiscriminator() + "``. Thanks for visiting the NamelessMC Discord.")
                        .setFooter(footerText, null);
                welcomeChannel.sendMessage(embedBuilder.build()).queue();
            }
        }
    }
}
