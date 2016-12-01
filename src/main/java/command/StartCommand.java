package command;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.bots.commands.ICommandRegistry;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;


/**
 * Created by vdenisov on 01/12/2016.
 */
public class StartCommand extends BotCommand {

    private final ICommandRegistry commandRegistry;

    private static final String LOGTAG = "STARTCOMMAND";

    public StartCommand(ICommandRegistry registry) {
        super("start", "Начало работы с ботом");
        this.commandRegistry = registry;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("<b>Привет!</b>\n");
        messageBuilder.append("Используй команды чтобы получить моднейший спич\n");


        for (BotCommand botCommand : commandRegistry.getRegisteredCommands()) {
            messageBuilder.append(botCommand.toString()).append("\n");
        }

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.enableHtml(true);
        answer.setText(messageBuilder.toString());

        try {
            absSender.sendMessage(answer);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}
