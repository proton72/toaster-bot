package command;

import handler.JsonHandler;
import handler.RestHandler;
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
public class ToastCommand extends BotCommand {

    private static final int TOAST_API_CODE = 6;
    private static final String LOGTAG = "TOASTCOMMAND";

    private final ICommandRegistry commandRegistry;


    public ToastCommand(ICommandRegistry registry) {
        super("toast", "Получить самый тупой тост в мире");
        this.commandRegistry = registry;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("<b>Наполняем бокалы!</b>\n");


        try {
            String toastText = JsonHandler.getContent(RestHandler.getContent(TOAST_API_CODE));
            messageBuilder.append(toastText);
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
            messageBuilder.append("Не вышел тост, тамада загрустил (=;");
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
