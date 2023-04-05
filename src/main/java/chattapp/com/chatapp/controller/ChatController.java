package chattapp.com.chatapp.controller;

import chattapp.com.chatapp.dao.model.Chats;

import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Chats receiveMessage(@Payload Chats message) {
        return message;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Chats sendMessageWithEmoji(Chats message) {
        // Convert Emoji shortcodes to Unicode characters
        String unicodeMessage = EmojiParser.parseToUnicode(message.getMessage());
        message.setMessage(unicodeMessage);
        return message;
    }

//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public Chats sendMessageWithEmoji(Chats message) {
//        // Add emoji support to the message
//        message.setMessage(emoji.emojize(message.getMessage(), use_aliases = True));
//        return message;
//    }

    @MessageMapping("/private-message")
    public Chats recMessage(@Payload Chats message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        System.out.println(message.toString());
        return message;
    }

}
