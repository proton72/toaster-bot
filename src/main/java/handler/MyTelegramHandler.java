package handler;

import command.StartCommand;
import command.ToastCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;


public class MyTelegramHandler extends TelegramLongPollingCommandBot {

    private static final String LOGTAG = "MYTELEGRAMHANDLER";

    public MyTelegramHandler() {
        register(new StartCommand(this));
        register(new ToastCommand(this));

        registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText(message.getText() + "? И что за хрень ты несешь! Больше не наливать!");
            try {
                absSender.sendMessage(commandUnknownMessage);
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }
        });
    }

    @Override
    public String getBotToken() {
        return "315217412:AAEiQloNTFZeTer62CBKDibVNkgRirRDHec";
    }

    @Override
    public String getBotUsername() {
        return "ToastGeneratorBot";
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            //check if the message has text. it could also contain for example a location ( message.hasLocation() )
            if (message.hasText()) {
                //create an object that contains the information to send back the message
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString()); //who should get from the message the sender that sent it.
                if (message.getText().equalsIgnoreCase("боярский")) {
                    try {
                        sendMessageRequest.setText("Тысяча чертей!");
                        sendMessage(sendMessageRequest);
                    } catch (Exception e) {
                        BotLogger.error(LOGTAG, e);
                    }
                }
            }
        }
    }
}