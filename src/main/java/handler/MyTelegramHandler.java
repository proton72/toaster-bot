package handler;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class MyTelegramHandler extends TelegramLongPollingBot {
    @Override
    public String getBotToken() {
        return "287183421:AAEzPu_ijLValOBBsUYFOkQVzGxhfcS7SNw";
    }

    @Override
    public String getBotUsername() {
        return "file_downloader_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        //check if the update has a message
        String tost;
        if (update.hasMessage()) {
            Message message = update.getMessage();

            //check if the message has text. it could also contain for example a location ( message.hasLocation() )
            if (message.hasText()) {
                //create an object that contains the information to send back the message
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString()); //who should get from the message the sender that sent it.
                if (message.getText().equalsIgnoreCase("тост")) {
                    try {
                        tost = JsonHandler.getContent(RestHandler.getContent(6));
                        sendMessageRequest.setText(tost);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                } catch (TelegramApiException e) {
                    //do some error handling
                }
            }
        }
    }
}