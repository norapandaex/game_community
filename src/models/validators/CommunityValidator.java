package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Community;

public class CommunityValidator {
    public static List<String> validate(Community c) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(c.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String game_error = _validateGame(c.getGame());
        if(!game_error.equals("")) {
            errors.add(game_error);
        }

        String content_error = _validateContent(c.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "アカウント名を入力してください。";
        }

        return "";
    }

    private static String _validateGame(String game) {
        if(game == null || game.equals("")) {
            return "作品名を入力してください。";
        }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "コミュニティの概要を入力してください。";
        }

        return "";
    }

}
