package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Account;
import utils.DBUtil;

public class AccountValidators {
    public static List<String> validate(Account a, Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String code_error = _validateCode(a.getCode(), code_duplicate_check_flag);
        if(!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_error = _validateName(a.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = _validatePassword(a.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        String vpassword_error = _checkPassword(a.getPassword());
        if(!vpassword_error.equals("")) {
            errors.add(vpassword_error);
        }

        return errors;
    }

    private static String _validateCode(String code, Boolean code_duplicate_check_flag) {
        if(code == null || code.equals("")) {
            return "IDを入力してください。";
        }

        if(code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long accounts_count = (long)em.createNamedQuery("checkId", Long.class)
                                          .setParameter("code", code)
                                          .getSingleResult();
            em.close();
            if(accounts_count > 0) {
                return "入力されたIDはすでに存在しています。";
            }
        }

        return "";
    }

    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "アカウント名を入力してください。";
        }

        return "";
    }

    private static String _validatePassword(String password, Boolean password_check_flag) {
        //パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }

        return "";
    }

    private static String _checkPassword(String password) {
        if(password.equals("error")) {
            return "パスワードが一致していません。";
        }

        return "";
    }

}
